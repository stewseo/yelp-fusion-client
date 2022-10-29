//package org.example.elasticsearch.client.elasticsearch.enrich;
//
//import org.example.elasticsearch.client.json.*;
//
//@JsonpDeserializable
//public class DeletePolicyResponse extends AcknowledgedResponseBase {
//    // ---------------------------------------------------------------------------------------------
//
//    private DeletePolicyResponse(Builder builder) {
//        super(builder);
//
//    }
//
//    public static DeletePolicyResponse of(Function<Builder, ObjectBuilder<DeletePolicyResponse>> fn) {
//        return fn.apply(new Builder()).build();
//    }
//
//
//    public static class Builder extends AcknowledgedResponseBase.AbstractBuilder<Builder>
//            implements
//            ObjectBuilder<DeletePolicyResponse> {
//        @Override
//        protected Builder self() {
//            return this;
//        }
//
//        /**
//         * Builds a {@link DeletePolicyResponse}.
//         *
//         * @throws NullPointerException
//         *             if some of the required fields are null.
//         */
//        public DeletePolicyResponse build() {
//            _checkSingleUse();
//
//            return new DeletePolicyResponse(this);
//        }
//    }
//
//
//    public static final JsonpDeserializer<DeletePolicyResponse> _DESERIALIZER = ObjectBuilderDeserializer
//            .lazy(Builder::new, DeletePolicyResponse::setupDeletePolicyResponseDeserializer);
//
//    protected static void setupDeletePolicyResponseDeserializer(ObjectDeserializer<DeletePolicyResponse.Builder> op) {
//        Object AcknowledgedResponseBase;
//        AcknowledgedResponseBase.setupAcknowledgedResponseBaseDeserializer(op);
//    }
//
//}