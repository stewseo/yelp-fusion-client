package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.HOURS_END;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.HOURS_START;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.IS_CLOSED;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.IS_OVERNIGHT;
import static org.assertj.core.api.Assertions.assertThat;

class SpecialHoursTest extends ModelTestCase<SpecialHours> {

    private final String date = "1/6/2023";

    private final SpecialHours specialHours = of();
    @Override
    public SpecialHours of() {
        return SpecialHours.of(s -> s
                .date(date)
                .start(HOURS_START)
                .end(HOURS_END)
                .is_closed(IS_CLOSED)
                .is_overnight(IS_OVERNIGHT));
    }

    @Test
    public void testBuilder() {

        SpecialHours.Builder builder = new SpecialHours.Builder().date(date);

        SpecialHours.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        SpecialHours specialHours = builder.build();

        assertThat(specialHours.toString()).isEqualTo("{\"date\":\"1/6/2023\"}");
    }

    @Test
    public void testOf() {
        assertThat(specialHours.date()).isEqualTo(date);
        assertThat(specialHours.start()).isEqualTo(HOURS_START);
        assertThat(specialHours.end()).isEqualTo(HOURS_END);
        assertThat(specialHours.is_closed()).isEqualTo(IS_CLOSED);
        assertThat(specialHours.is_overnight()).isEqualTo(IS_OVERNIGHT);

    }

    private final String expected = "{\"date\":\"1/6/2023\",\"is_closed\":false,\"start\":\"0800\",\"end\":\"1700\",\"is_overnight\":false}";
    JsonGenerator generator = generator();
    @Test
    public void testSerialize() {

        specialHours.serialize(generator, mapper);
        assertThat(specialHours.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        specialHours.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(specialHours.toString()).isNotNull();
    }

    @Test
    public void testDeserializer() {
        assertThat(SpecialHours._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserialize() {

        SpecialHours searchBusinessRes = SpecialHours._DESERIALIZER.deserialize(parser(), mapper);

        assertThat(searchBusinessRes.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(specialHours);
    }
}