//package org.example.elasticsearch.client.elasticsearch.enrich;
//
//import jakarta.json.stream.JsonGenerator;
//import org.example.elasticsearch.client.json.*;
//import org.example.elasticsearch.client.util.*;
//
//import java.util.Objects;
//import java.util.function.Function;
//import javax.annotation.Nullable;
//@JsonpDeserializable
//public class ExecuteEnrichPolicyStatus implements JsonpSerializable {
//    private final EnrichPolicyPhase phase;
//
//    // ---------------------------------------------------------------------------------------------
//
//    private ExecuteEnrichPolicyStatus(Builder builder) {
//
//        this.phase = ApiTypeHelper.requireNonNull(builder.phase, this, "phase");
//
//    }
//
//    public static ExecuteEnrichPolicyStatus of(Function<Builder, ObjectBuilder<ExecuteEnrichPolicyStatus>> fn) {
//        return fn.apply(new Builder()).build();
//    }
//
//    /**
//     * Required - API name: {@code phase}
//     */
//    public final EnrichPolicyPhase phase() {
//        return this.phase;
//    }
//
//    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
//        generator.writeStartObject();
//        serializeInternal(generator, mapper);
//        generator.writeEnd();
//    }
//
//    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
//
//        generator.writeKey("phase");
//        this.phase.serialize(generator, mapper);
//
//    }
//
//    @Override
//    public String toString() {
//        return JsonpUtils.toString(this);
//    }
//
//    public static class Builder extends WithJsonObjectBuilderBase<Builder>
//            implements
//            ObjectBuilder<ExecuteEnrichPolicyStatus> {
//        private EnrichPolicyPhase phase;
//
//
//        public final Builder phase(EnrichPolicyPhase value) {
//            this.phase = value;
//            return this;
//        }
//
//        @Override
//        protected Builder self() {
//            return this;
//        }
//
//
//        public ExecuteEnrichPolicyStatus build() {
//            _checkSingleUse();
//
//            return new ExecuteEnrichPolicyStatus(this);
//        }
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//    /**
//     * Json deserializer for {@link ExecuteEnrichPolicyStatus}
//     */
//    public static final JsonpDeserializer<ExecuteEnrichPolicyStatus> _DESERIALIZER = ObjectBuilderDeserializer
//            .lazy(Builder::new, ExecuteEnrichPolicyStatus::setupExecuteEnrichPolicyStatusDeserializer);
//
//    protected static void setupExecuteEnrichPolicyStatusDeserializer(
//            ObjectDeserializer<ExecuteEnrichPolicyStatus.Builder> op) {
//
//        op.add(Builder::phase, EnrichPolicyPhase._DESERIALIZER, "phase");
//
//    }
//
//}