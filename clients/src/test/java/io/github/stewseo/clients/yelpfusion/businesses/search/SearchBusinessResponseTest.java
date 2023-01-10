package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReview;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.REGION;
import static io.github.stewseo.clients.yelpfusion._types.TestData.SEARCH_BUSINESS_RESULT;
import static io.github.stewseo.clients.yelpfusion._types.TestData.TOTAL;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchBusinessResponseTest extends ModelTestCase<SearchBusinessResponse> {
    
    @Override
    public SearchBusinessResponse of() {
        return SearchBusinessResponse.of(b -> b
                .businesses(SearchBusinessResult.of(s -> s
                        .id(ID))
                )
                .total(TOTAL)
                .businesses(SEARCH_BUSINESS_RESULT)
                .region(REGION)
        );
    }

    private final SearchBusinessResponse searchBusinessResponse = of();


    @Test
    public void testBuilder() {

        SearchBusinessResponse.Builder builder = new SearchBusinessResponse.Builder().businesses(SearchBusinessResult.of(s -> s
                .id(ID)))
                .total(TOTAL)
                .businesses(SEARCH_BUSINESS_RESULT)
                .region(REGION);

        SearchBusinessResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        SearchBusinessResponse response = builder.build();

        assertThat(response.toString()).isEqualTo(expected);
    }

    private final String expected =
            "{\"businesses\":[{\"id\":\"id\"},{\"id\":\"id\"}],\"total\":1,\"region\":{\"center\":{\"longitude\":-122.4189,\"latitude\":37.7829}}}";

    private final JsonGenerator generator = generator();

    @Test
    public void testOf() {
        AssertionsForClassTypes.assertThat(searchBusinessResponse.businesses().toString())
                .isEqualTo("[{\"id\":\"id\"}, {\"id\":\"id\"}]");
        AssertionsForClassTypes.assertThat(searchBusinessResponse.region()).isEqualTo(REGION);
        AssertionsForClassTypes.assertThat(searchBusinessResponse.total()).isEqualTo(TOTAL);
    }

    @Test
    public void testSerialize() {
        searchBusinessResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(searchBusinessResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        searchBusinessResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(searchBusinessResponse.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(searchBusinessResponse);
    }

    @Test
    public void testDeserialize() {
        AssertionsForClassTypes.assertThat(SearchBusinessResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserializer() {

        JsonParser parser = parser();

        SearchBusinessResponse businessMatchRes = SearchBusinessResponse._DESERIALIZER.deserialize(parser, mapper);

        AssertionsForClassTypes.assertThat(businessMatchRes.toString()).isEqualTo(expected);
    }
}
