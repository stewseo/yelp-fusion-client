package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.yelpfusion._types.QueryParameter;
import io.github.stewseo.clients.yelpfusion._types.TermEnum;
import io.github.stewseo.clients.yelpfusion._types.TermQueryParameter;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ElasticsearchServiceTest {

    private static final ElasticsearchService esService = new ElasticsearchService(ElasticsearchServiceCtx.createElasticsearchAsyncClient());

    private static final String indexName = "test-index";

    static class TestModel {

        public Long id;

        public String url;
        public String title;
        public Integer order;
        public Boolean completed;

        public TestModel(){}

        public TestModel(Long id, String url, String title, Integer order, Boolean completed) {
            this.id = id;
            this.url = url;
            this.title = title;
            this.order = order;
            this.completed = completed;
        }

        public Long id() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String url() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String title() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer order() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Boolean completed() {
            return completed;
        }

        public void setCompleted(Boolean completed) {
            this.completed = completed;
        }
    }

    private static final int size = 5;

    @BeforeAll
    static void beforeAll() {

        for(int i = size; i > -1; i--) {
            TestModel testModel = new TestModel((long) i, "title", "url/"+i, i, false);
            esService.createOrUpdate(indexName, String.valueOf(testModel.id()), testModel);
        }

    }

    @ElasticsearchTest
    void testDocsCount() {
        int docsCount = esService.docsCount(indexName);
        assertThat(docsCount).isEqualTo(1);
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

    @ElasticsearchTest
    void testSearchWithRangeQuery() {
        List<Hit<ObjectNode>> hits = esService.searchWithRangeQuery("1", indexName);
        assertThat(hits.size()).isEqualTo(1);
    }

    @ElasticsearchTest
    void testTermsAggregationByCategory() {
        List<StringTermsBucket> stringTermsBuckets = esService.termsAggregation("title.keyword",1, indexName);
        assertThat(stringTermsBuckets.size()).isEqualTo(1);
    }
}