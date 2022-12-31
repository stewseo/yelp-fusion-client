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

import io.github.stewseo.client.json.JsonData;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.Map;

// typedef: _types.aggregations.Aggregation

public abstract class AggregationBase implements JsonpSerializable {
    private final Map<String, JsonData> meta;

    @Nullable
    private final String name;

    // ---------------------------------------------------------------------------------------------

    protected AggregationBase(AbstractBuilder<?> builder) {

        this.meta = ApiTypeHelper.unmodifiable(builder.meta);
        this.name = builder.name;

    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupAggregationBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        op.add(AbstractBuilder::meta, JsonpDeserializer.stringMapDeserializer(JsonData._DESERIALIZER), "meta");
        op.add(AbstractBuilder::name, JsonpDeserializer.stringDeserializer(), "name");

    }

    /**
     * API name: {@code meta}
     */
    public final Map<String, JsonData> meta() {
        return this.meta;
    }

    /**
     * API name: {@code name}
     */
    @Nullable
    public final String name() {
        return this.name;
    }

    /**
     * Serialize this object to JSON.
     */
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.meta)) {
            generator.writeKey("meta");
            generator.writeStartObject();
            for (Map.Entry<String, JsonData> item0 : this.meta.entrySet()) {
                generator.writeKey(item0.getKey());
                item0.getValue().serialize(generator, mapper);

            }
            generator.writeEnd();

        }
        if (this.name != null) {
            generator.writeKey("name");
            generator.write(this.name);

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
        private Map<String, JsonData> meta;

        @Nullable
        private String name;

        public final BuilderT meta(Map<String, JsonData> map) {
            this.meta = _mapPutAll(this.meta, map);
            return self();
        }

        public final BuilderT meta(String key, JsonData value) {
            this.meta = _mapPut(this.meta, key, value);
            return self();
        }

        public final BuilderT name(@Nullable String value) {
            this.name = value;
            return self();
        }

        protected abstract BuilderT self();

    }

}
