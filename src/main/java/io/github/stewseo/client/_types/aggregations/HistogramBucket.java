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

// typedef: _types.aggregations.HistogramBucket

/**
 *
 * @see <a href=
 *      "../../doc-files/api-spec.html#_types.aggregations.HistogramBucket">API
 *      specification</a>
 */
@JsonpDeserializable
public class HistogramBucket extends MultiBucketBase {
	@Nullable
	private final String keyAsString;

	private final double key;

	// ---------------------------------------------------------------------------------------------

	private HistogramBucket(Builder builder) {
		super(builder);

		this.keyAsString = builder.keyAsString;
		this.key = ApiTypeHelper.requireNonNull(builder.key, this, "key");

	}

	public static HistogramBucket of(Function<Builder, ObjectBuilder<HistogramBucket>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * API name: {@code key_as_string}
	 */
	@Nullable
	public final String keyAsString() {
		return this.keyAsString;
	}

	/**
	 * Required - API name: {@code key}
	 */
	public final double key() {
		return this.key;
	}

	protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

		super.serializeInternal(generator, mapper);
		if (this.keyAsString != null) {
			generator.writeKey("key_as_string");
			generator.write(this.keyAsString);

		}
		generator.writeKey("key");
		generator.write(this.key);

	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Builder for {@link HistogramBucket}.
	 */

	public static class Builder extends AbstractBuilder<Builder>
			implements
				ObjectBuilder<HistogramBucket> {
		@Nullable
		private String keyAsString;

		private Double key;

		/**
		 * API name: {@code key_as_string}
		 */
		public final Builder keyAsString(@Nullable String value) {
			this.keyAsString = value;
			return this;
		}

		/**
		 * Required - API name: {@code key}
		 */
		public final Builder key(double value) {
			this.key = value;
			return this;
		}

		@Override
		protected Builder self() {
			return this;
		}

		/**
		 * Builds a {@link HistogramBucket}.
		 *
		 * @throws NullPointerException
		 *             if some of the required fields are null.
		 */
		public HistogramBucket build() {
			_checkSingleUse();

			return new HistogramBucket(this);
		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Json deserializer for {@link HistogramBucket}
	 */
	public static final JsonpDeserializer<HistogramBucket> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
			HistogramBucket::setupHistogramBucketDeserializer);

	protected static void setupHistogramBucketDeserializer(ObjectDeserializer<Builder> op) {
		setupMultiBucketBaseDeserializer(op);
		op.add(Builder::keyAsString, JsonpDeserializer.stringDeserializer(), "key_as_string");
		op.add(Builder::key, JsonpDeserializer.doubleDeserializer(), "key");

	}

}
