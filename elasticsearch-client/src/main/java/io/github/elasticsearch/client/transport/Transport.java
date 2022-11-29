package io.github.elasticsearch.client.transport;

import io.github.elasticsearch.client.json.JsonpMapper;
import org.apache.http.*;


import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public interface Transport extends Closeable {

    <RequestT, ResponseT, ErrorT>
    ResponseT performRequest(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint, // adds id for endpoint /_ingest/pipeline/{pipeline_id}
            TransportOptions options
    ) throws IOException, URISyntaxException, HttpException, ExecutionException, InterruptedException, TimeoutException;


    <RequestT, ResponseT, ErrorT> CompletableFuture<ResponseT> performRequestAsync(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            TransportOptions options
    );

    JsonpMapper jsonpMapper();

    TransportOptions options();
}