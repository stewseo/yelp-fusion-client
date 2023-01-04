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

package io.github.stewseo.clients.yelpfusion._types.aggregations;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.UnionDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.ObjectBuilderBase;
import io.github.stewseo.clients.util.TaggedUnion;
import io.github.stewseo.clients.util.TaggedUnionUtils;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

// typedef: _types.aggregations.Buckets

/**
 * Aggregation buckets. By default they are returned as an array, but if the
 * aggregation has keys configured for the different buckets, the result is a
 * dictionary.
 *
 * @see <a href="../../doc-files/api-spec.html#_types.aggregations.Buckets">API
 * specification</a>
 */

public class Buckets<TBucket> implements TaggedUnion<Buckets.Kind, Object>, JsonpSerializable {

    private final Kind _kind;
    private final Object _value;
    private final JsonpSerializer<TBucket> tBucketSerializer = null;

    private Buckets(Kind kind, Object value) {
        this._kind = kind;
        this._value = value;
    }

    private Buckets(Builder<TBucket> builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");

    }

    public static <TBucket> Buckets<TBucket> of(Function<Builder<TBucket>, ObjectBuilder<Buckets<TBucket>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    public static <TBucket> JsonpDeserializer<Buckets<TBucket>> createBucketsDeserializer(
            JsonpDeserializer<TBucket> tBucketDeserializer) {
        return new UnionDeserializer.Builder<Buckets<TBucket>, Kind, Object>(Buckets<TBucket>::new, false)
                .addMember(Kind.Array, JsonpDeserializer.arrayDeserializer(tBucketDeserializer))
                .addMember(Kind.Keyed, JsonpDeserializer.stringMapDeserializer(tBucketDeserializer)).build();
    }

    @Override
    public final Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    /**
     * Is this variant instance of kind {@code array}?
     */
    public boolean isArray() {
        return _kind == Kind.Array;
    }

    /**
     * Get the {@code array} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code array} kind.
     */
    public List<TBucket> array() {
        return TaggedUnionUtils.get(this, Kind.Array);
    }

    /**
     * Is this variant instance of kind {@code keyed}?
     */
    public boolean isKeyed() {
        return _kind == Kind.Keyed;
    }

    /**
     * Get the {@code keyed} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code keyed} kind.
     */
    public Map<String, TBucket> keyed() {
        return TaggedUnionUtils.get(this, Kind.Keyed);
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        } else {
            switch (_kind) {
                case Array:
                    generator.writeStartArray();
                    for (TBucket item0 : ((List<TBucket>) this._value)) {
                        JsonpUtils.serialize(item0, generator, tBucketSerializer, mapper);

                    }
                    generator.writeEnd();

                    break;
                case Keyed:
                    generator.writeStartObject();
                    for (Map.Entry<String, TBucket> item0 : ((Map<String, TBucket>) this._value).entrySet()) {
                        generator.writeKey(item0.getKey());
                        JsonpUtils.serialize(item0.getValue(), generator, tBucketSerializer, mapper);

                    }
                    generator.writeEnd();

                    break;
            }
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public enum Kind {
        Array, Keyed

    }

    public static class Builder<TBucket> extends ObjectBuilderBase implements ObjectBuilder<Buckets<TBucket>> {
        private Kind _kind;
        private Object _value;

        public ObjectBuilder<Buckets<TBucket>> array(List<TBucket> v) {
            this._kind = Kind.Array;
            this._value = v;
            return this;
        }

        public ObjectBuilder<Buckets<TBucket>> keyed(Map<String, TBucket> v) {
            this._kind = Kind.Keyed;
            this._value = v;
            return this;
        }

        public Buckets<TBucket> build() {
            _checkSingleUse();
            return new Buckets<>(this);
        }

    }

}
