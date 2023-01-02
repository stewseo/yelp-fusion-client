package io.github.stewseo.client.transport.restclient;

import io.github.stewseo.client.transport.ElasticsearchTransport;
import io.github.stewseo.client.transport.TransportOptions;
import io.github.stewseo.client.ApiClient;
import io.github.stewseo.client.elasticsearch.ElasticsearchService;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

public class TestElasticsearchClient extends ApiClient<ElasticsearchTransport, TestElasticsearchClient> {

    private ElasticsearchService elasticsearchService;

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


}
