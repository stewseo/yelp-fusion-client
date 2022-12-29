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

// typedef: _types.aggregations.StringRareTermsAggregate

/**
 * Result of the <code>rare_terms</code> aggregation when the field is a string.
 * 
 * @see <a href=
 *      "../../doc-files/api-spec.html#_types.aggregations.StringRareTermsAggregate">API
 *      specification</a>
 */
@JsonpDeserializable
public class StringRareTermsAggregate extends MultiBucketAggregateBase<StringRareTermsBucket>
		implements
        AggregateVariant {
	// ---------------------------------------------------------------------------------------------

	private StringRareTermsAggregate(Builder builder) {
		super(builder);

	}

	public static StringRareTermsAggregate of(Function<Builder, ObjectBuilder<StringRareTermsAggregate>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * Aggregate variant kind.
	 */
	@Override
	public Aggregate.Kind _aggregateKind() {
		return Aggregate.Kind.Srareterms;
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Builder for {@link StringRareTermsAggregate}.
	 */

	public static class Builder extends AbstractBuilder<StringRareTermsBucket, Builder>
			implements
				ObjectBuilder<StringRareTermsAggregate> {
		@Override
		protected Builder self() {
			return this;
		}

		/**
		 * Builds a {@link StringRareTermsAggregate}.
		 *
		 * @throws NullPointerException
		 *             if some of the required fields are null.
		 */
		public StringRareTermsAggregate build() {
			_checkSingleUse();
			super.tBucketSerializer(null);

			return new StringRareTermsAggregate(this);
		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Json deserializer for {@link StringRareTermsAggregate}
	 */
	public static final JsonpDeserializer<StringRareTermsAggregate> _DESERIALIZER = ObjectBuilderDeserializer
			.lazy(Builder::new, StringRareTermsAggregate::setupStringRareTermsAggregateDeserializer);

	protected static void setupStringRareTermsAggregateDeserializer(
			ObjectDeserializer<Builder> op) {
		MultiBucketAggregateBase.setupMultiBucketAggregateBaseDeserializer(op, StringRareTermsBucket._DESERIALIZER);

	}

}
