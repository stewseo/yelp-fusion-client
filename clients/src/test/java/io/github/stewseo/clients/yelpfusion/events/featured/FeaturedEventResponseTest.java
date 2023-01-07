package io.github.stewseo.clients.yelpfusion.events.featured;

import io.github.stewseo.clients.json.LazyDeserializer;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchResponse;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionResponseTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FeaturedEventResponseTest extends YelpFusionResponseTestCase<FeaturedEventResponse> {

    private final FeaturedEventResponse featuredEventsResponse = of();

    private final String expected = "{\"event\":{\"name\":\"featuredEvent\"},\"business_id\":\"id\"}";

    private final JsonGenerator generator = generator();

    @Override
    public FeaturedEventResponse of() {
        return FeaturedEventResponse.of(fr -> fr
                .business_id(id)
                .event(Event.of(e -> e
                        .name("featuredEvent")))
        );
    }

    @Test
    public void testOf() {
        assertThat(featuredEventsResponse.toString()).isEqualTo(expected);
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

    @Override
    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(featuredEventsResponse.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
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
