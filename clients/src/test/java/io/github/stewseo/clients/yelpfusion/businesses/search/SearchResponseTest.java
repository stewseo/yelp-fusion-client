package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.NamedDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class SearchResponseTest {
    private final JsonpDeserializer<SearchResponse<SearchBusinessResult>> tDocumentDeserializer =
            ObjectBuilderDeserializer.createForObject((Supplier<SearchResponse.Builder<SearchBusinessResult>>) SearchResponse.Builder::new,
                    op -> SearchResponse.setupSearchResponseDeserializer(op,
                            new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search.TDocument")));

    @Test
    public void testSetupResponseBodyDeserializer() {

        assertThat(tDocumentDeserializer).isExactlyInstanceOf(ObjectBuilderDeserializer.class);
    }
}