package io.github.stewseo.clients.json;

import java.util.concurrent.CompletableFuture;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.LazyDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LazyDeserializerTest extends Assertions {


    @Test
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
}