package io.github.stewseo.clients.util;

import io.github.stewseo.clients.json.testcases.TestJson;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateTimeTest extends TestJson {

    long millis = 1643822172348L;
    String millisJson = "1643822172348";

    String text = "2022-02-02T17:16:12.348Z";
    String textJson = "\"" + text + "\"";

    @Test
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

    @Test
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

    @Test
    public void testInvalidString() {

        // Invalid values should be accepted, and an exception thrown
        // only when we parse the value.
        // This lazy parsing allows application code that doesn't access
        // ill-formed data to not fail on deserialization.
        DateTime dateTime = fromJson("\"foobar\"", DateTime.class);
        assertEquals("foobar", dateTime.getString());

        assertThrows(DateTimeParseException.class, dateTime::toInstant);
    }

    @Test
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

    @Test
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

    @Test
    void ofEpochMilli() {
        DateTime dateTime = DateTime.ofEpochMilli(1L);
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().toFormatter();
        dateTime = DateTime.ofEpochMilli(1L, dateTimeFormatter);
    }

    @Test
    void testOfEpochMilli() {
    }

    @Test
    void of() {
    }

    @Test
    void testOf() {
    }

    @Test
    void testOf1() {
    }

    @Test
    void testOf2() {
    }

    @Test
    void getString() {
    }

    @Test
    void getFormatter() {
    }

    @Test
    void toInstant() {
    }

    @Test
    void testToInstant() {
    }

    @Test
    void toZonedDateTime() {
    }

    @Test
    void testToZonedDateTime() {
    }

    @Test
    void toEpochMilli() {
    }

    @Test
    void testToEpochMilli() {
    }

    @Test
    void serialize() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}