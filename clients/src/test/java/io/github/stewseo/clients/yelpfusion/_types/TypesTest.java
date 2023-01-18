package io.github.stewseo.clients.yelpfusion._types;

import co.elastic.clients.elasticsearch._types.aggregations.StatsAggregate;
import co.elastic.clients.elasticsearch._types.aggregations.StringStatsAggregate;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.SEARCH_BUSINESSES_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

public class TypesTest extends ModelJsonTestCase {

    @TypeTest
    public void testFieldValue() {

        FieldValue f;

        f = FieldValue.of(FieldValue.Builder::nullValue);
        f = checkJsonRoundtrip(f, "null");
        assertTrue(f.isNull());
        assertEquals("null", f._toJsonString());

        f = FieldValue.of(b -> b.doubleValue(1.23));
        f = checkJsonRoundtrip(f, "1.23");
        assertTrue(f.isDouble());
        assertEquals(1.23, f.doubleValue(), 0.01);
        assertEquals("1.23", f._toJsonString());

        f = FieldValue.of(b -> b.longValue(123));
        f = checkJsonRoundtrip(f, "123");
        assertTrue(f.isLong());
        assertEquals(123, f.longValue());
        assertEquals("123", f._toJsonString());

        f = FieldValue.of(b -> b.booleanValue(true));
        f = checkJsonRoundtrip(f, "true");
        assertTrue(f.isBoolean());
        assertTrue(f.booleanValue());
        assertEquals("true", f._toJsonString());

        f = FieldValue.of(b -> b.booleanValue(false));
        f = checkJsonRoundtrip(f, "false");
        assertTrue(f.isBoolean());
        assertFalse(f.booleanValue());
        assertEquals("false", f._toJsonString());

        f = FieldValue.of(b -> b.stringValue("foo"));
        f = checkJsonRoundtrip(f, "\"foo\"");
        assertTrue(f.isString());
        assertEquals("foo", f.stringValue());
        assertEquals("foo", f._toJsonString());

    }

    @TypeTest
    public void testOpen() {
        Open open = Open.of(o -> o
                .day(1)
                .start("0800")
                .end("1700")
                .is_overnight(true)
        );
        String json = toJson(open);
        assertThat(json).isEqualTo("{\"is_overnight\":true,\"day\":1,\"start\":\"0800\",\"end\":\"1700\"}");
        Open o = fromJson(json, Open.class);
        assertThat(o.toString()).isEqualTo(json);

        Open op = checkJsonRoundtrip(open, json);
        assertThat(op.day()).isNotNull();
        assertThat(op.is_overnight()).isNotNull();
        assertThat(op.end()).isNotNull();
        assertThat(op.start()).isNotNull();
    }

    public void testNullableDouble() {
        StatsAggregate stats;
        QueryParameter qp;
        qp = new QueryParameter(QueryParameter.Kind.Term, SEARCH_BUSINESSES_REQUEST);
        // Regular values
        stats = StatsAggregate.statsAggregateOf(b -> b // Parent classes can't have an overloaded "of" method
                .count(10)
                .min(1.0)
                .avg(1.5)
                .max(2.0)
                .sum(5.0)
        );

        stats = checkJsonRoundtrip(stats, "{\"count\":10,\"min\":1.0,\"max\":2.0,\"avg\":1.5,\"sum\":5.0}");
        assertEquals(10, stats.count());
        assertEquals(1.0, stats.min(), 0.01);
        assertEquals(1.5, stats.avg(), 0.01);
        assertEquals(2.0, stats.max(), 0.01);
        assertEquals(5.0, stats.sum(), 0.01);

        // Missing values (JSON null, Java infinite)
        String json = "{\"count\":0,\"min\":null,\"max\":null,\"avg\":null,\"sum\":0.0}";
        stats = fromJson(json, StatsAggregate.class);

        assertEquals(0, stats.count());
        assertTrue(Double.isInfinite(stats.min()));
        assertEquals(0.0, stats.avg(), 0.01);
        assertTrue(Double.isInfinite(stats.max()));
        assertEquals(0.0, stats.sum(), 0.01);

        // We don't serialize finite default values as null
        assertEquals("{\"count\":0,\"min\":null,\"max\":null,\"avg\":0.0,\"sum\":0.0}", toJson(stats));
    }


    public void testNullableInt() {

        StringStatsAggregate stats = StringStatsAggregate.of(b -> b
                .count(1)
                .minLength(2)
                .avgLength(3)
                .maxLength(4)
                .entropy(0)
        );

        stats = checkJsonRoundtrip(stats, "{\"count\":1,\"min_length\":2,\"max_length\":4,\"avg_length\":3.0,\"entropy\":0.0}");
        assertEquals(2, stats.minLength());
        assertEquals(4, stats.maxLength());

        // Missing values
        String json = "{\"count\":1,\"min_length\":null,\"max_length\":null,\"avg_length\":null,\"entropy\":null}";
        stats = fromJson(json, StringStatsAggregate.class);
        assertEquals(0, stats.minLength());
        assertEquals(0, stats.maxLength());
        assertEquals(0.0, stats.entropy(), 0.01);
    }


    public void testVoidDeserialization() {
        String json = "{\"_shards\":{\"failed\":0.0,\"successful\":1.0,\"total\":1.0}," +
                "\"hits\":{\"hits\":[{\"_index\":\"foo\",\"_id\":\"w\",\"_source\":{\"foo\": \"bar\"}}]}," +
                "\"took\":42,\"timed_out\":false" +
                "}";

        SearchResponse<Void> response = fromJson(json, SearchResponse.createSearchResponseDeserializer(JsonpDeserializer.of(Void.class)));

        // Void type skips all the JSON and is serialized to null.
        assertThat(response.hits().size()).isEqualTo(1);
        assertThat(response.hits().get(0).source()).isNotNull();

    }
}
