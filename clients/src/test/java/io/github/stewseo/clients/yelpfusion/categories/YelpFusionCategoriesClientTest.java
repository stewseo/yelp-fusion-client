package io.github.stewseo.clients.yelpfusion.categories;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.categories.alias.CategoriesAliasResponse;
import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesResponse;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.ResponseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

//    @Test
//    void all() throws Exception {
//
//        CategoriesResponse response = client.all(catReq -> catReq
//                .locale(LOCALE));
//
//        assertThat(response.categories().size()).isEqualTo(1295);
//    }

//    @Test
//    void testCategoriesAlias() throws Exception {
//
//        CategoriesAliasResponse response = client.alias(catAlias -> catAlias
//                .alias("pizza"));
//    }


    @Test
    void all() throws Exception {
        String expected = "method [GET], host [https://api.yelp.com:443], URI [v3/categories?locale=en_US], status line [HTTP/1.1 429 Too Many Requests]\n" +
                "{\"error\": {\"code\": \"ACCESS_LIMIT_REACHED\", \"description\": \"You've reached the access limit for this client. See instructions for requesting a higher access limit at https://www.yelp.com/developers/documentation/v3/rate_limiting\"}}";

        Exception exception = assertThrows(ResponseException.class,
                () -> client.all(catReq -> catReq.locale(LOCALE))
        );

        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void testCategoriesAlias() throws Exception {
        String expected = "method [GET], host [https://api.yelp.com:443], URI [v3/categories/pizza], status line [HTTP/1.1 429 Too Many Requests]\n" +
                "{\"error\": {\"code\": \"ACCESS_LIMIT_REACHED\", \"description\": \"You've reached the access limit for this client. See instructions for requesting a higher access limit at https://www.yelp.com/developers/documentation/v3/rate_limiting\"}}";

        Exception exception = assertThrows(ResponseException.class,
                () -> client.alias(s -> s.alias("pizza"))
        );
        assertThat(exception.getMessage()).isEqualTo(expected);

    }
}