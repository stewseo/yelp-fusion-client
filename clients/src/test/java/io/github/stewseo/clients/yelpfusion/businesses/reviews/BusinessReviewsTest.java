package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Tag;

import static io.github.stewseo.clients.yelpfusion._types.QueryParam.TEXT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.FIELD_URL;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.RATING;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.USER;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("reviews")
public class BusinessReviewsTest extends ModelTestCase<BusinessReview> {

    private final BusinessReview businessReview = of();

    public BusinessReview of() {

        return BusinessReview.of(e -> e
                .id(ID)
                .url(FIELD_URL)
                .text(TEXT.name())
                .time_created("time_created")
                .user(USER)
                .rating(RATING)
        );
    }
    String expected = "{\"id\":\"id\"," +
            "\"text\":\"TEXT\"," +
            "\"url\":\"url\"," +
            "\"rating\":4.5," +
            "\"time_created\":\"time_created\"," +
            "\"user\":{\"id\":\"idValue\",\"name\":\"name\"}}";

    @YelpFusionTest
    public void testOf() {
        assertThat(businessReview.id()).isEqualTo(ID);
        assertThat(businessReview.url()).isEqualTo(FIELD_URL);
        assertThat(businessReview.text()).isEqualTo(TEXT.name());
        assertThat(businessReview.user()).isEqualTo(USER);
        assertThat(businessReview.rating()).isEqualTo(RATING);
    }
    private BusinessReview.Builder ofBuilder() {
        return new BusinessReview.Builder()
                .id(ID)
                .url(FIELD_URL)
                .text(TEXT.name())
                .time_created("time_created")
                .user(USER)
                .rating(RATING);
    }
    @YelpFusionTest
    public void testBuilder() {

        BusinessReview.Builder builder = ofBuilder();

        BusinessReview.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        assertThat(builder).isNotEqualTo(ofBuilder().self());

        BusinessReview result = builder.build();

        assertThat(result.toString()).isEqualTo(expected);
    }

    JsonGenerator generator = generator();
    @YelpFusionTest
    public void testSerialize() {
        businessReview.serialize(generator, mapper);
        assertThat(businessReview.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessReview.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessReview.toString()).isNotNull();
    }

    @YelpFusionTest
    public void testDeserializer() {
        assertThat(BusinessReview._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserialize() {

        BusinessReview cat = BusinessReview._DESERIALIZER.deserialize(parser(), mapper);

        assertThat(cat.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(businessReview);
    }
}
