package io.github.stewseo.clients.yelpfusion.businesses.search.test_model;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.NamedDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class TestSearchBusinessResponse <TDocument> extends ResponseBody<TDocument> {

    private TestSearchBusinessResponse(TestSearchBusinessResponse.Builder<TDocument> builder) {
        super(builder);

    }

    public static <TDocument> TestSearchBusinessResponse<TDocument> of(
            Function<TestSearchBusinessResponse.Builder<TDocument>, ObjectBuilder<TestSearchBusinessResponse<TDocument>>> fn) {
        return fn.apply(new TestSearchBusinessResponse.Builder<>()).build();
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Builder for {@link TestSearchBusinessResponse}.
     */

    public static class Builder<TDocument> extends ResponseBody.AbstractBuilder<TDocument, TestSearchBusinessResponse.Builder<TDocument>>
            implements
            ObjectBuilder<TestSearchBusinessResponse<TDocument>> {
        @Override
        protected TestSearchBusinessResponse.Builder<TDocument> self() {
            return this;
        }

        /**
         * Builds a {@link TestSearchBusinessResponse}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public TestSearchBusinessResponse<TDocument> build() {
            _checkSingleUse();

            return new TestSearchBusinessResponse<TDocument>(this);
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Create a JSON deserializer for SearchResponse
     */
    public static <TDocument> JsonpDeserializer<TestSearchBusinessResponse<TDocument>> createSearchResponseDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<TestSearchBusinessResponse.Builder<TDocument>>) TestSearchBusinessResponse.Builder::new,
                op -> TestSearchBusinessResponse.setupSearchResponseDeserializer(op, tDocumentDeserializer));
    }

    ;

    /**
     * Json deserializer for {@link TestSearchBusinessResponse} based on named deserializers
     * provided by the calling {@code JsonMapper}.
     */
    public static final JsonpDeserializer<TestSearchBusinessResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createSearchResponseDeserializer(
                    new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search.TDocument")));

    protected static <TDocument> void setupSearchResponseDeserializer(
            ObjectDeserializer<Builder<TDocument>> op,
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        ResponseBody.setupResponseBodyDeserializer(op, tDocumentDeserializer);

    }
}

