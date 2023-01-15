package io.github.stewseo.clients._type;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

public abstract class QueryParameterBase implements JsonpSerializable {

    private final Map<String, QueryParameter> queryParams;

    protected QueryParameterBase(AbstractBuilder<?> builder) {
        this.queryParams = ApiTypeHelper.unmodifiable(builder.queryParams);

    }

    public final Map<String, QueryParameter> queryFields() {
        return this.queryParams;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.queryParams)) {
            generator.writeKey("query_parameters");
            generator.writeStartObject();
            for (Map.Entry<String, QueryParameter> item0 : this.queryParams.entrySet()) {
                generator.writeKey(item0.getKey());
                item0.getValue().serialize(generator, mapper);

            }
            generator.writeEnd();
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {

        @Nullable
        private Map<String, QueryParameter> queryParams;

        public final BuilderT queryParams(Map<String, QueryParameter> map) {
            this.queryParams = _mapPutAll(this.queryParams, map);
            return self();
        }

        public final BuilderT queryParams(String key, QueryParameter value) {
            this.queryParams = _mapPut(this.queryParams, key, value);
            return self();
        }

        public final BuilderT queryParams(String key, Function<QueryParameter.Builder, ObjectBuilder<QueryParameter>> fn) {
            return queryParams(key, fn.apply(new QueryParameter.Builder()).build());
        }

        protected abstract BuilderT self();

    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupQueryFieldBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        op.add(AbstractBuilder::queryParams, JsonpDeserializer.stringMapDeserializer(QueryParameter._DESERIALIZER),
                "query_params");

    }

}

