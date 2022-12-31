package io.github.stewseo.client.yelpfusion.misc;

import co.elastic.clients.util.ApiTypeHelper;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.client.yelpfusion.business.Business;
import io.github.stewseo.client.yelpfusion.business.Term;
import io.github.stewseo.client.yelpfusion.categories.Category;

import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

// TODO: extend Result<TDocument> and replace all, terms, businesses with TDocument result
@JsonpDeserializable
public class AutoCompleteResponse implements JsonpSerializable {
    // ------------------------------ Fields ------------------------------------ //
    private final List<Category> categories;
    private final List<Term> terms;
    private final List<Business> businesses;
    // ------------------------------ Constructor ------------------------------------ //
    protected AutoCompleteResponse(Builder builder) {
        this.categories = builder.categories;
        this.terms = builder.terms;
        this.businesses = builder.businesses;
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
    public final List<Business> businesses() {
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
            for (Business item0 : businesses) {
                item0.serialize( generator, mapper);
            }
            generator.writeEnd();
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    // ------------------------------ Builder ------------------------------------ //

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<AutoCompleteResponse> {
        
        @Nullable
        private List<Category> categories;

        @Nullable
        private List<Term> terms;
        @Nullable
        private List<Business> businesses;

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


        public final Builder businesses(List<Business> businesses) {
            this.businesses = _listAddAll(this.businesses, businesses);
            return self();
        }

        public final Builder businesses(Business value, Business... values) {
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

    // ------------------------------ Deserializer ------------------------------------ //

    public static final JsonpDeserializer<AutoCompleteResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new, AutoCompleteResponse::setupAutoCompleteDeserializer);
    
    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::terms, JsonpDeserializer.arrayDeserializer(Term._DESERIALIZER), "terms");
        op.add(Builder::categories, JsonpDeserializer.arrayDeserializer(Category._DESERIALIZER), "categories");
        op.add(Builder::businesses, JsonpDeserializer.arrayDeserializer(Business._DESERIALIZER), "businesses");
    }

}
