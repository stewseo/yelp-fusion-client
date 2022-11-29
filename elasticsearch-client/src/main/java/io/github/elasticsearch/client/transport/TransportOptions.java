package io.github.elasticsearch.client.transport;

import io.github.elasticsearch.client.util.ObjectBuilder;

import java.util.*;
import java.util.function.*;

public interface TransportOptions {

    Collection<Map.Entry<String, String>> headers();

    Map<String, String> queryParameters();

    Function<List<String>, Boolean> onWarnings();

    Builder toBuilder();

    default TransportOptions with(Consumer<Builder> fn) {
        Builder builder = toBuilder();
        fn.accept(builder);
        return builder.build();
    }

    interface Builder extends ObjectBuilder<TransportOptions> {

        Builder addHeader(String name, String value);

        Builder setParameter(String name, String value);

        Builder onWarnings(Function<List<String>, Boolean> listener);
    }
}