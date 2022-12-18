package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import io.github.stewseo.yelp.fusion.client.Elasticsearch;
import io.github.stewseo.yelp.fusion.client.ElasticsearchConnection;
import io.github.stewseo.yelp.fusion.client.YelpConnection;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAllCategoriesTest {
    private final static Logger logger = LoggerFactory.getLogger(GetAllCategoriesTest.class);

    @Test
    public void getAllCategoriesTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        GetCategoriesResponse response = client.categories().all(c -> c.locale("en_US"));
        assertThat(response.categories().toString().length()).isEqualTo(199188);
        assertThat(response.categories().size()).isEqualTo(1295);

        for(Categories cat : response.categories()) {
            if (cat != null) {
                logger.info(" " + cat);
            }
        }
    }

    @Test
    public void getAllCategoriesAsyncTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(apiKey);

        CompletableFuture<GetCategoriesResponse> future = asyncClient.categories().categories(c -> c.locale("en_US"));

        List<Categories> categories = future.get().categories();
        assertThat(categories.size()).isEqualTo(1295);
        for(Categories category : categories) {
            if (category != null) {
                logger.info(" " + category);
            }
        }
    }

    @Test
    public void termsAggregationTest() throws IOException {

        ElasticsearchConnection.initElasticsearchClient();
        ElasticsearchClient esClient = Elasticsearch.getInstance().client();

        // Dynamically build each unique bucket by field: all alias
        TermsAggregation termsAggregation = TermsAggregation.of(t -> t
                .field("categories.alias.keyword")
                .size(350)
        );
        // match all documents containing the queryName: "all"
        Query matchAll = MatchAllQuery.of(m -> m
                .queryName("location")
        )._toQuery();

        SearchResponse<Void> response = null;

        try {
            response = esClient.search(b -> b
                            .index("yelp-businesses-restaurants-nyc")
                            .size(0) // Set the number of matching documents to zero
                            .query(matchAll) // Set the query that will filter the businesses on which to run the aggregation (all contain all)
                            .aggregations("all-aggs", a -> a // Create an aggregation named "all-aggs"
                                    .terms(termsAggregation) // Select the terms aggregation variant.
                            ),
                    Void.class // Using Void will ignore any document in the response.
            );
        } catch (IOException | ElasticsearchException e) {
            throw new RuntimeException(e);
        }

        List<StringTermsBucket> buckets = response.aggregations()
                .get("all-aggs")
                .sterms()
                .buckets().array();

        for(StringTermsBucket bucket: buckets) {
            System.out.println(bucket);
        }
    }
}
