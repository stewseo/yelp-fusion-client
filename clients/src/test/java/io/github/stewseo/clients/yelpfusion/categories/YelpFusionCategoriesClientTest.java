package io.github.stewseo.clients.yelpfusion.categories;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.categories.alias.CategoriesAliasResponse;
import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesResponse;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;

class YelpFusionCategoriesClientTest extends YelpFusionClientTestCase {

    @Test
    void withTransportOptions() throws IOException {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionCategoriesClient client = new YelpFusionCategoriesClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        }
    }


    YelpFusionCategoriesClient client = new YelpFusionCategoriesClient(restClientTransport());

    @Test
    void all() throws Exception {

        CategoriesResponse response = client.all(catReq -> catReq
                .locale(LOCALE));

        assertThat(response.categories().size()).isEqualTo(1295);
    }

    @Test
    void testCategoriesAlias() throws Exception {

        CategoriesAliasResponse response = client.alias(catAlias -> catAlias
                .alias("pizza"));
    }
}