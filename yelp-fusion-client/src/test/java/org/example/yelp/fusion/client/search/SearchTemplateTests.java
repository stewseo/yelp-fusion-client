//package org.example.yelp.fusion.client.business;
//
//import co.elastic.clients.elasticsearch._types.aggregations.*;
//import co.elastic.clients.elasticsearch._types.query_dsl.*;
//import co.elastic.clients.elasticsearch.core.*;
//import co.elastic.clients.elasticsearch.core.search.*;
//import co.elastic.clients.json.*;
//import org.apache.commons.logging.*;
//import org.example.yelp.fusion.client.*;
//import org.junit.jupiter.api.*;
//
//import java.io.*;
//import java.util.*;
//
//public class SearchTemplateTests extends AbstractRequestTestCase {
//    private static final Log logger = LogFactory.getLog(SearchTemplateTests.class);
//
//    @Test
//    void searchTemplateTest() {
//        try {
//            elasticSearch.client().putScript(r -> r
//                    .id("query-script")
//                            .
//
//                    script(s -> s
//                            .lang("mustache")
//                                    .
//
//                            source("{\"query\":{\"match\":{\"{{field}}\":\"{{value}}\"}}}")
//                    ));
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        SearchTemplateResponse<Business> response = null;
//        try {
//            response = elasticSearch.client().searchTemplate(r -> r
//                            .index(indexNyc)
//                            .id("query-script")
//                            .params("field", JsonData.of("categories.alias"))
//                            .params("value", JsonData.of("sushi")),
//                    Business.class
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        List<Hit<Business>> hits = response.hits().hits();
//        for (Hit<Business> hit : hits) {
//            Business business = hit.source();
//            logger.info("Found product " + business.id() + ", score " + hit.score());
//        }
//    }
//
//    @Test
//    void aggregationTest() {
//        SearchResponse<Business_> searchResponse = null;
//        try {
//            searchResponse = elasticSearch.client().search(_1 -> _1
//                            .index("products")
//                            .size(0)
//                            .aggregations(
//                                    "prices", _3 -> _3
//                                            .histogram(_4 -> _4
//                                                    .field("price")
//                                                    .interval(10.0)
//                                            ).aggregations(
//                                                    "average", _5 -> _5
//                                                            .avg(_6 -> _6.field("price"))
//                                            )
//                            )
//                    , Business_.class
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        HistogramAggregate prices = searchResponse.aggregations().get("prices").histogram();
//    }
//
//    private static final SearchResponse<JsonData> searchResponse = SearchResponse.of(b -> b
//            .aggregations(new HashMap<>())
//            .took(0)
//            .timedOut(false)
//            .hits(h -> h
//                    .total(t -> t.value(0).relation(TotalHitsRelation.Eq))
//                    .hits(new ArrayList<>())
//            )
//            .shards(s -> s
//                    .total(1)
//                    .failed(0)
//                    .successful(1)
//            )
//            .aggregations("price-histogram", a -> a.histogram(h -> h
//                    .buckets(bu -> bu.array(Collections.singletonList(HistogramBucket.of(hb -> hb
//                            .key(50).docCount(1)
//                    ))))
//            ))
//    );
//
//    @Test
//    public void priceHistogram() throws Exception {
//
//        //tag::price-histo-request
//        String searchText = "sushi";
//
//        Query query = MatchQuery.of(m -> m
//                .field("categories.alias")
//                .query(searchText)
//        )._toQuery();
//
//        SearchResponse<Void> response = elasticSearch.client().search(b -> b
//                        .index(indexNyc)
//                        .size(0) // <1>
//                        .query(query) // <2>
//                        .aggregations("price-histogram", a -> a // <3>
//                                .histogram(h -> h // <4>
//                                        .field("price")
//                                )
//                        ),
//                Void.class // <5>
//        );
//        //end::price-histo-request
//
//        //tag::price-histo-response
//        List<HistogramBucket> buckets = response.aggregations()
//                .get("price-histogram") // <1>
//                .histogram() // <2>
//                .buckets().array(); // <3>
//
//        for (HistogramBucket bucket: buckets) {
//            logger.info("There are " + bucket.docCount() +
//                    " restaurants under " + bucket.key());
//        }
//
//        //end::price-histo-response
//    }
//
//}
