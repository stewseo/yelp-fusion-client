package io.github.stewseo.clients.yelpfusion.event;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.events.FeaturedEventResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FeaturedEventTest extends YelpFusionTestCase {

    @Test
    public void featuredEventTest() throws Exception {

        BreinTemporalDataResult breinTemporalDataResult = locationByCity("NYC");

        testFeaturedEvent(breinTemporalDataResult);

    }

    private void testFeaturedEvent(BreinTemporalDataResult breinTemporalDataResult) {

        BreinLocationResult breinLocationResult = breinTemporalDataResult.getLocation();

        assertThat(breinLocationResult).isNotNull();

        String city = breinLocationResult.getCity();

        assertThat(city).isEqualTo("New York City");

        double lat = breinLocationResult.getLat();

        assertThat(lat).isEqualTo(40.71427);

        double lon = breinLocationResult.getLon();

        assertThat(lon).isEqualTo(-74.00597);

        try {

            FeaturedEventResponse response = yelpFusionServiceCtx.getYelpFusionAsyncClient().events().featuredEvent(f -> f
                    .latitude(lat)
                    .longitude(lon)
            ).get();

            testFeaturedEvent(response.event());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void testFeaturedEvent(Event event) {
        assertThat(event.toString()).isEqualTo("{}");
    }
}
