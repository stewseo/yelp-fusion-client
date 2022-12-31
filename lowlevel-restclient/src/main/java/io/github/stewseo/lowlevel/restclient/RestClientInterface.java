package io.github.stewseo.lowlevel.restclient;

import org.apache.http.HttpHost;

import java.io.IOException;

public interface RestClientInterface {

    Response performRequest(Request request) throws IOException;

    void close() throws IOException;

    Cancellable performRequestAsync(Request clientReq, ResponseListener responseListener);

    HttpHost httpHost();

}
