package io.github.stewseo.clients.yelpfusion.businesses.transactions;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.yelpfusion._types.Center;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion._types.Region;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionResponseTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static io.github.stewseo.clients.yelpfusion._types.TestData.TOTAL;
import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LONGITUDE;
class SearchTransactionResponseTest extends YelpFusionResponseTestCase<SearchTransactionResponse> {

    private final SearchTransactionResponse searchTransactionsResponse = of();
    private final List<SearchBusinessResult> searchBusinessResults = List.of(SearchBusinessResult.of(b -> b.id(ID)));

    private final Region region = Region.of(r -> r
            .center(Center.of(c-> c
                    .latitude(LATITUDE)
                    .longitude(LONGITUDE)))
    );

    @Override
    public final SearchTransactionResponse of() {
        return SearchTransactionResponse.of(s -> s
                .businesses(List.of(SearchBusinessResult.of(b -> b.id(ID))))
                .total(1)
                .region(Region.of(r -> r.center(Center.of(cent -> cent.latitude(38.0)))))
        );

    }


    @Test
    public void testOf() {
        assertThat(searchTransactionsResponse.businesses().toString()).isEqualTo("[{\"id\":\"id\"}]");
        assertThat(searchTransactionsResponse.region().toString()).isEqualTo("{\"center\":{\"latitude\":38.0}}");
        assertThat(searchTransactionsResponse.total()).isEqualTo(TOTAL);
    }

    private final JsonGenerator generator = generator();
    String expected = "{\"businesses\":[{\"id\":\"id\"}],\"total\":1,\"region\":{\"center\":{\"latitude\":38.0}}}";

    @Test
    public void testSerialize() {
        searchTransactionsResponse.serialize(generator, mapper);
        assertThat(searchTransactionsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        searchTransactionsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(searchTransactionsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        Event searchBusinessRes = Event._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes).isNotNull();
    }

    @Test
    public void testDeserializer() {

        assertThat(SearchTransactionResponse._DESERIALIZER.toString())
                .contains("io.github.stewseo.clients.json.LazyDeserializer");

        SearchTransactionResponse.Builder builder = new SearchTransactionResponse.Builder()
                .businesses(searchBusinessResults);

        JsonpDeserializer<SearchTransactionRequest> _DESERIALIZER = ObjectBuilderDeserializer.lazy(
                SearchTransactionRequest.Builder::new,
                SearchTransactionRequest::setupSearchRequestDeserializer);

        assertThat(_DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");
    }

    @Override
    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(searchTransactionsResponse.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

    @Test
    public void testBuildWithJson() {
        
    }

    @Test
    public void testBuilder() {

        SearchTransactionResponse.Builder builder = new SearchTransactionResponse.Builder().total(1);

        SearchTransactionResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        SearchTransactionResponse searchTransactionResp = builder.build();

        assertThat(searchTransactionResp.toString()).isEqualTo("{\"businesses\":[],\"total\":1}");
    }
}