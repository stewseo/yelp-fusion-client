package io.github.stewseo.yelp.fusion.client.aggregation;

import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.yelp.fusion.client.YelpFusionTestCase;
import io.github.stewseo.yelp.fusion.client.connection.ElasticsearchConnection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TermsAggregationTest extends YelpFusionTestCase {

    @Test
    void storedFieldsTest() throws IOException {
        int size = 50;
        ElasticsearchConnection.createElasticsearchService();

        try {
            SearchResponse<ObjectNode> respon = elasticsearchService.getAsyncClient().search(s -> s
                            .storedFields("id")
                            .index(index)
                                    .size(size),
                    ObjectNode.class)
                    .get();

            TotalHits hits = respon.hits().total();

            int total = Math.toIntExact(hits.value());

            System.out.println(total);

            List<Hit<ObjectNode>> hitsHits = respon.hits().hits();

            Predicate<Double> greaterThan = e -> e > 0.8;
            Predicate<Double> lessThan = e -> e < 1.5;
            Predicate<Double> inRange = lessThan.and(greaterThan);

            long countScoreGreaterThan = hitsHits.stream().map(Hit::score).filter(Objects::nonNull).filter(inRange).count();

            assertThat(countScoreGreaterThan).isEqualTo(size);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void termsAggregationTest() throws Exception {

        List<StringTermsBucket> buckets = elasticsearchService.termsAggregationByCategory(350, index);

        for (StringTermsBucket bucket: buckets) {
            System.out.println(PrintUtils.cyan("category: " + bucket.key().stringValue() +
                    ", "+PrintUtils.green("documents: " + bucket.docCount()
                            )
                    )
            );
        }

    }
}
