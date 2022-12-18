package io.github.stewseo.lowlevel.restclient;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

final class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {

    static final String METHOD_NAME = HttpGet.METHOD_NAME;

    HttpGetWithEntity(final URI uri) {
        setURI(uri);
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}