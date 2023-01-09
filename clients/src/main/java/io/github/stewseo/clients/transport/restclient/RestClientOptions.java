package io.github.stewseo.clients.transport.restclient;

import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.lowlevel.restclient.RequestOptions;
import io.github.stewseo.lowlevel.restclient.WarningsHandler;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RestClientOptions implements TransportOptions {

    private final RequestOptions options; // gets shared between multiple requests in an application

    public RestClientOptions(RequestOptions options) {
        this.options = addBuiltinHeaders(options.toBuilder()).build();
    }

    static RestClientOptions of(TransportOptions options) {
        // if options have been set, return Transport Options
        if (options instanceof RestClientOptions) {
            return (RestClientOptions) options; // return the parameter options

        } else {
            final Builder builder = new Builder(RequestOptions.DEFAULT.toBuilder());
            options.headers().forEach(h -> builder.addHeader(h.getKey(), h.getValue()));

            options.queryParameters().forEach(builder::setParameter);
            builder.onWarnings(options.onWarnings());
            return builder.build();
        }
    }

    static RestClientOptions initialOptions() {
        return new RestClientOptions(RequestOptions.DEFAULT);
    }

    private static RequestOptions.Builder addBuiltinHeaders(RequestOptions.Builder builder) {
        if (builder.getHeaders().stream().noneMatch(h -> h.getName().equalsIgnoreCase("Accept"))) {

        }
        return builder;
    }

    public RequestOptions restClientRequestOptions() {
        return this.options;
    }

    @Override
    public Collection<Map.Entry<String, String>> headers() {

        return options.getHeaders().stream()
                .map(h -> new AbstractMap.SimpleImmutableEntry<>(h.getName(), h.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public Function<List<String>, Boolean> onWarnings() {
        final WarningsHandler handler = options.getWarningsHandler();
        if (handler == null) {
            return null;
        }

        return warnings -> options.getWarningsHandler().warningsShouldFailRequest(warnings);
    }

    @Override
    public Map<String, String> queryParameters() {
        return options.getParameters();
    }

    @Override
    public Builder toBuilder() {
        return new Builder(options.toBuilder());
    }

    public static class Builder implements TransportOptions.Builder {

        private final RequestOptions.Builder builder; //  RequestOptions

        // initializes RequestOptions.Builder
        public Builder(RequestOptions.Builder builder) {
            this.builder = builder;
        }

        // Get the wrapped Rest Client request options builder.
        public RequestOptions.Builder restClientRequestOptionsBuilder() {
            return this.builder;
        }

        // add headers to RequestOptions.Builder
        @Override
        public Builder addHeader(String name, String value) {
            builder.addHeader(name, value);
            return this;
        }

        // set parameters RequestOptions.Builder
        @Override
        public Builder setParameter(String name, String value) {
            builder.addParameter(name, value);
            return this;
        }

        // warnings for RequestOptions.Builder
        @Override
        public Builder onWarnings(Function<List<String>, Boolean> listener) {
            if (listener == null) {
                builder.setWarningsHandler(null);
            } else {
                builder.setWarningsHandler(w -> {
                    if (w != null && !w.isEmpty()) {
                        return listener.apply(w);
                    } else {
                        return false;
                    }
                });
            }

            return this;
        }

        @Override
        public RestClientOptions build() {
            return new RestClientOptions(addBuiltinHeaders(builder).build());
        }
    }

}
