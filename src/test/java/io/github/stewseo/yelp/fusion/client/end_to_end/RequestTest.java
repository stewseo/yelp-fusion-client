package io.github.stewseo.yelp.fusion.client.end_to_end;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.lowlevel.restclient.RestClient;
import io.github.stewseo.yelp.fusion.client.ElasticsearchConnection;
import io.github.stewseo.yelp.fusion.client.json.JsonData;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.details.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.BusinessSearch;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.BusinessSearchResponse;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings({"unused"})
public class RequestTest extends ElasticsearchConnection {
    private static final Logger logger = LoggerFactory.getLogger(RequestTest.class);

    static YelpFusionClient yelpClient;

    private static final String indexNyc = "yelp-businesses-restaurants-nyc";

    static Map<String, List<String>> categoriesMap;
    static int docsCount;
    static Set<String> setOfBusinessIds;

    static int indexOf;
    static String categoryName;

    @BeforeAll
    static void setup() throws IOException {

        loadCategoriesMap();

        categoryName = "vegan";

        assertThat(docsCount-5).isEqualTo(setOfBusinessIds.size());

        String apiKey = System.getenv("YELP_API_KEY");

        RestClient restClient = RestClient.builder(apiKey).build();

        YelpRestClientTransport yelpTransport = new YelpRestClientTransport(restClient, new JacksonJsonpMapper());

        yelpClient = new YelpFusionClient(yelpTransport);
    }

    static List<StringTermsBucket> termsAggregationByCategory;
    private static void loadCategoriesMap() {
        initElasticsearchClient();
        docsCount = elasticSearch.getDocsCount(indexNyc);

        logger.info(PrintUtils.green("index: " + indexNyc + " docs count: " + docsCount));

        termsAggregationByCategory = elasticSearch.getStringTermsBuckets();
        logger.info(PrintUtils.debug("number of all with at least 1 restaurant: " + termsAggregationByCategory.size()));

        for(StringTermsBucket bucket: termsAggregationByCategory) {
            logger.info("Category: " + bucket.key().stringValue() + ", document count: " + bucket.docCount());
        }

        String timestamp = elasticSearch.getTimestamp("1", SortOrder.Asc);

        assertThat(timestamp).isEqualTo("2022-11-10T07:16:35.187452598Z");

        setOfBusinessIds = new HashSet<>();

        int iterations = (int) Math.ceil((double)docsCount / 10000);

        for(int j = 0; j < iterations; j++) {

            List<Hit<ObjectNode>> nodes = elasticSearch.getBusinessIdsWithTimestamps(timestamp);

            for (int i = 0; i < nodes.size(); i++) {
                JsonNode sourceNode = nodes.get(i).source();

                if(sourceNode != null) {
                    setOfBusinessIds.add(sourceNode.get("id").asText());
                    if (i + 1 == nodes.size()) {
                        timestamp = sourceNode.get("timestamp").asText();
                    }

                }

            }
        }

//        map.remove("restaurants");
//        map.remove("bars");
//        map.remove("nightlife");
//        map.remove("food");
//        map.remove("newamerican");

//        categoriesMap = map.entrySet().stream().sorted(comparingInt(e -> e.getValue().size()))
//                .collect(toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (a, b) -> {
//                            throw new AssertionError();
//                        },
//                        LinkedHashMap::new
//                ));
    }

