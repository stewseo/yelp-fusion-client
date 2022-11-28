package org.example.yelp.fusion.client.restclient;

import jakarta.json.stream.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.example.elasticsearch.client.json.JsonpDeserializer;
import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.example.elasticsearch.client.transport.JsonEndpoint;
import org.example.lowlevel.restclient.Node;
import org.example.lowlevel.restclient.PrintUtils;
import org.example.lowlevel.restclient.Request;
import org.example.lowlevel.restclient.RestClient;
import org.example.yelp.fusion.client.business.BusinessDetailsRequest;
import org.example.yelp.fusion.client.business.BusinessDetailsResponse_;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class RequestExecutionTests {

    private static final Logger logger = LoggerFactory.getLogger(RequestExecutionTests.class);
    String uri = "http://api.yelp.com/v3/businesses/search?location=sf";

    @Test
    public void executeHttpRequestBaseTest() throws IOException, ExecutionException, InterruptedException {

        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        client.start();
        HttpGet request = new HttpGet(uri);
        HttpRequestBase httpRequestBase = new HttpGet(uri);
        request.setHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));

        HttpResponse response = client.execute(httpRequestBase, null).get();

        assertThat(response.getStatusLine().toString()).isEqualTo("HTTP/1.1 200 OK");

        logger.debug(PrintUtils.debug("Future<HttpResponse> future: " + response.getStatusLine() + " available bytes: " + response.getEntity().getContent().available()));
        client.close();
    }

    static  Node node = new Node(new HttpHost("api.yelp.com"));
    static  Request request = new Request("Get", "v3/businesses/search");

    private HttpEntity sendRequestAsync() throws Exception {
        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        client.start();
        AuthCache authCache = new BasicAuthCache();
        URI uri = new URI("v3/businesses/wu3w6IlUct9OvYmYXDMGJA");

        URI u = RestClient.buildUri(null, "v3/businesses/wu3w6IlUct9OvYmYXDMGJA", new HashMap<>());

        HttpRequestBase httpRequestBase = RestClient.createHttpRequest(request.getMethod(), u, request.getEntity(), true);

        httpRequestBase.setHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));


        HttpAsyncRequestProducer requestProducer = HttpAsyncMethods.create(node.getHost(), httpRequestBase);

        HttpAsyncResponseConsumer<HttpResponse> asyncResponseConsumer = request.getOptions()
                .getHttpAsyncResponseConsumerFactory()
                .createHttpAsyncResponseConsumer();


        HttpClientContext context = HttpClientContext.create();

        context.setAuthCache(authCache);

        Future<org.apache.http.HttpResponse> future = client.execute(requestProducer, asyncResponseConsumer, context, null);

        HttpResponse httpResponse = future.get();
        client.close();
        return httpResponse.getEntity();
    }

    @SuppressWarnings("unchecked")
    @Test
    void endpointTest() {
        BusinessDetailsRequest businessSearchRequest = BusinessDetailsRequest.of(s -> s
                .id("wu3w6IlUct9OvYmYXDMGJA"));

        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse_, ?> jsonEndpoint = (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse_, ?>) businessSearchRequest._ENDPOINT;

        assertThat(jsonEndpoint.id()).isEqualTo("v3/businesses");

        assertThat(jsonEndpoint.method(businessSearchRequest)).isEqualTo("GET");
        assertThat(jsonEndpoint.requestUrl(businessSearchRequest)).isEqualTo("v3/businesses/wu3w6IlUct9OvYmYXDMGJA");
        assertThat(jsonEndpoint.responseDeserializer()).isEqualTo("GET");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void requestProducerTest() throws Exception {
        HttpEntity entity = sendRequestAsync();

//        BusinessDetailsRequest businessDetailsRequest = BusinessDetailsRequest.of(s -> s
//                .id("wu3w6IlUct9OvYmYXDMGJA"));


        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse_, ?> jsonEndpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse_, ?>) BusinessDetailsRequest._ENDPOINT;


        JsonpDeserializer<BusinessDetailsResponse_> responseParser = jsonEndpoint.responseDeserializer();
//
        JacksonJsonpMapper mapper = new JacksonJsonpMapper();

        InputStream content = entity.getContent();

        JsonParser parser = mapper.jsonProvider().createParser(content);


//        if(parser.hasNext()) {
//            JsonParser.Event event = parser.next();
//            switch (event) {
//                case START_ARRAY, END_ARRAY, START_OBJECT, END_OBJECT, VALUE_FALSE, VALUE_NULL, VALUE_TRUE -> logger.info(event.toString());
//                case KEY_NAME -> logger.info(event.toString() + " " + parser.getString() + " - ");
//                case VALUE_STRING, VALUE_NUMBER -> logger.info(event.toString() + " " + parser.getString());
//            }
//
//        }

        BusinessDetailsResponse_ response = responseParser.deserialize(parser, mapper);
        logger.debug(PrintUtils.debug("response " + response.result()));

    }

}
