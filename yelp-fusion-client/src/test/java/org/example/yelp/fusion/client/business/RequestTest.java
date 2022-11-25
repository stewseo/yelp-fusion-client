package org.example.yelp.fusion.client.business;


import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.elasticsearch.client.elasticsearch.core.Hit;
import org.example.lowlevel.restclient.PrintUtils;
import org.example.yelp.fusion.client.AbstractRequestTestCase;
import org.example.yelp.fusion.client.business.search.Business;
import org.example.yelp.fusion.client.exception.YelpFusionException;
import org.example.yelp.fusion.client.exception.YelpRequestLogger;
import org.example.yelp.fusion.client.transport.YelpRestTransport;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings({"ConstantConditions", "unused"})
public class RequestTest extends AbstractRequestTestCase {
    private static final Log logger = LogFactory.getLog(RequestTest.class);
    static Map<String, List<String>> categoriesMap;

    static int docsCount;
    static Set<String> setOfBusinessIds;
    static int indexOf;
    static String categoryName;


    @BeforeAll
    static void setup() {

        loadCategoriesMap();

        categoryName = "japanese";

//        assertThat(docsCount).isEqualTo(setOfBusinessIds.size());

        int numberOfIdsInValues = categoriesMap.values().stream().mapToInt(List::size).sum();

        indexOf = categoriesMap.keySet().stream().toList().indexOf(categoryName);

        logger.info(PrintUtils.cyan("category name = " + categoryName + " number of businesses = " + categoriesMap.get(categoryName).size() + " index position = " + indexOf));

    }

