package io.github.stewseo.yelp.fusion.client.util;

@FunctionalInterface
public interface TriConsumer<T, U, V> {
    
    void accept(T t, U u, V v);
}