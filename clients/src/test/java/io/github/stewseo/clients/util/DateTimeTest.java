package io.github.stewseo.clients.util;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion._types.Time;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Tag;

import java.io.StringWriter;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Tag("utils")

public class DateTimeTest extends ModelJsonTestCase {

    long millis = 1643822172348L;
    String millisJson = "1643822172348";

    String text = "2022-02-02T17:16:12.348Z";
    String textJson = "\"" + text + "\"";

    private final Time time = new Time.Builder().time("time").build();

    @UtilTest
    public void testMillis() {
        DateTime dateTime;

        dateTime = DateTime.ofEpochMilli(millis);
        assertEquals(millis, dateTime.toInstant().toEpochMilli());

        // Millis, no format
        dateTime = checkJsonRoundtrip(DateTime.ofEpochMilli(millis), millisJson);
        assertEquals(millis, dateTime.toEpochMilli());
        assertNull(dateTime.formatter);

        // Millis, roundtrip through ISO format
        dateTime = checkJsonRoundtrip(DateTime.ofEpochMilli(millis, DateTimeFormatter.ISO_INSTANT), textJson);
        assertEquals(millis, dateTime.toEpochMilli());
        assertNull(dateTime.formatter);
    }

    @UtilTest
    public void testText() {

        DateTime dateTime;

        // Millis as string
        dateTime = fromJson("\"1643822172348\"", DateTime.class);
        assertEquals(millis, dateTime.toEpochMilli());
        assertNull(dateTime.formatter);

        DateTime dateTimeOrig = dateTime;

        // test STRICT_DATE_OPTIONAL_TIME_FORMATTER
        dateTime = fromJson(textJson, DateTime.class);
        assertEquals(text, dateTime.toString());

        assertEquals(dateTime, dateTimeOrig);

        dateTime = fromJson("\"2022-02-27\"", DateTime.class);
        assertEquals("2022-02-27T00:00:00Z", dateTime.toInstant().toString());

        dateTime = fromJson("\"2022-02\"", DateTime.class);
        assertEquals("2022-02-01T00:00:00Z", dateTime.toInstant().toString());

        dateTime = fromJson("\"2022\"", DateTime.class);
        assertEquals("2022-01-01T00:00:00Z", dateTime.toInstant().toString());

        assertEquals("\"2022\"", toJson(dateTime));

    }

    @UtilTest
    public void testInvalidString() {

        // Invalid values should be accepted, and an exception thrown
        // only when we parse the value.
        // This lazy parsing allows application code that doesn't access
        // ill-formed data to not fail on deserialization.
        DateTime dateTime = fromJson("\"foobar\"", DateTime.class);
        assertEquals("foobar", dateTime.getString());

        assertThrows(DateTimeParseException.class, dateTime::toInstant);
    }

    @UtilTest
    public void testInstant() {

        DateTime dateTime;

        dateTime = DateTime.of(text, DateTimeFormatter.ISO_INSTANT);
        assertEquals(millis, dateTime.toInstant().toEpochMilli());

        assertEquals(millis, dateTime.toInstant(DateTimeFormatter.ISO_INSTANT).toEpochMilli());

        Instant inst = Instant.ofEpochMilli(millis);

        dateTime = DateTime.of(inst);
        assertEquals(millisJson, toJson(dateTime));

        dateTime = DateTime.of(inst, DateTimeFormatter.ISO_DATE_TIME);
        assertEquals(textJson, toJson(dateTime));

    }

    @UtilTest
    public void testZonedDateTime() {

        DateTime dateTime;
        ZonedDateTime zdt;

        // With timezone information
        dateTime = fromJson("\"2022-02-02T18:16:12.348+01:00\"", DateTime.class);
        assertEquals(millis, dateTime.toEpochMilli());

        zdt = dateTime.toZonedDateTime();
        assertEquals(3600, zdt.getOffset().getTotalSeconds());

        zdt = dateTime.toZonedDateTime(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        assertEquals(millis, zdt.toInstant().toEpochMilli());

        assertEquals(millis, DateTime.of(millisJson).toZonedDateTime().toInstant().toEpochMilli());

    }

    private final DateTime dateTime = DateTime.of(text, DateTimeFormatter.ISO_INSTANT);

    @UtilTest
    void testGetString() {

        assertThat(dateTime.getString()).isEqualTo("2022-02-02T17:16:12.348Z");
    }

    @UtilTest
    void testGetFormatter() {
        assertThat(dateTime.getFormatter()).isInstanceOf(DateTimeFormatter.class);

    }
    long expectedEpochMilli = 1643822172348L;

    @UtilTest
    void testSerialize() {
        JsonGenerator generator = mapper.jsonProvider().createGenerator(new StringWriter());
        dateTime.serialize(generator, mapper);
        assertThat(dateTime.toString()).isEqualTo("2022-02-02T17:16:12.348Z");
    }

    @UtilTest
    void testEquals() {
        assertThat(dateTime.equals(DateTime.of(text, DateTimeFormatter.ISO_INSTANT))).isTrue();
    }

    @UtilTest
    void testHashCode() {
        assertThat(dateTime.hashCode()).isEqualTo(-2083014308);
    }

    @UtilTest
    void testToString() {
        assertThat(dateTime.toString()).isEqualTo("2022-02-02T17:16:12.348Z");

    }

    @UtilTest
    void toEpochMilli() {
        assertThat(dateTime.toEpochMilli()).isEqualTo(expectedEpochMilli);
    }

    @UtilTest
    void testToEpochMilli() {
        assertThat(dateTime.toEpochMilli(DateTimeFormatter.ISO_DATE_TIME)).isEqualTo(expectedEpochMilli);

    }

}