package io.github.yelp.fusion.client.end_to_end;


import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;

import io.github.yelp.fusion.client.AbstractRequestTestCase;
import io.github.yelp.fusion.client.YelpFusionClient;
import io.github.yelp.fusion.client.business.BusinessDetailsRequest;
import io.github.yelp.fusion.client.business.BusinessDetailsResponse;
import io.github.yelp.fusion.client.business.BusinessSearchRequest;
import io.github.yelp.fusion.client.business.BusinessSearchResponse;
import io.github.yelp.fusion.client.exception.YelpFusionException;
import io.github.yelp.fusion.client.exception.YelpRequestLogger;
import io.github.yelp.fusion.client.transport.YelpRestTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import io.github.elasticsearch.client.json.JsonpMapper;
import io.github.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import io.github.lowlevel.restclient.PrintUtils;
import io.github.lowlevel.restclient.RestClient;
import io.github.lowlevel.restclient.RestClientBuilder;
import io.github.yelp.fusion.client.business.model.Business;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings({"unused"})
public class RequestTest extends AbstractRequestTestCase {
    private static final Logger logger = LoggerFactory.getLogger(RequestTest.class);

    static YelpFusionClient yelpClient;

    private static void initYelpFusionClient() throws IOException {
        String yelpFusionHost = "api.yelp.com";
        int port = 80;
        HttpHost httpHost = new HttpHost(yelpFusionHost, port, "http");
        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        RestClientBuilder builder = RestClient.builder(
                        httpHost)
                .setMetaHeaderEnabled(false)
                .setUserAgentEnable(false)
                .setDefaultHeaders(defaultHeaders);

        RestClient restClient = builder.build();

        JsonpMapper mapper = new JacksonJsonpMapper();

        YelpRestTransport yelpTransport = null;
        try {
            yelpTransport = new YelpRestTransport(restClient, mapper);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        yelpClient = new YelpFusionClient(yelpTransport);
    }

    static Map<String, List<String>> categoriesMap;
    static int docsCount;
    static Set<String> setOfBusinessIds;
    static int indexOf;
    static String categoryName;

    @BeforeAll
    static void setup() throws IOException {

        loadCategoriesMap();

        categoryName = "bagels";

        assertThat(docsCount-1).isEqualTo(setOfBusinessIds.size());

        int numberOfIdsInValues = categoriesMap.values().stream().mapToInt(List::size).sum();

        indexOf = categoriesMap.keySet().stream().toList().indexOf(categoryName);

        logger.info(PrintUtils.cyan("category name = " + categoryName + " number of businesses = " + categoriesMap.get(categoryName).size() + " index position = " + indexOf));

        initYelpFusionClient();

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

        map.remove("restaurants");
        map.remove("bars");
        map.remove("nightlife");
        map.remove("food");
        map.remove("newamerican");

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

        assertThat(indexNyc).isEqualTo("yelp-businesses-restaurants-nyc");
        int maxTotal = 1000;
        int radius = 6000;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";

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
        List<String> categories = categoriesMap.keySet().stream().skip(indexOf).toList();
        for (String category : categories) {
            // reset offset, total, count, maxOffset
            int offset = 0;
            int maxOffset = 0;
            Integer total;
            int count = 0;

            List<String> listOfNeighborHoodKeys = List.of("manhattan", "bronx", "brooklyn", "queens", "statenisland");
            for (String neighborhood : listOfNeighborHoodKeys) {

                do {
                    final int finalOffset = offset;
                    count++;

                    Coordinates p = neighborHoodCoordinates.get(neighborhood);

                    PrintUtils.cyan("location = " + neighborhood +
                            " category = " + category +
                            " offset = " + offset +
                            " latitude: " + p.latitude +
                            " longitude: " + p.longitude);

                    try {

                        BusinessSearchResponse<Business> businessSearchResponse = yelpClient.businessSearch(s -> s
                                        .location(neighborhood)
                                        .coordinates(coord -> coord
                                                .geo_coordinates(geo -> geo
                                                        .latitude(p.latitude)
                                                        .longitude(p.longitude)))
                                        .term(term)
                                        .categories(c -> c
                                                .alias(category))
                                        .limit(limit)
                                        .offset(finalOffset)
                                        .sort_by(sort_by)
                                        .radius(radius)
                                ,
                                Business.class
                        );

                        assertThat(businessSearchResponse).isNotNull();

                        total = businessSearchResponse.total();

                        if (businessSearchResponse.hits() != null && businessSearchResponse.hits().size() > 0) {
                            List<Business> businesses = businessSearchResponse.hits();
                            Set<String> notIndexed = new HashSet<>();

                            for (Business business : businesses) {

                                String businessId = business.id();

                                if (setOfBusinessIds.add(businessId)) {
                                    notIndexed.add(businessId);
                                }
                            }
                            if (notIndexed.size() > 0) {
                                logger.info(PrintUtils.cyan("indexing " + notIndexed.size() + " businesses"));

                                indexBusiness(notIndexed);
                            }

                        } else {
                            YelpRequestLogger.logEmptyResponseBody(logger, businessSearchResponse);
                        }

                    } catch (Exception e) {
                        if (e instanceof YelpFusionException) {

                            BusinessSearchRequest request = BusinessSearchRequest.of(s -> s
                                    .location(neighborhood)
                                    .coordinates(coord -> coord
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
                            );
                            YelpRequestLogger.logFailedRequest(logger, request);
                        }
                        throw new RuntimeException(e);
                    }

                    offset += limit;
                } while (total != 0 && offset <= (total + limit)); // iterate up to 20 50-business pages
            }
        }
    }


    static Integer total;

    private void indexBusiness(Set<String> setOfBusinessIdsToAdd) throws Exception {

        BusinessDetailsResponse businessDetailsResponse = null;

        BulkRequest.Builder br = new BulkRequest.Builder();


        for (String businessId : setOfBusinessIdsToAdd) {

            String formattedId = businessId.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");
            logger.info(PrintUtils.red(" formattedId  = " + formattedId));

            BusinessDetailsRequest businessDetailsRequest = BusinessDetailsRequest.of(s -> s
                    .id(formattedId));

            businessDetailsResponse = yelpClient.businessDetails(businessDetailsRequest);


            if (businessDetailsResponse != null && businessDetailsResponse.result() != null) {

                List<Business> business = businessDetailsResponse.result();

                String documentId = indexNyc + "-" + businessId;

                br.operations(op -> op
                        .index(idx -> idx
                                .index(indexNyc)
                                .id(documentId)
                                .pipeline(timestampPipeline) // add timestamp
                                .document(business)
                        )
                );
            }
            else {
                YelpRequestLogger.logFailedRequest(logger, businessDetailsRequest);
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
        try {
            elasticSearch.client()._transport().close();
            yelpClient._transport().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
