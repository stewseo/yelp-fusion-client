package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ATTRIBUTES;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CITY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LIMIT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.OFFSET;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.OPEN_AT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.OPEN_NOW;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PRICE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.RADIUS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.SORT_BY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TERM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchBusinessesRequestBaseTest extends ModelTestCase<SearchBusinessesRequestBase> {

    private final SearchBusinessesRequestBase searchBusinessesRequestBase = of();

    @Override
    public SearchBusinessesRequestBase of() {
        return SearchBusinessesRequest.of(e -> e
                .locale(LOCALE)
                .sort_by(SORT_BY)
                .location(CITY)
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
        assertThat(searchBusinessesRequestBase.locale()).isEqualTo(LOCALE);
        assertThat(searchBusinessesRequestBase.sort_by()).isEqualTo(SORT_BY);
        assertThat(searchBusinessesRequestBase.location()).isEqualTo(CITY);
        assertThat(searchBusinessesRequestBase.offset()).isEqualTo(OFFSET);
        assertThat(searchBusinessesRequestBase.limit()).isEqualTo(LIMIT);
        assertThat(searchBusinessesRequestBase.categories()).isEqualTo(CATEGORY);
        assertThat(searchBusinessesRequestBase.center()).isEqualTo(CENTER);
        assertThat(searchBusinessesRequestBase.limit()).isEqualTo(LIMIT);
        assertThat(searchBusinessesRequestBase.radius()).isEqualTo(RADIUS);
        assertThat(searchBusinessesRequestBase.open_at()).isEqualTo(OPEN_AT);
        assertThat(searchBusinessesRequestBase.price()).isEqualTo(PRICE);
        assertThat(searchBusinessesRequestBase.limit()).isEqualTo(LIMIT);
        assertThat(searchBusinessesRequestBase.radius()).isEqualTo(RADIUS);
        assertThat(searchBusinessesRequestBase.term().get(0)).isEqualTo(TERM);
        assertThat(searchBusinessesRequestBase.open_now()).isEqualTo(OPEN_NOW);
        assertThat(searchBusinessesRequestBase.attributes()).isEqualTo(ATTRIBUTES);
    }

    private final String expected = "SearchBusinessesRequest: GET v3/businesses/search?open_now=true&offset=5&price=3&limit=50&term=term&location=sf&attributes=attribute&categories=alias&sort_by=sort_by&radius=20000&locale=en_US&open_at=1 {\"term\":[\"term\"],\"location\":\"sf\",\"categories\":{\"alias\":\"alias\"},\"radius\":20000,\"offset\":5,\"sort_by\":\"sort_by\",\"limit\":50,\"open_at\":1,\"price\":3,\"attributes\":[{\"attribute\":\"attribute\"}]}";

    @YelpFusionTest
    public void testSerialize() {
        JsonGenerator generator = generator();

        searchBusinessesRequestBase.serialize(generator, mapper);

        assertThat(searchBusinessesRequestBase.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        searchBusinessesRequestBase.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchBusinessesRequestBase.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testBuilder() {
        SearchBusinessesRequest.Builder builder
                = new SearchBusinessesRequest.Builder();

        builder.locale(LOCALE)
                .sort_by(SORT_BY)
                .location(CITY)
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
                .price(PRICE);

        assertThat(builder.self()).isNotNull();
        assertThat(builder.build()).isNotNull();

    }


    public JsonParser parser() {
        return parser(searchBusinessesRequestBase);
    }

}