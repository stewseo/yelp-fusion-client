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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.lowlevel.restclient.YelpFusionRestClient;
import io.github.stewseo.yelp.fusion.client.Elasticsearch;
import io.github.yelp.fusion.stewseo.client.ElasticsearchRequestTestCase;
import io.github.stewseo.yelp.fusion.client.json.JsonData;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.yelpfusion.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.BusinessSearchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.BusinessSearchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.BusinessSearch;
import io.github.yelp.fusion.stewseo.client.YelpRequestTestCase;
import jakarta.json.stream.JsonGenerator;
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

import java.io.*;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

        categoryName = "japanese";

        assertThat(docsCount-5).isEqualTo(setOfBusinessIds.size());

        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionRestClient restClient = YelpFusionRestClient.builder(apiKey).build();

        YelpRestClientTransport yelpTransport = new YelpRestClientTransport(restClient, new JacksonJsonpMapper());

        yelpClient = new YelpFusionClient(yelpTransport);

    }
    static List<StringTermsBucket> numberOfDocumentsPerCategory;
    private static void loadCategoriesMap() {
        initElasticsearchClient();
        docsCount = elasticSearch.getDocsCount(indexNyc);

        logger.info(PrintUtils.green("index: " + indexNyc + " docs count: " + docsCount));

        numberOfDocumentsPerCategory = elasticSearch.getStringTermsBuckets();
        logger.info(PrintUtils.debug("number of categories with at least 1 restaurant: " + numberOfDocumentsPerCategory.size()));

        for(StringTermsBucket bucket: numberOfDocumentsPerCategory) {
            logger.info("Category: " + bucket.key().stringValue() + ", document count: " + bucket.docCount());;
        }

        String timestamp = elasticSearch.getTimestamp("1", SortOrder.Asc);
        assertThat(timestamp).isEqualTo("2022-11-10T07:16:35.187452598Z");
        setOfBusinessIds = new HashSet<>();

        int iterations = (int) Math.ceil((double)docsCount / 10000);

        for(int j = 0; j < iterations; j++) {

            List<Hit<ObjectNode>> nodes = elasticSearch.getSetOfBusinessIds(timestamp);

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

        List<String> list = numberOfDocumentsPerCategory.stream().map(StringTermsBucket::key).map(FieldValue::stringValue).toList();

        for (String category : list) {

            Set<String> neighborHoods = neighborHoodCoordinates.keySet();

            for (String neighborhood : neighborHoods) {
                // reset offset, total,
                Integer offset = 0;
                Integer total;
                do {


                    final Integer finalOffset = offset; // update final Integer to current offset

                    Double latitude = neighborHoodCoordinates.get(neighborhood).getLatitude(); // latitude of neighborhood
                    Double longitude = neighborHoodCoordinates.get(neighborhood).getLongitude(); // longitude of neighborhood

                    try {
                        BusinessSearchResponse businessSearchResponse = yelpClient.businessSearch(s -> s
                                .location(neighborhood)
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
                        if (total > 0 && businessSearchResponse.businesses().size() > 0) {

                            List<BusinessSearch> listOfBusinessSearch = businessSearchResponse.businesses();

                            BulkRequest.Builder br = new BulkRequest.Builder();
                            BusinessDetailsResponse businessDetailsResponse;
                            logger.info(PrintUtils.debug(" category: " + category + ", trying to add : " + listOfBusinessSearch.size() + " BusinessSearch results. Current offset: " + finalOffset));
                            for (BusinessSearch businessSearch : listOfBusinessSearch) {
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
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    if (total > 1000 && offset >= 1000) {
                        String path = this.getClass().getResource("categories-gt-max").getPath();
                        // the second parameter specifies whether the file should be appended
                        try (OutputStream fos = new FileOutputStream(new File(path), true)) {

                            JsonGenerator generator = mapper.jsonProvider().createGenerator(fos);
                            generator.writeStartObject();

                            generator.writeStartArray("Categories and Params returning greater than 1000");
                            generator.write("category", category);
                            generator.write("location", neighborhood);
                            generator.write("radius", radius);
                            generator.write("latitude", latitude);
                            generator.write("longitude", longitude);
                            generator.writeEnd();

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        total = 0;
                    }

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
