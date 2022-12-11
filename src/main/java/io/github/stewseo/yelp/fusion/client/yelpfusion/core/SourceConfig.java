package io.github.stewseo.yelp.fusion.client.yelpfusion.core;


import co.elastic.clients.elasticsearch.core.search.SourceFilter;
import co.elastic.clients.json.*;
import co.elastic.clients.util.*;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class SourceConfig implements TaggedUnion<SourceConfig.Kind, Object>, JsonpSerializable {
    public enum Kind {
        Filter, Fetch
    }
    private final SourceConfig.Kind _kind;
    private final Object _value;

    @Override
    public final SourceConfig.Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    private SourceConfig(SourceConfig.Kind kind, Object value) {
        this._kind = kind;
        this._value = value;
    }

    private SourceConfig(SourceConfig.Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");

    }

    public static SourceConfig of(Function<SourceConfig.Builder, ObjectBuilder<SourceConfig>> fn) {
        return fn.apply(new SourceConfig.Builder()).build();
    }

    /**
     * Is this variant instance of kind {@code filter}?
     */
    public boolean isFilter() {
        return _kind == SourceConfig.Kind.Filter;
    }


    public SourceFilter filter() {
        return TaggedUnionUtils.get(this, SourceConfig.Kind.Filter);
    }

    public boolean isFetch() {
        return _kind == SourceConfig.Kind.Fetch;
    }

    public Boolean fetch() {
        return TaggedUnionUtils.get(this, SourceConfig.Kind.Fetch);
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        } else {
            switch (_kind) {
                case Fetch -> generator.write(((Boolean) this._value));
            }
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<SourceConfig> {
        private SourceConfig.Kind _kind;
        private Object _value;

        public ObjectBuilder<SourceConfig> filter(SourceFilter v) {
            this._kind = SourceConfig.Kind.Filter;
            this._value = v;
            return this;
        }

        public ObjectBuilder<SourceConfig> filter(Function<SourceFilter.Builder, ObjectBuilder<SourceFilter>> fn) {
            return this.filter(fn.apply(new SourceFilter.Builder()).build());
        }

        public ObjectBuilder<SourceConfig> fetch(Boolean v) {
            this._kind = SourceConfig.Kind.Fetch;
            this._value = v;
            return this;
        }

        public SourceConfig build() {
            _checkSingleUse();
            return new SourceConfig(this);
        }

    }

    private static JsonpDeserializer<SourceConfig> buildSourceConfigDeserializer() {
        return new UnionDeserializer.Builder<SourceConfig, SourceConfig.Kind, Object>(SourceConfig::new, false)
                .addMember(SourceConfig.Kind.Filter, SourceFilter._DESERIALIZER)
                .addMember(SourceConfig.Kind.Fetch, JsonpDeserializer.booleanDeserializer()).build();
    }

    public static final JsonpDeserializer<SourceConfig> _DESERIALIZER = JsonpDeserializer
            .lazy(SourceConfig::buildSourceConfigDeserializer);
}
