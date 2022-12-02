package io.github.yelp.fusion.stewseo.client.end_to_end;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;


import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.lowlevel.restclient.YelpFusionRestClient;
import io.github.yelp.fusion.stewseo.client.ElasticsearchRequestTestCase;
import io.github.stewseo.yelp.fusion.client.json.JsonData;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.yelpfusion.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.BusinessSearchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.BusinessSearchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.BusinessSearch;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings({"unused"})
public class RequestTest extends ElasticsearchRequestTestCase {
    private static final Logger logger = LoggerFactory.getLogger(RequestTest.class);

    static YelpFusionClient yelpClient;

    private static final String indexNyc = "yelp-businesses-restaurants-nyc";


    private static void initYelpFusionClient() {
        String yelpFusionHost = "api.yelp.com";
        int port = 80;
        HttpHost httpHost = new HttpHost(yelpFusionHost, port, "http");
        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        YelpFusionRestClient yelpFusionRestClient = YelpFusionRestClient.builder(
                        httpHost)
                .setMetaHeaderEnabled(false)
                .setDefaultHeaders(defaultHeaders)
                .build();


        JsonpMapper mapper = new JacksonJsonpMapper();

        YelpRestClientTransport yelpTransport;
        try {
            yelpTransport = new YelpRestClientTransport(yelpFusionRestClient, mapper);
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

        categoryName = "burgers";

        assertThat(docsCount-2).isEqualTo(setOfBusinessIds.size());

        int numberOfIdsInValues = categoriesMap.values().stream().mapToInt(List::size).sum();

        indexOf = categoriesMap.keySet().stream().toList().indexOf(categoryName);

        logger.info(PrintUtils.cyan("category name = " + categoryName + " number of businesses = " + categoriesMap.get(categoryName).size() + " index position = " + indexOf));

        initYelpFusionClient();

    }

    private static void loadCategoriesMap() {
        initElasticsearchClient();
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
            logger.info("timestamp = " + timestamp + " keys in categories = " + map.keySet().size());
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

    Set<BusinessSearchRequest> unindexed;
    @Test
    public void businessSearchTest() {

        assertThat(indexNyc).isEqualTo("yelp-businesses-restaurants-nyc");
        int maxTotal = 1000;
        int radius = 5000;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";

        Map<String, Coordinates_> neighborHoodCoordinates = Map.of("manhattan", new Coordinates_(40.713135, -74.004044),
                "brooklyn", new Coordinates_(40.692436, -73.990372),
                "bronx", new Coordinates_(40.713272, -73.828461),
                "statenisland", new Coordinates_(40.642269, -74.076385),
                "queens", new Coordinates_(40.713272, -73.828461)
//                "chinatown", new Coordinates(40.7158, -73.9970),
//                "littleitaly", new Coordinates(40.7191, -73.9973),
//                "chelsea", new Coordinates(40.7465, -74.0014),
//                "nolita", new Coordinates(40.7230, -73.9949),
//                "harlem", new Coordinates(40.8116, -73.9465),
//                "soho", new Coordinates(40.7246, -74.0019)
        );

        List<String> categories = categoriesMap.keySet().stream().toList();
        categories.forEach(System.out::println);

        for (String category : categories) {

            List<String> listOfNeighborHoodKeys = List.of("manhattan", "bronx", "brooklyn", "queens", "statenisland");

            for (String neighborhood : listOfNeighborHoodKeys) {
                // reset offset, total, count, maxOffset
                Integer offset = 0;
                Integer total;

                do {
                    final int finalOffset = offset;

                    Coordinates_ p = neighborHoodCoordinates.get(neighborhood);

                    PrintUtils.cyan("location = " + neighborhood +
                            " category = " + category +
                            " offset = " + offset +
                            " latitude: " + p.latitude +
                            " longitude: " + p.longitude);

                    try {

                        BusinessSearchResponse businessSearchResponse = yelpClient.businessSearch(s -> s
                                .location(neighborhood)
                                .coordinates(coord -> coord
                                        .latitude(p.latitude)
                                        .longitude(p.longitude))
                                .term(term)
                                .categories(c -> c
                                        .alias(category))
                                .limit(limit)
                                .offset(finalOffset)
                                .sort_by(sort_by)
                                .radius(radius)
                        );

                        assertThat(businessSearchResponse).isNotNull();

                        total = businessSearchResponse.total();
                        assertThat(total).isNotNull();

                        if (total > 0 && businessSearchResponse.businesses().size() > 0) {

                            List<BusinessSearch> businesses = businessSearchResponse.businesses();

                            BulkRequest.Builder br = new BulkRequest.Builder();
                            BusinessDetailsResponse businessDetailsResponse;

                            for (BusinessSearch businessSearch : businesses) {
                                String businessId = businessSearch.id();
//                                String formattedId = businessId.replaceAll("[^A-Za-z0-9_-]", "");

                                if (setOfBusinessIds.add(businessId)) {

                                    // submit request to Business Details Endpoint
                                    businessDetailsResponse = yelpClient.businessDetails(s -> s.id(businessId));

                                    Business business = businessDetailsResponse.result().get(0);

                                    assertThat(business).isNotNull();

                                    int jsonStart = business.toString().indexOf("{");
                                    String withoutClassName = business.toString().substring(jsonStart);

                                    assertThat(withoutClassName.codePointAt(0)).isEqualTo("{".hashCode());

                                    Reader input = new StringReader(withoutClassName);


                                    IndexRequest<JsonData> request = IndexRequest.of(i -> i
                                            .index(indexNyc)
                                            .id(indexNyc + "-" + businessId)
                                            .pipeline(timestampPipeline) // add timestamp
                                            .withJson(input)
                                    );

                                    IndexResponse response = elasticSearch.client().index(request);

                                }
                            }
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    offset += limit;

                } while (offset <= (total + limit) && total > 0); // iterate up to 20 50-business pages
            }
        }
    }

    static Integer indexed;

    @AfterAll
    static void afterAll() {
        try {
            elasticSearch.client()._transport().close();
            yelpClient._transport().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void termsAggregationTest() {
        String queryName = "categories";

        Query query = MatchAllQuery.of(m -> m
                .queryName(queryName)
        )._toQuery();


        String field = "categories.alias";
        SearchResponse<Void> searchResponse;
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

    static class Coordinates_ {

        public Double latitude, longitude;


        public Coordinates_(){}

        public Coordinates_(double v, double v1) {
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
