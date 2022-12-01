package io.github.yelp.fusion.client.json;

import io.github.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.yelp.fusion.client.transport.YelpRestClientTransport;
import io.github.yelp.fusion.client.yelpfusion.BusinessSearchResponse;
import io.github.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.yelp.fusion.client.yelpfusion.business.BusinessSearch;
import io.github.yelp.fusion.restclient.YelpFusionRestClient;
import io.github.yelp.fusion.util.PrintUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeserializeBusinessSearchTest {

    private final static Logger logger = LoggerFactory.getLogger(DeserializeBusinessSearchTest.class);

    static HttpHost httpHost;
    static YelpFusionClient yelpClient;
    private static JsonpMapper mapper;

    @BeforeAll
    static void beforeAll() {
        String yelpFusionHost = "api.yelp.com";
        int port = 80;
        httpHost = new HttpHost(yelpFusionHost, port, "http");
        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        YelpFusionRestClient restClient = YelpFusionRestClient.builder(
                        httpHost)
                .setMetaHeaderEnabled(false)
                .setDefaultHeaders(defaultHeaders)
                .build();

        mapper = new JacksonJsonpMapper();

        YelpRestClientTransport yelpTransport;
        try {
            yelpTransport = new YelpRestClientTransport(restClient, mapper);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        yelpClient = new YelpFusionClient(yelpTransport);

    }

    // index results from BusinessSearch Endpoint
    @Test
    void businessSearchTest() throws Exception {

        int radius = 6000;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";
        String category = "bagels";
        String neighborhood = "bronx";
        Double latitude = 40.713272;
        Double longitude = -73.828461;
        Integer offset = 0;

        BusinessSearchResponse businessSearchResponse = yelpClient.businessSearch(s -> s
                .location(neighborhood)
                .coordinates(coord -> coord
                        .latitude(latitude)
                        .longitude(longitude))
                .term(term)
                .categories(c -> c
                        .alias(category))
        );
        int total = businessSearchResponse.total();
        assertThat(total).isGreaterThanOrEqualTo(1);

        List<BusinessSearch> businesses = businessSearchResponse.businesses();

        assertThat(total).isEqualTo(businesses.size());

        for (BusinessSearch business : businessSearchResponse.businesses()) {
            assertThat(business.id()).isNotNull();
            assertThat(business.location()).isNotNull();
            assertThat(business.phone()).isNotNull();

            if (business.price() == null) {
                logger.error(PrintUtils.red("PRICE IS NULL business name: " + business.name() +
                        " business alias: " + business.alias()));
            }

            assertThat(business.alias()).isNotNull();
            assertThat(business.name()).isNotNull();
            assertThat(business.display_phone()).isNotNull();
            assertThat(business.rating()).isNotNull();
            assertThat(business.transactions()).isNotNull();
            assertThat(business.review_count()).isNotNull();
            assertThat(business.url()).isNotNull();
            assertThat(business.image_url()).isNotNull();
        }
    }
}
