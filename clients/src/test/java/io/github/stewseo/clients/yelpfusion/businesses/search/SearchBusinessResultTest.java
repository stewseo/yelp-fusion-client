package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.yelpfusion.testcases.ResultTestCase;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchBusinessResultTest implements ResultTestCase {

    private final SearchBusinessResult searchBusinessResult = SearchBusinessResult.of(e -> e
            .distance(distance)
            .id(id)
            .is_closed(is_closed)
            .review_count(review_count)
            .categories(categories)
            .location(location)
            .coordinates(coordinates)
            .image_url(image_url)
            .phone(phone)
            .name(name)
            .price(price)
            .rating(rating)
            .transactions(transactions)
    );

    @Test
    public void testOf() {
        assertThat(searchBusinessResult.distance()).isEqualTo(distance);
        assertThat(searchBusinessResult.id()).isEqualTo(id);
        assertThat(searchBusinessResult.is_closed()).isEqualTo(is_closed);
        assertThat(searchBusinessResult.location()).isEqualTo(location);
        assertThat(searchBusinessResult.categories()).isEqualTo(categories);
        assertThat(searchBusinessResult.coordinates()).isEqualTo(coordinates);
        assertThat(searchBusinessResult.phone()).isEqualTo(phone);
        assertThat(searchBusinessResult.image_url()).isEqualTo(image_url);
        assertThat(searchBusinessResult.phone()).isEqualTo(phone);
        assertThat(searchBusinessResult.price()).isEqualTo(price);
        assertThat(searchBusinessResult.name()).isEqualTo(name);
        assertThat(searchBusinessResult.rating()).isEqualTo(rating);
        assertThat(searchBusinessResult.transactions()).isEqualTo(transactions);
        assertThat(searchBusinessResult.categories()).isEqualTo(categories);
    }
    private final String expected = "{" +
            "\"id\":\"id\"," +
            "\"name\":\"name\"," +
            "\"image_url\":\"image_url\"," +
            "\"phone\":\"phone\"," +
            "\"price\":\"price\"," +
            "\"is_closed\":true," +
            "\"distance\":1.0," +
            "\"rating\":3.5," +
            "\"review_count\":5," +
            "\"transactions\":[\"transactions\"]," +
            "\"location\":{\"city\":\"San Francisco\"}," +
            "\"coordinates\":{\"latitude\":37.0,\"longitude\":-122.0}," +
            "\"categories\":[{\"alias\":\"categories\"}]}";

    @Test
    public void testSerialize() {

        searchBusinessResult.serialize(generator, mapper);

        assertThat(searchBusinessResult.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {

        generator.writeStartObject();
        searchBusinessResult.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchBusinessResult.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserializer() {
        assertThat(SearchBusinessResult._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserialize() {

        InputStream content = IOUtils.toInputStream(searchBusinessResult.toString(), StandardCharsets.UTF_8);

        JsonParser parser = mapper.jsonProvider().createParser(content);

        SearchBusinessResult searchBusinessRes = SearchBusinessResult._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes.toString()).isEqualTo(expected);
    }

}