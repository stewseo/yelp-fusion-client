package io.github.stewseo.lowlevel.restclient;

import org.apache.http.HttpHost;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestClientTest {

    private final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
    private HttpHost buildHttpHost() {
        String host = "api.yelp.com";
        int port = 443;
        String scheme = "https";
        return new HttpHost(host, port, scheme);
    }

    @Test
    void testBuilder() {
    }

    @Test
    void buildUri() {

    }

    @Test
    void testIsRunning() {
        HttpHost httpHost = buildHttpHost();

        try(RestClient restClient = RestClient.builder(httpHost).build()) {
            assertTrue(restClient.isRunning());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RestClient createRestClient() {
        HttpHost httpHost = buildHttpHost();
        return RestClient.builder(httpHost).build();
    }

    private RequestOptions buildRequestOptions() {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));

        return builder.build();
    }

    private final String method = "get";
    private final String endpoint = "v3/businesses/search";

    @Test
    void testPerformRequest() {

        Request request = new Request(method, endpoint);

        request.addParameter("location", "sf");

        RequestOptions options = buildRequestOptions();

        request.setOptions(options);

        try(RestClient restClient = createRestClient()){

            Response resp = restClient.performRequest(request);
            assertThat(resp.getRequestLine().toString()).isEqualTo("GET v3/businesses/search?location=sf HTTP/1.1");
            assertThat(resp.getHttpResponse().getStatusLine().toString()).isEqualTo("HTTP/1.1 200 OK");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private final RestClient restClient = createRestClient();
    private final List<String> locations = List.of("sf", "carmel-by-the-sea", "losangeles", "napavalley");

    @Test
    void performRequestAsync() {

        int length = locations.size();

        final CountDownLatch latch = new CountDownLatch(length);

        RequestOptions requestOptions = buildRequestOptions();

        for (int i = 0; i < length; i++) {

            Request request = new Request(method, endpoint);
            request.addParameter("location", locations.get(i));
            request.setOptions(requestOptions);
            restClient.performRequestAsync(
                    request,
                    new ResponseListener() {
                        @Override
                        public void onSuccess(Response response) {
                            logger.info("onSuccess: " + response.getHttpResponse().getStatusLine());
                            // Decrements the count of the latch, releasing all waiting threads if the count reaches zero.
                            // If the current count is greater than zero then it is decremented.
                            // If the new count is zero then all waiting threads are re-enabled for thread scheduling purposes.
                            // If the current count equals zero then nothing happens.
                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            logger.info("onFailure: " + exception);
                            // Decrements the count of the latch, releasing all waiting threads if the count reaches zero.
                            //If the current count is greater than zero then it is decremented.
                            // If the new count is zero then all waiting threads are re-enabled for thread scheduling purposes.
                            //If the current count equals zero then nothing happens.
                            latch.countDown();
                        }
                    }
            );
        }
        try {
            // Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted.
            // If the current count is zero then this method returns immediately.
            logger.info("latch.await()");
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void httpHost() {
    }

    @Test
    void close() {
    }
}