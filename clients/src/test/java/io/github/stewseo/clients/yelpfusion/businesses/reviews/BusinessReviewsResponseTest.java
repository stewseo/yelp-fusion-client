package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TOTAL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BusinessReviewsResponseTest extends YelpFusionTestCase<BusinessReviewsResponse> {

    private final List<BusinessReview> businessReviews = List.of(BusinessReview.of(br -> br.id(ID)));

    private final String possibleLanguages = "possibleLanguages";

    private final BusinessReviewsResponse businessReviewsResponse = of();

    @Override
    public BusinessReviewsResponse of() {
        return BusinessReviewsResponse.of(b -> b
                .reviews(businessReviews)
                .possible_languages(List.of(possibleLanguages))
                .total(TOTAL)
        );
    }

    private final String expected =
            "{\"reviews\":[{\"id\":\"id\"}],\"possible_languages\":[\"possibleLanguages\"],\"total\":1}";
    @YelpFusionTest
    public void testBuilder() {

        BusinessReviewsResponse.Builder builder = new BusinessReviewsResponse.Builder()
                .reviews(businessReviews)
                .possible_languages(List.of(possibleLanguages))
                .total(TOTAL);

        BusinessReviewsResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessReviewsResponse searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo(expected);
    }

    private final JsonGenerator generator = generator();

    @YelpFusionTest
    public void testOf() {
        assertThat(businessReviewsResponse.reviews()).isEqualTo(businessReviews);
        assertThat(businessReviewsResponse.possible_languages().get(0)).isEqualTo(possibleLanguages);
        assertThat(businessReviewsResponse.total()).isEqualTo(TOTAL);
    }

    @YelpFusionTest
    public void testSerialize() {
        businessReviewsResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(businessReviewsResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessReviewsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(businessReviewsResponse.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(businessReviewsResponse);
    }

    @YelpFusionTest
    public void testDeserialize() {
        assertThat(BusinessReviewsResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserializer() {

        JsonParser parser = parser();

        BusinessReviewsResponse businessMatchRes = BusinessReviewsResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(businessMatchRes.toString()).isEqualTo(expected);
    }
}