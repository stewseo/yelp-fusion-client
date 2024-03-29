package io.github.stewseo.lowlevel.restclient;

import org.junit.jupiter.api.Test;

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

@SuppressWarnings("SpellCheckingInspection")
public class CustomStringSubscriberTest {
    private static final String reqLine = "http://api.yelp.com/v3/businesses/search?location=sf&limit=50";

    @Test
    public void customStringSubscriberTest() {

        CustomStringSubscriberTest custSubTest = new CustomStringSubscriberTest();

        Object ent;
        try {
            ent = custSubTest.get(reqLine).get();

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

        final List<ByteBuffer> responseData = new ArrayList<>();

        Flow.Subscription subscription;

        private String body;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(List<ByteBuffer> buffers) {
            System.out.println("onNext with:" + buffers);
            responseData.addAll(buffers);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("throwable: " + throwable);
        }

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
            body = new String(ba, StandardCharsets.UTF_8);
        }
    }
}



