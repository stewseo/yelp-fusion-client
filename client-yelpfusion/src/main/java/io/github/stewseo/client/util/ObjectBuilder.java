package io.github.stewseo.client.util;

@FunctionalInterface
public interface ObjectBuilder<T> {
    T build();
}