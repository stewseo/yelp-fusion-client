package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionResponseTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BusinessReviewsResponseTest extends YelpFusionResponseTestCase<BusinessReviewsResponse> {

    private final List<BusinessReview> businessReview = List.of(BusinessReview.of(br -> br.id("idValue")));

    private final String possibleLanguages = "possibleLanguages";

    private final BusinessReviewsResponse businessReviewsResponse = of();

    @Override
    public BusinessReviewsResponse of() {
        return BusinessReviewsResponse.of(b -> b
                .reviews(businessReview)
                .possible_languages(List.of(possibleLanguages))
                .total(total)
        );
    }

    private final String expected =
            "{\"reviews\":[{\"id\":\"idValue\"}],\"possible_languages\":[\"possibleLanguages\"],\"total\":1}";

    private final JsonGenerator generator = generator();

    @Test
    public void testOf() {
        assertThat(businessReviewsResponse.reviews()).isEqualTo(businessReview);
        assertThat(businessReviewsResponse.possible_languages().get(0)).isEqualTo(possibleLanguages);
        assertThat(businessReviewsResponse.total()).isEqualTo(total);
    }

    @Test
    public void testSerialize() {
        businessReviewsResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(businessReviewsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessReviewsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(businessReviewsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserialize() {
        assertThat(BusinessReviewsResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserializer() {

        JsonParser parser = parser();

        BusinessReviewsResponse businessMatchRes = BusinessReviewsResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(businessMatchRes.toString()).isEqualTo(expected);
    }

    @Override
    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(businessReviewsResponse.toString(), StandardCharsets.UTF_8);
        return jsonProvider().createParser(content);
    }

}