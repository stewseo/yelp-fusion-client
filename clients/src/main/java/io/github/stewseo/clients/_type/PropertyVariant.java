package io.github.stewseo.clients._type;

import io.github.stewseo.clients.json.JsonpSerializable;

public interface PropertyVariant extends JsonpSerializable {

	Property.Kind _propertyKind();

	default Property _toProperty() {
		return new Property(this);
	}

}
