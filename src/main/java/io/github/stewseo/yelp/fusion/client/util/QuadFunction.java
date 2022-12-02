package io.github.stewseo.yelp.fusion.client.util;

@FunctionalInterface
public interface QuadFunction<T, U, V, W, R> {

    R apply(T t, U u, V v, W w);
}