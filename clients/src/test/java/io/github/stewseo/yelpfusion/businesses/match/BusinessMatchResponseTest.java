package io.github.stewseo.yelpfusion.businesses.match;

import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatch;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BusinessMatchResponseTest extends ModelTestCase<BusinessMatchResponse> {

    private final BusinessMatchResponse businessMatchResponse = of();

    private final String expected = "{\"businesses\":[{\"id\":\"id\"}]}";

    private final JsonGenerator generator = generator();

    @Override
    public final BusinessMatchResponse of() {
        return BusinessMatchResponse.of(b ->
                b.businesses(List.of(BusinessMatch.of(bm -> bm.id("id"))))
        );
    }

    public JsonParser parser() {
        return parser(businessMatchResponse);
    }

    @Test
    public void testSerialize() {
        businessMatchResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(businessMatchResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessMatchResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(businessMatchResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testOf() {
        assertThat(businessMatchResponse.toString()).isEqualTo("{\"businesses\":[{\"id\":\"id\"}]}");
    }

    @Test
    public void testDeserialize() {
        assertThat(BusinessMatchResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserializer() {

        JsonParser parser = parser();

        BusinessMatchResponse businessMatchRes = BusinessMatchResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(businessMatchRes).isNotNull();
    }

    @Test
    public void testBuilder() {

    }
}