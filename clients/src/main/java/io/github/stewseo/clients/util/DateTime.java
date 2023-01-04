package io.github.stewseo.clients.util;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpDeserializerBase;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.EnumSet;
import java.util.Locale;

@JsonpDeserializable
public class DateTime implements JsonpSerializable {

    public static final JsonpDeserializer<DateTime> _DESERIALIZER = new TimestampDeserializer();
    // Visible for testing
    @Nullable
    final DateTimeFormatter formatter;
    private final long millis;
    @Nullable
    private final String str;

    private DateTime(long epochMillis, String str, DateTimeFormatter format) {
        this.millis = epochMillis;
        this.str = str;
        this.formatter = format;
    }

    public static DateTime ofEpochMilli(long epochMilli, DateTimeFormatter format) {
        return new DateTime(epochMilli, null, format);
    }

    public static DateTime ofEpochMilli(long epochMilli) {
        return ofEpochMilli(epochMilli, null);
    }

    public static DateTime of(String text) {
        return of(text, null);
    }

    public static DateTime of(String text, DateTimeFormatter parser) {
        return new DateTime(0, text, parser);
    }

    public static DateTime of(Temporal instant) {
        return of(instant, null);
    }

    public static DateTime of(Temporal instant, DateTimeFormatter format) {
        long millis = instant.getLong(ChronoField.INSTANT_SECONDS) * 1000L + instant.getLong(ChronoField.MILLI_OF_SECOND);
        return new DateTime(millis, null, format);
    }

    @Nullable
    public String getString() {
        return str;
    }

    @Nullable
    DateTimeFormatter getFormatter() {
        return formatter;
    }

    public Instant toInstant() {
        if (str == null) {
            return Instant.ofEpochMilli(millis);
        } else {
            if (formatter == null) {
                try {
                    ZonedDateTime zdt = DateTimeUtil.from(
                            DateTimeUtil.STRICT_DATE_OPTIONAL_TIME_FORMATTER.parse(str),
                            Locale.ROOT,
                            ZoneOffset.UTC
                    );
                    return zdt.toInstant();
                } catch (DateTimeParseException dtpe) {
                    // Ignore
                }

                // Try milliseconds as string
                try {
                    long ms = Long.parseLong(str);
                    return Instant.ofEpochMilli(ms);
                } catch (NumberFormatException nfe) {
                    throw new DateTimeParseException(
                            "Cannot parse date-time with format [strict_date_optional_time||epoch_millis]", str, 0
                    );
                }
            } else {
                return formatter.parse(str, Instant::from);
            }
        }
    }

    public Instant toInstant(DateTimeFormatter parser) {
        if (str == null) {
            return Instant.ofEpochMilli(millis);
        } else {
            return parser.parse(str, Instant::from);
        }
    }

    public ZonedDateTime toZonedDateTime() {
        if (str == null) {
            return ZonedDateTime.ofInstant(toInstant(), ZoneOffset.UTC);
        } else {
            try {
                return DateTimeUtil.from(
                        DateTimeUtil.STRICT_DATE_OPTIONAL_TIME_FORMATTER.parse(str),
                        Locale.ROOT,
                        ZoneOffset.UTC
                );
            } catch (DateTimeParseException dtpe) {
                // Ignore
            }

            // Try milliseconds as string
            try {
                long ms = Long.parseLong(str);
                return ZonedDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneOffset.UTC);
            } catch (NumberFormatException nfe) {
                throw new DateTimeParseException("Cannot parse date-time with format [strict_date_optional_time||epoch_millis]", str, 0);
            }
        }
    }

    public ZonedDateTime toZonedDateTime(DateTimeFormatter parser) {
        if (str == null) {
            return ZonedDateTime.ofInstant(toInstant(), ZoneOffset.UTC);
        } else {
            return parser.parse(str, ZonedDateTime::from);
        }
    }

    public long toEpochMilli() {
        if (str == null) {
            return millis;
        } else {
            return toInstant().toEpochMilli();
        }
    }

    public long toEpochMilli(DateTimeFormatter format) {
        if (str == null) {
            return millis;
        } else {
            return toInstant(format).toEpochMilli();
        }
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        if (str == null) {
            if (formatter == null) {
                generator.write(millis);
            } else {
                generator.write(formatter.format(Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC)));
            }
        } else {
            generator.write(str);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.toInstant().equals(((DateTime) o).toInstant());
    }

    @Override
    public int hashCode() {
        return toInstant().hashCode();
    }

    @Override
    public String toString() {
        if (str == null) {
            if (formatter == null) {
                return String.valueOf(millis);
            } else {
                return formatter.format(Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC));
            }
        } else {
            return str;
        }
    }

    private static class TimestampDeserializer extends JsonpDeserializerBase<DateTime> {
        TimestampDeserializer() {
            super(EnumSet.of(JsonParser.Event.VALUE_NUMBER, JsonParser.Event.VALUE_STRING));
        }

        @Override
        public DateTime deserialize(JsonParser parser, JsonpMapper mapper, JsonParser.Event event) {
            if (event == JsonParser.Event.VALUE_NUMBER) {
                return DateTime.ofEpochMilli(parser.getLong());
            } else {
                return DateTime.of(parser.getString());
            }
        }
    }
}