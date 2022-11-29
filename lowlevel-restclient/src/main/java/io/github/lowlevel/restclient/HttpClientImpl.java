package io.github.lowlevel.restclient;

import java.net.http.*;

public class HttpClientImpl implements HttpClientInterface {
    private HttpClient httpClient;

    public HttpClientImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpClientImpl( ) {
    }

    public HttpClient httpClient() {
        return httpClient;
    }

    public HttpClientImpl setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }
}
