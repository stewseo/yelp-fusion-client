package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.DelegatingDeserializer;
import io.github.stewseo.clients.json.JsonpDeserializer;

import java.util.function.Supplier;

public class LazyDeserializer<T> extends DelegatingDeserializer.SameType<T> {

    private final Supplier<JsonpDeserializer<T>> ctor;

    // "Use a thread-safe type; adding "volatile" is not enough to make this field thread-safe"
    private volatile JsonpDeserializer<T> deserializer = null;

    public LazyDeserializer(Supplier<JsonpDeserializer<T>> ctor) {
        this.ctor = ctor;
    }

    protected JsonpDeserializer<T> unwrap() {
        // See SEI CERT LCK10-J https://wiki.sei.cmu.edu/confluence/x/6zdGBQ
        JsonpDeserializer<T> d = deserializer;
        if (d != null) {
            return d;
        } else {
            synchronized (this) {
                if (deserializer == null) {
                    deserializer = ctor.get();
                }
            }
            return deserializer;
        }
    }
    // cases where d has been assigned by another thread
    // after the current thread has entered the synchronized block:
    protected JsonpDeserializer<T> testUnwrap() {

        JsonpDeserializer<T> d = deserializer;
        if (d == null) {
            synchronized (this) {
                if (deserializer == null) {
                    d = ctor.get();
                    System.out.println("d: " + d);
                    deserializer = d;
                    System.out.println("deserializer: " + deserializer);
                }
            }
        }
        return d;
    }

}
