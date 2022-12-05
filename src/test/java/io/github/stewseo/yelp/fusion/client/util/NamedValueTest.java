package io.github.stewseo.yelp.fusion.client.util;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;


import co.elastic.clients.util.NamedValue;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.ModelTestCase;
import org.junit.jupiter.api.Test;

class NamedValueTest extends ModelTestCase {

    @Test
    public void testTermAggregation() {

        String json = "{\"order\":[{\"a\":\"asc\"},{\"b\":\"desc\"}]}";

        TermsAggregation ta = TermsAggregation.of(b -> b
                .order(NamedValue.of("a", SortOrder.Asc))
                .order( NamedValue.of("b", SortOrder.Desc)
                )
        );

        ta = checkJsonRoundtrip(ta, json);

        assertEquals(2, ta.order().size());

        assertEquals("a", ta.order().get(0).name());
        assertEquals(SortOrder.Asc, ta.order().get(0).value());
        assertEquals("b", ta.order().get(1).name());
        assertEquals(SortOrder.Desc, ta.order().get(1).value());
    }
}