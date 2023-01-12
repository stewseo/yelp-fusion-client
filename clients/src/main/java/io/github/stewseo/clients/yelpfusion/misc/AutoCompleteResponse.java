package io.github.stewseo.clients.yelpfusion.misc;

import co.elastic.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Term;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class AutoCompleteResponse implements JsonpSerializable {

    private final List<Category> categories;
    private final List<Term> terms;
    private final List<BusinessDetails> businesses;

    protected AutoCompleteResponse(Builder builder) {
        this.categories = ApiTypeHelper.unmodifiable(builder.categories);
        this.terms = ApiTypeHelper.unmodifiable(builder.terms);
        this.businesses = ApiTypeHelper.unmodifiable(builder.businesses);
    }

    public static AutoCompleteResponse of(Function<Builder, ObjectBuilder<AutoCompleteResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    @Nullable
    public final List<Category> categories() {
        return this.categories;
    }

    @Nullable
    public final List<Term> terms() {
        return this.terms;
    }

    @Nullable
    public final List<BusinessDetails> businesses() {
        return this.businesses;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.categories)) {
            generator.writeKey("categories");
            generator.writeStartArray();
            for (Category item0 : categories) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.terms)) {
            generator.writeKey("terms");
            generator.writeStartArray();
            for (Term item0 : terms) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
        if (ApiTypeHelper.isDefined(this.businesses)) {
            generator.writeKey("businesses");
            generator.writeStartArray();
            for (BusinessDetails item0 : businesses) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<AutoCompleteResponse> {

        @Nullable
        private List<Category> categories;

        @Nullable
        private List<Term> terms;
        @Nullable
        private List<BusinessDetails> businesses;

        public final Builder categories(List<Category> categories) {
            this.categories = _listAddAll(this.categories, categories);
            return self();
        }

        public final Builder categories(Category value, Category... values) {
            this.categories = _listAdd(this.categories, value, values);
            return self();
        }

        public final Builder terms(List<Term> categories) {
            this.terms = _listAddAll(this.terms, categories);
            return self();
        }

        public final Builder terms(Term value, Term... values) {
            this.terms = _listAdd(this.terms, value, values);
            return self();
        }


        public final Builder businesses(List<BusinessDetails> businesses) {
            this.businesses = _listAddAll(this.businesses, businesses);
            return self();
        }

        public final Builder businesses(BusinessDetails value, BusinessDetails... values) {
            this.businesses = _listAdd(this.businesses, value, values);
            return self();
        }

        @Override
        protected Builder self() {
            return this;
        }

        public AutoCompleteResponse build() {
            _checkSingleUse();
            return new AutoCompleteResponse(this);
        }
    }

    public static final JsonpDeserializer<AutoCompleteResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new, AutoCompleteResponse::setupAutoCompleteDeserializer);

    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::terms, JsonpDeserializer.arrayDeserializer(Term._DESERIALIZER), "terms");
        op.add(Builder::categories, JsonpDeserializer.arrayDeserializer(Category._DESERIALIZER), "categories");
        op.add(Builder::businesses, JsonpDeserializer.arrayDeserializer(BusinessDetails._DESERIALIZER), "businesses");
    }


}
