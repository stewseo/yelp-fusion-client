package io.github.stewseo.clients.yelpfusion.events.search;

import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.TestData.EVENT;
import static io.github.stewseo.clients.yelpfusion._types.TestData.TOTAL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchEventsResponseTest extends ModelTestCase<SearchEventsResponse> {

    private final SearchEventsResponse searchEventsResponse = of();

    private final String expected = "{\"events\":[{\"name\":\"nameValue\"}],\"total\":1}";

    private final JsonGenerator generator = generator();

    @Override
    public SearchEventsResponse of() {
        return SearchEventsResponse.of(fr -> fr
                .total(TOTAL)
                .events(List.of(EVENT))
        );
    }

    @Test
    public void testOf() {
        assertThat(searchEventsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerialize() {
        searchEventsResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(searchEventsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        searchEventsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(searchEventsResponse.toString()).isEqualTo(expected);
    }


    public JsonParser parser() {
        return parser(searchEventsResponse);
    }

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        SearchEventsResponse deserializedSearchEventsResponse =
                SearchEventsResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedSearchEventsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserializer() {

        assertThat(SearchEventsResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }

    @Test
    public void testBuildWithJson() {

    }

    @Test
    public void testBuilder() {

    }
}
