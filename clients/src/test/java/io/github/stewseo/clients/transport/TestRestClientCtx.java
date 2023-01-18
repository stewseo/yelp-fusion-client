package io.github.stewseo.clients.transport;

import io.github.stewseo.lowlevel.restclient.Request;
import io.github.stewseo.lowlevel.restclient.RequestOptions;
import io.github.stewseo.lowlevel.restclient.RestClient;
import io.github.stewseo.lowlevel.restclient.RestClientBuilder;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;


import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

    public class TestRestClientCtx {
    static RequestOptions YELP_AUTHORIZATION_HEADER;
    private final RestClient restClient;

    public TestRestClientCtx() {
        HttpHost host = new HttpHost("api.yelp.com", 80, "http");

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        RestClientBuilder builder = RestClient.builder(
                        host)
                .setDefaultHeaders(defaultHeaders);

        restClient = builder.build();

        RequestOptions.Builder optionsBuilder = RequestOptions.DEFAULT.toBuilder();
        optionsBuilder.addHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));
        YELP_AUTHORIZATION_HEADER = optionsBuilder.build();
    }


    @TransportTest
    void uriTest() {
        String method = "GET";
        String endpoint = "businesses/searchBusinesses";
        Request request = new Request(method, endpoint);
        Map<String, String> params = Map.of("location", "cabo");

        request.addParameters(params);

        request.setOptions(YELP_AUTHORIZATION_HEADER);

        assertThat(request.getMethod()).isEqualTo(method);

        assertThat(request.getOptions()).isEqualTo(YELP_AUTHORIZATION_HEADER);

        String cleanedParamKey = request
                .getParameters()
                .keySet()
                .stream()
                .toList()
                .get(0)
                .replaceAll("[^A-Za-z]", "");

        assertThat(cleanedParamKey).isEqualTo("location");

    }

}
