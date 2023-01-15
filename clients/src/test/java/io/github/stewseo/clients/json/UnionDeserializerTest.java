package io.github.stewseo.clients.json;

import io.github.stewseo.clients._type.QueryParameter;
import jakarta.json.stream.JsonParser;

import java.io.StringReader;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TERM;
import static org.assertj.core.api.Assertions.assertThat;


class UnionDeserializerTest implements DeserializeFromJson {

    private final JsonpDeserializer<QueryParameter> propertyJsonpDeserializer =
            new UnionDeserializer.Builder<QueryParameter, QueryParameter.Kind, Object>(QueryParameter::new, false)
            .addMember(QueryParameter.Kind.Term, JsonpDeserializer.stringDeserializer()).build();

    @JsonTest
    void testNativeEvents() {
        assertThat(propertyJsonpDeserializer.nativeEvents().toString()).isEqualTo("[VALUE_STRING]");
    }

    @Override
    public JsonParser parser() {
        return null;
    }
    @JsonTest
    public void testDeserializer() {
        assertThat(propertyJsonpDeserializer.acceptedEvents().toString()).isEqualTo("[VALUE_STRING]");

    }

    @JsonTest
    public void testDeserialize() {


        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("\"field\":\"restaurants\""));

        QueryParameter property = propertyJsonpDeserializer.deserialize(parser, mapper);

        assertThat(property._get()).isEqualTo("field");
        assertThat(property._kind().toString()).isEqualToIgnoringCase(TERM);
    }

    @JsonTest
    void testDeserializeEvent() {

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("\"field\":\"restaurants\""));
        JsonParser.Event event = parser.next();
        QueryParameter queryField = propertyJsonpDeserializer.deserialize(parser, mapper, event);
        assertThat(queryField._get()).isEqualTo("field");
        assertThat(queryField._kind().toString()).isEqualToIgnoringCase(TERM);
    }
}