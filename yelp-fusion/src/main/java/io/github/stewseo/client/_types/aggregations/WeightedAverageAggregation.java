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

import io.github.stewseo.client._types.aggregations.Aggregation;
import io.github.stewseo.client._types.aggregations.AggregationVariant;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

// typedef: _types.aggregations.WeightedAverageAggregation

/**
 *
 * @see <a href=
 *      "../../doc-files/api-spec.html#_types.aggregations.WeightedAverageAggregation">API
 *      specification</a>
 */
@JsonpDeserializable
public class WeightedAverageAggregation extends AggregationBase implements AggregationVariant {
	@Nullable
	private final String format;

	@Nullable
	private final WeightedAverageValue value;

	@Nullable
	private final WeightedAverageValue weight;

	// ---------------------------------------------------------------------------------------------

	private WeightedAverageAggregation(Builder builder) {
		super(builder);

		this.format = builder.format;
		this.value = builder.value;
		this.weight = builder.weight;

	}

	public static WeightedAverageAggregation of(Function<Builder, ObjectBuilder<WeightedAverageAggregation>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * Aggregation variant kind.
	 */
	@Override
	public Aggregation.Kind _aggregationKind() {
		return Aggregation.Kind.WeightedAvg;
	}

	/**
	 * API name: {@code format}
	 */
	@Nullable
	public final String format() {
		return this.format;
	}

	/**
	 * API name: {@code value}
	 */
	@Nullable
	public final WeightedAverageValue value() {
		return this.value;
	}


	/**
	 * API name: {@code weight}
	 */
	@Nullable
	public final WeightedAverageValue weight() {
		return this.weight;
	}

	protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

		super.serializeInternal(generator, mapper);
		if (this.format != null) {
			generator.writeKey("format");
			generator.write(this.format);

		}
		if (this.value != null) {
			generator.writeKey("value");
			this.value.serialize(generator, mapper);

		}
		if (this.weight != null) {
			generator.writeKey("weight");
			this.weight.serialize(generator, mapper);

		}

	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Builder for {@link WeightedAverageAggregation}.
	 */

	public static class Builder extends AbstractBuilder<Builder>
			implements
				ObjectBuilder<WeightedAverageAggregation> {
		@Nullable
		private String format;

		@Nullable
		private WeightedAverageValue value;

		@Nullable
		private WeightedAverageValue weight;

		/**
		 * API name: {@code format}
		 */
		public final Builder format(@Nullable String value) {
			this.format = value;
			return this;
		}

		/**
		 * API name: {@code value}
		 */
		public final Builder value(@Nullable WeightedAverageValue value) {
			this.value = value;
			return this;
		}

		/**
		 * API name: {@code value}
		 */
		public final Builder value(Function<WeightedAverageValue.Builder, ObjectBuilder<WeightedAverageValue>> fn) {
			return this.value(fn.apply(new WeightedAverageValue.Builder()).build());
		}

		/**
		 * API name: {@code weight}
		 */
		public final Builder weight(@Nullable WeightedAverageValue value) {
			this.weight = value;
			return this;
		}

		/**
		 * API name: {@code weight}
		 */
		public final Builder weight(Function<WeightedAverageValue.Builder, ObjectBuilder<WeightedAverageValue>> fn) {
			return this.weight(fn.apply(new WeightedAverageValue.Builder()).build());
		}

		@Override
		protected Builder self() {
			return this;
		}

		/**
		 * Builds a {@link WeightedAverageAggregation}.
		 *
		 * @throws NullPointerException
		 *             if some of the required fields are null.
		 */
		public WeightedAverageAggregation build() {
			_checkSingleUse();

			return new WeightedAverageAggregation(this);
		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Json deserializer for {@link WeightedAverageAggregation}
	 */
	public static final JsonpDeserializer<WeightedAverageAggregation> _DESERIALIZER = ObjectBuilderDeserializer
			.lazy(Builder::new, WeightedAverageAggregation::setupWeightedAverageAggregationDeserializer);

	protected static void setupWeightedAverageAggregationDeserializer(
			ObjectDeserializer<Builder> op) {
		setupAggregationBaseDeserializer(op);
		op.add(Builder::format, JsonpDeserializer.stringDeserializer(), "format");
		op.add(Builder::value, WeightedAverageValue._DESERIALIZER, "value");
		op.add(Builder::weight, WeightedAverageValue._DESERIALIZER, "weight");

	}

}
