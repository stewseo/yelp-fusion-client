package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion._types.LocationQueryParameter;
import io.github.stewseo.clients.yelpfusion._types.QueryParameter;
import io.github.stewseo.clients.yelpfusion._types.TermQueryParameter;
import jakarta.json.stream.JsonParser;

import java.io.StringReader;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TERM;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TERM_QUERY_PARAM;
import static org.assertj.core.api.Assertions.assertThat;


class UnionDeserializerTest extends ModelJsonTestCase {

    private final JsonpDeserializer<QueryParameter> propertyJsonpDeserializer =
            new UnionDeserializer.Builder<QueryParameter, QueryParameter.Kind, Object>(QueryParameter::new, false)
            .addMember(QueryParameter.Kind.Term, JsonpDeserializer.stringDeserializer()).build();



    @JsonTest
    void testNativeEvents() {

        JsonpDeserializer<QueryParameter> unionDeserializer =
                new UnionDeserializer.Builder<QueryParameter, QueryParameter.Kind, Object>(QueryParameter::new, false)
                        .addMember(QueryParameter.Kind.Term, TermQueryParameter._DESERIALIZER)
                        .addMember(QueryParameter.Kind.Location, LocationQueryParameter._DESERIALIZER).build();


        assertThat(unionDeserializer.nativeEvents().toString()).isEqualTo("[START_OBJECT]");

        QueryParameter qp = unionDeserializer.deserialize(parser(), mapper);
        assertThat(qp.toString()).isEqualTo("{\"term\":{\"term\":\"restaurants\"}}");

        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
                () -> unionDeserializer.deserialize(parser(), mapper, JsonParser.Event.START_OBJECT));

        assertThat(illegalStateException.getMessage()).startsWith("Unexpected event 'null' at [Source: (ByteArrayInputStream)");


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

    public JsonParser parser() {
        return parser(TERM_QUERY_PARAM);
    }
}