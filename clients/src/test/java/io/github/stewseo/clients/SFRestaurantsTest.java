package io.github.stewseo.clients;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.elasticsearch.ElasticsearchTestCase;
import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SFRestaurantsTest extends YelpFusionClientTestCase {

    private ElasticsearchAsyncClient esAsyncClient;
    private static YelpFusionClient yelpFusionClient;

    private final RestClientTransport restClientTransport = restClientTransport();
    static int offset = 0;
    static int total = 0;

    private final int limit = 50;

    private Set<String> categoriesGt = new HashSet<>();

    @Test
    public void testSearchBusinessRequest() throws Exception {
        yelpFusionClient = new YelpFusionClient(restClientTransport());
        Set<Category> categories = loadAllRestaurantCategories();
        esAsyncClient = new ElasticsearchTestCase().elasticsearchService.getAsyncClient();
        for(Category category: categories) {
            offset = 0;
            total = 0;

            do {
                SearchBusinessRequest searchBusinessRequest = searchBusinessRequest(category, offset);

                SearchBusinessResponse searchBusinessResponse = yelpFusionClient.businesses()
                        .businessSearch(searchBusinessRequest);

                assertThat(searchBusinessResponse).isNotNull();
                total = searchBusinessResponse.total();
                assertThat(total).isNotNull();


                List<SearchBusinessResult> results = searchBusinessResponse.businesses();
                System.out.println("Category: " + category + " offset: " + offset + " number of results: " + results.size());
                for(SearchBusinessResult searchBusinessResult: results) {

                    String businessId = searchBusinessResult.id();
                    String formattedId = businessId.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");

                    IndexRequest<JsonData> request = IndexRequest.of(i -> i
                            .index("yelp-fusion-businesses-restaurants-sf-s")
                            .id(formattedId)
                            .pipeline("timestamp-pipeline") // add timestamp
                            .document(JsonData.of(searchBusinessResult.toString()))
                    );

                    IndexResponse response = esAsyncClient.index(request).get();
                }

                offset += limit;

                if(offset >= 950) {
                    System.out.println(", offset >= 950: " + offset + ", category: " + category);
                    categoriesGt.add(category.alias());
                }
            }while (offset < (total + limit) && offset <= 1000);
        }

        System.out.println(categoriesGt.size() + " , categories gt 1000 results: " + categoriesGt.toString());
    }

    private SearchBusinessRequest searchBusinessRequest(Category category, int offset) {
        return SearchBusinessRequest.of(s -> s
                .term("restaurants")
                .limit(limit)
                .offset(offset)
                .location("san+francisco")
                .categories(c -> c
                        .alias(category.alias()))
                .sort_by("review_count")
        );
    }

    @Test
    public void testBusinessDetailsRequest() throws Exception {

        esAsyncClient = new ElasticsearchTestCase().elasticsearchService.getAsyncClient();

        SearchResponse<ObjectNode> searchResponse = searchWithMatchAllQuery();
        yelpFusionClient = new YelpFusionClient(restClientTransport());

        searchResponse.hits().hits().forEach(hit -> {
            String id = hit.id();
            try {
                BusinessDetails businessDetails = yelpFusionClient.businesses().businessDetails(b -> b
                                .id(id))
                        .result();

                JsonNode node = new JacksonJsonpMapper().objectMapper().readValue(businessDetails.toString(), ObjectNode.class);

                esAsyncClient.index(i -> i
                        .index("yelp-fusion-businesses-restaurants-sf")
                        .id(businessDetails.id())
                        .pipeline("timestamp-pipeline")
                        .document(node)
                ).get();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    private Set<Category> loadAllRestaurantCategories() {
        Set<Category> restCats = new HashSet<>();
        try {
            List<Category> categories = yelpFusionClient
                    .categories().all(c -> c
                            .locale(LOCALE))
                    .categories();

            for(Category category: categories) {
                if (category.parent_aliases() != null) {
                    for (String parent : category.parent_aliases()) {
                        if (parent.equals("restaurants")) {
                            restCats.add(category);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return restCats;
    }

    private SearchResponse<ObjectNode> searchWithMatchAllQuery() {
        try {
            return esAsyncClient.search(s -> s
                    .query(q -> q
                            .matchAll(m -> m
                                    .queryName("_id"))
                    ), ObjectNode.class).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
