package io.github.stewseo.client._types.aggregations;

import io.github.stewseo.client._types.aggregations.TermsPartition;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.UnionDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.ObjectBuilderBase;
import io.github.stewseo.client.util.TaggedUnion;
import io.github.stewseo.client.util.TaggedUnionUtils;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class TermsInclude implements TaggedUnion<TermsInclude.Kind, Object>, JsonpSerializable {

    public enum Kind {
        Terms, Partition, Regexp

    }

    private final TermsInclude.Kind _kind;
    private final Object _value;

    @Override
    public final TermsInclude.Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    private TermsInclude(TermsInclude.Kind kind, Object value) {
        this._kind = kind;
        this._value = value;
    }

    private TermsInclude(TermsInclude.Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");

    }

    public static TermsInclude of(Function<TermsInclude.Builder, ObjectBuilder<TermsInclude>> fn) {
        return fn.apply(new TermsInclude.Builder()).build();
    }

    /**
     * Is this variant instance of kind {@code terms}?
     */
    public boolean isTerms() {
        return _kind == TermsInclude.Kind.Terms;
    }

    /**
     * Get the {@code terms} variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not of the {@code terms} kind.
     */
    public List<String> terms() {
        return TaggedUnionUtils.get(this, TermsInclude.Kind.Terms);
    }

    /**
     * Is this variant instance of kind {@code partition}?
     */
    public boolean isPartition() {
        return _kind == TermsInclude.Kind.Partition;
    }

    /**
     * Get the {@code partition} variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not of the {@code partition} kind.
     */
    public TermsPartition partition() {
        return TaggedUnionUtils.get(this, TermsInclude.Kind.Partition);
    }

    /**
     * Is this variant instance of kind {@code regexp}?
     */
    public boolean isRegexp() {
        return _kind == TermsInclude.Kind.Regexp;
    }

    /**
     * Get the {@code regexp} variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not of the {@code regexp} kind.
     */
    public String regexp() {
        return TaggedUnionUtils.get(this, TermsInclude.Kind.Regexp);
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        } else {
            switch (_kind) {
                case Terms :
                    generator.writeStartArray();
                    for (String item0 : ((List<String>) this._value)) {
                        generator.write(item0);

                    }
                    generator.writeEnd();

                    break;
                case Regexp :
                    generator.write(((String) this._value));

                    break;
            }
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<TermsInclude> {
        private TermsInclude.Kind _kind;
        private Object _value;

        public ObjectBuilder<TermsInclude> terms(List<String> v) {
            this._kind = TermsInclude.Kind.Terms;
            this._value = v;
            return this;
        }

        public ObjectBuilder<TermsInclude> partition(TermsPartition v) {
            this._kind = TermsInclude.Kind.Partition;
            this._value = v;
            return this;
        }

        public ObjectBuilder<TermsInclude> partition(
                Function<TermsPartition.Builder, ObjectBuilder<TermsPartition>> fn) {
            return this.partition(fn.apply(new TermsPartition.Builder()).build());
        }

        public ObjectBuilder<TermsInclude> regexp(String v) {
            this._kind = TermsInclude.Kind.Regexp;
            this._value = v;
            return this;
        }

        public TermsInclude build() {
            _checkSingleUse();
            return new TermsInclude(this);
        }

    }

    private static JsonpDeserializer<TermsInclude> buildTermsIncludeDeserializer() {
        return new UnionDeserializer.Builder<TermsInclude, TermsInclude.Kind, Object>(TermsInclude::new, false)
                .addMember(TermsInclude.Kind.Terms, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()))
                .addMember(TermsInclude.Kind.Partition, TermsPartition._DESERIALIZER)
                .addMember(TermsInclude.Kind.Regexp, JsonpDeserializer.stringDeserializer()).build();
    }

    public static final JsonpDeserializer<TermsInclude> _DESERIALIZER = JsonpDeserializer
            .lazy(TermsInclude::buildTermsIncludeDeserializer);
}
