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
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

// typedef: _types.aggregations.LongTermsBucket

/**
 *
 * @see <a href=
 *      "../../doc-files/api-spec.html#_types.aggregations.LongTermsBucket">API
 *      specification</a>
 */
@JsonpDeserializable
public class LongTermsBucket extends TermsBucketBase {
	private final long key;

	@Nullable
	private final String keyAsString;

	// ---------------------------------------------------------------------------------------------

	private LongTermsBucket(Builder builder) {
		super(builder);

		this.key = ApiTypeHelper.requireNonNull(builder.key, this, "key");
		this.keyAsString = builder.keyAsString;

	}

	public static LongTermsBucket of(Function<Builder, ObjectBuilder<LongTermsBucket>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * Required - API name: {@code key}
	 */
	public final long key() {
		return this.key;
	}

	/**
	 * API name: {@code key_as_string}
	 */
	@Nullable
	public final String keyAsString() {
		return this.keyAsString;
	}

	protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

		super.serializeInternal(generator, mapper);
		generator.writeKey("key");
		generator.write(this.key);

		if (this.keyAsString != null) {
			generator.writeKey("key_as_string");
			generator.write(this.keyAsString);

		}

	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Builder for {@link LongTermsBucket}.
	 */

	public static class Builder extends AbstractBuilder<Builder>
			implements
				ObjectBuilder<LongTermsBucket> {
		private Long key;

		@Nullable
		private String keyAsString;

		/**
		 * Required - API name: {@code key}
		 */
		public final Builder key(long value) {
			this.key = value;
			return this;
		}

		/**
		 * API name: {@code key_as_string}
		 */
		public final Builder keyAsString(@Nullable String value) {
			this.keyAsString = value;
			return this;
		}

		@Override
		protected Builder self() {
			return this;
		}

		/**
		 * Builds a {@link LongTermsBucket}.
		 *
		 * @throws NullPointerException
		 *             if some of the required fields are null.
		 */
		public LongTermsBucket build() {
			_checkSingleUse();

			return new LongTermsBucket(this);
		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Json deserializer for {@link LongTermsBucket}
	 */
	public static final JsonpDeserializer<LongTermsBucket> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
			LongTermsBucket::setupLongTermsBucketDeserializer);

	protected static void setupLongTermsBucketDeserializer(ObjectDeserializer<Builder> op) {
		setupTermsBucketBaseDeserializer(op);
		op.add(Builder::key, JsonpDeserializer.longDeserializer(), "key");
		op.add(Builder::keyAsString, JsonpDeserializer.stringDeserializer(), "key_as_string");

	}

}
