package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import jakarta.json.stream.JsonLocation;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParsingException;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static jakarta.json.stream.JsonParser.Event.START_ARRAY;
import static jakarta.json.stream.JsonParser.Event.KEY_NAME;
import static jakarta.json.stream.JsonParser.Event.START_OBJECT;
import static jakarta.json.stream.JsonParser.Event.VALUE_STRING;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DelegatingJsonParserTest extends ModelJsonTestCase {

    private final JsonParser delegatingJsonParser = getDelegatingJsonParser();

    private JsonParser getDelegatingJsonParser() {

        SearchBusinessesResult searchBusinessesResult = SearchBusinessesResult.of(s -> s
                .id("id")
                .alias("alias")
                .url("url")
                .display_phone("display_phone")
                .transactions(List.of("delivery"))
                .review_count(50)
                .is_closed(false)
        );

        InputStream content = IOUtils.toInputStream(searchBusinessesResult.toString(), StandardCharsets.UTF_8);

        JsonParser parser = mapper.jsonProvider().createParser(content);

        final JsonLocation location = parser.getLocation();

        return new DelegatingJsonParser(parser) {
            @Override
            public JsonLocation getLocation() {
                return new JsonLocationImpl(location.getLineNumber(), location.getColumnNumber(), location.getStreamOffset()) {
                    @Override
                    public String toString() {
                        return "(in object at " + super.toString().substring(1);
                    }
                };
            }
        };

    }

    @JsonTest
    void testHasNext() {

       assertThat(delegatingJsonParser.hasNext()).isEqualTo(true);
    }

    @JsonTest
    void testNext() {
        assertThat(delegatingJsonParser.next()).isEqualTo(START_OBJECT);

    }

    @JsonTest
    void testGetString() {
        assertThat(delegatingJsonParser.next()).isEqualTo(START_OBJECT);
        assertThat(delegatingJsonParser.next()).isEqualTo(KEY_NAME);
        assertThat(delegatingJsonParser.getString()).isEqualTo("id");

    }

    @JsonTest
    void testIsIntegralNumber() {
        assertThat(delegatingJsonParser.isIntegralNumber()).isFalse();

    }

    @JsonTest
    void testGetInt() {
        JsonParsingException jsonParsingException = assertThrows(JsonParsingException.class, delegatingJsonParser::getInt);
        assertThat(Arrays.toString(jsonParsingException.getStackTrace()))
                .contains("JacksonJsonpParser.convertException",
                        "JacksonJsonpParser.getInt",
                        "DelegatingJsonParser.getInt"
                );


        while(delegatingJsonParser.next() != JsonParser.Event.VALUE_NUMBER);

        assertThat(delegatingJsonParser.getInt()).isEqualTo(50);
    }

    @JsonTest
    void testGetLong() {
        JsonParsingException jsonParsingException = assertThrows(JsonParsingException.class, delegatingJsonParser::getLong);
        assertThat(Arrays.toString(jsonParsingException.getStackTrace()))
                .contains("JacksonJsonpParser.convertException",
                        "JacksonJsonpParser.getLong",
                        "DelegatingJsonParser.getLong"
                );

        while(delegatingJsonParser.next() != JsonParser.Event.VALUE_NUMBER);

        assertThat(delegatingJsonParser.getLong()).isEqualTo(50L);
    }

    @JsonTest
    void testGetBigDecimal() {
        JsonParsingException jsonParsingException = assertThrows(JsonParsingException.class, delegatingJsonParser::getBigDecimal);
        assertThat(Arrays.toString(jsonParsingException.getStackTrace()))
                .contains("JacksonJsonpParser.convertException",
                        "JacksonJsonpParser.getBigDecimal",
                        "DelegatingJsonParser.getBigDecimal"
                );

        while(delegatingJsonParser.next() != JsonParser.Event.VALUE_NUMBER);

        assertThat(delegatingJsonParser.getBigDecimal().intValue()).isEqualTo(50);
    }

    @JsonTest
    void testGetLocation() {
        assertThat(delegatingJsonParser.getLocation().toString()).isEqualTo("(in object at line no=1, column no=1, offset=0)");

    }

    @JsonTest
    void testGetObject() {
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, delegatingJsonParser::getObject);
        assertThat(illegalStateException.getMessage()).startsWith("Unexpected event 'null' at [Source: (ByteArrayInputStream)");

        while(delegatingJsonParser.next() != START_OBJECT);

        assertThat(delegatingJsonParser.getObject()).isNotNull();
    }

    @JsonTest
    void testGetValue() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, delegatingJsonParser::getValue);
        assertThat(nullPointerException.getMessage()).startsWith("Cannot invoke \"com.fasterxml.jackson.core.JsonToken.ordinal()\" because the return value of \"com.fasterxml.jackson.core.JsonParser.currentToken()\" is null");

        while(delegatingJsonParser.next() != VALUE_STRING);

        assertThat(delegatingJsonParser.getValue()).isNotNull();

    }

    @JsonTest
    void testGetArray() {
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, delegatingJsonParser::getArray);
        assertThat(illegalStateException.getMessage()).startsWith("Unexpected event 'null' at");

        while(delegatingJsonParser.next() != START_ARRAY);

        assertThat(delegatingJsonParser.getArray()).isNotNull();

    }

    @JsonTest
    void testGetArrayStream() {
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, delegatingJsonParser::getArrayStream);
        assertThat(illegalStateException.getMessage()).startsWith("Unexpected event 'null' at");

        while(delegatingJsonParser.next() != START_ARRAY);

        assertThat(delegatingJsonParser.getArrayStream()).isNotNull();

    }

    @JsonTest
    void testGetObjectStream() {
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, delegatingJsonParser::getObjectStream);
        assertThat(illegalStateException.getMessage()).startsWith("Unexpected event 'null' at");

        while(delegatingJsonParser.next() != START_OBJECT);

        assertThat(delegatingJsonParser.getObjectStream()).isNotNull();
    }

    @JsonTest
    void testGetValueStream() {
        UnsupportedOperationException unsupportedOperationException = assertThrows(UnsupportedOperationException.class, delegatingJsonParser::getValueStream);
        assertThat(unsupportedOperationException).isNotNull();
    }

    @JsonTest
    void testSkipArray() {
        while(delegatingJsonParser.next() != START_ARRAY);
        delegatingJsonParser.skipArray();

        assertThat(delegatingJsonParser.next()).isEqualTo(KEY_NAME);
        assertThat(delegatingJsonParser.next()).isEqualTo(VALUE_STRING);
    }

    @JsonTest
    void testSkipObject() {
        assertThat(delegatingJsonParser.next()).isEqualTo(START_OBJECT);
        delegatingJsonParser.skipObject();
        assertThat(delegatingJsonParser.hasNext()).isFalse();
    }

    @JsonTest
    void testClose() {
        delegatingJsonParser.close();
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, delegatingJsonParser::next);
        assertThat(noSuchElementException.getMessage()).isNull();
    }
}