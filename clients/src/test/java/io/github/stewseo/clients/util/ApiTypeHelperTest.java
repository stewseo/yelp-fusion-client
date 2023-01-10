package io.github.stewseo.clients.util;

import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.util.ApiTypeHelper.DisabledChecksHandle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApiTypeHelperTest {

    @Test
    void testDANGEROUS_disableRequiredPropertiesCheck() {

        assertThat(ApiTypeHelper.requiredPropertiesCheckDisabled()).isFalse();

        try(DisabledChecksHandle disabledChecksHandle =
                    ApiTypeHelper.DANGEROUS_disableRequiredPropertiesCheck(true)) {

            assertThat(disabledChecksHandle).isInstanceOf(DisabledChecksHandle.class);

            assertThat(ApiTypeHelper.requiredPropertiesCheckDisabled()).isTrue();

            disabledChecksHandle.close();
        }
    }

    @Test
    void testRequireNonNull() {

        BusinessDetails businessDetails = BusinessDetails.of(b -> b.id("id"));

        ApiTypeHelper.requireNonNull(businessDetails, this, "name");

        assertThat(businessDetails).isNotNull();
    }

    @Test
    void testIsDefined() {
        assertThrows(org.opentest4j.AssertionFailedError.class,
                () -> assertThat(ApiTypeHelper.isDefined(List.of("value1"))).isFalse()
        );
        boolean isDefined = ApiTypeHelper.isDefined(ApiTypeHelper.undefinedMap());
        assertThat(isDefined).isFalse();

        isDefined = ApiTypeHelper.isDefined(ApiTypeHelper.undefinedList());
        assertThat(isDefined).isFalse();
    }

    @Test
    void testUnmodifiable() {
        assertThrows(java.lang.AssertionError.class,
                () -> assertThat(ApiTypeHelper.unmodifiable(List.of("value1"))).isNotInstanceOf(List.class)
        );
        assertThat(ApiTypeHelper.unmodifiable(ApiTypeHelper.undefinedMap())).isNotNull();
        assertThat(ApiTypeHelper.unmodifiable(ApiTypeHelper.undefinedList())).isNotNull();

    }

    @Test
    void testUnmodifiableRequired() {

        assertThrows(MissingRequiredPropertyException.class,
                () -> ApiTypeHelper.unmodifiableRequired(ApiTypeHelper.undefinedList(), this, "listName")
        );
        assertThrows(MissingRequiredPropertyException.class,
                () -> ApiTypeHelper.unmodifiableRequired(ApiTypeHelper.undefinedMap(), this, "mapName"));

    }
}