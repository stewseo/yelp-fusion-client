package io.github.stewseo.clients.yelpfusion.businesses.transactions;

import co.elastic.clients.elasticsearch.core.SearchRequest;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequestBase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@JsonpDeserializable
public class SearchTransactionRequest extends SearchBusinessesRequestBase {

    private final String transaction_type;

    private SearchTransactionRequest(Builder builder) {
        super(builder);
        this.transaction_type = ApiTypeHelper.requireNonNull(builder.transaction_type, this, "transaction_type");
    }

    public static SearchTransactionRequest of(Function<Builder, ObjectBuilder<SearchTransactionRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String transaction_type() {
        return transaction_type;
    }


    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if (this.transaction_type != null) {
            generator.writeKey("transaction_type");
            generator.write(this.transaction_type);
        }
    }


    public static class Builder extends AbstractBuilder<Builder> implements ObjectBuilder<SearchTransactionRequest> {
        private String transaction_type;

        public final Builder transaction_type(@Nullable String transaction_type) {
            this.transaction_type = transaction_type;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SearchTransactionRequest build() {
            _checkSingleUse();
            return new SearchTransactionRequest(this);
        }
    }

    public static final JsonpDeserializer<SearchTransactionRequest> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(SearchTransactionRequest.Builder::new,
                    SearchTransactionRequest::setupSearchTransactionRequestDeserializer);


    protected static void
    setupSearchTransactionRequestDeserializer(ObjectDeserializer<SearchTransactionRequest.Builder> op) {
        setupSearchBusinessesRequestBaseDeserializer(op);
        op.add(Builder::transaction_type, JsonpDeserializer.stringDeserializer(), "transaction_type");
    }

    public static final SimpleEndpoint<SearchTransactionRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/transactions",
            // Request method
            request -> "GET",
            // Request path
            request -> {
                if (request.transaction_type != null) {
                    return "v3/transactions/" + request.transaction_type + "/search";
                }
                throw SimpleEndpoint.noPathTemplateFound("path");
            },
            // Request parameters
            request -> {

                Map<String, String> parameters = new HashMap<>();

                if (request.transaction_type != null) {
                    parameters.put("transaction_type", request.transaction_type);
                }
                Double latitude = request.latitude();
                if (latitude != null) {
                    parameters.put("latitude", String.valueOf(latitude));
                }
                Double longitude = request.longitude();
                if (longitude != null) {
                    parameters.put("longitude", String.valueOf(longitude));
                }
                if (request.location() != null) {
                    parameters.put("location", request.location());
                }
                return parameters;
            }, SimpleEndpoint.emptyMap(), false, SearchResponse._DESERIALIZER);

    	public static <ResultT> Endpoint<SearchTransactionRequest, SearchResponse<ResultT>, ErrorResponse> createSearchEndpoint(
			JsonpDeserializer<ResultT> resultTDeserializer) {
		return _ENDPOINT
				.withResponseDeserializer(SearchResponse.createSearchResponseDeserializer(resultTDeserializer));
	}
}



