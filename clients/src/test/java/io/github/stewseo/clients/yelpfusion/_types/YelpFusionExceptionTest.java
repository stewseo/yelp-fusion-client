package io.github.stewseo.clients.yelpfusion._types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YelpFusionExceptionTest {

    private void testYelpFusionException(int input) {
        if(input != 1) {
            throw new YelpFusionException("endpointId", ErrorResponse.of(e -> e
                    .error(ec -> ec
                            .reason("reason")
                            .type("type")
                    ).status(401)
            ));
        }
    }

    @Test
    void testYelpFusionException() {
        int input = 2;
        YelpFusionException exception = assertThrows(YelpFusionException.class,
                () -> testYelpFusionException(input)
        );
        assertThat(exception.endpointId()).isEqualTo("endpointId");

        assertThat(exception.response().toString()).isEqualTo("{\"error\":{\"type\":\"type\",\"reason\":\"reason\"},\"status\":401}");
        assertThat(exception.error().toString()).isEqualTo("{\"type\":\"type\",\"reason\":\"reason\"}");
        assertThat(exception.status()).isEqualTo(401);
    }

}