package io.github.stewseo.clients.yelpfusion.categories.alias;

import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion.categories.alias.CategoriesAliasResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CategoriesAliasResponseTest extends ModelTestCase<CategoriesAliasResponse> {

    private final CategoriesAliasResponse categoriesAliasResponse = of();

    private final String expected = "{\"category\":{\"alias\":\"categoryAliasValue\"}}";

    private final JsonGenerator generator = generator();

    @Override
    public CategoriesAliasResponse of() {
        return CategoriesAliasResponse.of(fr -> fr
                .categories(c -> c
                        .alias("categoryAliasValue"))
        );
    }

    @Test
    public void testOf() {
        assertThat(categoriesAliasResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerialize() {
        categoriesAliasResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(categoriesAliasResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        categoriesAliasResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(categoriesAliasResponse.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(categoriesAliasResponse.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        CategoriesAliasResponse deserializedCategoriesAliasResponse =
                CategoriesAliasResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedCategoriesAliasResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserializer() {

        assertThat(CategoriesAliasResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }

    @Test
    public void testBuilder() {

        CategoriesAliasResponse.Builder builder = new CategoriesAliasResponse.Builder().categories(Category.of(e-> e.alias("aliasValue")));

        CategoriesAliasResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);


        CategoriesAliasResponse searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"category\":{\"alias\":\"aliasValue\"}}");
    }
}
