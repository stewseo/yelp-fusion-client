package io.github.stewseo.clients.json;

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
}
