package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.NamedDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.util.List;
import java.util.function.Supplier;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.EXPECTED_SEARCH_BUSINESS_RESULT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.REGION;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TOTAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResponseBodyTest extends ModelTestCase<SearchResponse<SearchBusinessResult>> {

    private static final JsonpSerializer<SearchBusinessResult> toStringSerializer =
            (JsonpSerializer<SearchBusinessResult>) (value, generator, mapper) -> {
                if (value == null) {
                    generator.writeNull();
                } else {
                    generator.write(value.toString());
                }
            };

    private final Hit<SearchBusinessResult> HIT = Hit.of(b -> b
            .source(EXPECTED_SEARCH_BUSINESS_RESULT)
            .tDocumentSerializer(toStringSerializer)
    );

    @Override
    public SearchResponse<SearchBusinessResult> of() {
        return SearchResponse.of(s -> s
                .hits(List.of(HIT))
                .total(TOTAL)
                .region(REGION));
    }

    private final SearchResponse<SearchBusinessResult> searchResponse = of();

    @YelpFusionTest
    public void testBuilder() {

        SearchResponse.Builder<SearchBusinessResult> builder = new SearchResponse.Builder<SearchBusinessResult>()
                .region(REGION)
                .hits(HIT)
                .total(TOTAL);

        assertThat(builder.self())
                .isExactlyInstanceOf(SearchResponse.Builder.class);

        assertThat(builder.build().toString())
                .isEqualTo(searchResponse.toString());

        IllegalStateException illegalStateException =
                assertThrows(IllegalStateException.class,
                        () -> builder.self().build());

        assertThat(illegalStateException.getMessage())
                .isEqualTo("Object builders can only be used once");

    }

    @YelpFusionTest
    public void testOf() {
        SearchBusinessResult searchBusinessResult = searchResponse.hits().get(0).source();
        assertThat(searchBusinessResult).isNotNull();
        assertThat(searchBusinessResult).isEqualTo(EXPECTED_SEARCH_BUSINESS_RESULT);
        assertThat(searchResponse.total()).isEqualTo(1);
        assertThat(searchResponse.region()).isEqualTo(REGION);
    }

    private final JsonParser parser = parser(searchResponse);

    private final String expected = "{\"hits\":[{\"source\":\"{\\\"id\\\":\\\"id\\\",\\\"center\\\":{\\\"latitude\\\":37.7829,\\\"longitude\\\":-122.4189},\\\"rating\\\":4.5}\"}],\"total\":1,\"region\":{\"center\":{\"latitude\":37.7829,\"longitude\":-122.4189}}}";

    @YelpFusionTest
    public void testSerialize() {
        JsonGenerator generator = generator();
        searchResponse.serialize(generator, mapper);

        assertThat(searchResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        searchResponse.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchResponse.toString()).isEqualTo(expected);
    }

    private final JsonpDeserializer<SearchResponse<SearchBusinessResult>> searchRespDeser =
            ObjectBuilderDeserializer.createForObject((Supplier<SearchResponse.Builder<SearchBusinessResult>>) SearchResponse.Builder::new,
                    op -> SearchResponse.setupSearchResponseDeserializer(op,
                            new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.searchBusinesses.ResultT")));

    public JsonParser parser() {
        return parser(searchResponse);
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(searchRespDeser.toString()).contains("io.github.stewseo.clients.json.ObjectBuilderDeserializer");

        ObjectDeserializer<SearchResponse.Builder<SearchBusinessResult>> res = 
                new ObjectDeserializer<>(SearchResponse.Builder<SearchBusinessResult>::new);

        JsonpDeserializer<SearchBusinessResult> deser = SearchBusinessResult._DESERIALIZER;

        ResponseBody.setupResponseBodyDeserializer(res, deser);


        assertThat(searchResponse.toString()).isEqualTo(expected);
    }



}