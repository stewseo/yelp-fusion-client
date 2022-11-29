package io.github.yelp.fusion.client.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;

public class JdkHttpClientTest {

    private static final Logger logger = LoggerFactory.getLogger(JdkHttpClientTest.class);
    private static final String reqLine = "http://api.yelp.com/v3/businesses/search?location=sf&limit=50";


    public static void main(String[] args) {
        JdkHttpClientTest jdes = new JdkHttpClientTest();

        Object ent = null;
        try {
            ent = jdes.get(reqLine).get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("body: " + ent.toString().getBytes(StandardCharsets.UTF_8).length);
    }

    public CompletableFuture<Object> get(String uri) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofMinutes(1))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .headers("Authorization", "Bearer: " + System.getenv("YELP_API_KEY"))
                .build();


        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.fromSubscriber(
                new StringSubscriber(), StringSubscriber::getBody);

        return client.sendAsync(request, bodyHandler)
                .whenComplete((r, t) -> System.out.println("status code: " + r.statusCode()))
                .thenApply(HttpResponse::body);
    }

    static class StringSubscriber implements Flow.Subscriber<List<ByteBuffer>> {

        Flow.Subscription subscription;
        List<ByteBuffer> responseData = new ArrayList<>();

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
            logger.info("subscription :");
        }

        @Override
        public void onNext(List<ByteBuffer> buffers) {
            logger.info("onNext with:" + buffers);
            responseData.addAll(buffers);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            logger.error("throwable: ", throwable);
        }

        private String body;

        public String getBody() {
            return this.body;
        }

        @Override
        public void onComplete() {
            int size = responseData.stream().mapToInt(ByteBuffer::remaining).sum();
            byte[] ba = new byte[size];
            int offset = 0;
            for (ByteBuffer buffer : responseData) {
                int remaining = buffer.remaining();
                buffer.get(ba, offset, remaining);
                offset += remaining;
            }
            body = new String(ba);
            logger.info("complete. body length: " + body.length());
        }
    }
}



