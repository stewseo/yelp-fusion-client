package io.github.stewseo.clients.yelpfusion.events.featured;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Tag;


import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Tag("events")
@Tag("search")
public class FeaturedEventResponseTest extends YelpFusionTestCase<FeaturedEventResponse> {

    private final FeaturedEventResponse featuredEventsResponse = of();

    private final String expected = "{\"event\":{\"name\":\"featured\"},\"business_id\":\"id\"}";

    private final JsonGenerator generator = generator();

    @Override
    public FeaturedEventResponse of() {
        return FeaturedEventResponse.of(fr -> fr
                .business_id(ID)
                .event(Event.of(e -> e
                        .name("featured")))
        );
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(featuredEventsResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testBuilder() {

        FeaturedEventResponse.Builder builder = new FeaturedEventResponse.Builder().event(Event.of(e-> e.name("nameValue")));

        FeaturedEventResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);


        FeaturedEventResponse searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"event\":{\"name\":\"nameValue\"}}");
    }

    @YelpFusionTest
    public void testSerialize() {
        featuredEventsResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(featuredEventsResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        featuredEventsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(featuredEventsResponse.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return super.parser(featuredEventsResponse);
    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        FeaturedEventResponse deserializedFeaturedEventResponse =
                FeaturedEventResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedFeaturedEventResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(FeaturedEventResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }
}
