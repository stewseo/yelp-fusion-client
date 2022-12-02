package io.github.lowlevel.restclient;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

final class HttpDeleteWithEntity extends HttpEntityEnclosingRequestBase {

    static final String METHOD_NAME = HttpDelete.METHOD_NAME;

    HttpDeleteWithEntity(final URI uri) {
        setURI(uri);
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}