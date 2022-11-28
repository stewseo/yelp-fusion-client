package org.example.yelp.fusion.client.json.json.jackson;

import jakarta.json.stream.JsonParser;
import org.apache.http.entity.HttpEntityWrapper;
import org.example.elasticsearch.client.elasticsearch.core.Hit;
import org.example.elasticsearch.client.json.JsonpDeserializer;

import org.example.yelp.fusion.client.business.model.Business;
import org.example.yelp.fusion.client.model.ModelTestCase;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class JsonpDeserializerTest extends ModelTestCase {

    private final static String reqLine = "http://api.yelp.com/v3/businesses/search?location=sf&limit=50";

    public static void main(String[] args) throws URISyntaxException {
        JsonpDeserializerTest jsonpDeser = new JsonpDeserializerTest();

        CompletableFuture<Object> ent = jsonpDeser.get(reqLine);

        System.out.println("body: " + ent.join().toString().getBytes(StandardCharsets.UTF_8).length);
    }

    public CompletableFuture<Object> get(String uri) throws URISyntaxException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(Duration.ofSeconds(20))
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(uri))
                .setHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))
                .build();




        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.fromSubscriber(
                new StringSubscriber(), StringSubscriber::getBody);

        return client.sendAsync(request,  bodyHandler)
                .whenComplete((r, t) -> System.out.println("status code: "  + r.statusCode()))
                .thenApply(HttpResponse::body);
    }

    static class StringSubscriber implements Flow.Subscriber<List<ByteBuffer>> {

        Flow.Subscription subscription;
        List<ByteBuffer> responseData = new ArrayList<>();

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

    @Test
    public void testNullStringInArray() {

        JsonpDeserializer<List<String>> deser = JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer());

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("[\"a\", null, \"b\"]"));
        List<String> list = deser.deserialize(parser, mapper);

        assertEquals("a", list.get(0));
        assertNull(list.get(1));
        assertEquals("b", list.get(2));
    }

    @Test
    public void jsonPDeserializerTest() {
        JsonpDeserializer<Hit<Business>>  deserializer = Hit.createHitDeserializer(Business._DESERIALIZER);
    }



    @Test
    public void parseJsonStreamTest() throws URISyntaxException {
        HttpEntityWrapper wrapper;

        JsonpDeserializerTest jsonpDeser = new JsonpDeserializerTest();

        CompletableFuture<Object> ent = get(reqLine);


        System.out.println("body: " + ent.join().toString().getBytes(StandardCharsets.UTF_8).length);
    }

}
