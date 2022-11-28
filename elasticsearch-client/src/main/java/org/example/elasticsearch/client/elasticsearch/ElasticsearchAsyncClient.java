package org.example.elasticsearch.client.elasticsearch;

import org.example.elasticsearch.client.*;
import org.example.elasticsearch.client.transport.*;
import org.example.elasticsearch.client.transport.endpoints.*;

import java.util.concurrent.*;

public class ElasticsearchAsyncClient extends ApiClient<ElasticsearchTransport, ElasticsearchAsyncClient> {

    public ElasticsearchAsyncClient(ElasticsearchTransport transport) {
        super(transport, null);
    }

    public ElasticsearchAsyncClient(ElasticsearchTransport transport, TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public ElasticsearchAsyncClient withTransportOptions( TransportOptions transportOptions) {
        return new ElasticsearchAsyncClient(this.transport, transportOptions);
    }



//    public final <TDocument> CompletableFuture<SearchResponse<TDocument>> search(
//            Function<SearchRequest.Builder, ObjectBuilder<SearchRequest>> fn, Type tDocumentType) {
//        return search(fn.apply(new SearchRequest.Builder()).build(), tDocumentType);
//    }
//
//    // ----- Endpoint: search_mvt
//
//
//    public CompletableFuture<BinaryResponse> searchMvt(SearchMvtRequest request) {
//        @SuppressWarnings("unchecked")
//        Endpoint<SearchMvtRequest, BinaryResponse, ErrorResponse> endpoint = (Endpoint<SearchMvtRequest, BinaryResponse, ErrorResponse>) SearchMvtRequest._ENDPOINT;
//
//        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
//    }
}
