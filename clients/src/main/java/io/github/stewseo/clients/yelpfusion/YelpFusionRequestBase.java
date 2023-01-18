package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import jakarta.json.stream.JsonGenerator;

@JsonpDeserializable
public abstract class YelpFusionRequestBase extends RequestBase implements JsonpSerializable {

    private final String location;
    private final Double latitude;
    private final Double longitude;
    private final String locale;

    protected YelpFusionRequestBase(AbstractBuilder<?> builder) {
        this.location = builder.location;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.locale = builder.locale;
    }

    public final String location() {
        return location;
    }

    public final String locale() {
        return locale;
    }

    public final Double latitude() {
        return latitude;
    }

    public final Double longitude() {
        return longitude;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.location != null) {
            generator.writeKey("location");
            generator.write(this.location);
        }
        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }
        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }
        if (this.locale != null) {
            generator.writeKey("locale");
            generator.write(this.locale);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }



	protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupYelpFusionRequestDeserializer(
			ObjectDeserializer<BuilderT> op) {

		op.add(AbstractBuilder::locale, JsonpDeserializer.stringDeserializer(), "locale");
		op.add(AbstractBuilder::location, JsonpDeserializer.stringDeserializer(), "location");
		op.add(AbstractBuilder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(AbstractBuilder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");

	}
    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
                RequestBase.AbstractBuilder<BuilderT> {
        private String location;
        private Double longitude;
        private Double latitude;
        private String locale;

        public final BuilderT location(String value) {
            this.location = value;
            return self();
        }

        public final BuilderT latitude(Double value) {
            this.latitude = value;
            return self();
        }

        public final BuilderT longitude(Double value) {
            this.longitude = value;
            return self();
        }

        public final BuilderT locale(String value) {
            this.locale = value;
            return self();
        }
    }
}
