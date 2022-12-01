package io.github.yelp.fusion.client.transport.endpoints;

import java.io.IOException;
import java.io.InputStream;

public interface BinaryResponse extends AutoCloseable {

    String contentType();


    long contentLength();

    InputStream content() throws IOException;

    @Override
    void close() throws IOException;
}