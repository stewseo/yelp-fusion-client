package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.json.jsonb.JsonbJsonpMapper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.Assertions;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonDataTest extends Assertions {


    public static <T, B extends ObjectBuilder<T>> B withJson(B builder, Reader json, YelpFusionClient client) {
        return withJson(builder, json, client._transport().jsonpMapper());
    }

    public static <T, B extends ObjectBuilder<T>> B withJson(B builder, Reader json, JsonpMapper mapper) {
        JsonpDeserializer<?> classDeser = JsonpMapperBase.findDeserializer(builder.getClass().getEnclosingClass());

        @SuppressWarnings("unchecked")
        ObjectDeserializer<B> builderDeser = (ObjectDeserializer<B>)DelegatingDeserializer.unwrap(classDeser);

        JsonParser parser = mapper.jsonProvider().createParser(json);
        builderDeser.deserialize(builder, parser, mapper, parser.next());
        return builder;
    }

    @JsonTest
    public void testBuilderDeserializerHack() {

        SearchBusinessesResult.Builder builder = new SearchBusinessesResult.Builder();

        // Required request parameter
        builder.id("foo");

        // Read body from JSON
        withJson(builder, new StringReader("{\"aliases\": {\"foo\": {\"is_hidden\": true}}}"), new JacksonJsonpMapper());

        SearchBusinessesResult createSearchBusinessResult = builder.build();
        assertThat(createSearchBusinessResult.id()).isEqualTo("foo");
    }

    @JsonTest
    public void testParsing() {
        JsonpMapper mapper = new JsonbJsonpMapper();
        String json = "{\"children\":[{\"doubleValue\":3.2,\"intValue\":2}],\"doubleValue\":2.1,\"intValue\":1," +
                "\"stringValue\":\"foo\"}";

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));

        JsonData data = JsonData.from(parser, mapper);
        assertThat(data.toJson().asJsonObject().getString("stringValue")).isEqualTo("foo");

        JsonpMapperTest.SomeClass to = data.to(JsonpMapperTest.SomeClass.class);
        assertThat(to.getStringValue()).isEqualTo("foo");
    }

    @JsonTest
    public void testSerialize() {

        JsonpMapper mapper = new JsonbJsonpMapper();
        String json = "{\"children\":[{\"doubleValue\":3.2,\"intValue\":2}],\"doubleValue\":2.1,\"intValue\":1," +
                "\"stringValue\":\"foo\"}";

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        JsonpMapperTest.SomeClass sc =
                mapper.deserialize(parser, JsonpMapperTest.SomeClass.class);

        assertThat(sc.getStringValue()).isEqualTo("foo");
        assertThat(sc.getChildren().size()).isEqualTo(1);
        assertThat(sc.getChildren().get(0).getIntValue()).isEqualTo(2);
        // All good

        JsonData data = JsonData.of(sc);

        StringWriter sw = new StringWriter();
        JsonGenerator generator = mapper.jsonProvider().createGenerator(sw);

        data.serialize(generator, mapper);
        generator.close();

        assertThat(sw.toString()).isEqualTo(json);
    }

    @JsonTest
    public void testConvert() {

        JsonData json = JsonData.of("foo");

        final JsonValue value = json.toJson(new JsonbJsonpMapper());

        assertThat(JsonValue.ValueType.STRING).isEqualTo(value.getValueType());
        assertThat("foo").isEqualTo(((JsonString)value).getString());
    }
}