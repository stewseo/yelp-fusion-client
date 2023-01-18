package io.github.stewseo.clients.yelpfusion.events.search;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Tag;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.EVENT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TOTAL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("search")
public class SearchEventsResponseTest extends YelpFusionTestCase<SearchEventsResponse> {

    private final SearchEventsResponse searchEventsResponse = of();

    private final String expected = "{\"events\":[{\"name\":\"name\"}],\"total\":1}";

    private final JsonGenerator generator = generator();

    @Override
    public SearchEventsResponse of() {
        return SearchEventsResponse.of(fr -> fr
                .total(TOTAL)
                .events(List.of(EVENT))
        );
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(searchEventsResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testBuilder() {

        SearchEventsResponse.Builder builder = new SearchEventsResponse.Builder().events(Event.of(e-> e.name("nameValue")));

        SearchEventsResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        SearchEventsResponse searchEventsResp = builder.build();

        assertThat(searchEventsResp.toString()).isEqualTo("{\"events\":[{\"name\":\"nameValue\"}]}");
    }
    @YelpFusionTest
    public void testSerialize() {
        searchEventsResponse.serialize(generator, mapper);
        assertThat(searchEventsResponse.toString()).contains(expected);
    }
    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        searchEventsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(searchEventsResponse.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(searchEventsResponse);
    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        SearchEventsResponse deserializedSearchEventsResponse =
                SearchEventsResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedSearchEventsResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(SearchEventsResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }


}
