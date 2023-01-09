package io.github.stewseo.clients.yelpfusion.categories;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion.categories.alias.CategoriesAliasResponse;
import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesResponse;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;

class YelpFusionCategoriesAsyncClientTest extends YelpFusionClientTestCase {

    @Test
    void withTransportOptions() throws IOException {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionClient client = new YelpFusionClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        }
    }


    YelpFusionCategoriesAsyncClient asyncClient = new YelpFusionCategoriesAsyncClient(restClientTransport());

    @Test
    void all() throws Exception {

        CompletableFuture<CategoriesResponse> cf = asyncClient.all(catReq -> catReq
                .locale(LOCALE));

        assertThat(cf.get().categories().size()).isEqualTo(1295);
    }

    @Test
    void testCategoriesAlias() throws Exception {
        CompletableFuture<CategoriesAliasResponse> cf = asyncClient.categoriesAlias(catAlias -> catAlias
                .alias(ALIAS));
    }
}