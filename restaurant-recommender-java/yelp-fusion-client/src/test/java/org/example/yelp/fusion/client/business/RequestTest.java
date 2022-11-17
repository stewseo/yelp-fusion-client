package org.example.yelp.fusion.client.business;


import co.elastic.clients.elasticsearch.core.*;

import org.apache.commons.logging.*;
import org.apache.http.*;
import org.example.lowlevel.restclient.*;
import org.example.yelp.fusion.client.*;
import org.example.yelp.fusion.client.business.search.*;
import org.example.yelp.fusion.client.exception.*;
import org.example.yelp.fusion.client.transport.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.AssertionsForClassTypes.*;

@SuppressWarnings({"ConstantConditions", "unused"})
public class RequestTest extends AbstractRequestTestCase {
    private static final Log logger = LogFactory.getLog(RequestTest.class);
    static Map<String, List<String>> categoriesMap;

    static int docsCount;
    static Set<String> setOfBusinessIds;
    
    static int skip;


    // initialize
    @BeforeAll
    static void setup() {

        loadCategoriesMap();

        String categoryName = "bubbletea";

        int indexOf =  categoriesMap.keySet().stream().toList().indexOf(categoryName);

        logger.info(PrintUtils.red(categoryName + " index of = "   + indexOf));

        skip = indexOf;

        assertThat(docsCount).isEqualTo(setOfBusinessIds.size());
        
        PrintUtils.cyan("number of sandwiches restaurants: " + categoriesMap.get("sandwiches").size() + " in index: " + indexNyc);
        categoriesMap.forEach((key, value) -> System.out.println("key: " + key + " v: " + value.size()));
    }

    private static void loadCategoriesMap() {

        docsCount = elasticSearch.getDocsCount(indexNyc);

        logger.info(PrintUtils.red("index: " + indexNyc + " docs count: " + docsCount));

        Map<String, List<String>> map = new HashMap<>();

        int maxResults = 10000;

        for(int i = 0; i < docsCount; i += maxResults) {
            String timestamp = elasticSearch.getTimestamp();
            map.putAll(elasticSearch.getCategoriesMap(indexNyc, maxResults, timestamp));
        }

        String timestamp = elasticSearch.getTimestamp();
        map.putAll(elasticSearch.getCategoriesMap(indexNyc, docsCount % maxResults, timestamp));
        setOfBusinessIds = elasticSearch.getSetOfBusinessIds();

        logger.info(PrintUtils.green(
                "number of categories = " + map.keySet().size() +
                        " number of business id's = " + setOfBusinessIds.size()));

        map.remove("restaurants");
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

    @Test
    void emptyTest(){
//        logger.info(PrintUtils.red("bubbletea " + categoriesMap.keySet().stream().toList().indexOf("bubbletea")));
    }

    @Test
    public void businessSearchTest() throws Exception  {
        int docsCount = elasticSearch.getDocsCount(indexNyc);

        categoriesMap.entrySet().stream().skip(skip);
        assertThat(indexNyc).isEqualTo("yelp-businesses-restaurants-nyc");
        PrintUtils.cyan("list of keys after skipping " + skip + " = " + categoriesMap.keySet().size());
        int maxResults = 1000;
        int limit = 50;
        String sort_by = "review_count";
        String term = "restaurants";
        String location = "new+york+city";

        for (String category : categoriesMap.keySet().stream().skip(skip-1).toList()) {
                // reset offset, total, count, maxOffset
                int offset = 0;
                int maxOffset = 0;
                Integer total = 0; // if total == 0 next category. if total > 1000, add parameters
                int count = 0;
                // do while total > 0 && offset < total + limit - 1
                boolean addParams = false;
                do {
                    final int finalOffset = offset;
                    count++;
                    PrintUtils.cyan("category = " + category + " offset = " + offset + " maxOffset = " + maxOffset);

                    BusinessSearchResponse<Business> businessSearchResponse = yelpClient.businessSearch(s -> s
                                    .location("nyc")
                                    .term("restaurants")
                                    .categories(c -> c
                                            .alias(category))
                                    .sort_by(sort_by)
                                    .offset(finalOffset)
                                    .limit(limit)
                            , Business.class);

                    // TODO: Create MetaDataHit, Hit, InnerHit, Source models
                    total = businessSearchResponse.total();
                    YelpRestTransport yelpTransport = (YelpRestTransport) yelpClient._transport();
                    if (total == null) {
                        HttpClient jdkHttpClient = yelpTransport.restClient().getJdkHttpClient();
                        YelpRequestLogger.logNullResponseBody(logger, businessSearchResponse);
                    }
                    if (total == 0) {
                        HttpClient jdkHttpClient = yelpTransport.restClient().getJdkHttpClient();
                        YelpRequestLogger.logEmptyResponseBody(logger, businessSearchResponse);
                    }
                    else {

                        maxOffset = total + 49;

                        if (businessSearchResponse != null) {
                            List<Business> businesses = businessSearchResponse.businesses();

                            if (businesses.size() > 0) {
                                logger.info(PrintUtils.green("category = " + category + " offset = " + offset + " total results = " + total + " number of businesses " + businesses.size()));

                                Set<String> idsToAdd = new HashSet<>();

                                for (Business business : businesses) {
                                    String businessId = business.id();

                                    if (setOfBusinessIds.add(businessId)) {
                                        idsToAdd.add(businessId);
                                    }
                                }
                                logger.info(PrintUtils.cyan("number of ids not in database  = " + idsToAdd.size()));
                                indexBusiness(idsToAdd);
                            }
                        }
                    }
                    offset += limit;
                } while (total > 0); // iterate up to 20 50-business pages
            }
//        }
    }

    private void indexBusiness(Set<String> setOfBusinessIdsToAdd) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();
        for (String businessId : setOfBusinessIdsToAdd) {

            String formattedId = businessId.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");
            assertThat(formattedId).isNotNull();

            try {

                BusinessDetailsResponse<Business> businessDetailsResponse = yelpClient.businessDetails(s -> s
                                .id(formattedId),
                        Business.class);

                Business business = businessDetailsResponse.getBusiness();
                String documentId = indexNyc + "-" + business.id();
                br.operations(op -> op
                        .index(idx -> idx
                                .index(indexNyc)
                                .id(documentId)
                                .pipeline(timestampPipeline) // add timestamp
                                .pipeline(geoPointPipeline) // add geolocation
                                .document(business)
                        )
                );

            } catch (Exception e) {
                if(e instanceof URISyntaxException) {

                }
                if(e instanceof HttpException) {

                }
                if(e instanceof IOException) {
                    throw new IOException(e);
                }
                throw new RuntimeException(e);
            }

            BulkResponse result = elasticSearch.client().bulk(br.build());

        }
    }
}
