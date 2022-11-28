//package org.example.elasticsearch.client.elasticsearch.enrich;
//
//import jakarta.json.stream.*;
//import org.example.elasticsearch.client.json.*;
//import org.example.elasticsearch.client.util.*;
//
//import javax.annotation.*;
//import java.util.function.*;
//
//@JsonpDeserializable
//public class ExecutePolicyResponse implements JsonpSerializable {
//    private final ExecuteEnrichPolicyStatus status;
//
//    @Nullable
//    private final String taskId;
//
//
//
//    private ExecutePolicyResponse(Builder builder) {
//
//        this.status = ApiTypeHelper.requireNonNull(builder.status, this, "status");
//        this.taskId = builder.taskId;
//
//    }
//
//    public static ExecutePolicyResponse of(Function<Builder, ObjectBuilder<ExecutePolicyResponse>> fn) {
//        return fn.apply(new Builder()).build();
//    }
//
//
//    public final ExecuteEnrichPolicyStatus status() {
//        return this.status;
//    }
//
//
//    @Nullable
//    public final String taskId() {
//        return this.taskId;
//    }
//
//
//    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
//        generator.writeStartObject();
//        serializeInternal(generator, mapper);
//        generator.writeEnd();
//    }
//
//    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
//
//        generator.writeKey("status");
//        this.status.serialize(generator, mapper);
//
//        if (this.taskId != null) {
//            generator.writeKey("task_id");
//            generator.write(this.taskId);
//
//        }
//
//    }
//
//    @Override
//    public String toString() {
//        return JsonpUtils.toString(this);
//    }
//
//
//    public static class Builder extends WithJsonObjectBuilderBase<Builder>
//            implements
//            ObjectBuilder<ExecutePolicyResponse> {
//        private ExecuteEnrichPolicyStatus status;
//
//        @Nullable
//        private String taskId;
//
//
//        public final Builder status(ExecuteEnrichPolicyStatus value) {
//            this.status = value;
//            return this;
//        }
//
//
//        public final Builder status(
//                Function<ExecuteEnrichPolicyStatus.Builder, ObjectBuilder<ExecuteEnrichPolicyStatus>> fn) {
//            return this.status(fn.apply(new ExecuteEnrichPolicyStatus.Builder()).build());
//        }
//
//
//        public final Builder taskId(@Nullable String value) {
//            this.taskId = value;
//            return this;
//        }
//
//        @Override
//        protected Builder self() {
//            return this;
//        }
//
//
//        public ExecutePolicyResponse build() {
//            _checkSingleUse();
//
//            return new ExecutePolicyResponse(this);
//        }
//    }
//
//
//    public static final JsonpDeserializer<ExecutePolicyResponse> _DESERIALIZER = ObjectBuilderDeserializer
//            .lazy(Builder::new, ExecutePolicyResponse::setupExecutePolicyResponseDeserializer);
//
//    protected static void setupExecutePolicyResponseDeserializer(ObjectDeserializer<ExecutePolicyResponse.Builder> op) {
//
//        op.add(Builder::status, ExecuteEnrichPolicyStatus._DESERIALIZER, "status");
//        op.add(Builder::taskId, JsonpDeserializer.stringDeserializer(), "task_id");
//
//    }
//
//}