package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.SEARCH_BUSINESSES_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

public class BehaviorsTest extends ModelJsonTestCase {


    @TypeTest
    public void testAdditionalPropertyOnClass() {
        Open open = Open.of(o -> o
                .day(1)
                .start("0800")
                .end("1700")
                .is_overnight(true)
        );
        assertThat(open).hasNoNullFieldsOrProperties();

        Hours hours = Hours.of(h -> h
                .open(open)
                .hours_type("type")
                .is_open_now(false)
        );

        assertThat(hours).hasNoNullFieldsOrProperties();

    }

    @TypeTest
    public void testAdditionalProperty() {


        TermQueryParameter termQueryParameter = TermQueryParameter.of(t -> t.term(TermEnum.Restaurants));

        QueryParameter queryParameter = new QueryParameter.Builder()
                .term(termQueryParameter)
                .build();


        queryParameter = checkJsonRoundtrip(queryParameter, "{\"term\":{\"term\":\"restaurants\"}}");

        assertThat(QueryParameter.Kind.Term).isEqualTo(queryParameter._kind());
        assertThat(TermEnum.Restaurants).isEqualTo(queryParameter.term().term());
    }
}