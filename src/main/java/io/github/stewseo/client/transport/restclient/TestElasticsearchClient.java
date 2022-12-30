package io.github.stewseo.client.transport.restclient;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.ErrorResponse;
import co.elastic.clients.elasticsearch.core.ExistsRequest;
import co.elastic.clients.transport.Endpoint;
import co.elastic.clients.util.ObjectBuilder;
import io.github.stewseo.client.ApiClient;
import io.github.stewseo.client.elasticsearch.ElasticsearchService;
import io.github.stewseo.client.transport.ElasticsearchTransport;
import io.github.stewseo.client.transport.Transport;
import io.github.stewseo.client.transport.TransportOptions;
import io.github.stewseo.client.transport.endpoints.BooleanResponse;
import io.github.stewseo.lowlevel.restclient.EsRestClient;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClientBuilder;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class TestElasticsearchClient extends ApiClient<ElasticsearchTransport, TestElasticsearchClient> {

    public TestElasticsearchClient(ElasticsearchTransport transport) {
        super(transport, null);

//        HttpHost host = httpHost(transport.restClientInterface().httpHost());
//
//        Header[] defaultHeaders = defaultHeader(transport.options().headers());
//
//        org.elasticsearch.client.RestClient restClient = org.elasticsearch.client.RestClient.builder(host)
//                .setDefaultHeaders(defaultHeaders)
//                .build();
//
//        co.elastic.clients.transport.ElasticsearchTransport trans = new RestClientTransport(restClient, new JacksonJsonpMapper());
//
//        elasticsearchClient = new ElasticsearchClient(trans, null);

    }

    public TestElasticsearchClient(ElasticsearchTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public TestElasticsearchClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new TestElasticsearchClient(this.transport, transportOptions);
    }

    private HttpHost httpHost(HttpHost httpHost) {

        String host = httpHost.getHostName();

        int port = httpHost.getPort();

        String scheme = httpHost.getSchemeName();

        return new HttpHost(host, port, scheme);
    }


    private Header[] defaultHeader(Collection<Map.Entry<String, String>> headers) {

        String key = headers.stream().toList().get(0).getKey();

        String value = headers.stream().toList().get(0).getValue();

       return new BasicHeader[]{new BasicHeader(key, value)};
    }

    public TransportOptions _transportOptions() {
        return this.transportOptions;
    }

    public ElasticsearchTransport _transport() {
        return this.transport;
    }

    public void ping() {
    }

    public void info() {
    }

    private ElasticsearchService elasticsearchService;
    
    public co.elastic.clients.transport.endpoints.BooleanResponse exists(String name) {

        try {
            return elasticsearchService.getAsyncClient().exists(r -> r.index("capture-headers").id(name)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    //         String apiKeyIdAndSecret = apiKeyId + ":" + apiKeySecret;
    //
    //        String encodedApiKey = Base64.getEncoder() // The encoder maps the input to a set of characters in the A-Za-z0-9+/ character set
    //                .encodeToString((apiKeyIdAndSecret) // Encodes the specified byte array into a String using the Base64 encoding scheme.
    //                        .getBytes(StandardCharsets.UTF_8));

}
