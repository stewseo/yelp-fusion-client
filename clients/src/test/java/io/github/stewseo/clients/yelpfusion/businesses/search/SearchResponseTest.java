package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.NamedDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.SEARCH_RESPONSE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.HIT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.REGION;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TOTAL;
import static org.assertj.core.api.Assertions.assertThat;

class SearchResponseTest extends YelpFusionTestCase<SearchResponse<SearchBusinessesResult>> {
    private final JsonpDeserializer<SearchResponse<SearchBusinessesResult>> tDocumentDeserializer =
            ObjectBuilderDeserializer.createForObject((Supplier<SearchResponse.Builder<SearchBusinessesResult>>) SearchResponse.Builder::new,
                    op -> SearchResponse.setupSearchResponseDeserializer(op,
                            new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search.ResultT")));

    private final SearchResponse<SearchBusinessesResult> searchResponse = of();

    // {"hits":[{"source":{"id":"id","rating":4.5,"center":{"latitude":37.7829,"longitude":-122.4189}}}],"total":1,"region":{"center":{"latitude":37.7829,"longitude":-122.4189}}}
    private final String expected = "" +
            "{" +
                "\"hits\":" +
                    "[" +
                        "{" +
                            "\"source\":" +
                                "{" +
                                    "\"id\":\"id\"," +
                                    "\"rating\":4.5," +
                                    "\"center\":" +
                                        "{" +
                                            "\"latitude\":37.7829," +
                                            "\"longitude\":-122.4189" +
                                        "}" +
                                "}" +
                        "}" +
                    "]," +
                "\"total\":1," +
                "\"region\":" +
                    "{" +
                        "\"center\":" +
                            "{" +
                                "\"latitude\":37.7829," +
                                "\"longitude\":-122.4189" +
                            "}" +
                    "}" +
            "}";

    @YelpFusionTest
    public void testSetupResponseBodyDeserializer() {

        assertThat(searchResponse.toString()).isEqualTo(expected);

        try(InputStream content = IOUtils.toInputStream(searchResponse.toString(), StandardCharsets.UTF_8)) {
            JsonParser parser = mapper.jsonProvider().createParser(content);

            Hit._DESERIALIZER.deserialize(parser, mapper);

            assertThat(tDocumentDeserializer).isExactlyInstanceOf(ObjectBuilderDeserializer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SearchResponse<SearchBusinessesResult> of() {
        return SEARCH_RESPONSE;
    }

    @YelpFusionTest
    public void testBuilder() {
        SearchResponse<SearchBusinessesResult> response = SearchResponse.of(s -> s
                .hits(HIT)
                .region(REGION)
                .total(TOTAL)
        );

        assertThat(response.hits().size()).isEqualTo(1);

        assertThat(response.region().toString()).isEqualTo("{\"center\":{\"latitude\":37.7829,\"longitude\":-122.4189}}");

        assertThat(response.total()).isEqualTo(1);

    }

    @YelpFusionTest
    public void testOf() {
        assertThat(searchResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testNamedDeser() {

        JsonpDeserializer<SearchBusinessesResult> resultTDeserializer = new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search.ResultT");


        JsonpDeserializer<SearchResponse<SearchBusinessesResult>> searchBusinessResult =
                JsonpDeserializer.lazy(() ->
                        ObjectBuilderDeserializer.createForObject(
                                (Supplier<SearchResponse.Builder<SearchBusinessesResult>>) SearchResponse.Builder::new,
                                op -> SearchResponse.setupSearchResponseDeserializer(op, resultTDeserializer)
                )
        );

    }

}