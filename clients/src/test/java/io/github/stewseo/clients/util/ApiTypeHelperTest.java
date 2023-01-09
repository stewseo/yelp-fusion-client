package io.github.stewseo.clients.util;

import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.util.ApiTypeHelper.DisabledChecksHandle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApiTypeHelperTest {

    @Test
    void DANGEROUS_disableRequiredPropertiesCheck() {

        assertThat(ApiTypeHelper.requiredPropertiesCheckDisabled()).isFalse();

        try(DisabledChecksHandle disabledChecksHandle =
                    ApiTypeHelper.DANGEROUS_disableRequiredPropertiesCheck(true)) {

            assertThat(disabledChecksHandle).isInstanceOf(DisabledChecksHandle.class);

            assertThat(ApiTypeHelper.requiredPropertiesCheckDisabled()).isTrue();

            disabledChecksHandle.close();
        }
    }

    @Test
    void requireNonNull() {

        BusinessDetails businessDetails = BusinessDetails.of(b -> b.id("id"));

        ApiTypeHelper.requireNonNull(businessDetails, this, "name");

        assertThat(businessDetails).isNotNull();
    }

    @Test
    void undefinedList() {

        assertThrows(IndexOutOfBoundsException.class,
                () -> System.out.println(ApiTypeHelper.undefinedList().get(0))
                );

    }

    @Test
    void isDefined() {

        assertThrows(org.opentest4j.AssertionFailedError.class,
                () -> assertThat(ApiTypeHelper.isDefined(List.of("value1"))).isFalse()
        );
    }

    @Test
    void unmodifiable() {
        assertThrows(java.lang.AssertionError.class,
                () -> assertThat(ApiTypeHelper.unmodifiable(List.of("value1"))).isNotInstanceOf(List.class)
        );
    }

    @Test
    void unmodifiableRequired() {

        ApiTypeHelper.unmodifiableRequired(List.of("t"), this, "name");
    }

    @Test
    void undefinedMap() {
    }

    @Test
    void testIsDefined() {
    }

    @Test
    void testUnmodifiable() {
    }

    @Test
    void testUnmodifiableRequired() {
    }
}