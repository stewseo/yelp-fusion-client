package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;//package io.github.stewseo.clients.yelpfusion.businesses.searchBusinesses;
//
//import com.brein.domain.results.temporaldataparts.BreinLocationResult;
//import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
//import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
//import io.github.stewseo.clients.yelpfusion._types.Center;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.concurrent.CompletableFuture;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class SearchBusinessTest extends FunctionalTestCase {
//
//    private static final Logger logger = LoggerFactory.getLogger(SearchBusinessTest.class);
//
//    static int minNumChars = 5;
//    private final String sort_by = "distance";
//    private final String term = "restaurants";
//    private final String categoryAlias = "pizza";
//    private final int limit = 50;
//    private final Integer offset = 0;
//    private final int radius = 1610;
//
//    @YelpFusionTest
//    public void testSearchBusinessEndpoint() {
//
//        SearchBusinessRequest searchBusinessRequest = SearchBusinessRequest.of(s -> s.location(l -> l

//        assertThat("v3/businesses/searchBusinesses")
//                .isEqualTo(SearchBusinessRequest._ENDPOINT.requestUrl(searchBusinessRequest));
//    }
//
//    @YelpFusionTest
//    void testSearchBusinessWithAsyncClient() {
//
//        getStreamOfTemporalData().forEach(breinTemporalDataResult -> {
//
//            BreinLocationResult locationResult = breinTemporalDataResult.getLocation();
//
//            SearchBusinessRequest searchBusinessRequest = buildSearchBusinessRequest(locationResult);
//
//            CompletableFuture<SearchBusinessResponse> searchBusinessResponse = yelpFusionService
//                    .yelpFusionAsyncClient()
//                    .businesses()
//                    .searchBusinesses(searchBusinessRequest
//                            ,
//                            SearchBusinessResult.class
//                    ).whenComplete((response, exception) -> {
//
//                        if (exception != null) {
//                            logger.error("Search Business failed: " + exception);
//                        } else {
//                            logger.info("Search Business successful: : ");
//                        }
//                    });
//            try {
//                testSearchBusinessResponse(searchBusinessResponse.get());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    @YelpFusionTest
//    void testSearchBusinessWithBlockingClient() throws IOException {
//
//        YelpFusionClient yelpFusionClient = YelpFusionClient.createClient(System.getenv("YELP_API_KEY"));
//
//        getStreamOfTemporalData().forEach(breinTemporalDataResult -> {
//
//            BreinLocationResult locationResult = breinTemporalDataResult.getLocation();
//
//            SearchBusinessRequest searchBusinessRequest = buildSearchBusinessRequest(locationResult);
//
//            try {
//                SearchBusinessResponse searchBusinessResponse =
//                        yelpFusionClient.businesses().businessSearch(searchBusinessRequest);
//
//                testSearchBusinessResponse(searchBusinessResponse);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    // assert that Response fields total and region are of the expected type
//    private void testSearchBusinessResponse(SearchBusinessResponse resp) {
//
//        Center center = resp.region().center();
//
//        assertThat(resp.total()).isInstanceOf(Integer.class);
//
//        assertThat(center.latitude()).isInstanceOf(Double.class);
//
//        assertThat(center.longitude()).isInstanceOf(Double.class);
//
//        resp.businesses().forEach(this::testSearchBusiness);
//    }
//
//
//    private void testSearchBusiness(SearchBusinessResult searchBusiness) {
//
//        assertThat(searchBusiness.id().length()).isGreaterThanOrEqualTo(minNumChars);
//
//        assertThat(searchBusiness.review_count()).isGreaterThanOrEqualTo(1);
//
//        assertThat(searchBusiness.location().city()).isNotNull();
//
//    }
//
//    private SearchBusinessRequest buildSearchBusinessRequest(BreinLocationResult locationResult) {
//
//        Double latitude = locationResult.getLat();
//        Double longitude = locationResult.getLon();
//        String resultCity = locationResult.getCity();
//
//        return SearchBusinessRequest.of(s -> s
//                .location(resultCity)
//                .coordinates(c -> c
//                        .latitude(latitude)
//                        .longitude(longitude))
//                .term(term)
//                .categories(cat -> cat
//                        .alias(categoryAlias))
//                .offset(offset)
//                .limit(limit)
//                .radius(radius)
//                .sort_by(sort_by));
//    }
//}
