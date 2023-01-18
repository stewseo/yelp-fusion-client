package io.github.stewseo.clients.yelpfusion._types;


import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.OpenTaggedUnion;
import io.github.stewseo.clients.util.TaggedUnionUtils;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

// typedef: _types.QueryParameter
/**
 *
 * @see <a href=
 *      "https://docs.developer.yelp.com/reference/v3_business_search">Documentation
 *      on yelp fusion query parameters</a>
 */
@JsonpDeserializable
public class QueryParameter implements OpenTaggedUnion<QueryParameter.Kind, Object>, JsonpSerializable {

    public enum Kind implements JsonEnum {

        Term("term"),
        TransactionType("transaction_type"),

        Location("location"),
//        Limit("limit"),
        _Custom(null);

        private final String jsonValue;

        Kind(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String jsonValue() {
            return this.jsonValue;
        }

    }

	private final Kind _kind;

	private final Object _value;

    @Nullable
    private final String _customKind;

	@Override
	public final Kind _kind() {
		return _kind;
	}

	@Override
	public final Object _get() {
		return _value;
	}

    public QueryParameter(Kind kind, Object o) {
        this._kind = kind;
        this._value = o;
        this._customKind = null;
    }

    public QueryParameter(@Nullable String kind, JsonData value) {
        this._kind = QueryParameter.Kind._Custom;
        this._value = value;
        this._customKind = kind;
    }

    public QueryParameter(QueryParameterVariant value) {

		this._kind = ApiTypeHelper.requireNonNull(value._queryFieldKind(), this, "<variant kind>");
		this._value = ApiTypeHelper.requireNonNull(value, this, "<variant value>");
		this._customKind = null;

	}

	private QueryParameter(Builder builder) {

		this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
		this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");
		this._customKind = builder._customKind;

	}

	public static QueryParameter of(Function<Builder, ObjectBuilder<QueryParameter>> fn) {
		return fn.apply(new Builder()).build();
	}

    public boolean isTerm() {
        return _kind == Kind.Term;
    }

    public TermQueryParameter term() {
        return TaggedUnionUtils.get(this, Kind.Term);
    }

    public boolean isLocation() {
        return _kind == Kind.Location;
    }

    public LocationQueryParameter location() {
        return TaggedUnionUtils.get(this, Kind.Location);
    }

    /**
     * Get the actual kind when {@code _kind()} equals {@link QueryParameter.Kind#_Custom}
     * (plugin-defined variant).
     */
    public final String _customKind() {
        return _customKind;
    }

    /**
     * Get the custom plugin-defined variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not {@link QueryParameter.Kind#_Custom}.
     */
    public boolean _isCustom() {
        return _kind == Kind._Custom;
    }

    /**
     * Get the custom plugin-defined variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not {@link Kind#_Custom}.
     */
    public JsonData _custom() {
        return TaggedUnionUtils.get(this, Kind._Custom);
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeStartObject();

        generator.writeKey(_kind == QueryParameter.Kind._Custom ? _customKind : _kind.jsonValue());

        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        }

        generator.writeEnd();

    }

    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<QueryParameter> {

        private Kind _kind;
        private Object _value;
        private String _customKind;

        public final Builder kind(Kind value) {
            this._kind = value;
            return this;
        }

        public final Builder _value(Object value) {
            this._value = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public ObjectBuilder<QueryParameter> term(TermQueryParameter v) {
            this._kind = Kind.Term;
            this._value = v;
            return this;
        }

        public ObjectBuilder<QueryParameter> term(Function<TermQueryParameter.Builder, ObjectBuilder<TermQueryParameter>> fn) {
            return this.term(fn.apply(new TermQueryParameter.Builder()).build());
        }

        public ObjectBuilder<QueryParameter> location(LocationQueryParameter v) {
			this._kind = Kind.Location;
			this._value = v;
			return this;
		}

        public ObjectBuilder<QueryParameter> location(Function<LocationQueryParameter.Builder, ObjectBuilder<LocationQueryParameter>> fn) {
            return this.location(fn.apply(new LocationQueryParameter.Builder()).build());
        }


        public ObjectBuilder<QueryParameter> _custom(String name, Object data) {
            this._kind = Kind._Custom;
            this._customKind = name;
            this._value = JsonData.of(data);
            return this;
        }

        @Override
        public QueryParameter build() {
            _checkSingleUse();
            return new QueryParameter(this);
        }
    }


    public static final JsonpDeserializer<QueryParameter> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            QueryParameter::setupQueryFieldDeserializer, Builder::build);

    protected static void setupQueryFieldDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::term, TermQueryParameter._DESERIALIZER, "term");
        op.add(Builder::location, LocationQueryParameter._DESERIALIZER, "location");

        op.setUnknownFieldHandler((builder, name, parser, mapper) -> {
            JsonpUtils.ensureCustomVariantsAllowed(parser, mapper);
            builder._custom(name, JsonData._DESERIALIZER.deserialize(parser, mapper));
        });

        // locale
        // categories, attributes
        // limit, offset
        // radius, distance
    }


}
