package io.github.stewseo.client.json.jackson;

import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;

public class JsonpDeserializerTest extends YelpFusionTestCase {
    private static final Logger logger = LoggerFactory.getLogger(JsonpDeserializerTest.class);
    private static final String reqLine = "http://api.yelp.com/v3/businesses/search?" +
            "location=sf&limit=50&term=restaurants&latitude=38.2142&longitude=-122.1429&all=";
    static int nbThreads = Thread.getAllStackTraces().keySet().size();
    static int nbNew = 0;
    static int nbRunning = 0;
    static int nbBlocked = 0;
    static int nbWaiting = 0;
    static int nbTerminated = 0;
    static int nbTimedWaiting = 0;

    public static void main(String[] args) throws Exception {

        threadInfo();
        threadStates();

        String apiKey = System.getenv("YELP_API_KEY");

        List<URI> uris = new ArrayList<>();

        for (String category : List.of("sushi", "pizza", "japanese", "burgers")) {
            uris.add(new URI(reqLine + category));
        }

        // Returns the managed bean for the thread system of the Java virtual machine.
        threadInfo();
        threadStates();

        List<CompletableFuture<String>> completeableFutures = get(uris, apiKey);

        try {
            for (CompletableFuture<String> cf : completeableFutures) {
                String result = cf.get();
                logger.info(PrintUtils.cyan("result length: " + result.length()));
            }

        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e);
            }
        }
        // use managed bean for thread system of JVM
        threadInfo();
        threadStates();

    }

    private static void threadInfo() {
        logger.info(PrintUtils.green("all live thread IDs " + Arrays.toString(ManagementFactory.getThreadMXBean().getAllThreadIds())));


        Arrays.stream(ManagementFactory
                        .getThreadMXBean().getThreadInfo(
                                ManagementFactory.getThreadMXBean().getAllThreadIds())
                )
                .forEach(thread -> {
                    logger.info("" +
                            PrintUtils.cyan("ThreadId: " + thread.getThreadId()) +
                            PrintUtils.red(", ThreadName: " + thread.getThreadName() +
                                    ", ThreadState: " + thread.getThreadState()) +
                            PrintUtils.green(", Priority: " + thread.getPriority() +
                                    ", BlockedCount: " + thread.getBlockedCount() +
                                    ", BlockedTime: " + thread.getBlockedTime())
                    );

                });

        logger.info(PrintUtils.green("getCurrentThreadCpuTime " + ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime()));
        logger.info(PrintUtils.green("getPeakThreadCount " + ManagementFactory.getThreadMXBean().getPeakThreadCount()));
        logger.info(PrintUtils.green("getTotalStartedThreadCount " + ManagementFactory.getThreadMXBean().getTotalStartedThreadCount()));
        logger.info(PrintUtils.green("getDaemonThreadCount " + ManagementFactory.getThreadMXBean().getDaemonThreadCount()));
    }

    private static void threadStates() {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getState() == Thread.State.NEW) nbNew++;
            if (t.getState() == Thread.State.RUNNABLE) nbRunning++;
            if (t.getState() == Thread.State.BLOCKED) nbBlocked++;
            if (t.getState() == Thread.State.WAITING) nbWaiting++;
            if (t.getState() == Thread.State.TIMED_WAITING) nbTimedWaiting++;
            if (t.getState() == Thread.State.TERMINATED) nbTerminated++;
        }
        logger.info(PrintUtils.green("Total threads " + nbThreads));
        logger.info(PrintUtils.red("Thread.State.NEW " + nbNew));
        logger.info(PrintUtils.red("Thread.State.RUNNABLE " + nbRunning));
        logger.info(PrintUtils.red("Thread.State.BLOCKED " + nbBlocked));
        logger.info(PrintUtils.red("Thread.State.WAITING " + nbWaiting));
        logger.info(PrintUtils.red("Thread.State.TIMED_WAITING " + nbTimedWaiting));
        logger.info(PrintUtils.red("Thread.State.TERMINATED " + nbTerminated));
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
                .whenComplete((r, t) -> System.out.println("status code: " + r.statusCode()))
                .thenApply(HttpResponse::body)).toList();
    }

    static class StringSubscriber implements Flow.Subscriber<List<ByteBuffer>> {
        final List<ByteBuffer> responseData = new ArrayList<>();
        Flow.Subscription subscription;
        private String body;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
//            logger.info(PrintUtils.red("onSubscribe: " + subscription));
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(List<ByteBuffer> buffers) {
//            logger.info(PrintUtils.red("onNext with:"+ buffers));
            responseData.addAll(buffers);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
        }

        public String getBody() {
//            logger.info(PrintUtils.red("returning body as String"));
            return this.body;
        }

        @Override
        public void onComplete() {
            int size = responseData.stream().mapToInt(ByteBuffer::remaining).sum();
            logger.info(PrintUtils.cyan("onComplete"));

//            logger.info(PrintUtils.red("number of ByteBuffers: " + responseData.size() + ", total remaining byte buffers being converted to byte array: " + size));

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
