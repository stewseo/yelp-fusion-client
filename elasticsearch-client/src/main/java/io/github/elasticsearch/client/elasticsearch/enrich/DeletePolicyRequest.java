//package org.example.elasticsearch.client.elasticsearch.enrich;
//
//import org.example.elasticsearch.client._types.*;
//import org.example.elasticsearch.client.transport.*;
//import org.example.elasticsearch.client.transport.endpoints.*;
//import org.example.elasticsearch.client.util.*;
//
//import java.util.*;
//import java.util.function.*;
//
//public class DeletePolicyRequest extends RequestBase {
//    private final String name;
//
//    // ---------------------------------------------------------------------------------------------
//
//    private DeletePolicyRequest(Builder builder) {
//
//        this.name = ApiTypeHelper.requireNonNull(builder.name, this, "name");
//
//    }
//
//    public static DeletePolicyRequest of(Function<Builder, ObjectBuilder<DeletePolicyRequest>> fn) {
//        return fn.apply(new Builder()).build();
//    }
//
//    /**
//     * Required - The name of the enrich policy
//     * <p>
//     * API name: {@code name}
//     */
//    public final String name() {
//        return this.name;
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//    /**
//     * Builder for {@link DeletePolicyRequest}.
//     */
//
//    public static class Builder extends RequestBase.AbstractBuilder<Builder>
//            implements
//            ObjectBuilder<DeletePolicyRequest> {
//        private String name;
//
//        /**
//         * Required - The name of the enrich policy
//         * <p>
//         * API name: {@code name}
//         */
//        public final Builder name(String value) {
//            this.name = value;
//            return this;
//        }
//
//        @Override
//        protected Builder self() {
//            return this;
//        }
//
//        /**
//         * Builds a {@link DeletePolicyRequest}.
//         *
//         * @throws NullPointerException
//         *             if some of the required fields are null.
//         */
//        public DeletePolicyRequest build() {
//            _checkSingleUse();
//
//            return new DeletePolicyRequest(this);
//        }
//    }
//
//
//    public static final Endpoint<DeletePolicyRequest, DeletePolicyResponse, ErrorResponse> _ENDPOINT = new SimpleEndpoint<>(
//            "es/enrich.delete_policy",
//
//            // Request method
//            request -> {
//                return "DELETE";
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
//                    return buf.toString();
//                }
//                throw SimpleEndpoint.noPathTemplateFound("path");
//
//            },
//
//            // Request parameters
//            request -> {
//                return Collections.emptyMap();
//
//            }, SimpleEndpoint.emptyMap(), false, DeletePolicyResponse._DESERIALIZER);
//}