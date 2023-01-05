package io.github.stewseo.clients.util;

@FunctionalInterface
public interface ObjectBuilder<T> {
    T build();
}