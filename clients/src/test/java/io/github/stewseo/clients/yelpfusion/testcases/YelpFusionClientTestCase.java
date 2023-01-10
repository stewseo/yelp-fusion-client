package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.testcases.context.YelpFusionTransportCtx;

import static io.github.stewseo.clients.yelpfusion._types.TestData.FQ_RESPONSE_EXCEPTION_CLASSNAME;
import static io.github.stewseo.clients.yelpfusion._types.TestData.HOST_NAME;
import static io.github.stewseo.clients.yelpfusion._types.TestData.METHOD;

public abstract class YelpFusionClientTestCase implements ApiClientTestCase {

    private final RestClientTransport restClientTransport;
    private final String apiKey;
    public final String expectedStatusLineErrorDescription =
            "status line [HTTP/1.1 429 Too Many Requests]\n" +
                    "{\"error\": {\"code\": \"ACCESS_LIMIT_REACHED\", " +
                    "\"description\": \"You've reached the access limit for this client. " +
                    "See instructions for requesting a higher access limit at https://www.yelp.com/developers/documentation/v3/rate_limiting\"}}";

    public YelpFusionClientTestCase() {
        YelpFusionTransportCtx transportCtx = new YelpFusionTransportCtx();
        restClientTransport = (RestClientTransport) transportCtx.getTransport();
        apiKey = System.getenv("YELP_API_KEY");
    }

    public RestClientTransport restClientTransport() {
        return restClientTransport;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String buildExpectedExecutionExceptionMessage(String expectedUri) {
        return FQ_RESPONSE_EXCEPTION_CLASSNAME +
                ": " + METHOD +
                ", " + HOST_NAME + ", " +
                expectedUri + ", " +
                expectedStatusLineErrorDescription;
    }
    public String buildExpectedResponseExceptionMessage(String expectedUri) {
        return METHOD + ", " + HOST_NAME + ", " + expectedUri + ", " + expectedStatusLineErrorDescription;
    }
}
