package io.github.yelp.fusion.stewseo.client.end_to_end;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;


import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

@SuppressWarnings({"unused"})
public class RequestTest extends ElasticsearchRequestTestCase {
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

        YelpFusionRestClient restClient = YelpFusionRestClient.builder(apiKey).build();

        YelpRestClientTransport yelpTransport = new YelpRestClientTransport(restClient, new JacksonJsonpMapper());

        yelpClient = new YelpFusionClient(yelpTransport);
    }

    static List<StringTermsBucket> termsAggregationByCategory;
    private static void loadCategoriesMap() {
        initElasticsearchClient();
        docsCount = elasticSearch.getDocsCount(indexNyc);

        logger.info(PrintUtils.green("index: " + indexNyc + " docs count: " + docsCount));

        termsAggregationByCategory = elasticSearch.getStringTermsBuckets();
        logger.info(PrintUtils.debug("number of categories with at least 1 restaurant: " + termsAggregationByCategory.size()));

        for(StringTermsBucket bucket: termsAggregationByCategory) {
            logger.info("Category: " + bucket.key().stringValue() + ", document count: " + bucket.docCount());;
        }

        String timestamp = elasticSearch.getTimestamp("1", SortOrder.Asc);
        assertThat(timestamp).isEqualTo("2022-11-10T07:16:35.187452598Z");
        setOfBusinessIds = new HashSet<>();

        int iterations = (int) Math.ceil((double)docsCount / 10000);

        for(int j = 0; j < iterations; j++) {

            List<Hit<ObjectNode>> nodes = elasticSearch.getBusinessIdsWithTimestamps(timestamp);

            for (int i = 0; i < nodes.size(); i++) {
                JsonNode sourceNode = nodes.get(i).source();
                setOfBusinessIds.add(sourceNode.get("id").asText());
                if (i + 1 == nodes.size()) {
                    timestamp = sourceNode.get("timestamp").asText();
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

    Set<BusinessSearchRequest> unindexed;
    @Test
    public void businessSearchTest() {
        assertThat(indexNyc).isEqualTo("yelp-businesses-restaurants-nyc");

        int radius = 6000;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";

        Map<String, Coordinates_> neighborHoodCoordinates = Map.of("manhattan", new Coordinates_(40.713135, -74.004044),
                "brooklyn", new Coordinates_(40.692436, -73.990372),
                "bronx", new Coordinates_(40.713272, -73.828461),
                "statenisland", new Coordinates_(40.642269, -74.076385),
                "queens", new Coordinates_(40.713272, -73.828461)
        );
        Map<String, Coordinates_> brooklynPizza = Map.of(
                "l-industrie", new Coordinates_(40.7116, -73.9579),
                "lucali", new Coordinates_(40.692436, -73.990372),
                "luigis", new Coordinates_(40.713272, -73.828461),
                "lombardos", new Coordinates_(40.642269, -74.076385),
                "DiFaras", new Coordinates_( 40.625057, -73.961517),
                "brooklyn-dop", new Coordinates_( 40.6250, -73.9615),
                "l-and-b-spumoni-gardens", new Coordinates_( 40.6703,  -73.9584)
        );
        
        List<String> list = termsAggregationByCategory.stream()
                .map(StringTermsBucket::key)
                .map(FieldValue::stringValue)
                .toList();

        for (String category : list) {

            Set<String> neighborHoods = neighborHoodCoordinates.keySet();

            for (Coordinates_ coords : brooklynPizza.values().stream().skip(2).toList()) {
                // reset offset, total,
                Integer offset = 0;
                Integer total;
                Double distance = null;
                do {

                    final Integer finalOffset = offset; // update final Integer to current offset
                    Double latitude = coords.getLatitude(); // latitude of neighborhood
                    Double longitude = coords.getLongitude(); // longitude of neighborhood

                    try {
                        BusinessSearchResponse businessSearchResponse = yelpClient.businessSearch(s -> s
                                .location("brooklyn")
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

                        int count = 0;
                        if (total > 0 && total <= 1000) {

                            List<BusinessSearch> listOfBusinessSearch = businessSearchResponse.businesses();

                            BulkRequest.Builder br = new BulkRequest.Builder();
                            BusinessDetailsResponse businessDetailsResponse;
                            logger.info(PrintUtils.debug(PrintUtils.cyan(" category: " + category + ", total: " + total + ",\n" + " distance: " + distance + ", region: " + businessSearchResponse.region().center() + PrintUtils.green("\ntrying to add : " + listOfBusinessSearch.size() + " BusinessSearch results. Current offset: " + finalOffset))));

                            for (BusinessSearch businessSearch : listOfBusinessSearch) {
                                distance = businessSearch.distance();
                                String businessId = businessSearch.id();
                                String formattedId = businessId.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");

                                if (setOfBusinessIds.add(formattedId)) {
                                    count++;
                                    logger.info(PrintUtils.debug("adding business with id: " + formattedId + " business id: " + businessId));
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
                                    logger.info(PrintUtils.debug("added version: " + response.version()));
                                    assertThat(response.result().name().equalsIgnoreCase("created"));
                                }
                            }
                        } else {
                            String path = this.getClass().getResource("categories-gt-max.json").getPath();
                            // the second parameter specifies whether the file should be appended
                            try (OutputStream fos = new FileOutputStream(new File(path), true)) {

                                AtomicInteger i = new AtomicInteger();

                                int index = termsAggregationByCategory.stream()
                                        .map(StringTermsBucket::key)
                                        .peek(v -> i.incrementAndGet())
                                        .anyMatch(user ->
                                                user.stringValue().equals(category)) ? i.get() - 1 : -1;
                                JsonFactory factory = new JsonFactory();
                                JsonGenerator generator = factory.createGenerator(new File("post.json"), JsonEncoding.UTF8);

                                generator.writeStartObject();
//                            generator.writeStartArray("first category returning > 1000 results");
                                generator.writeStringField("category", category);
                                generator.writeNumberField("index", index);
                                generator.writeStringField("location", "nyc");
                                generator.writeNumberField("distance", distance);
                                generator.writeNumberField("latitude", latitude);
                                generator.writeNumberField("longitude", longitude);
                                generator.writeEndObject();

                            }
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
