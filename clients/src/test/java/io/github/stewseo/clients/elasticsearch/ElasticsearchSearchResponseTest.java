package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch._types.ShardStatistics;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.EXPECTED_SEARCH_BUSINESS_RESULT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.ID;

public class ElasticsearchSearchResponseTest {

    public void testESSearchResponse() {
        SearchResponse<SearchBusinessResult> esS
                = SearchResponse.of(s -> s
                .maxScore(1.0)
                .took(1L)
                .timedOut(false)
                .shards(ShardStatistics.of(sh -> sh
                        .total(1)
                        .failed(0)
                        .successful(1)
                )
                )
                .hits(HitsMetadata.of(hit -> hit
                        .total(total -> total
                                .value(1)
                                .relation(TotalHitsRelation.Eq)
                        )
                        .hits(h -> h
                                .id(ID)
                                .source(EXPECTED_SEARCH_BUSINESS_RESULT)
                                .index("index"))
                        )
                )
        );

    }
}
