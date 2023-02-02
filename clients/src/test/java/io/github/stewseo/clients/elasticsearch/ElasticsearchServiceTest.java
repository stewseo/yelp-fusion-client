package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.yelpfusion._types.QueryParameter;
import io.github.stewseo.clients.yelpfusion._types.TermEnum;
import io.github.stewseo.clients.yelpfusion._types.TermQueryParameter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ElasticsearchServiceTest {

    ElasticsearchService esService = new ElasticsearchService(ElasticsearchServiceCtx.createElasticsearchAsyncClient());

    

    @ElasticsearchTest
    void testDocsCount() {

        int docsCount = esService.docsCount("test-index");
        assertThat(docsCount).isEqualTo(0);
    }

    @ElasticsearchTest
    void testRangeQuery() {
        Query query = esService.rangeQuery("id", 1L)._toQuery();
        assertThat(query).isNotNull();
    }

    @ElasticsearchTest
    void testMatchAllQuery() {
        Query query = esService.matchAllQuery("id")._toQuery();
        assertThat(query).isNotNull();
    }

    @ElasticsearchTest
    void testSortOptions() {
        QueryParameter queryParameter = QueryParameter.of(q -> q
                .term(TermQueryParameter.of(t -> t
                        .term(TermEnum.Restaurants)))
        );

        SortOptions sortOptions = esService.sortOptions("id", SortOrder.Asc);

        assertThat(sortOptions).isNotNull();
    }
}