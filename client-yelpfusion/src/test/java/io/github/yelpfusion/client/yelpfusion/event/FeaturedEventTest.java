package io.github.yelpfusion.client.yelpfusion.event;

import com.brein.domain.results.BreinTemporalDataResult;
import io.github.yelpfusion.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.events.Event;
import io.github.stewseo.client.yelpfusion.events.FeaturedEventResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FeaturedEventTest extends YelpFusionTestCase {

    @Test
    public void featuredEventTest() throws Exception {

        BreinTemporalDataResult breinTemporalDataResult = locationByCity("NYC");

        String city = breinTemporalDataResult.getLocation().getCity();

        testFeaturedEvent(city);

    }

    private void testFeaturedEvent(String location) {
        System.out.println(location);
        try {

            FeaturedEventResponse response = yelpFusionServiceCtx.getYelpFusionAsyncClient().events().featuredEvent(f -> f.location(location)).get();

            testFeaturedEvent(response.event());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void testFeaturedEvent(Event event) {
        assertThat(event).isNotNull();
        assertThat(event.id()).isNotNull();
        assertThat(event.attending_count()).isGreaterThanOrEqualTo(1);
    }
}
