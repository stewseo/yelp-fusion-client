package io.github.stewseo.yelp.fusion.client.json.jackson;

import io.github.stewseo.yelp.fusion.client.yelpfusion.business.ModelTestCase;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;

public class JsonpDeserializerTest extends ModelTestCase {

    private final static String reqLine = "http://api.yelp.com/v3/businesses/search?location=sf&limit=50";
//    JsonpDeserializerTest jsonpDeser = new JsonpDeserializerTest();

    public static void main(String[] args) throws URISyntaxException {
        String apiKey = System.getenv("YELP_API_KEY");

        CompletableFuture<Object> completeableFuture = get(reqLine, apiKey);

        System.out.println("body: " + completeableFuture.join().toString());
    }

    public static CompletableFuture<Object> get(String uri, String apiKey) throws URISyntaxException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(uri))
                .setHeader("Authorization", "Bearer " + apiKey)
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenComplete((r, t) -> System.out.println("status code: "  + r.statusCode()))
                .thenApply(HttpResponse::body);
    }

//    public CompletableFuture<Object> get(String uri) throws URISyntaxException {
//        HttpClient client = HttpClient.newBuilder()
//                .followRedirects(HttpClient.Redirect.NORMAL)
//                .connectTimeout(Duration.ofSeconds(20))
//                .version(HttpClient.Version.HTTP_2)
//                .build();
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .uri(new URI(uri))
//                .setHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))
//                .build();
//
//        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.fromSubscriber(
//                new StringSubscriber(), StringSubscriber::getBody);
//
//        return client.sendAsync(request,  bodyHandler)
//                .whenComplete((r, t) -> System.out.println("status code: "  + r.statusCode()))
//                .thenApply(HttpResponse::body);
//    }

    static class StringSubscriber implements Flow.Subscriber<List<ByteBuffer>> {

        Flow.Subscription subscription;
        final List<ByteBuffer> responseData = new ArrayList<>();

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(List<ByteBuffer> buffers) {
            System.out.println("onNext with:"+ buffers);
            responseData.addAll(buffers);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {}
        private String body;
        public String getBody(){return this.body;}

        @Override
        public void onComplete() {
            int size = responseData.stream().mapToInt(ByteBuffer::remaining).sum();
            byte[] ba = new byte[size];
            int offset = 0;
            for(ByteBuffer buffer: responseData) {
                int remaining = buffer.remaining();
                buffer.get(ba, offset, remaining);
                offset += remaining;
            }
             body = new String(ba);
        }
    }


    Logger logger = LoggerFactory.getLogger(JsonpDeserializerTest.class);

//    @Test
//    public void testNullStringInArray() {
//
//        JsonpDeserializer<List<String>> deser = JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer());
//
//        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("[\"a\", null, \"b\"]"));
//        List<String> list = deser.deserialize(parser, mapper);
//
//        assertEquals("a", list.get(0));
//        assertNull(list.get(1));
//        assertEquals("b", list.get(2));
//    }
//
//    @Test
//    public void jsonPDeserializerTest() {
//        JsonpDeserializer<Hit<Business>>  deserializer = Hit.createHitDeserializer(Business._DESERIALIZER);
//    }



//    @Test
//    public void parseJsonStreamTest() throws URISyntaxException {
//        HttpEntityWrapper wrapper;
//
//        JsonpDeserializerTest jsonpDeser = new JsonpDeserializerTest();
//
//        CompletableFuture<Object> ent = get(reqLine);
//
//
//        System.out.println("body: " + ent.join().toString().getBytes(StandardCharsets.UTF_8).length);
//    }

}
