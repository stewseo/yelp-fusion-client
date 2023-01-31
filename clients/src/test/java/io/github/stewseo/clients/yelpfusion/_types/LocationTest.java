package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.testcases.TestJsonp;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationTest extends YelpFusionTestCase<Location> {

    private final Location location = of();

    @Override
    public Location of() {
        return Location.of(l -> l
                .city("san francisco")
                .zip_code("94111")
                .state("ca")
                .address1("address1")
                .address2("address2")
                .address3("address3")
                .county("county")
                .country("country")
                .cross_streets("cross_street")
                .display_address("display_address")
                .display_address(List.of("display_address"))
        );
    }

    private final String expected = "{\"address1\":\"address1\"," +
            "\"address2\":\"address2\"," +
            "\"address3\":\"address3\"," +
            "\"city\":\"san francisco\"," +
            "\"zip_code\":\"94111\"," +
            "\"country\":\"country\"," +
            "\"state\":\"ca\"," +
            "\"display_address\":[\"display_address\",\"display_address\"]}";

    JsonGenerator generator = generator();

    @YelpFusionTest
    public void testSerialize() {

        location.serialize(generator, TestJsonp.mapper);
        assertThat(location.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        location.serializeInternal(generator, TestJsonp.mapper);
        generator.writeEnd().close();
        assertThat(location.toString()).isNotNull();
    }

    @YelpFusionTest
    public void testBuilder() {

        Location.Builder builder = new Location.Builder()
                .city("san francisco")
                .zip_code("94111")
                .state("ca")
                .address1("address1")
                .address2("address2")
                .address3("address3")
                .county("county")
                .country("country")
                .cross_streets("cross_street")
                .display_address("display_address")
                .display_address(List.of("display_address"));

        assertThat(builder.build()).hasNoNullFieldsOrProperties();
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(location.address1()).isNotNull();
        assertThat(location.address2()).isNotNull();
        assertThat(location.address3()).isNotNull();
        assertThat(location.city()).isNotNull();
        assertThat(location.zip_code()).isNotNull();
        assertThat(location.display_address()).isNotNull();
        assertThat(location.cross_street()).isNotNull();
        assertThat(location.state()).isNotNull();
        assertThat(location.country()).isNotNull();
        assertThat(location.county()).isNotNull();

    }

    @YelpFusionTest
    public void testDeserializer() {
        assertThat(Messaging._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserialize() {

        Location location = Location._DESERIALIZER.deserialize(parser(), TestJsonp.mapper);

        assertThat(location.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(location);
    }
}