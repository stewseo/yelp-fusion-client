package io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete;

import io.github.stewseo.yelp.fusion.client.YelpConnection;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoCompleteTest {

    private static final Logger logger = LoggerFactory.getLogger(AutoCompleteTest.class);

    @Test
    public void autoCompleteTest() throws Exception {
        AutoCompleteRequest request = AutoCompleteRequest.of(a -> a.text("del"));
        YelpConnection.initYelpFusionClient();
        YelpFusionClient client = YelpConnection.getYelpClient();
        AutoCompleteResponse response = client.autocomplete(request);
        assertThat(Objects.requireNonNull(response.terms()).toString()).isEqualTo("[Term: {\"text\":\"Delivery\"}, Term: {\"text\":\"Delivery Food\"}, Term: {\"text\":\"Deli Sandwich\"}]");
        assertThat(Objects.requireNonNull(response.categories()).toString()).isEqualTo("[Categories: {\"alias\":\"delis\",\"title\":\"Delis\"}, Categories: {\"alias\":\"icedelivery\",\"title\":\"Ice Delivery\"}, Categories: {\"alias\":\"waterdelivery\",\"title\":\"Water Delivery\"}]");
        assertThat(Objects.requireNonNull(response.businesses()).toString()).isEqualTo("[]");
    }
}
