package io.github.stewseo.clients.json;

import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class LazyDeserializerTest {

    @JsonTest
    public void testConcurrentInit() throws Exception {

        // See https://github.com/elastic/elasticsearch-java/issues/58

        final LazyDeserializer<String> ld = new LazyDeserializer<>(JsonpDeserializer::stringDeserializer);

        CompletableFuture<JsonpDeserializer<String>> fut1;
        CompletableFuture<JsonpDeserializer<String>> fut2;

        // Lock the mutex and start 2 threads that will compete for it.
        synchronized (ld) {
            fut1 = futureUnwrap(ld);
            fut2 = futureUnwrap(ld);
        }

        // We should see the same non-null results everywhere
        assertNotNull(fut1.get());
        assertNotNull(fut2.get());

        final JsonpDeserializer<String> unwrapped = ld.unwrap();
        assertEquals(unwrapped, fut1.get());
        assertEquals(unwrapped, fut2.get());

    }

    @JsonTest
    public void testConcurInit() throws Exception {

        // See https://github.com/elastic/elasticsearch-java/issues/58

        final LazyDeserializer<String> ld = new LazyDeserializer<>(JsonpDeserializer::stringDeserializer);

        CompletableFuture<JsonpDeserializer<String>> fut1;
        CompletableFuture<JsonpDeserializer<String>> fut2;

        // Lock the mutex and start 2 threads that will compete for it.
        synchronized (ld) {
            fut1 = futureUnwrap(ld);
            fut2 = futureUnwrap(ld);
        }

        // We should see the same non-null results everywhere
        assertNotNull(fut1.get());
        assertNotNull(fut2.get());

        final JsonpDeserializer<String> unwrapped = ld.unsafeUnwrap();
        assertEquals(unwrapped, fut1.get());
        assertEquals(unwrapped, fut2.get());

    }

    private CompletableFuture<JsonpDeserializer<String>> unsafeFutureUnwrap(LazyDeserializer<String> d) {

        final CompletableFuture<JsonpDeserializer<String>> result = new CompletableFuture<>();

        new Thread(() -> {
            try {
                result.complete(d.unsafeUnwrap());
            } catch (Throwable e) {
                result.completeExceptionally(e);
            }
        }).start();

        return result;
    }

    private CompletableFuture<JsonpDeserializer<String>> futureUnwrap(LazyDeserializer<String> d) {

        final CompletableFuture<JsonpDeserializer<String>> result = new CompletableFuture<>();

        new Thread(() -> {
            try {
                result.complete(d.unwrap());
            } catch (Throwable e) {
                result.completeExceptionally(e);
            }
        }).start();

        return result;
    }

    //  reduce the overhead of acquiring a lock by first testing the locking criterion without actually acquiring the lock.
    //  Double-checked locking improves performance by limiting synchronization to the rare case of computing the field's value or constructing
    //  a new instance for the field to reference and by foregoing synchronization during the common case of retrieving an already-created instance or value.
    
    @JsonTest
    public void testRaceCondition() {
        // Fix race condition in LazyDeserializer initialization #59
        // https://github.com/elastic/elasticsearch-java/commit/7dc37429a97613f099455ca42d19acf4b70c80e9

        JsonpDeserializer<SearchResponse<Object>> searchResponseDeser =
                JsonpDeserializer.lazy(() -> SearchResponse.createSearchResponseDeserializer(
                                new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search.ResultT")));

    }

}