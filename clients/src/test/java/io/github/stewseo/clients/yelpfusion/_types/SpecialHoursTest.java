package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.HOURS_END;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.HOURS_START;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.RequestConstants.IS_CLOSED;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.RequestConstants.IS_OVERNIGHT;
import static org.assertj.core.api.Assertions.assertThat;


class SpecialHoursTest extends YelpFusionTestCase<SpecialHours> {

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

    @YelpFusionTest
    public void testBuilder() {

        SpecialHours.Builder builder = new SpecialHours.Builder().date(date);

        SpecialHours.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        SpecialHours specialHours = builder.build();

        assertThat(specialHours.toString()).isEqualTo("{\"date\":\"1/6/2023\"}");
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(specialHours.date()).isEqualTo(date);
        assertThat(specialHours.start()).isEqualTo(HOURS_START);
        assertThat(specialHours.end()).isEqualTo(HOURS_END);
        assertThat(specialHours.is_closed()).isEqualTo(IS_CLOSED);
        assertThat(specialHours.is_overnight()).isEqualTo(IS_OVERNIGHT);

    }

    private final String expected = "{\"date\":\"1/6/2023\",\"is_closed\":false,\"start\":\"0800\",\"end\":\"1700\",\"is_overnight\":false}";
    JsonGenerator generator = generator();
    @YelpFusionTest
    public void testSerialize() {

        specialHours.serialize(generator, mapper);
        assertThat(specialHours.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        specialHours.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(specialHours.toString()).isNotNull();
    }

    @YelpFusionTest
    public void testDeserializer() {
        assertThat(SpecialHours._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserialize() {

        SpecialHours searchBusinessRes = SpecialHours._DESERIALIZER.deserialize(parser(), mapper);

        assertThat(searchBusinessRes.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(specialHours);
    }
}