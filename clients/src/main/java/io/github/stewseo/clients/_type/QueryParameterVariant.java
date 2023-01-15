package io.github.stewseo.clients._type;

import io.github.stewseo.clients.json.JsonpSerializable;

public interface QueryParameterVariant extends JsonpSerializable {

	QueryParameter.Kind _queryFieldKind();

	default QueryParameter _toQueryField() {
		return new QueryParameter(this);
	}

}
