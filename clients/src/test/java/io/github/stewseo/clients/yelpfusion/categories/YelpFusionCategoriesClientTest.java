package io.github.stewseo.clients.yelpfusion.categories;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesClient;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.BAD_LOCALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YelpFusionCategoriesClientTest extends YelpFusionClientTestCase {

    private final YelpFusionCategoriesClient client = new YelpFusionCategoriesClient(restClientTransport());

    @Test
    public void testClient() {
         assertThat(client).isNotNull();
    }

    @Test
    public void testWithTransportOptions() {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionCategoriesClient client = new YelpFusionCategoriesClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCategories() throws Exception {
        assertThrows(Exception.class, () -> client.all(cat -> cat.locale(BAD_LOCALE)));
    }

    @Test
    public void testCategoriesAlias() throws Exception {
        assertThrows(Exception.class, () -> client.alias(catAlias -> catAlias.alias("piza")));

    }


}