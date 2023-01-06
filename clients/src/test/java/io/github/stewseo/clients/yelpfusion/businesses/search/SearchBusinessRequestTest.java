package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import io.github.stewseo.clients.yelpfusion._types.Attribute;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchBusinessRequestTest implements RequestTestCase<SearchBusinessRequest> {

    private final String price = "price";

    private final int open_at = 1;

    private final boolean open_now = true;

    private final Coordinates coordinates = Coordinates.of(c -> c
            .latitude(latitude)
            .longitude(longitude));

    private final Category category = Category.of(cat -> cat.alias("alias"));
    private final List<Attribute> attributes = List.of(Attribute.of(a -> a.attribute("attribute")));

    private final List<String> location = List.of("location"), term = List.of("term");


    private final SearchBusinessRequest searchBusinessRequest = SearchBusinessRequest.of(e -> e
            .locale(locale)
            .sort_by(sort_by)
            .location(location)
            .offset(offset)
            .limit(limit)
            .open_now(open_now)
            .categories(category)
            .coordinates(coordinates)
            .attributes(attributes)
            .limit(limit)
            .term(term)
            .radius(radius)
            .open_at(open_at)
            .price(price)
    );

    @Override
    public Endpoint<SearchBusinessRequest, ?, ?> endpoint() {
        return SearchBusinessRequest._ENDPOINT;
    }
    @Test
    public void testOf() {
        assertThat(searchBusinessRequest.locale()).isEqualTo(locale);
        assertThat(searchBusinessRequest.sort_by()).isEqualTo(sort_by);
        assertThat(searchBusinessRequest.location()).isEqualTo(location);
        assertThat(searchBusinessRequest.location()).isEqualTo(location);
        assertThat(searchBusinessRequest.offset()).isEqualTo(offset);
        assertThat(searchBusinessRequest.limit()).isEqualTo(limit);
        assertThat(searchBusinessRequest.categories()).isEqualTo(category);
        assertThat(searchBusinessRequest.coordinates()).isEqualTo(coordinates);
        assertThat(searchBusinessRequest.limit()).isEqualTo(limit);
        assertThat(searchBusinessRequest.radius()).isEqualTo(radius);
        assertThat(searchBusinessRequest.open_at()).isEqualTo(open_at);
        assertThat(searchBusinessRequest.price()).isEqualTo(price);
        assertThat(searchBusinessRequest.limit()).isEqualTo(limit);
        assertThat(searchBusinessRequest.radius()).isEqualTo(radius);
        assertThat(searchBusinessRequest.term()).isEqualTo(term);
        assertThat(searchBusinessRequest.attributes()).isEqualTo(attributes);
    }

    private final String expected = "{" +
            "\"term\":[\"term\"]," +
            "\"location\":[\"location\"]," +
            "\"categories\":{\"alias\":\"alias\"},\"coordinates\":{\"latitude\":44.0,\"longitude\":-122.0},\"radius\":20000," +
            "\"offset\":5," +
            "\"sort_by\":\"sort_by\"," +
            "\"limit\":50,\"open_at\":1," +
            "\"price\":\"price\"," +
            "\"attributes\":[{\"attributes\":\"attribute\"}]}";

    @Test
    public void testSerialize() {
        assertThat(searchBusinessRequest.toString()).isEqualTo(expected);
        searchBusinessRequest.serialize(generator, mapper);

        assertThat(searchBusinessRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {

        generator.writeStartObject();
        searchBusinessRequest.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchBusinessRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testEndpoint() {
        assertThat("v3/businesses/search")
                .isEqualTo(SearchBusinessRequest._ENDPOINT.requestUrl(searchBusinessRequest));
    }
}