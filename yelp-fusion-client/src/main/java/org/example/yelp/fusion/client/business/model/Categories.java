package org.example.yelp.fusion.client.business.model;


import jakarta.json.stream.*;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;

import java.util.*;
import java.util.function.*;

import static org.example.elasticsearch.client.json.JsonpDeserializer.stringDeserializer;

@JsonpDeserializable
public class Categories implements JsonpSerializable {

    private final String title;

    private final List<String> alias;
    private final List<String> parents;
    private final List<String> country_whitelist;
    private final List<String> country_blacklist;


    private Categories(Builder builder) {
        this.alias = builder.alias;
        this.title = builder.title;
        this.parents = builder.parents;
        this.country_blacklist = builder.country_blacklist;
        this.country_whitelist = builder.country_whitelist;
    }
    public List<String> alias() {
        return alias;
    }

    private String title() {
        return title;
    }

    private List<String> parents() {
        return parents;
    }

    private List<String> country_whitelist() {
        return country_whitelist;
    }

    private List<String> country_blacklist() {
        return country_blacklist;
    }

    public static Categories of(Function<Builder, ObjectBuilder<Categories>> fn) {
        return fn.apply(new Builder()).build();
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.writeStartArray();
            for (String item0 : this.alias) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (this.title != null) {
            generator.writeKey("title");
            generator.write(this.title);
        }

        if (this.parents != null) {
            generator.writeKey("parents");
            generator.writeStartArray();
            for (String item0 : this.parents) {
                generator.write(item0);
            }
            generator.writeEnd();
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }
    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<Categories> {
        private  List<String> alias;
        private String title;
        private List<String> parents;

        private List<String> country_whitelist;
        private List<String> country_blacklist;

        public Builder(){}

        public final Builder alias(List<String> value) {
            this.alias = value;
            return this;
        }

        public final Builder alias(String value, String... values) {
            this.alias = _listAdd(this.alias, value, values);
            return this;
        }
        public final Builder title(String title) {
            this.title = title;
            return this;
        }

        public final Builder parents(List<String> parents) {
            this.parents = _listAddAll(this.parents, parents);
            return this;
        }

        public final Builder parents(String value, String... values) {
            this.parents = _listAdd(this.parents, value, values);
            return this;
        }

        public final Builder country_whitelist(List<String> parents) {
            this.country_whitelist = _listAddAll(this.country_whitelist, parents);
            return this;
        }

        public final Builder country_whitelist(String value, String... values) {
            this.country_whitelist = _listAdd(this.country_whitelist, value, values);
            return this;
        }
        public final Builder country_blacklist(List<String> parents) {
            this.country_blacklist = _listAddAll(this.country_blacklist, parents);
            return this;
        }

        public final Builder country_blacklist(String value, String... values) {
            this.country_blacklist = _listAdd(this.country_blacklist, value, values);
            return this;
        }

        public Categories build() {
            _checkSingleUse();
            return new Categories(this);
        }
    }

    public static final JsonpDeserializer<Categories> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Categories.Builder::new,
                    Categories::setupCategoriesDeserializer);

    protected static void setupCategoriesDeserializer(ObjectDeserializer<Categories.Builder> op) {

        op.add(Builder::alias, JsonpDeserializer.arrayDeserializer(stringDeserializer()), "alias");
        op.add(Builder::title, stringDeserializer(), "title");
        op.add(Builder::parents, JsonpDeserializer.arrayDeserializer(stringDeserializer()), "parents");

    }
}
