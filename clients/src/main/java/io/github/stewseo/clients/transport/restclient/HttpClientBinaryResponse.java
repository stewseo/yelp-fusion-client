package io.github.stewseo.clients.transport.restclient;

import io.github.stewseo.clients.transport.endpoints.BinaryResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

class HttpClientBinaryResponse implements BinaryResponse {

    private final HttpEntity entity;

    private boolean consumed = false;

    HttpClientBinaryResponse(HttpEntity entity) {
        this.entity = entity;
    }

    @Override
    public String contentType() {
        Header h = entity.getContentType();
        return h == null ? "application/octet-stream" : h.getValue();
    }

    @Override
    public long contentLength() {
        long len = entity.getContentLength();
        return len < 0 ? -1 : entity.getContentLength();
    }

    @Override
    public InputStream content() throws IOException {
        if (consumed) {
            throw new IllegalStateException("Response content has already been consumed");
        }
        consumed = true;
        return entity.getContent();
    }

    @Override
    public void close() throws IOException {
        consumed = true;
        EntityUtils.consume(entity);
    }
}
