//package org.example.elasticsearch.client.elasticsearch.enrich;
//
//import org.example.elasticsearch.client._types.*;
//import org.example.elasticsearch.client.transport.endpoints.*;
//import org.example.elasticsearch.client.util.*;
//
//import javax.annotation.*;
//import java.util.*;
//import java.util.function.*;
//
//public class ExecutePolicyRequest extends RequestBase {
//    private final String name;
//
//    @Nullable
//    private final Boolean waitForCompletion;
//
//    // ---------------------------------------------------------------------------------------------
//
//    private ExecutePolicyRequest(Builder builder) {
//
//        this.name = ApiTypeHelper.requireNonNull(builder.name, this, "name");
//        this.waitForCompletion = builder.waitForCompletion;
//
//    }
//
//    public static ExecutePolicyRequest of(Function<Builder, ObjectBuilder<ExecutePolicyRequest>> fn) {
//        return fn.apply(new Builder()).build();
//    }
//
//    public final String name() {
//        return this.name;
//    }
//
//    @Nullable
//    public final Boolean waitForCompletion() {
//        return this.waitForCompletion;
//    }
//
//
//
//    public static class Builder extends RequestBase.AbstractBuilder<Builder>
//            implements
//            ObjectBuilder<ExecutePolicyRequest> {
//        private String name;
//
//        @Nullable
//        private Boolean waitForCompletion;
//
//
//        public final Builder name(String value) {
//            this.name = value;
//            return this;
//        }
//
//
//        public final Builder waitForCompletion(@Nullable Boolean value) {
//            this.waitForCompletion = value;
//            return this;
//        }
//
//        @Override
//        protected Builder self() {
//            return this;
//        }
//
//
//        public ExecutePolicyRequest build() {
//            _checkSingleUse();
//
//            return new ExecutePolicyRequest(this);
//        }
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//    /**
//     * Endpoint "{@code enrich.execute_policy}".
//     */
//    public static final Endpoint<ExecutePolicyRequest, ExecutePolicyResponse, ErrorResponse> _ENDPOINT = new SimpleEndpoint<>(
//            "es/enrich.execute_policy",
//
//            // Request method
//            request -> {
//                return "PUT";
//
//            },
//
//            // Request path
//            request -> {
//                final int _name = 1 << 0;
//
//                int propsSet = 0;
//
//                propsSet |= _name;
//
//                if (propsSet == (_name)) {
//                    StringBuilder buf = new StringBuilder();
//                    buf.append("/_enrich");
//                    buf.append("/policy");
//                    buf.append("/");
//                    SimpleEndpoint.pathEncode(request.name, buf);
//                    buf.append("/_execute");
//                    return buf.toString();
//                }
//                throw SimpleEndpoint.noPathTemplateFound("path");
//
//            },
//
//            // Request parameters
//            request -> {
//                Map<String, String> params = new HashMap<>();
//                if (request.waitForCompletion != null) {
//                    params.put("wait_for_completion", String.valueOf(request.waitForCompletion));
//                }
//                return params;
//
//            }, SimpleEndpoint.emptyMap(), false, ExecutePolicyResponse._DESERIALIZER);
//}
