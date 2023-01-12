package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.ATTRIBUTES;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LIMIT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.OFFSET;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.OPEN_AT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.OPEN_NOW;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.RADIUS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.SORT_BY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.TERM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchBusinessRequestTest extends ModelTestCase<SearchBusinessRequest>
        implements RequestTestCase<SearchBusinessRequest> {

    private final String PRICE = "price";

    public static final List<String> LOCATIONS = List.of("location"), TERMS = List.of("term");

    private final SearchBusinessRequest searchBusinessRequest = of();

    @Override
    public SearchBusinessRequest of() {
        return SearchBusinessRequest.of(e -> e
                .locale(LOCALE)
                .sort_by(SORT_BY)
                .location(LOCATIONS)
                .offset(OFFSET)
                .limit(LIMIT)
                .open_now(OPEN_NOW)
                .categories(CATEGORY)
                .center(CENTER)
                .attributes(ATTRIBUTES)
                .limit(LIMIT)
                .term(TERMS)
                .radius(RADIUS)
                .open_at(OPEN_AT)
                .price(PRICE)
        );
    }

    @Test
    public void testOf() {
        assertThat(searchBusinessRequest.locale()).isEqualTo(LOCALE);
        assertThat(searchBusinessRequest.sort_by()).isEqualTo(SORT_BY);
        assertThat(searchBusinessRequest.location()).isEqualTo(LOCATIONS);
        assertThat(searchBusinessRequest.offset()).isEqualTo(OFFSET);
        assertThat(searchBusinessRequest.limit()).isEqualTo(LIMIT);
        assertThat(searchBusinessRequest.categories()).isEqualTo(CATEGORY);
        assertThat(searchBusinessRequest.center()).isEqualTo(CENTER);
        assertThat(searchBusinessRequest.limit()).isEqualTo(LIMIT);
        assertThat(searchBusinessRequest.radius()).isEqualTo(RADIUS);
        assertThat(searchBusinessRequest.open_at()).isEqualTo(OPEN_AT);
        assertThat(searchBusinessRequest.price()).isEqualTo(PRICE);
        assertThat(searchBusinessRequest.limit()).isEqualTo(LIMIT);
        assertThat(searchBusinessRequest.radius()).isEqualTo(RADIUS);
        assertThat(searchBusinessRequest.term().get(0)).isEqualTo(TERM);
        assertThat(searchBusinessRequest.open_now()).isEqualTo(OPEN_NOW);
        assertThat(searchBusinessRequest.attributes()).isEqualTo(ATTRIBUTES);
    }

    private final String expected = "{\"term\":[\"term\"],\"location\":[\"location\"],\"categories\":{\"alias\":\"catAlias\"},\"center\":{\"latitude\":37.7829,\"longitude\":-122.4189},\"radius\":20000,\"offset\":5,\"sort_by\":\"sort_by\",\"limit\":50,\"open_at\":1,\"price\":\"price\",\"attributes\":[{\"attribute\":\"attribute\"}]}";
    @Test
    public void testSerialize() {
        JsonGenerator generator = generator();

        searchBusinessRequest.serialize(generator, mapper);

        assertThat(searchBusinessRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        searchBusinessRequest.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchBusinessRequest.toString()).isEqualTo(expected);
    }

    @Override
    public Endpoint<SearchBusinessRequest, ?, ?> endpoint() {
        return SearchBusinessRequest._ENDPOINT;
    }

    @Test
    public void testEndpoint() {
        assertThat("v3/businesses/search")
                .isEqualTo(SearchBusinessRequest._ENDPOINT.requestUrl(searchBusinessRequest));
    }

    @Test
    public void testBuilder() {
        SearchBusinessRequest.Builder builder = new SearchBusinessRequest.Builder().term("termValue");

        SearchBusinessRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        SearchBusinessRequest searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"term\":[\"termValue\"]}");
    }

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        SearchBusinessRequest searchBusinessRequest = SearchBusinessRequest._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserializer() {
        assertThat(BusinessReviewsResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");
    }

    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(searchBusinessRequest.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

}