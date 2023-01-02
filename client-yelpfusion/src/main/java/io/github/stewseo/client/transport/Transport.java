package io.github.stewseo.client.transport;

import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.lowlevel.restclient.RestClientInterface;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface Transport extends Closeable {

    <RequestT, ResponseT, ErrorT>
    ResponseT performRequest(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint, // adds id for endpoint /_ingest/pipeline/{pipeline_id}
            TransportOptions options
    ) throws IOException;


    <RequestT, ResponseT, ErrorT> CompletableFuture<ResponseT> performRequestAsync(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            TransportOptions options
    );

    JsonpMapper jsonpMapper();

    TransportOptions options();

    RestClientInterface restClientInterface();
}