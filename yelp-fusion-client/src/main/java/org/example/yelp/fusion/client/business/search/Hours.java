package org.example.yelp.fusion.client.business.search;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;

import java.io.*;

import static org.example.elasticsearch.client.json.JsonpDeserializer.booleanDeserializer;
import static org.example.elasticsearch.client.json.JsonpDeserializer.stringDeserializer;

//@JsonDeserialize(using = Hours.HoursDeserializer.class)
public class Hours {
    private final Object[] open;
    private final String hours_type;
    private final boolean is_open_now;

    public Object[] open() {
        return open;
    }

    public String hours_type() {
        return hours_type;
    }

    public boolean is_open_now() {
        return is_open_now;
    }

    private Hours(Builder builder) {

        this.open = builder.open;
        this.hours_type = builder.hours_type;
        this.is_open_now = builder.is_open_now;

    }


    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Hours> {
        private Object[] open;
        private String hours_type;
        private boolean is_open_now;

        public final Builder open(Object[] value) {
            this.open = value;
            return this;
        }

        public final Builder hours_type(String value) {
            this.hours_type = value;
            return this;
        }

        public final Builder is_open_now(Boolean value) {
            this.is_open_now = value;
            return this;
        }


        @Override
        protected Builder self() {
            return this;
        }


        public Hours build() {
            _checkSingleUse();
            return new Hours(this);
        }
    }

    public static final JsonpDeserializer<Hours> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Hours.Builder::new,
            Hours::setupHoursDeserializer);

    protected static void setupHoursDeserializer(ObjectDeserializer<Hours.Builder> op) {
        op.add(Hours.Builder::hours_type, stringDeserializer(), "address2");
        op.add(Hours.Builder::is_open_now, booleanDeserializer(), "address3");
    }

    public static class HoursDeserializer extends JsonDeserializer<Hours> {

        @Override
        public Hours deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return null;
        }


    }

}
