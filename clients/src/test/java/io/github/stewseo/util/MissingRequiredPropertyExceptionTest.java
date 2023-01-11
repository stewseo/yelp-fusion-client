package io.github.stewseo.util;

import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.MissingRequiredPropertyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MissingRequiredPropertyExceptionTest {

    private String id;

    @Test
    void getObjectClass() {

        MissingRequiredPropertyException exception =
                assertThrows(MissingRequiredPropertyException.class,
                        () -> ApiTypeHelper.requireNonNull(id, this, "idName"));

        assertThat(exception.getObjectClass()).isEqualTo(this.getClass());

        assertThat(exception.getObjectClass()).isEqualTo(MissingRequiredPropertyExceptionTest.class);

        assertThat(exception.getPropertyName()).isEqualTo("idName");

    }

}