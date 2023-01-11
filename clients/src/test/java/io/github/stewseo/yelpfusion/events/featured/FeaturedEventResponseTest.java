package io.github.stewseo.yelpfusion.events.featured;

import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.events.featured.FeaturedEventResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.QueryParameter;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FeaturedEventResponseTest extends ModelTestCase<FeaturedEventResponse> {

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

    @Test
    public void testOf() {
        assertThat(featuredEventsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testBuilder() {

        FeaturedEventResponse.Builder builder = new FeaturedEventResponse.Builder().event(Event.of(e-> e.name("nameValue")));

        FeaturedEventResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);


        FeaturedEventResponse searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"event\":{\"name\":\"nameValue\"}}");
    }

    @Test
    public void testSerialize() {
        featuredEventsResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(featuredEventsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        featuredEventsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(featuredEventsResponse.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return super.parser(featuredEventsResponse);
    }

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        FeaturedEventResponse deserializedFeaturedEventResponse =
                FeaturedEventResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedFeaturedEventResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserializer() {

        assertThat(FeaturedEventResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }
}