    private static void loadCategoriesMap() {

        docsCount = elasticSearch.getDocsCount(indexNyc);

        logger.info(PrintUtils.green("index: " + indexNyc + " docs count: " + docsCount));

        categoriesMap = new LinkedHashMap<>();

        Map<String, List<String>> map = new LinkedHashMap<>();

        int maxResults = 10000;

        String timestamp = elasticSearch.getTimestamp("1", SortOrder.Asc);

        assertThat(timestamp).isEqualTo("2022-11-10T07:16:35.187452598Z");

        int counter = docsCount;

        while (timestamp != null) {
            map.putAll(elasticSearch.getCategoriesMap(indexNyc, maxResults, timestamp));
            timestamp = elasticSearch.getTimestamp();
            logger.info("timestamp = " + timestamp + " keys in categories = " + categoriesMap.keySet().size());
        }
        setOfBusinessIds = elasticSearch.getSetOfBusinessIds();

        logger.info(
                " total categories " + map.values().size() +
                        " total business id's = " + setOfBusinessIds.size());

        map.remove("restaurants");
        map.remove("nightlife");
        map.remove("food");


        categoriesMap = map.entrySet().stream().sorted(comparingInt(e -> e.getValue().size()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
    }

    void termsAggregationTest() {
        String queryName = "categories";

        Query query = MatchAllQuery.of(m -> m
                .queryName(queryName)
        )._toQuery();


        String field = "categories.alias";
        SearchResponse<Void> searchResponse = null;
        TermsAggregation termsAggregation = TermsAggregation.of(a -> a
                .field(field));

        Aggregation aggs = Aggregation.of(a -> a.terms(termsAggregation));

        Map<String, Aggregation> aggsMap = Map.of("categories-aggregation", aggs);

        try {
            searchResponse = elasticSearch.client().search(_1 -> _1
                            .index(indexNyc)
                            .size(0)
                            .aggregations(aggsMap),
                    Void.class
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void businessSearchTest() throws Exception {
        int docsCount = elasticSearch.getDocsCount(indexNyc);

        assertThat(indexNyc).isEqualTo("yelp-businesses-restaurants-nyc");
        int maxTotal = 1000;
        int radius = 7000;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";
        String location = "new+york+city";

        Map<String, Coordinates> neighborHoodCoordinates = Map.of("manhattan", new Coordinates(40.713135, -74.004044),
                "brooklyn", new Coordinates(40.692436, -73.990372),
                "bronx", new Coordinates(40.713272, -73.828461),
                "statenisland", new Coordinates(40.642269, -74.076385),
                "queens", new Coordinates(40.713272, -73.828461)
//                "chinatown", new Coordinates(40.7158, -73.9970),
//                "littleitaly", new Coordinates(40.7191, -73.9973),
//                "chelsea", new Coordinates(40.7465, -74.0014),
//                "nolita", new Coordinates(40.7230, -73.9949),
//                "harlem", new Coordinates(40.8116, -73.9465),
//                "soho", new Coordinates(40.7246, -74.0019)
        );

        for (String category : categoriesMap.keySet().stream().skip(indexOf).toList()) {
            // reset offset, total, count, maxOffset
            int offset = 0;
            int maxOffset = 0;
            Integer total;
            int count = 0;

            List<String> listOfNeighborHoodKeys = List.of("manhattan", "bronx", "brooklyn", "queens", "statenisland");

            String neighborhood = listOfNeighborHoodKeys.get(2);

            do {
                final int finalOffset = offset;
                count++;
                PrintUtils.cyan("location = " + neighborhood +
                        " category = " + category +
                        " offset = " + offset);

                Coordinates p = neighborHoodCoordinates.get(neighborhood);

                try {

                    BusinessSearchResponse<Business> businessSearchResponse = yelpClient.businessSearch(s -> s
                                    .location(neighborhood)
                                    .coordinate(coord -> coord
                                            .geo_coordinates(geo -> geo
                                                    .latitude(p.getLatitude())
                                                    .longitude(p.getLongitude()))
                                    )
                                    .term(term)
                                    .categories(c -> c
                                            .alias(category))
                                    .sort_by(sort_by)
                                    .offset(finalOffset)
                                    .limit(limit)
                                    .radius(radius)
                            , Business.class);

                    assertThat(businessSearchResponse).isNotNull();
                    assertThat(businessSearchResponse.total()).isGreaterThan(0);

                    logger.info("business search response: " + businessSearchResponse.total());

                    total = businessSearchResponse.total();
                    YelpRestTransport yelpTransport = (YelpRestTransport) yelpClient._transport();


                    if (businessSearchResponse == null || total == null) {
                        logger.info("null response " + businessSearchResponse);
                    } else {


                        if (total == 0) {
                            YelpRequestLogger.logEmptyResponseBody(logger, businessSearchResponse);
                        } else {

                            if (businessSearchResponse.hits() != null && businessSearchResponse.total() > 0) {
                                List<Hit<Business>> businesses = businessSearchResponse.hits();
                                for (Hit<Business> hit : businesses) {
                                    Business business = hit.source();
                                    logger.info(PrintUtils.green("category = " + category + " lat/long/distance = " +
                                            business.coordinates() + "/" + business.location() + "/" + business.distance() +
                                            " offset = " + offset + " total results = " + total + " number of businesses " + businesses.size()));

                                    Set<String> notIndexed = new HashSet<>();

                                    String businessId = business.id();

                                    if (setOfBusinessIds.add(businessId)) {
                                        notIndexed.add(businessId);
                                    }

                                    if (notIndexed.size() > 0) {
                                        logger.info(PrintUtils.cyan("number of ids not in database  = " + notIndexed.size()));

                                        indexBusiness(notIndexed);
                                    }

                                }
                            }
                        }
                    }
                } catch (YelpFusionException e) {
                    throw new RuntimeException(e);
                }
                offset += limit;
            } while (total > 0 && offset <= (total + limit)) ; // iterate up to 20 50-business pages

        }
    }

    static Integer total;

    private void indexBusiness(Set<String> setOfBusinessIdsToAdd) throws IOException {

        BusinessDetailsResponse<Business> businessDetailsResponse = null;

        BulkRequest.Builder br = new BulkRequest.Builder();


        for (String businessId : setOfBusinessIdsToAdd) {

            logger.info(PrintUtils.red("number of ids being added = " + setOfBusinessIdsToAdd.size()));

            String formattedId = businessId.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");
            logger.info(PrintUtils.red(" formattedId  = " + formattedId));

            businessDetailsResponse = yelpClient.businessDetails(s -> s
                            .id(formattedId),
                    Business.class);

            if (businessDetailsResponse == null) {
                YelpRequestLogger.logResponseError(logger, businessDetailsResponse);
            } else {

                if (businessDetailsResponse.total() != null && businessDetailsResponse.hits() != null) {

                    List<Hit<Business>> business = businessDetailsResponse.hits();


                    String documentId = indexNyc + "-" + businessId;

                    br.operations(op -> op
                            .index(idx -> idx
                                    .index(indexNyc)
                                    .id(documentId)
                                    .pipeline(timestampPipeline) // add timestamp
                                    .document(business)
                            )
                    );
                } else {
                    logger.info(" response body is empty ");
                }
            }
        }

        BulkResponse result = elasticSearch.client().bulk(br.build());

        if (result.errors()) {
            logger.error("Bulk had errors");
            for (BulkResponseItem item : result.items()) {
                if (item.error() != null) {
                    YelpRequestLogger.logFailedElasticsearchRequest(logger, businessDetailsResponse);
                    logger.error(item.error().reason());
                }
            }
        }
        logger.info(PrintUtils.red("indexed "));
    }

    @AfterAll
    static void afterAll() {
        logger.info(PrintUtils.red("index : [" + indexOf + "] = " + categoryName + "" +
                "Store latitude, longitude, and distance from most recent BusinessSearchRequest "));
    }

    static class Coordinates {

        public Double latitude, longitude;


        public Coordinates(){}

        public Coordinates(double v, double v1) {
            this.latitude = v;
            this.longitude = v1;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
}
