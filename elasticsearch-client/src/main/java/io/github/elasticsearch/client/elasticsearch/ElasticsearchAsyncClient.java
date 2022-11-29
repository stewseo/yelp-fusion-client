package io.github.elasticsearch.client.elasticsearch;

import io.github.elasticsearch.client.ApiClient;
import io.github.elasticsearch.client.transport.ElasticsearchTransport;
import io.github.elasticsearch.client.transport.TransportOptions;


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
