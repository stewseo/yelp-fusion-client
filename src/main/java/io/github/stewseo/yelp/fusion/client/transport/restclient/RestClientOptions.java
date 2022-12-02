package io.github.stewseo.yelp.fusion.client.transport.restclient;

import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.Version;
import io.github.stewseo.lowlevel.restclient.RequestOptions;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RestClientOptions implements TransportOptions {

    private final RequestOptions options;

    public static RestClientOptions of(TransportOptions options) {
        if (options instanceof RestClientOptions) {
            return (RestClientOptions)options;

        } else {
            final Builder builder = new Builder(RequestOptions.DEFAULT.toBuilder());
            options.headers().forEach(h -> builder.addHeader(h.getKey(), h.getValue()));
            options.queryParameters().forEach(builder::setParameter);
            builder.onWarnings(options.onWarnings());
            return builder.build();
        }
    }

    public RestClientOptions(RequestOptions options) {
        this.options = options.toBuilder().build();
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
    public Map<String, String> queryParameters() {
        return options.getParameters();
    }

    @Override
    public Function<List<String>, Boolean> onWarnings() {
        return null;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(options.toBuilder());
    }


    @Override
    public TransportOptions with(Consumer<TransportOptions.Builder> fn) {
        return TransportOptions.super.with(fn);
    }

    public static class Builder implements TransportOptions.Builder {
        private final RequestOptions.Builder builder;

        public Builder(RequestOptions.Builder builder) {
            this.builder = builder;
        }

        public RequestOptions.Builder restClientRequestOptionsBuilder() {
            return this.builder;
        }

        @Override
        public TransportOptions.Builder addHeader(String name, String value) {
            builder.addHeader(name, value);
            return this;
        }

        @Override
        public TransportOptions.Builder setParameter(String name, String value) {
            builder.addParameter(name, value);
            return this;
        }

        @Override
        public TransportOptions.Builder onWarnings(Function<List<String>, Boolean> listener) {
            return null;
        }

        @Override
        public RestClientOptions build() {
            return new RestClientOptions(builder.build());
        }

    }

    static RestClientOptions initialOptions() {
        return new RestClientOptions(RequestOptions.DEFAULT);
    }


    private static RequestOptions.Builder addBuiltInHeaders(RequestOptions.Builder builder) {

//        builder.removeHeader(CLIENT_META_HEADER);

        if (builder.getHeaders().stream().noneMatch(h -> h.getName().equalsIgnoreCase("Accept"))) {
            builder.addHeader("Accept", RestClientTransport.JsonContentType.toString());
        }

        return builder;
    }

    private static String getUserAgent() {
        return String.format(
                Locale.ROOT,
                "elastic-java/%s (Java/%s)",
                Version.VERSION == null ? "Unknown" : Version.VERSION.toString(),
                System.getProperty("java.version")
        );
    }

//    private static String getClientMeta() {
//        VersionInfo httpClientVersion = null;
//        try {
//            httpClientVersion = VersionInfo.loadVersionInfo(
//
////                    "org.apache.http.nio.client" ,
////                    );
//
//        } catch (Exception e) {
//            // Keep unknown
//        }

        // Use a single 'p' suffix for all prerelease versions (snapshot, beta, etc).
//        String metaVersion = Version.VERSION == null ? "" : Version.VERSION.toString();
//        int dashPos = metaVersion.indexOf('-');
//        if (dashPos > 0) {
//            metaVersion = metaVersion.substring(0, dashPos) + "p";
//        }
//
//        // service, language, transport, followed by additional information
//        return "es="
//                + metaVersion
//                + ",jv="
//                + System.getProperty("java.specification.version")
//                + ",hl=2"
//                + ",t="
//                + metaVersion
//                + ",hc="
//                + (httpClientVersion == null ? "" : httpClientVersion.getRelease());
////                + co.elastic.clients.transport.rest_client.LanguageRuntimeVersions.getRuntimeMetadata();
//    }

}