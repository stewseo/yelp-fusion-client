package io.github.stewseo.yelp.fusion.client.aggregation;

import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.yelp.fusion.client.Elasticsearch;
import io.github.stewseo.yelp.fusion.client.ElasticsearchRequestTestCase;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TermsAggregationTest {

    private final static Logger logger = LoggerFactory.getLogger(TermsAggregationTest.class);

    static YelpFusionClient yelpClient;


    @Test
    void termsAggregationTest() throws Exception {

        // Dynamically build each unique bucket: categories alias
        TermsAggregation termsAggregation = TermsAggregation.of(t -> t
                .field("categories.alias.keyword")
                .size(315)
        );
        // match all documents containing the queryName: "categories"
        Query matchAll = MatchAllQuery.of(m -> m
                .queryName("location")
        )._toQuery();


        ElasticsearchRequestTestCase.initElasticsearchClient();

        SearchResponse<Void> response =  Elasticsearch.getInstance().client().search(b -> b
                        .index("yelp-businesses-restaurants-nyc")
                        .size(0) // Set the number of matching documents to zero
                        .query(matchAll) // Set the query that will filter the businesses on which to run the aggregation (all contain categories)
                        .aggregations("categories-aggs", a -> a // Create an aggregation named "categories-aggs"
                                .terms(termsAggregation) // Select the terms aggregation variant.

                        ),
                Void.class // Using Void will ignore any document in the response.
        );

        List<StringTermsBucket> buckets = response.aggregations()
                .get("categories-aggs")
                .sterms()
                .buckets().array();

        for (StringTermsBucket bucket: buckets) {
            System.out.println(PrintUtils.cyan("category: " + bucket.key().stringValue() +
                    ", "+PrintUtils.greenln("documents: " + bucket.docCount()
                            )
                    )
            );
        }

    }
}
