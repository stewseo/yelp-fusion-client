package io.github.stewseo.clients.elasticsearch.aggregation;

import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.elasticsearch.ElasticsearchTestCase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

import static io.github.stewseo.clients.yelpfusion._types.TestData.MAX_RESULTS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TermsAggregationTest extends ElasticsearchTestCase {

    @Test
    void storedFieldsTest() {
        int size = 50;

        try {
            SearchResponse<ObjectNode> respon = elasticsearchService.getAsyncClient().search(s -> s
                                    .storedFields("id")
                                    .index(index)
                                    .size(size),
                            ObjectNode.class)
                    .get();

            TotalHits hits = respon.hits().total();

            int total = Math.toIntExact(Objects.requireNonNull(hits).value());

            System.out.println(total);

            List<Hit<ObjectNode>> hitsHits = respon.hits().hits();

            Predicate<Double> greaterThan = e -> e > 0.8;
            Predicate<Double> lessThan = e -> e < 1.5;
            Predicate<Double> inRange = lessThan.and(greaterThan);

            long countScoreGreaterThan = hitsHits.stream().map(Hit::score).filter(Objects::nonNull).filter(inRange).count();

            assertThat(countScoreGreaterThan).isEqualTo(50L);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void termsAggregationTest() {

        List<StringTermsBucket> buckets = elasticsearchService.termsAggregationByCategory(MAX_RESULTS, index);
        assertThat(buckets.size()).isEqualTo(222);
    }

    @Test
    void docsCountTest() {
        int count = elasticsearchService.docsCount(index);
        assertThat(count).isEqualTo(3697);
    }

}


