package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.COUNTRY_BLACKLIST;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.COUNTRY_WHITELIST;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.PARENT_ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.TITLE;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest extends ModelTestCase<Category> {

    public Category of() {

        return Category.of(e -> e
                .alias(ALIAS)
                .title(TITLE)
                .country_blacklist(COUNTRY_BLACKLIST)
                .country_whitelist(COUNTRY_WHITELIST)
                .parent_aliases(PARENT_ALIAS)
        );
    }

    private final Category category = of();

    String expected = "{\"alias\":\"alias\"," +
            "\"title\":\"title\"," +
            "\"parent_aliases\":[\"parents\"]," +
            "\"country_whitelist\":[\"country_whitelist\"]," +
            "\"country_blacklist\":[\"country_blacklist\"]}";

    @Test
    public void testOf() {
        assertThat(category.alias()).isEqualTo(ALIAS);
        assertThat(category.title()).isEqualTo(TITLE);
        assertThat(category.parent_aliases()).isNotNull();
        assertThat(category.country_blacklist()).isNotNull();
        assertThat(category.country_whitelist()).isNotNull();
    }
    private Category.Builder ofBuilder() {
        return new Category.Builder()
                .alias(ALIAS)
                .title(TITLE)
                .country_blacklist(List.of(COUNTRY_BLACKLIST))
                .country_whitelist(List.of(COUNTRY_WHITELIST))
                .parent_aliases(List.of(PARENT_ALIAS));
    }
    @Test
    public void testBuilder() {

        Category.Builder builder = ofBuilder();

        Category.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        assertThat(builder).isNotEqualTo(ofBuilder().self());

        Category cat = builder.build();

        assertThat(cat.toString()).isEqualTo(expected);
    }

    JsonGenerator generator = generator();
    @Test
    public void testSerialize() {
        category.serialize(generator, mapper);
        assertThat(category.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        category.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(category.toString()).isNotNull();
    }

    @Test
    public void testDeserializer() {
        assertThat(Category._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserialize() {

        Category cat = Category._DESERIALIZER.deserialize(parser(), mapper);

        assertThat(cat.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(category);
    }

}
