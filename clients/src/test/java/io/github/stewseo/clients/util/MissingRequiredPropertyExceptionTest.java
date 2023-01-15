package io.github.stewseo.clients.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("utils")

class MissingRequiredPropertyExceptionTest {

    private String id;

    @UtilTest
    void getObjectClass() {

        MissingRequiredPropertyException exception =
                assertThrows(MissingRequiredPropertyException.class,
                        () -> ApiTypeHelper.requireNonNull(id, this, "idName"));

        assertThat(exception.getObjectClass()).isEqualTo(this.getClass());

        assertThat(exception.getObjectClass()).isEqualTo(MissingRequiredPropertyExceptionTest.class);

        assertThat(exception.getPropertyName()).isEqualTo("idName");

    }

}