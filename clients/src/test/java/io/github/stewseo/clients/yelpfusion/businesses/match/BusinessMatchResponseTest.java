package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Tag;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@Tag("businesses")
class BusinessMatchResponseTest extends YelpFusionTestCase<MatchBusinessesResponse> {

    private final MatchBusinessesResponse businessMatchResponse = of();

    private final String expected = "{\"businesses\":[{\"id\":\"id\"}]}";

    private final JsonGenerator generator = generator();

    @Override
    public final MatchBusinessesResponse of() {
        return MatchBusinessesResponse.of(b ->
                b.businesses(List.of(BusinessMatch.of(bm -> bm.id("id"))))
        );
    }

    public JsonParser parser() {
        return parser(businessMatchResponse);
    }

    @YelpFusionTest
    public void testSerialize() {
        businessMatchResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(businessMatchResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessMatchResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(businessMatchResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(businessMatchResponse.toString()).isEqualTo("{\"businesses\":[{\"id\":\"id\"}]}");
    }

    @YelpFusionTest
    public void testDeserialize() {
        assertThat(MatchBusinessesResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserializer() {

        JsonParser parser = parser();

        MatchBusinessesResponse businessMatchRes = MatchBusinessesResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(businessMatchRes).isNotNull();
    }

    @YelpFusionTest
    public void testBuilder() {

    }
}