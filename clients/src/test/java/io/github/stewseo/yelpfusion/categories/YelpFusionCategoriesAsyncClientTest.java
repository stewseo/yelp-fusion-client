package io.github.stewseo.yelpfusion.categories;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesAsyncClient;
import io.github.stewseo.clients.yelpfusion.categories.alias.CategoriesAliasResponse;
import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesResponse;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YelpFusionCategoriesAsyncClientTest extends YelpFusionClientTestCase {

    @Test
    public void testWithTransportOptions() {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionClient client = new YelpFusionClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    YelpFusionCategoriesAsyncClient asyncClient = new YelpFusionCategoriesAsyncClient(restClientTransport());

    @Test
    void all() throws Exception {

        CompletableFuture<CategoriesResponse> cf = asyncClient.all(catReq -> catReq
                .locale(LOCALE));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> assertThat(cf.get().categories()).isNull()
        );

        assertThat(executionException.getMessage()).isNotNull();
    }

    @Test
    void testCategoriesAlias() throws Exception {
        CompletableFuture<CategoriesAliasResponse> cf = asyncClient.categoriesAlias(catAlias -> catAlias
                .alias(ALIAS));

        assertThrows(ExecutionException.class, cf::get);
    }
}