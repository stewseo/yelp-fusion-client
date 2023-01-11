package io.github.stewseo.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReview;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.FIELD_URL;
import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.QueryParameter.TEXT;
import static io.github.stewseo.clients.yelpfusion._types.TestData.RATING;
import static io.github.stewseo.clients.yelpfusion._types.TestData.TITLE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.USER;
import static org.assertj.core.api.Assertions.assertThat;


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
            "\"url\":\"url\",\"rating\":4.5," +
            "\"time_created\":\"time_created\",\"user\":{\"id\":\"idValue\",\"name\":\"nameValue\"}}";

    @Test
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
    @Test
    public void testBuilder() {

        BusinessReview.Builder builder = ofBuilder();

        BusinessReview.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        assertThat(builder).isNotEqualTo(ofBuilder().self());

        BusinessReview result = builder.build();

        assertThat(result.toString()).isEqualTo(expected);
    }

    JsonGenerator generator = generator();
    @Test
    public void testSerialize() {
        businessReview.serialize(generator, mapper);
        assertThat(businessReview.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessReview.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessReview.toString()).isNotNull();
    }

    @Test
    public void testDeserializer() {
        assertThat(BusinessReview._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserialize() {

        BusinessReview cat = BusinessReview._DESERIALIZER.deserialize(parser(), mapper);

        assertThat(cat.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(businessReview);
    }
}
