package io.github.stewseo.clients.transport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class TestTransportImpl implements Transport {

     private final JsonpMapper mapper = new JacksonJsonpMapper(
        new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    );

    private final ThreadLocal<Object> result = new ThreadLocal<>();

    private final TransportOptions options = new TransportOptions() {
        @Override
        public Collection<Map.Entry<String, String>> headers() {
            return Collections.emptyList();
        }

        @Override
        public Map<String, String> queryParameters() {
            return Collections.emptyMap();
        }

        @Override
        public Function<List<String>, Boolean> onWarnings() {
            return null;
        }

        @Override
        public Builder toBuilder() {
            return null;
        }
    };

    public void setResult(Object result) {
        this.result.set(result);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RequestT, ResponseT, ErrorT> ResponseT performRequest(
        RequestT request,
        Endpoint<RequestT, ResponseT, ErrorT> endpoint,
        @Nullable TransportOptions options
    ) throws IOException {

        return (ResponseT) result.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RequestT, ResponseT, ErrorT> CompletableFuture<ResponseT> performRequestAsync(RequestT request, Endpoint<RequestT, ResponseT,
        ErrorT> endpoint, @Nullable TransportOptions options) {
        CompletableFuture<ResponseT> future = new CompletableFuture<>();
        future.complete((ResponseT) result.get());
        return future;
    }

    @Override
    public JsonpMapper jsonpMapper() {
        return mapper;
    }

    @Override
    public TransportOptions options() {
        return options;
    }

    @Override
    public void close() throws IOException {
    }
}
