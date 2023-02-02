package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.json.jsonb.JsonbJsonpMapper;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import jakarta.json.stream.JsonGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class JsonDataImplTest extends ModelJsonTestCase {

    SearchBusinessesResult searchBusinessResult = SearchBusinessesResult.of(s -> s
            .id("id")
            .url("url")
    );

    private final JsonpMapper mapper = new JacksonJsonpMapper();
    private final JsonDataImpl jsonDataImpl = new JsonDataImpl(searchBusinessResult, mapper);

    @JsonTest
    void testToString() {
        assertThat(jsonDataImpl.toString()).isEqualTo(searchBusinessResult.toString());
    }

    @JsonTest
    void testToJson() {
        assertThat(jsonDataImpl.toJson().toString()).isEqualTo(searchBusinessResult.toString());
        assertThat(jsonDataImpl.toJson(mapper).toString()).isEqualTo(searchBusinessResult.toString());

    }


    @JsonTest
    void testTo() {

        SearchBusinessesResult searchBusinessResult = SearchBusinessesResult.of(s -> s
                .id("id")
                .review_count(10)
        );
        JsonDataImpl jsonDataImpl = new JsonDataImpl(searchBusinessResult, mapper);
        assertThat(jsonDataImpl.to(SearchBusinessesResult.class)).isEqualTo(searchBusinessResult);

        assertThat(jsonDataImpl.to(SearchBusinessesResult.class, new JsonbJsonpMapper())).isEqualTo(searchBusinessResult);

    }

    @JsonTest
    void testDeserialize() {

        assertThat(jsonDataImpl.deserialize(SearchBusinessesResult._DESERIALIZER, mapper).toString()).isEqualTo(searchBusinessResult.toString());
        assertThat(jsonDataImpl.deserialize(SearchBusinessesResult._DESERIALIZER).toString()).isEqualTo(searchBusinessResult.toString());

    }

    @JsonTest
    void testSerialize() {
        JsonGenerator jsonGenerator = generator();
        jsonDataImpl.serialize(jsonGenerator, mapper);
        assertThat(jsonDataImpl.toString()).isEqualTo("{\"id\":\"id\",\"url\":\"url\"}");
    }
}