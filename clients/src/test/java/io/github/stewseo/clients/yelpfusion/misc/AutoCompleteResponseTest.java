package io.github.stewseo.clients.yelpfusion.misc;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AutoCompleteResponseTest extends YelpFusionTestCase<AutoCompleteResponse> {

    private final AutoCompleteResponse autocompleteResponse = of();

    @Override
    public AutoCompleteResponse of() {

        return AutoCompleteResponse.of(a -> a

                .businesses(BusinessDetails.of(t -> t.id("businessIdValue"))
                )
                .categories(Category.of(cat -> cat.alias("categoryAliasValue"))
                )
                .terms("restaurants")
        );
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(autocompleteResponse.businesses()).isNotNull();
        assertThat(autocompleteResponse.categories()).isNotNull();
        assertThat(autocompleteResponse.terms()).isNotNull();
    }

    @YelpFusionTest
    public void testBuilder() {

        AutoCompleteResponse.Builder builder = new AutoCompleteResponse.Builder()
                .businesses(List.of(BusinessDetails.of(t -> t
                                .id("businessIdValue")))
                ).categories(List.of(Category.of(c -> c
                                .alias("categoryAliasValue")))
                ).terms(List.of("restaurants")

        );

        AutoCompleteResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        AutoCompleteResponse autoCompleteResponse = builder.build();

        assertThat(autoCompleteResponse.toString().substring(0, 3)).startsWith("{\"c");
    }

    private final JsonGenerator generator = generator();

    @YelpFusionTest
    public void testSerialize() {
        autocompleteResponse.serialize(generator, mapper);
        assertThat(autocompleteResponse.toString().substring(0, 3)).startsWith("{\"c");
    }

    @YelpFusionTest
    public void testSerializeInternal() {

        generator.writeStartObject();
        autocompleteResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();

        AssertionsForClassTypes.assertThat(autocompleteResponse.toString().substring(0, 12)).startsWith("{\"categories");
    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        AutoCompleteResponse deserializedAutoCompleteResponse =
                AutoCompleteResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedAutoCompleteResponse.toString().substring(0, 3)).startsWith("{\"c");
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(AutoCompleteResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }

    public JsonParser parser() {
        return parser(autocompleteResponse);
    }
}
