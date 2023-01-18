package io.github.stewseo.clients.transport;

import co.elastic.clients.transport.ElasticsearchTransport;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.lowlevel.restclient.RestClient;

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

}