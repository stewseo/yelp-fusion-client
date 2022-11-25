//package org.example.elasticsearch.client.elasticsearch.enrich;
//
//import org.example.elasticsearch.client.*;
//import org.example.elasticsearch.client._types.*;
//import org.example.elasticsearch.client.transport.*;
//import org.example.elasticsearch.client.util.*;
//
//import javax.annotation.*;
//import java.util.concurrent.*;
//import java.util.function.*;
//
//public class ElasticsearchEnrichAsyncClient extends ApiClient<ElasticsearchTransport, ElasticsearchEnrichAsyncClient> {
//
//    public ElasticsearchEnrichAsyncClient(ElasticsearchTransport transport) {
//        super(transport, null);
//    }
//
//    public ElasticsearchEnrichAsyncClient(ElasticsearchTransport transport,
//                                          @Nullable TransportOptions transportOptions) {
//        super(transport, transportOptions);
//    }
//
//    @Override
//    public ElasticsearchEnrichAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
//        return new ElasticsearchEnrichAsyncClient(this.transport, transportOptions);
//    }
//
//
//    public CompletableFuture<DeletePolicyResponse> deletePolicy(DeletePolicyRequest request) {
//        @SuppressWarnings("unchecked")
//        JsonEndpoint<DeletePolicyRequest, DeletePolicyResponse, ErrorResponse> endpoint = (JsonEndpoint<DeletePolicyRequest, DeletePolicyResponse, ErrorResponse>) DeletePolicyRequest._ENDPOINT;
//
//        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
//    }
//
//
//    public final CompletableFuture<DeletePolicyResponse> deletePolicy(
//            Function<DeletePolicyRequest.Builder, ObjectBuilder<DeletePolicyRequest>> fn) {
//        return deletePolicy(fn.apply(new DeletePolicyRequest.Builder()).build());
//    }
//
//
//    public CompletableFuture<ExecutePolicyResponse> executePolicy(ExecutePolicyRequest request) {
//        @SuppressWarnings("unchecked")
//        JsonEndpoint<ExecutePolicyRequest, ExecutePolicyResponse, ErrorResponse> endpoint = (JsonEndpoint<ExecutePolicyRequest, ExecutePolicyResponse, ErrorResponse>) ExecutePolicyRequest._ENDPOINT;
//
//        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
//    }
//
//
//    public final CompletableFuture<ExecutePolicyResponse> executePolicy(
//            Function<ExecutePolicyRequest.Builder, ObjectBuilder<ExecutePolicyRequest>> fn) {
//        return executePolicy(fn.apply(new ExecutePolicyRequest.Builder()).build());
//    }
//
//
//
//    public CompletableFuture<GetPolicyResponse> getPolicy(GetPolicyRequest request) {
//        @SuppressWarnings("unchecked")
//        JsonEndpoint<GetPolicyRequest, GetPolicyResponse, ErrorResponse> endpoint = (JsonEndpoint<GetPolicyRequest, GetPolicyResponse, ErrorResponse>) GetPolicyRequest._ENDPOINT;
//
//        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
//    }
//
//    public final CompletableFuture<GetPolicyResponse> getPolicy(
//            Function<GetPolicyRequest.Builder, ObjectBuilder<GetPolicyRequest>> fn) {
//        return getPolicy(fn.apply(new GetPolicyRequest.Builder()).build());
//    }
//
//
//    public CompletableFuture<GetPolicyResponse> getPolicy() {
//        return this.transport.performRequestAsync(new GetPolicyRequest.Builder().build(), GetPolicyRequest._ENDPOINT,
//                this.transportOptions);
//    }
//
//    public CompletableFuture<PutPolicyResponse> putPolicy(PutPolicyRequest request) {
//        @SuppressWarnings("unchecked")
//        JsonEndpoint<PutPolicyRequest, PutPolicyResponse, ErrorResponse> endpoint = (JsonEndpoint<PutPolicyRequest, PutPolicyResponse, ErrorResponse>) PutPolicyRequest._ENDPOINT;
//
//        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
//    }
//
//
//
//    public final CompletableFuture<PutPolicyResponse> putPolicy(
//            Function<PutPolicyRequest.Builder, ObjectBuilder<PutPolicyRequest>> fn) {
//        return putPolicy(fn.apply(new PutPolicyRequest.Builder()).build());
//    }
//
//
//    public CompletableFuture<EnrichStatsResponse> stats() {
//        return this.transport.performRequestAsync(EnrichStatsRequest._INSTANCE, EnrichStatsRequest._ENDPOINT,
//                this.transportOptions);
//    }
//
//}