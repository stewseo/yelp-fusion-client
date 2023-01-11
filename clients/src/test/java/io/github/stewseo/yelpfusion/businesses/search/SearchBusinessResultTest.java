package io.github.stewseo.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.SerializeToJson;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static io.github.stewseo.clients.yelpfusion._types.TestData.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.TestData.COORDINATES;
import static io.github.stewseo.clients.yelpfusion._types.TestData.DISTANCE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.QueryParameter;
import static io.github.stewseo.clients.yelpfusion._types.TestData.IMAGE_URL;
import static io.github.stewseo.clients.yelpfusion._types.TestData.IS_CLOSED;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCATION;
import static io.github.stewseo.clients.yelpfusion._types.TestData.NAME;
import static io.github.stewseo.clients.yelpfusion._types.TestData.PHONE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.PRICE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.RATING;
import static io.github.stewseo.clients.yelpfusion._types.TestData.REVIEW_COUNT;
import static io.github.stewseo.clients.yelpfusion._types.TestData.TRANSACTIONS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchBusinessResultTest implements SerializeToJson, DeserializeFromJson {

    private final SearchBusinessResult searchBusinessResult = SearchBusinessResult.of(e -> e
            .distance(DISTANCE)
            .id(ID)
            .is_closed(IS_CLOSED)
            .review_count(REVIEW_COUNT)
            .categories(CATEGORY)
            .location(LOCATION)
            .coordinates(COORDINATES)
            .image_url(IMAGE_URL)
            .phone(PHONE)
            .name(NAME)
            .price(String.valueOf(PRICE))
            .rating(RATING)
            .transactions(TRANSACTIONS)

    );

    private final String expected = "" +
            "{\"id\":\"id\"," +
            "\"name\":\"name\"," +
            "\"image_url\":\"imageUrlValue\"," +
            "\"phone\":\"phoneValue\"," +
            "\"price\":\"3\",\"is_closed\":false,\"distance\":1.0,\"rating\":4.5,\"review_count\":1,\"transactions\":[\"transactionValue\"],\"location\":{\"address1\":\"addressOneValue\",\"city\":\"cityValue\",\"country\":\"countryValue\",\"state\":\"stateValue\"},\"coordinates\":{\"latitude\":37.7829,\"longitude\":-122.4189}," +
            "\"categories\":[{\"alias\":\"catAlias\"}]}";

    @Test
    public void testOf() {
        assertThat(searchBusinessResult.distance()).isEqualTo(DISTANCE);
        assertThat(searchBusinessResult.id()).isEqualTo(ID);
        assertThat(searchBusinessResult.is_closed()).isEqualTo(IS_CLOSED);
        assertThat(searchBusinessResult.location()).isEqualTo(LOCATION);
        assertThat(searchBusinessResult.categories().get(0)).isEqualTo(CATEGORY);
        assertThat(searchBusinessResult.coordinates()).isEqualTo(COORDINATES);
        assertThat(searchBusinessResult.phone()).isEqualTo(PHONE);
        assertThat(searchBusinessResult.image_url()).isEqualTo(IMAGE_URL);
        assertThat(searchBusinessResult.phone()).isEqualTo(PHONE);
        assertThat(searchBusinessResult.price()).isEqualTo(String.valueOf(PRICE));
        assertThat(searchBusinessResult.name()).isEqualTo(NAME);
        assertThat(searchBusinessResult.rating()).isEqualTo(RATING);
        assertThat(searchBusinessResult.transactions()).isEqualTo(TRANSACTIONS);
    }

    @Test
    public void testSerialize() {
        JsonGenerator generator = generator();
        searchBusinessResult.serialize(generator, mapper);

        assertThat(searchBusinessResult.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
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

        JsonParser parser = parser();

        SearchBusinessResult searchBusinessRes = SearchBusinessResult._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes.toString()).isEqualTo(expected);
    }

    @Override
    public JsonGenerator generator() {
        return mapper.jsonProvider().createGenerator(new StringWriter());
    }

    @Override
    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(searchBusinessResult.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }
}