/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

//----------------------------------------------------
// THIS CODE IS GENERATED. MANUAL EDITS WILL BE LOST.
//----------------------------------------------------

package io.github.stewseo.client._types.aggregations;

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

import java.util.function.Function;

// typedef: _types.aggregations.FieldDateMath

/**
 * A date range limit, represented either as a DateMath expression or a number
 * expressed according to the target field's precision.
 *
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.FieldDateMath">API
 * specification</a>
 */
@JsonpDeserializable
public class FieldDateMath implements TaggedUnion<FieldDateMath.Kind, Object>, JsonpSerializable {

    public static final JsonpDeserializer<FieldDateMath> _DESERIALIZER = JsonpDeserializer
            .lazy(FieldDateMath::buildFieldDateMathDeserializer);
    private final Kind _kind;
    private final Object _value;

    private FieldDateMath(Kind kind, Object value) {
        this._kind = kind;
        this._value = value;
    }

    private FieldDateMath(Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");

    }

    public static FieldDateMath of(Function<Builder, ObjectBuilder<FieldDateMath>> fn) {
        return fn.apply(new Builder()).build();
    }

    private static JsonpDeserializer<FieldDateMath> buildFieldDateMathDeserializer() {
        return new UnionDeserializer.Builder<FieldDateMath, Kind, Object>(FieldDateMath::new, false)
                .addMember(Kind.Expr, JsonpDeserializer.stringDeserializer())
                .addMember(Kind.Value, JsonpDeserializer.doubleDeserializer()).build();
    }

    @Override
    public final Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    public String _toJsonString() {
        switch (_kind) {
            case Expr:
                return this.expr();
            case Value:
                return String.valueOf(this.value());

            default:
                throw new IllegalStateException("Unknown kind " + _kind);
        }
    }

    /**
     * Is this variant instance of kind {@code expr}?
     */
    public boolean isExpr() {
        return _kind == Kind.Expr;
    }

    /**
     * Get the {@code expr} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code expr} kind.
     */
    public String expr() {
        return TaggedUnionUtils.get(this, Kind.Expr);
    }

    /**
     * Is this variant instance of kind {@code value}?
     */
    public boolean isValue() {
        return _kind == Kind.Value;
    }

    /**
     * Get the {@code value} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code value} kind.
     */
    public Double value() {
        return TaggedUnionUtils.get(this, Kind.Value);
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        } else {
            switch (_kind) {
                case Expr:
                    generator.write(((String) this._value));

                    break;
                case Value:
                    generator.write(((Double) this._value));

                    break;
            }
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public enum Kind {
        Expr, Value

    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<FieldDateMath> {
        private Kind _kind;
        private Object _value;

        public ObjectBuilder<FieldDateMath> expr(String v) {
            this._kind = Kind.Expr;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldDateMath> value(Double v) {
            this._kind = Kind.Value;
            this._value = v;
            return this;
        }

        public FieldDateMath build() {
            _checkSingleUse();
            return new FieldDateMath(this);
        }

    }
}
