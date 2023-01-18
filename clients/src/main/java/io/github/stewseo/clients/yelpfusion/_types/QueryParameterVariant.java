package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonpSerializable;

public interface QueryParameterVariant extends JsonpSerializable {

	QueryParameter.Kind _queryFieldKind();

	default QueryParameter _toQueryField() {
		return new QueryParameter(this);
	}

}
