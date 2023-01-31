package io.github.stewseo.clients.json.jackson;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import io.github.stewseo.clients.json.JsonTest;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import jakarta.json.JsonValue;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.SEARCH_BUSINESSES_REQUEST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class JacksonJsonpLocationTest extends ModelJsonTestCase {

    @JsonTest
    void testJacksonJsonpLocation() {

        JacksonJsonpLocation jacksonJsonpLocation = new JacksonJsonpLocation(JsonLocation.NA);

        assertThat(jacksonJsonpLocation.getLineNumber()).isEqualTo(-1L);
        assertThat(jacksonJsonpLocation.getStreamOffset()).isEqualTo(-1L);

    }

}