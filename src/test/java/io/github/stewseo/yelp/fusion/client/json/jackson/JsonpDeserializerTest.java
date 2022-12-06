package io.github.stewseo.yelp.fusion.client.json.jackson;

import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.ModelTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;

public class JsonpDeserializerTest extends ModelTestCase {
    private static final Logger logger = LoggerFactory.getLogger(JsonpDeserializerTest.class);
    private final static String reqLine = "http://api.yelp.com/v3/businesses/search?" +
            "location=sf&limit=50&term=restaurants&latitude=38.2142&longitude=-122.1429&categories=";


    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        List<URI> uris = new ArrayList<>();

        for(String category: List.of("sushi", "pizza")){
            uris.add(new URI(reqLine + category));
        }

        List<CompletableFuture<String>> completeableFutures = get(uris, apiKey);
        logger.info(PrintUtils.green("all live thread IDs " + Arrays.toString(ManagementFactory.getThreadMXBean().getAllThreadIds())));
        logger.info(PrintUtils.green("getCurrentThreadCpuTime " + ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime()));
        logger.info(PrintUtils.green("getPeakThreadCount " + ManagementFactory.getThreadMXBean().getPeakThreadCount()));
        logger.info(PrintUtils.green("getTotalStartedThreadCount " + ManagementFactory.getThreadMXBean().getTotalStartedThreadCount()));
        logger.info(PrintUtils.green("getDaemonThreadCount " + ManagementFactory.getThreadMXBean().getDaemonThreadCount()));
        
        int nbThreads =  Thread.getAllStackTraces().keySet().size();
        logger.info(PrintUtils.green("nbThreads " + nbThreads));

        int nbRunning = 0;
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getState()==Thread.State.RUNNABLE) nbRunning++;
        }
        
        try {
            for(CompletableFuture<String> cf: completeableFutures) {
                String result = cf.get();
                logger.info(PrintUtils.cyan("result length: " + result.length()));
            }

        } catch (Exception e) {
            if(e instanceof RuntimeException) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<CompletableFuture<String>> get(List<URI> uris, String apiKey) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_2)
                .build();
        List<HttpRequest> requests = uris.stream()
                .map(HttpRequest::newBuilder)
                .peek(e -> e.setHeader("Authorization", "Bearer " + apiKey))
                .map(HttpRequest.Builder::build)
                .toList();


        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.fromSubscriber(
                new StringSubscriber(), StringSubscriber::getBody);

        return requests.stream().map(request -> client
                .sendAsync(request, bodyHandler)
                .whenComplete((r, t) -> System.out.println("status code: "  + r.statusCode()))
                .thenApply(HttpResponse::body)).toList();
    }

    static class StringSubscriber implements Flow.Subscriber<List<ByteBuffer>> {
        Flow.Subscription subscription;
        final List<ByteBuffer> responseData = new ArrayList<>();

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
//            logger.info(PrintUtils.red("onSubscribe: " + subscription));
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(List<ByteBuffer> buffers) {
            logger.info(PrintUtils.red("onNext with:"+ buffers));
            responseData.addAll(buffers);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {}
        private String body;
        public String getBody(){
//            logger.info(PrintUtils.red("returning body as String"));
            return this.body;}

        @Override
        public void onComplete() {
            int size = responseData.stream().mapToInt(ByteBuffer::remaining).sum();
            logger.info(PrintUtils.red("number of ByteBuffers: " + responseData.size() + ", total remaining byte buffers being converted to byte array: " + size));

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
