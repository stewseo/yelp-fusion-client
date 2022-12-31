package io.github.stewseo.client.util;

import io.github.stewseo.client._types.SortOrder;
import io.github.stewseo.client._types.aggregations.Aggregation;
import io.github.stewseo.client._types.aggregations.HistogramAggregation;
import io.github.stewseo.client._types.aggregations.TermsAggregation;
import io.github.stewseo.client.json.JsonData;

import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NamedValueTest extends YelpFusionTestCase {


    @Test
    public void testTermAggregation() {

        String json = "{\"order\":[{\"a\":\"asc\"},{\"b\":\"desc\"}]}";

        TermsAggregation ta = TermsAggregation.of(b -> b
                .order(NamedValue.of("a", SortOrder.Asc))
                .order(NamedValue.of("b", SortOrder.Desc)
                )
        );

        System.out.println(ta);

        ta = jsonTestCase.checkJsonRoundtrip(ta, json);

        List<NamedValue<SortOrder>> sortedNameValues = ta.order();
        assertThat(sortedNameValues.size()).isEqualTo(2);

        NamedValue<SortOrder> namedValueA = sortedNameValues.get(0);
        NamedValue<SortOrder> namedValueB = sortedNameValues.get(1);

        assertThat(namedValueA.name()).isEqualTo("a");
        assertThat(namedValueA.value()).isEqualTo(SortOrder.Asc);

        assertThat(namedValueB.name()).isEqualTo("b");
        assertThat(namedValueB.value()).isEqualTo(SortOrder.Desc);
    }

    @Test
    public void histogramAggregationTest() {

        HistogramAggregation histogramAggregation = HistogramAggregation.of(h -> h // <3>
                .field("price")
                .interval(50.0)
        );

        String field = histogramAggregation.field();
        Double interval = histogramAggregation.interval();

        assertThat(field).isEqualTo("price");
        assertThat(interval).isEqualTo(50.0);

//        List<HistogramBucket> buckets = response.aggregations()
//                .get("histogram")
//                .histogram()
//                .buckets().array();

//        for (HistogramBucket bucket: buckets) {
//        }

    }

    @Test
    public void customAggregationTest() {

        Map<String, Object> params = new HashMap<>(); // <1>
        params.put("interval", 10);
        params.put("scale", "log");
        params.put("origin", List.of(145.0, 12.5, 1649.0));

        Aggregation customAggregation = Aggregation.of(a -> a
                ._custom("sphere-distance", params));

        boolean isCustom = customAggregation._isCustom();
        assertThat(isCustom).isTrue();

        JsonData jsonData = customAggregation._custom();
        assertThat(jsonData.toString()).isEqualTo("{origin=[145.0, 12.5, 1649.0], scale=log, interval=10}");

        String customKind = customAggregation._customKind();
        assertThat(customKind).isEqualTo("sphere-distance");

    }
}