    @Test
    public void businessSearchTest() {
        assertThat(indexNyc).isEqualTo("yelp-businesses-restaurants-nyc");

        int radius = 6000;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";

        Map<String, Coordinates_> neighborHoodCoordinates = Map.of(
                "manhattan", new Coordinates_(40.713135, -74.004044),
                "manhattan-upper-west", new Coordinates_(40.713135, -74.004044),
                "bronx-1", new Coordinates_(40.713135, -74.004044),
                "brooklyn", new Coordinates_(40.692436, -73.990372),
                "bronx-2", new Coordinates_(40.713272, -73.828461),
                "statenisland", new Coordinates_(40.642269, -74.076385),
                "queens", new Coordinates_(40.713272, -73.828461)
        );

        List<String> list = termsAggregationByCategory.stream()
                .map(StringTermsBucket::key)
                .map(FieldValue::stringValue)
                .skip(2)
                .toList();

        for (String category : list) {

            Set<String> neighborHoods = neighborHoodCoordinates.keySet();

            for (Map.Entry<String, Coordinates_> entry : neighborHoodCoordinates.entrySet()) {
                // reset offset, total,
                Integer offset = 0;
                Integer total;
                Double distance = null;
                do {
                    String location = entry.getKey();
                    final Integer finalOffset = offset; // update final Integer to current offset
                    Double latitude = entry.getValue().getLatitude(); // latitude of neighborhood
                    Double longitude = entry.getValue().getLongitude(); // longitude of neighborhood

                    try {
                        BusinessSearchResponse businessSearchResponse = yelpClient.businesses().businessSearch(s -> s
                                .location(location)
                                .coordinates(c -> c
                                        .latitude(latitude)
                                        .longitude(longitude))
                                .term(term)
                                .categories(cat -> cat
                                        .alias(category))
                                .limit(limit)
                                .offset(finalOffset)
                                .sort_by(sort_by)
                                .radius(radius) // meters
                        );

                        assertThat(businessSearchResponse).isNotNull();
                        total = businessSearchResponse.total();
                        assertThat(total).isNotNull();

                        if (total > 0 && finalOffset <= 1000) {

                            List<BusinessSearch> listOfBusinessSearch = businessSearchResponse.businesses();

                            BulkRequest.Builder br = new BulkRequest.Builder();
                            BusinessDetailsResponse businessDetailsResponse;
                            logger.info(PrintUtils.debug(PrintUtils.cyan(" category: " + category + ", total: " + total + ", location: " + location + ", distance: " + distance + ", region: " + businessSearchResponse.region().center() + PrintUtils.green("\ntrying to add : " + listOfBusinessSearch.size() + " BusinessSearch results. Current offset: " + finalOffset))));

                            for (BusinessSearch businessSearch : listOfBusinessSearch) {
                                distance = businessSearch.distance();
                                String businessId = businessSearch.id();

                                String formattedId = businessId.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");

                                if (setOfBusinessIds.add(formattedId)) {

                                    logger.info(PrintUtils.debug("adding business with id: " + formattedId + " business id: " + businessId));
                                    // submit request to Business Details Endpoint
                                    businessDetailsResponse = yelpClient.businesses().businessDetails(s -> s.id(businessId));

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
                                    logger.info(PrintUtils.debug("added version: " + response.version()));
                                    assertThat(response.result().name()).isEqualTo("Created");
                                }
                            }
                        } else {
                            // document query parameters and update category to next
                            offset = 0;

                            ObjectMapper mapper = new ObjectMapper();

                            AtomicInteger i = new AtomicInteger();

                            int index = termsAggregationByCategory.stream()
                                    .map(StringTermsBucket::key)
                                    .peek(v -> i.incrementAndGet())
                                    .anyMatch(user ->
                                            user.stringValue().equals(category)) ? i.get() - 1 : -1;

                            File file = new File(this.getClass().getResource("all-gt-max.txt").getPath());

                            JsonObjectBuilder builder = Json.createObjectBuilder();
                            builder.add("category", category);
                            builder.add("location", location);
                            builder.add("latitude", latitude);
                            builder.add("longitude", longitude);
                            JsonObject jsonObject = builder.build();

                            mapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonObject);

                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    offset += limit;


                } while (total > 0 && (offset + limit) <= 1000); // iterate up to 20 50-business pages
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
        String queryName = "all";

        Query query = MatchAllQuery.of(m -> m
                .queryName(queryName)
        )._toQuery();


        String field = "all.alias";
        SearchResponse<Void> searchResponse;
        TermsAggregation termsAggregation = TermsAggregation.of(a -> a
                .field(field));

        Aggregation aggs = Aggregation.of(a -> a.terms(termsAggregation));

        Map<String, Aggregation> aggsMap = Map.of("all-aggregation", aggs);

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
