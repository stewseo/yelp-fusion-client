package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsResponse;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ATTRIBUTES;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LIMIT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.OFFSET;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.OPEN_AT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.OPEN_NOW;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PRICE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.RADIUS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TERM;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestBusinessVars.CITY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.SORT_BY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class SearchBusinessRequestTest extends YelpFusionTestCase<SearchBusinessesRequest>
        implements RequestTestCase<SearchBusinessesRequest> {

    private final SearchBusinessesRequest searchBusinessRequest = of();

    @Override
    public SearchBusinessesRequest of() {
        return SearchBusinessesRequest.of(e -> e
                .locale(LOCALE)
                .sort_by(SORT_BY)
                .location(l -> l
                        .city(CITY))
                .offset(OFFSET)
                .limit(LIMIT)
                .open_now(OPEN_NOW)
                .categories(CATEGORY)
                .center(CENTER)
                .attributes(ATTRIBUTES)
                .limit(LIMIT)
                .term(TERM)
                .radius(RADIUS)
                .open_at(OPEN_AT)
                .price(PRICE)
        );
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(searchBusinessRequest.locale()).isEqualTo(LOCALE);
        assertThat(searchBusinessRequest.sort_by()).isEqualTo(SORT_BY);
        assertThat(searchBusinessRequest.location()).hasFieldOrPropertyWithValue("city", CITY);
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

    private final String expectedStartsWith = "SearchBusinessesRequest: GET v3/businesses/search?";

    @YelpFusionTest
    public void testSerialize() {
        JsonGenerator generator = generator();

        searchBusinessRequest.serialize(generator, mapper);

        assertThat(searchBusinessRequest.toString()).contains(expectedStartsWith);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        searchBusinessRequest.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchBusinessRequest.toString()).startsWith(expectedStartsWith);
    }

    @Override
    public Endpoint<SearchBusinessesRequest, ?, ?> endpoint() {
        return SearchBusinessesRequest._ENDPOINT;
    }

    @YelpFusionTest
    public void testEndpoint() {
        assertThat("v3/businesses/search")
                .isEqualTo(SearchBusinessesRequest._ENDPOINT.requestUrl(searchBusinessRequest));
    }

    @YelpFusionTest
    public void testBuilder() {
        SearchBusinessesRequest.Builder builder =
                new SearchBusinessesRequest.Builder().term("termValue");

        SearchBusinessesRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        SearchBusinessesRequest searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).startsWith(expectedStartsWith);
    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        SearchBusinessesRequest searchBusinessRequest =
                SearchBusinessesRequest._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRequest.toString()).startsWith(expectedStartsWith);
    }

    @YelpFusionTest
    public void testDeserializer() {
        assertThat(BusinessReviewsResponse._DESERIALIZER.toString()).contains("" + "io.github.stewseo.clients.json.LazyDeserializer");
    }

    public JsonParser parser() {
        return parser(searchBusinessRequest);
    }

}