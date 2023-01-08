package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion._types.Hours;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessDetailsTest extends FunctionalTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessDetailsTest.class);

    private final SearchBusinessRequest searchBusinessRequest = SearchBusinessRequest.of(s -> s
            .location("sf")
            .term("restaurants")
            .sort_by("review_count")
            .limit(1));

    private final String id = "lJAGnYzku5zSaLnQ_T6_GQ";

    private final String alias = "brendas-french-soul-food-san-francisco-6";

    @Test
    public void testBusinessDetailsEndpoint() {

        BusinessDetailsRequest businessDetailsRequest = BusinessDetailsRequest.of(b -> b
                .alias(alias));

        assertThat("v3/businesses/brendas-french-soul-food-san-francisco-6").isEqualTo(BusinessDetailsRequest._ENDPOINT.requestUrl(businessDetailsRequest));

        businessDetailsRequest = BusinessDetailsRequest.of(b -> b
                .alias(id));

        assertThat("v3/businesses/lJAGnYzku5zSaLnQ_T6_GQ").isEqualTo(BusinessDetailsRequest._ENDPOINT.requestUrl(businessDetailsRequest));

    }

    @Test
    public void testBusinessDetailsResponse() {

        final BusinessDetails businessDetails = BusinessDetails.of(b -> b.id(id).alias(alias));

        final BusinessDetailsResponse businessDetailsResponse = BusinessDetailsResponse.of(b -> b
                .result(businessDetails));

        assertThat(businessDetailsResponse).isNotNull();

        assertThat(businessDetailsResponse.result().toString()).isEqualTo("" +
                "{\"id\":\"lJAGnYzku5zSaLnQ_T6_GQ\",\"alias\":\"brendas-french-soul-food-san-francisco-6\"}");
    }

    @Test
    void testAsyncClient() throws Exception {

        CompletableFuture<BusinessDetails> future;

        try {
            future = yelpFusionService.yelpFusionAsyncClient().businesses().businessDetails(b -> b.id(id))
                    .whenComplete((response, exception) -> {

                        if (exception != null) {

                            logger.error("id: " + id + " did not return a business from the business details endpoint: ", exception);

                        } else {
                            logger.info("Business Details returned successfully : ");

                        }
                    }).thenApply(BusinessDetailsResponse::result);

            testBusinessDetails(future.get());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSyncBlockingClient() {

        try {

            YelpFusionClient yelpFusionClient = YelpFusionClient.createClient(System.getenv("YELP_API_KEY"));

            testBusinessDetails(yelpFusionClient.businesses().businessDetails(b -> b.alias(alias)).result());

            testBusinessDetails(yelpFusionClient.businesses().businessDetails(b -> b.id(alias)).result());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void testBusinessDetails(BusinessDetails business) {

        assertThat(business.toString()).isNotNull();

        assertThat(business.id()).isNotNull();

        assertThat(business.review_count()).isGreaterThanOrEqualTo(4000);
        assertThat(business.rating()).isGreaterThanOrEqualTo(3.5);

        assertThat(business.hours()).isNotNull();
        Hours hours = Objects.requireNonNull(business.hours()).get(0);
        assertThat(hours.open().size()).isGreaterThanOrEqualTo(5);

        assertThat(business.categories()).isNotNull();
        assertThat(business.attributes()).isNotNull();
        assertThat(business.display_phone()).isNotNull();
        assertThat(business.coordinates()).isNotNull();
        assertThat(business.image_url()).isNotNull();
        assertThat(business.phone()).isNotNull();
        assertThat(business.location()).isNotNull();
        assertThat(business.name()).isEqualTo("Brenda's French Soul Food");

        assertThat(business.photos()).isNull();
        assertThat(business.url()).isNotNull();
        assertThat(business.is_claimed()).isNull();
        assertThat(business.is_closed()).isNotNull();
        assertThat(business.special_hours()).isNotNull();
        assertThat(business.transactions()).isNotNull();

    }

}