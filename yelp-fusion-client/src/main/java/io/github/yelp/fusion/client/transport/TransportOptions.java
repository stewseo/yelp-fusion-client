package io.github.yelp.fusion.client.transport;

import io.github.yelp.fusion.client.util.ObjectBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

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