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
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

// typedef: _types.aggregations.MutualInformationHeuristic

/**
 *
 * @see <a href=
 *      "../../doc-files/api-spec.html#_types.aggregations.MutualInformationHeuristic">API
 *      specification</a>
 */
@JsonpDeserializable
public class MutualInformationHeuristic implements JsonpSerializable {
	@Nullable
	private final Boolean backgroundIsSuperset;

	@Nullable
	private final Boolean includeNegatives;

	// ---------------------------------------------------------------------------------------------

	private MutualInformationHeuristic(Builder builder) {

		this.backgroundIsSuperset = builder.backgroundIsSuperset;
		this.includeNegatives = builder.includeNegatives;

	}

	public static MutualInformationHeuristic of(Function<Builder, ObjectBuilder<MutualInformationHeuristic>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * API name: {@code background_is_superset}
	 */
	@Nullable
	public final Boolean backgroundIsSuperset() {
		return this.backgroundIsSuperset;
	}

	/**
	 * API name: {@code include_negatives}
	 */
	@Nullable
	public final Boolean includeNegatives() {
		return this.includeNegatives;
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

		if (this.backgroundIsSuperset != null) {
			generator.writeKey("background_is_superset");
			generator.write(this.backgroundIsSuperset);

		}
		if (this.includeNegatives != null) {
			generator.writeKey("include_negatives");
			generator.write(this.includeNegatives);

		}

	}

	@Override
	public String toString() {
		return JsonpUtils.toString(this);
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Builder for {@link MutualInformationHeuristic}.
	 */

	public static class Builder extends WithJsonObjectBuilderBase<Builder>
			implements
				ObjectBuilder<MutualInformationHeuristic> {
		@Nullable
		private Boolean backgroundIsSuperset;

		@Nullable
		private Boolean includeNegatives;

		/**
		 * API name: {@code background_is_superset}
		 */
		public final Builder backgroundIsSuperset(@Nullable Boolean value) {
			this.backgroundIsSuperset = value;
			return this;
		}

		/**
		 * API name: {@code include_negatives}
		 */
		public final Builder includeNegatives(@Nullable Boolean value) {
			this.includeNegatives = value;
			return this;
		}

		@Override
		protected Builder self() {
			return this;
		}

		/**
		 * Builds a {@link MutualInformationHeuristic}.
		 *
		 * @throws NullPointerException
		 *             if some of the required fields are null.
		 */
		public MutualInformationHeuristic build() {
			_checkSingleUse();

			return new MutualInformationHeuristic(this);
		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Json deserializer for {@link MutualInformationHeuristic}
	 */
	public static final JsonpDeserializer<MutualInformationHeuristic> _DESERIALIZER = ObjectBuilderDeserializer
			.lazy(Builder::new, MutualInformationHeuristic::setupMutualInformationHeuristicDeserializer);

	protected static void setupMutualInformationHeuristicDeserializer(
			ObjectDeserializer<Builder> op) {

		op.add(Builder::backgroundIsSuperset, JsonpDeserializer.booleanDeserializer(), "background_is_superset");
		op.add(Builder::includeNegatives, JsonpDeserializer.booleanDeserializer(), "include_negatives");

	}

}
