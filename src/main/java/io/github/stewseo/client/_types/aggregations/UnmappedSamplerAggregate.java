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
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;

import java.util.function.Function;

// typedef: _types.aggregations.UnmappedSamplerAggregate

/**
 *
 * @see <a href=
 *      "../../doc-files/api-spec.html#_types.aggregations.UnmappedSamplerAggregate">API
 *      specification</a>
 */
@JsonpDeserializable
public class UnmappedSamplerAggregate extends SingleBucketAggregateBase implements AggregateVariant {
	// ---------------------------------------------------------------------------------------------

	private UnmappedSamplerAggregate(Builder builder) {
		super(builder);

	}

	public static UnmappedSamplerAggregate of(Function<Builder, ObjectBuilder<UnmappedSamplerAggregate>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * Aggregate variant kind.
	 */
	@Override
	public Aggregate.Kind _aggregateKind() {
		return Aggregate.Kind.UnmappedSampler;
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Builder for {@link UnmappedSamplerAggregate}.
	 */

	public static class Builder extends AbstractBuilder<Builder>
			implements
				ObjectBuilder<UnmappedSamplerAggregate> {
		@Override
		protected Builder self() {
			return this;
		}

		/**
		 * Builds a {@link UnmappedSamplerAggregate}.
		 *
		 * @throws NullPointerException
		 *             if some of the required fields are null.
		 */
		public UnmappedSamplerAggregate build() {
			_checkSingleUse();

			return new UnmappedSamplerAggregate(this);
		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Json deserializer for {@link UnmappedSamplerAggregate}
	 */
	public static final JsonpDeserializer<UnmappedSamplerAggregate> _DESERIALIZER = ObjectBuilderDeserializer
			.lazy(Builder::new, UnmappedSamplerAggregate::setupUnmappedSamplerAggregateDeserializer);

	protected static void setupUnmappedSamplerAggregateDeserializer(
			ObjectDeserializer<Builder> op) {
		SingleBucketAggregateBase.setupSingleBucketAggregateBaseDeserializer(op);

	}

}
