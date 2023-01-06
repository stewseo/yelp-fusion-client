package io.github.stewseo.clients.json;


import co.elastic.clients.elasticsearch._types.InlineScript;
import co.elastic.clients.elasticsearch._types.SlicedScroll;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TextProperty;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.indices.PutIndicesSettingsRequest;
import co.elastic.clients.json.JsonData;
import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WithJsonTest extends FunctionalTestCase {

    @Test
    public void testRequestWithGenericValueBody() {
        String json = "{\"foo\": \"bar\", \"baz\": 1}";

        IndexRequest<JsonData> req = IndexRequest.of(b -> b
                .index("index") // required path parameter (cannot be expressed in json)
                .withJson(new StringReader(json))
        );

        assertThat("index").isEqualTo(req.index());
        assertThat("bar").isEqualTo(req.document().toJson().asJsonObject().getString("foo"));
        assertThat(1).isEqualTo(req.document().toJson().asJsonObject().getInt("baz"));
    }

    @Test
    public void testRequestWithValueBody() {
        String json = "{" +
                "\"analyze\": {" +
                "    \"max_token_count\": 12" +
                "  }" +
                "}";

        PutIndicesSettingsRequest req = PutIndicesSettingsRequest.of(b -> b
                .withJson(new StringReader(json))
        );

        assertThat(12).isEqualTo(req.settings().analyze().maxTokenCount().intValue());
    }

    @Test
    public void testRegularObject() {
        String json = "{\"field\": \"foo\", \"id\": 12}";

        SlicedScroll s = SlicedScroll.of(b -> b
                .withJson(new StringReader(json))
                .max(34) // required property not present in the json
        );

        assertThat("foo").isEqualTo(s.field());
        assertThat("12").isEqualTo(s.id());
        assertThat(34).isEqualTo(s.max());
    }

    @Test
    public void testObjectWithGenericParam() {

        String json = "{" +
                "  \"took\" : 9," +
                "  \"timed_out\" : false," +
                "  \"_shards\" : {" +
                "    \"total\" : 1," +
                "    \"successful\" : 1," +
                "    \"skipped\" : 0," +
                "    \"failed\" : 0" +
                "  }," +
                "  \"hits\" : {" +
                "    \"total\" : {" +
                "      \"value\" : 1," +
                "      \"relation\" : \"eq\"" +
                "    }," +
                "    \"max_score\" : 1.0," +
                "    \"hits\" : [" +
                "      {" +
                "        \"_index\" : \"test\"," +
                "        \"_id\" : \"8aSerXUBs1w7Wkuj31zd\"," +
                "        \"_score\" : 1.0," +
                "        \"_source\" : {" +
                "          \"foo\" : \"bar\"" +
                "        }" +
                "      }" +
                "    ]" +
                "  }" +
                "}";

        // withJson() will read values of the generic parameter type as JsonData
        SearchResponse<JsonData> r = SearchResponse.of(b -> b
                .withJson(new StringReader(json))
        );

        assertThat(1).isEqualTo(r.hits().total().value());

        assertThat("bar").isEqualTo(r.hits().hits().get(0).source().toJson().asJsonObject().getString("foo"));
    }

    @Test
    public void testTypeWithParent() {

        String json = "{\"source\": \"return doc;\"}";

        InlineScript is = InlineScript.of(b -> b
                .withJson(new StringReader(json))
        );

        assertThat("return doc;").isEqualTo(is.source());
    }

    @Test
    public void testContainerTaggedUnion() {
        String json = "{" +
                "    \"term\": {" +
                "      \"user.id\": {" +
                "        \"value\": \"kimchy\"," +
                "        \"boost\": 1.0" +
                "      }" +
                "    }" +
                "  }";

        Query q = Query.of(b -> b
                .withJson(new StringReader(json))
        );

        TermQuery tq = q.term();
        assertThat("user.id").isEqualTo(tq.field());
        assertThat("kimchy").isEqualTo(tq.value().stringValue());
//        assertThat(1.0, tq.boost(), 0.001);
    }

    @Test
    public void testInternallyTaggedUnion() {
        String json = "{ " +
                "        \"type\": \"text\"," +
                "        \"fields\": {" +
                "          \"some_field\": { " +
                "            \"type\": \"keyword\"," +
                "            \"normalizer\": \"lowercase\"" +
                "          }" +
                "        }" +
                "      }";

        Property p = Property.of(b -> b
                .withJson(new StringReader(json))
        );

        TextProperty tp = p.text();
        assertThat("lowercase").isEqualTo(tp.fields().get("some_field").keyword().normalizer());
    }
}