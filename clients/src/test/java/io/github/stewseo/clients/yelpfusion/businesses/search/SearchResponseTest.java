package io.github.stewseo.clients.yelpfusion.businesses.search;


import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.EXPECTED_BUSINESS_DETAILS_RESULT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.REGION;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchResponseTest {

    private static final JsonpSerializer<BusinessDetails> toStringSerializer =
            (JsonpSerializer<BusinessDetails>) (value, generator, mapper) -> {
                if (value == null) {
                    generator.writeNull();
                } else {
                    generator.write(value.toString());
                }
            };

    private final Hit<BusinessDetails> hit = Hit.of(b -> b
            .source(EXPECTED_BUSINESS_DETAILS_RESULT)
            .tDocumentSerializer(toStringSerializer)
    );

    private SearchResponse<BusinessDetails> of() {
        return SearchResponse.of(s -> s
                .hits(List.of(hit))
                .total(1)
                .region(REGION)
        );
    }

    SearchResponse<BusinessDetails> testSearchBusinessResponse = of();

    @Test
    void serialize() {

        BusinessDetails businessDetails = testSearchBusinessResponse.hits().get(0).source();
        assertThat(businessDetails).isNotNull();
        assertThat(businessDetails).isEqualTo(EXPECTED_BUSINESS_DETAILS_RESULT);
        assertThat(testSearchBusinessResponse.total()).isEqualTo(1);
        assertThat(testSearchBusinessResponse.region()).isEqualTo(REGION);
    }

    @Test
    void createSearchResponseDeserializer() {
    }

    @Test
    void setupSearchResponseDeserializer() {
    }
}