package io.github.stewseo.yelpfusion.misc;

import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Term;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AutoCompleteResponseTest extends ModelTestCase<AutoCompleteResponse> {

    // {"categories":[{"alias":"categoryAliasValue"},{"alias":"category"}],"terms":[{"text":"termTextValue"},{"text":"termTextValue"}],"businesses":[{"id":"businessIdValue"}]}
    private final String expected = "" +
            "{" +
                "\"categories\":" +
                    "[" +
                        "{" +
                            "\"alias\":\"categoryAliasValue\"" +
                        "}" +
                    "]," +
                "\"terms\":" +
                    "[" +
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

    private final AutoCompleteResponse autocompleteResponse = of();

    @Override
    public AutoCompleteResponse of() {

        return AutoCompleteResponse.of(a -> a

                .businesses(BusinessDetails.of(t -> t.id("businessIdValue"))
                )
                .categories(Category.of(cat -> cat.alias("categoryAliasValue"))
                )
                .terms(Term.of(term -> term.text("termTextValue"))
                )
        );
    }

    @Test
    public void testOf() {
        assertThat(autocompleteResponse.businesses()).isNotNull();
        assertThat(autocompleteResponse.categories()).isNotNull();
        assertThat(autocompleteResponse.terms()).isNotNull();
    }

    @Test
    public void testBuilder() {

        AutoCompleteResponse.Builder builder = new AutoCompleteResponse.Builder()
                .businesses(List.of(BusinessDetails.of(t -> t
                                .id("businessIdValue")))
                ).categories(List.of(Category.of(c -> c
                                .alias("categoryAliasValue")))
                ).terms(List.of(Term.of(t -> t
                                .text("termTextValue"))
                )

        );

        AutoCompleteResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        AutoCompleteResponse searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo(expected);
    }

    private final JsonGenerator generator = generator();

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

        assertThat(deserializedAutoCompleteResponse.toString()).contains(expected);
    }

    @Test
    public void testDeserializer() {

        assertThat(AutoCompleteResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }

    public JsonParser parser() {
        return parser(autocompleteResponse);
    }
}
