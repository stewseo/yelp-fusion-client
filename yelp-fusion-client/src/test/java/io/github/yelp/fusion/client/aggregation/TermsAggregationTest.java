package io.github.yelp.fusion.client.aggregation;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.TermsBucketBase;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import io.github.yelp.fusion.client.Elasticsearch;
import io.github.yelp.fusion.client.ElasticsearchRequestTestCase;
import io.github.yelp.fusion.client.YelpRequestTestCase;
import io.github.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.yelp.fusion.util.PrintUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TermsAggregationTest {

    private final static Logger logger = LoggerFactory.getLogger(TermsAggregationTest.class);

    static YelpFusionClient yelpClient;


    @Test
    void termsAggregationTest() throws Exception {

        // Dynamically build each unique bucket by field: categories alias
        TermsAggregation termsAggregation = TermsAggregation.of(t -> t
                .field("categories.alias.keyword")
        );
        // match all documents containing the queryName(field): "categories"
        Query matchAll = MatchAllQuery.of(m -> m
                .queryName("categories")
        )._toQuery();

        // HttpHost, Transport, JsonMapper, ESClient
        ElasticsearchRequestTestCase.initElasticsearchClient();


        SearchResponse<Void> response =  Elasticsearch.getInstance().client().search(b -> b
                        .index("yelp-businesses-restaurants-nyc")
                        .size(0) // Set the number of matching documents to zero
                        .query(matchAll) // Set the query that will filter the businesses on which to run the aggregation (all contain categories)
                        .aggregations("categories-aggs", a -> a // Create an aggregation named "categories-aggs"
                                .terms(termsAggregation) // Select the terms aggregation variant.

                        ),
                Void.class // We do not care about matches (size is set to zero), using Void will ignore any document in the response.
        );

        List<StringTermsBucket> buckets = response.aggregations()
                .get("categories-aggs")
                .sterms()
                .buckets().array();

        for (StringTermsBucket bucket: buckets) {
            logger.info("There are " + bucket.docCount() +
                    " restaurants in category: " + bucket.key().stringValue());
        }
        logger.info(PrintUtils.red("match all query with terms aggregations(categories.alias.keyword).size: " + response.aggregations().size()));


    }
}
