//package io.github.stewseo.client.elasticsearch;
//
//import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
//import io.github.stewseo.client._types.ErrorResponse;
//import io.github.stewseo.client.json.JsonData;
//import io.github.stewseo.client.json.JsonpDeserializer;
//import io.github.stewseo.client.json.JsonpMapper;
//import io.github.stewseo.client.json.jsonb.JsonbJsonpMapper;
//import io.github.stewseo.client.transport.ElasticsearchTransport;
//import io.github.stewseo.client.transport.JsonEndpoint;
//import io.github.stewseo.client.transport.Version;
//import io.github.stewseo.client.transport.endpoints.DelegatingJsonEndpoint;
//import io.github.stewseo.client.transport.restclient.EsRestClientTransport;
//import io.github.stewseo.client.transport.restclient.TestElasticsearchClient;
//import io.github.stewseo.lowlevel.restclient.EsRestClient;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.testcontainers.elasticsearch.ElasticsearchContainer;
//import org.testcontainers.images.builder.ImageFromDockerfile;
//import org.testcontainers.utility.DockerImageName;
//
//import javax.net.ssl.SSLContext;
//import java.io.IOException;
//import java.time.Duration;
//
//public class ElasticsearchTestServer implements AutoCloseable {
//
//    private final String[] plugins;
//    private volatile ElasticsearchContainer container;
//    private final JsonpMapper mapper = new JsonbJsonpMapper();
//    private EsRestClient restClient;
//    private ElasticsearchTransport transport;
//    private TestElasticsearchClient client;
//
//    private static ElasticsearchTestServer global;
//
//    public static synchronized ElasticsearchTestServer global() {
//        if (global == null) {
//            System.out.println("Starting global ES test server.");
//            global = new ElasticsearchTestServer();
//            global.start();
//            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//                System.out.println("Stopping global ES test server.");
//                global.close();
//            }));
//        }
//        return global;
//    }
//
//    public ElasticsearchTestServer(String... plugins) {
//        this.plugins = plugins;
//    }
//
//    public synchronized ElasticsearchTestServer start() {
//        Version version = Version.VERSION.major() < 8 ? new Version(7,17,5,false) : new Version(8,3,3,false);
//
//        // Note we could use version.major() + "." + version.minor() + "-SNAPSHOT" but plugins won't install on a snapshot version
//        String esImage = "docker.elastic.co/elasticsearch/elasticsearch:" + version;
//
//        DockerImageName image;
//        if (plugins.length == 0) {
//            image = DockerImageName.parse(esImage);
//        } else {
//            String esWithPluginsImage = new ImageFromDockerfile()
//                    .withDockerfileFromBuilder(b -> {
//                                b.from(esImage);
//                                for (String plugin : plugins) {
//                                    b.run("/usr/share/elasticsearch/bin/elasticsearch-plugin", "install", plugin);
//                                }
//                            }
//                    ).get();
//
//            image = DockerImageName
//                    .parse(esWithPluginsImage)
//                    .asCompatibleSubstituteFor("docker.elastic.co/elasticsearch/elasticsearch");
//        }
//
//        container = new ElasticsearchContainer(image)
//                .withEnv("ES_JAVA_OPTS", "-Xms256m -Xmx256m")
//                .withEnv("path.repo", "/tmp") // for snapshots
//                .withStartupTimeout(Duration.ofSeconds(30))
//                .withPassword("changeme");
//        container.start();
//
//        int port = container.getMappedPort(9200);
//
//        boolean useTLS = version.major() >= 8;
//        HttpHost host = new HttpHost("localhost", port, useTLS ? "https": "http");
//
//        SSLContext sslContext = useTLS ? container.createSslContextFromCa() : null;
//
//        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
//
//        restClient = EsRestClient.builder(host)
//                .setHttpClientConfigCallback(hc -> hc
//                        .setSSLContext(sslContext)
//                )
//                .build();
//        transport = new EsRestClientTransport(restClient, mapper);
//        client = new TestElasticsearchClient(transport);
//
//        return this;
//    }
//
//    /**
//     * Send a request to a server and return the response as JSON. Useful to debug response format issues.
//     */
//    public static <Req> JsonData getJsonResponse(TestElasticsearchClient client, Req request) throws IOException {
//
//        JsonEndpoint<Req, JsonData, ErrorResponse> endpoint;
//
//        try {
//            @SuppressWarnings("unchecked")
//            JsonEndpoint<Req, JsonData, ErrorResponse> endpoint0 = (JsonEndpoint<Req, JsonData, ErrorResponse>) request.getClass()
//                    .getDeclaredField("_ENDPOINT").get(null);
//            endpoint = endpoint0;
//        } catch (IllegalAccessException|NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
//
//        DelegatingJsonEndpoint<Req, JsonData, ErrorResponse> jsonEndpoint =
//                new DelegatingJsonEndpoint<Req, JsonData, ErrorResponse>(endpoint) {
//                    @Override
//                    public JsonpDeserializer<JsonData> responseDeserializer() {
//                        return JsonData._DESERIALIZER;
//                    }
//                };
//
//        return client._transport().performRequest(request, jsonEndpoint, client._transportOptions());
//    }
//
//    @Override
//    public void close() {
//        if (this == global) {
//            // Closed with a shutdown hook
//            return;
//        }
//
//        if (container != null) {
//            container.stop();
//        }
//        container = null;
//    }
//
//    public ElasticsearchContainer container() {
//        return this.container;
//    }
//
//    public EsRestClient restClient() {
//        return restClient;
//    }
//
//    public ElasticsearchTransport transport() {
//        return transport;
//    }
//
//    public JsonpMapper mapper() {
//        return mapper;
//    }
//
//    public TestElasticsearchClient client() {
//        return client;
//    }
//
//}