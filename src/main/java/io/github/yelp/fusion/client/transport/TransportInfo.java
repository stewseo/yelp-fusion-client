package io.github.yelp.fusion.client.transport;

import java.util.List;

public interface TransportInfo {

    String requestUrl();
    byte[] requestBody();
    String requestBodyText();
    // request headers

    int responseStatusCode();
    byte[] responseBody();
    String responseBodyText();
    // response headers

    List<String> warnings();
}