package io.github.stewseo.clients.yelpfusion._types;

import co.elastic.clients.util.ApiTypeHelper;
import co.elastic.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class Category implements JsonpSerializable {
    private final String title;
    private final String alias;
    private final List<String> parent_aliases;
    private final List<String> country_whitelist;
    private final List<String> country_blacklist;

    private Category(Builder builder) {
        this.alias = builder.alias;
        this.title = builder.title;
        this.parent_aliases = ApiTypeHelper.unmodifiable(builder.parent_aliases);
        this.country_blacklist = ApiTypeHelper.unmodifiable(builder.country_blacklist);
        this.country_whitelist = ApiTypeHelper.unmodifiable(builder.country_whitelist);
    }

    public static Category of(Function<Builder, ObjectBuilder<Category>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String alias() {
        return alias;
    }

    public final String title() {
        return title;
    }

    @Nullable
    public final List<String> parent_aliases() {
        return parent_aliases;
    }

    @Nullable
    public final List<String> country_whitelist() {
        return country_whitelist;
    }

    @Nullable
    public final List<String> country_blacklist() {
        return country_blacklist;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
        }

        if (this.title != null) {
            generator.writeKey("title");
            generator.write(this.title);
        }

        if (ApiTypeHelper.isDefined(this.parent_aliases)) {
            generator.writeKey("parent_aliases");
            generator.writeStartArray();
            for (String item0 : this.parent_aliases) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (ApiTypeHelper.isDefined(this.country_whitelist)) {
            generator.writeKey("country_whitelist");
            generator.writeStartArray();
            for (String item0 : this.country_whitelist) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (ApiTypeHelper.isDefined(this.country_blacklist)) {
            generator.writeKey("country_blacklist");
            generator.writeStartArray();
            for (String item0 : country_blacklist) {
                generator.write(item0);
            }
            generator.writeEnd();
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }
    
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Category> {

        private String alias;
        private String title;
        @Nullable
        private List<String> parent_aliases;
        @Nullable
        private List<String> country_whitelist;
        @Nullable
        private List<String> country_blacklist;

        public final Builder alias(String value) {
            this.alias = value;
            return this;
        }

        public final Builder title(String value) {
            this.title = value;
            return this;
        }

        public final Builder parent_aliases(List<String> values) {
            this.parent_aliases = _listAddAll(this.parent_aliases, values);
            return this;
        }

        public final Builder parent_aliases(String value, String... values) {
            this.parent_aliases = _listAdd(parent_aliases, value, values);
            return this;
        }

        public final Builder country_whitelist(List<String> values) {
            this.country_whitelist = _listAddAll(this.country_whitelist, values);
            return this;
        }

        public final Builder country_whitelist(String value, String... values) {
            this.country_whitelist = _listAdd(country_whitelist, value, values);
            return this;
        }

        public final Builder country_blacklist(List<String> value) {
            this.country_blacklist = _listAddAll(country_blacklist, value);
            return this;
        }

        public final Builder country_blacklist(String value, String... values) {
            this.country_blacklist = _listAdd(country_blacklist, value, values);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Category build() {
            _checkSingleUse();

            return new Category(this);
        }
    }

    public static final JsonpDeserializer<Category> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new,
                    Category::setupCategoriesDeserializer);

    protected static void setupCategoriesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Builder::title, JsonpDeserializer.stringDeserializer(), "title");
        op.add(Builder::parent_aliases, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()),
                "parent_aliases");
        op.add(Builder::country_whitelist, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()),
                "country_whitelist");
        op.add(Builder::country_blacklist, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()),
                "country_blacklist");
    }

}
