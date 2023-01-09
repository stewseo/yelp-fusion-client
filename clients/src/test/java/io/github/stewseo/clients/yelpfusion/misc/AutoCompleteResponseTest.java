package io.github.stewseo.clients.yelpfusion.misc;

import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Term;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AutoCompleteResponseTest extends ModelTestCase<AutoCompleteResponse> {
    private final AutoCompleteResponse autocompleteResponse = of();

    // {"categories":[{"alias":"categoryAliasValue"},{"alias":"category"}],"terms":[{"text":"termTextValue"},{"text":"termTextValue"}],"businesses":[{"id":"businessIdValue"}]}
    private final String expected = "" +
            "{" +
                "\"categories\":" +
                    "[" +
                        "{" +
                            "\"alias\":\"categoryAliasValue\"" +
                        "}," +
                        "{" +
                            "\"alias\":\"category\"" +
                        "}" +
                    "]," +
                "\"terms\":" +
                    "[" +
                        "{" +
                            "\"text\":\"termTextValue\"" +
                        "}," +
                        "{" +
                            "\"text\":\"termTextValue\"" +
                        "}" +
                    "]," +
                "\"businesses\":" +
                    "[" +
                        "{" +
                            "\"id\":\"businessIdValue\"" +
                        "}" +
                    "]" +
            "}";

    private final JsonGenerator generator = generator();

    @Override
    public AutoCompleteResponse of() {

        return AutoCompleteResponse.of(a -> a

                .businesses(List.of(BusinessDetails.of(t -> t
                                .id("businessIdValue")))
                )
                .categories(List.of(Category.of(c -> c
                                .alias("categoryAliasValue")))
                )
                .categories(Category.of(cat -> cat.alias("category")))
                .terms(Term.of(term -> term
                        .text("termTextValue"))
                )
                .terms(List.of(Term.of(t -> t
                                .text("termTextValue")))
                )
        );
    }

    @Test
    public void testOf() {
        assertThat(autocompleteResponse.businesses()).isNotNull();
        assertThat(autocompleteResponse.categories()).isNotNull();
        assertThat(autocompleteResponse.terms()).isNotNull();
        assertThat(autocompleteResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerialize() {
        autocompleteResponse.serialize(generator, mapper);
        AssertionsForClassTypes.assertThat(autocompleteResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        autocompleteResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(autocompleteResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        AutoCompleteResponse deserializedAutoCompleteResponse =
                AutoCompleteResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedAutoCompleteResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserializer() {

        assertThat(AutoCompleteResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }

    @Test
    public void testBuilder() {

        AutoCompleteResponse.Builder builder = new AutoCompleteResponse.Builder().terms(Term.of(t->t.text("termValue")));

        AutoCompleteResponse.Builder self = builder.self();

        Assertions.assertThat(self).isEqualTo(builder);

        AutoCompleteResponse searchBusinessReq = builder.build();

        Assertions.assertThat(searchBusinessReq.toString()).isEqualTo("{\"terms\":[{\"text\":\"termValue\"}]}");
    }

    public JsonParser parser() {
        return parser(autocompleteResponse);
    }
}
