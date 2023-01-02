package io.github.stewseo.client.yelpfusion.json;

import com.fasterxml.jackson.core.JacksonException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.business.Business;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonpSerializeTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(JsonpSerializeTest.class);

    int size = 10;

    @Test
    public void jsonpSerializeTest() throws IOException {

        logger.info("testing {} instances of Business", size);

        JsonpDeserializer<Business> businessJsonpDeserializer = Business._DESERIALIZER;

        List<String> list = List.of("San Francisco", "Oakland", "Carmel", "Napa", "Los Angeles", "San Diego");

        int max = list.size();
        String state = "CA";
        String country = "USA";

        final Stream<Business> businesses = generateBusinessInstances(size);

        businesses.forEach(this::testSerializeToJSON);

        logger.info("jsonpSerializeTest complete.");

    }

    /**
     * @param business Business instance
     * @link <a href="https://www.baeldung.com/java-validate-json-string">...</a>
     */
    private void testSerializeToJSON(Business business) {

        String businessToString = business.toString();

        // assert that business to String is valid JSON
        boolean validJsonByJackson = isValidJsonUsingJackson(businessToString);
        assertTrue(validJsonByJackson);

        boolean validJsonByGson = isValidJsonUsingGson(businessToString);
        assertTrue(validJsonByGson);

        // assert that expected data as input stream
        // is equal to actual data as input stream
        testByteArray(business);
    }

    public boolean isValidJsonUsingJackson(String json) {

        try {
            new JacksonJsonpMapper().objectMapper().readTree(json);

        } catch (JacksonException e) {
            return false;
        }
        return true;
    }

    public boolean isValidJsonUsingGson(String json) {

        final TypeAdapter<Business> strictAdapter = new Gson().getAdapter(Business.class);

        try {
            strictAdapter.fromJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void testByteArray(Business business) {

        final TypeAdapter<Business> strictAdapter = new Gson().getAdapter(Business.class);

        String toJson = new Gson().toJson(business);
        String businessToString = business.toString();

        if (!toJson.equals(businessToString)) {
            toJson = toJson.replaceAll("[^a-zA-Z0-9,{}:\"]", "");
            businessToString = businessToString.toString().replaceAll("[^a-zA-Z0-9,{}:\"]", "");
        }
        assertThat(toJson).isEqualTo(businessToString);

        final byte[] toJsonToByteArray = toJson.getBytes(StandardCharsets.UTF_8);
        final byte[] businessToStringToByteArray = businessToString.getBytes(StandardCharsets.UTF_8);
        assertThat(businessToStringToByteArray).isEqualTo(toJsonToByteArray);
    }

    private void testInputStream(String expectedJsonString, String actualJsonString) {

        final InputStream expectedJsonToIS = IOUtils.toInputStream(expectedJsonString, StandardCharsets.UTF_8);
        final InputStream actualJsonToIS = IOUtils.toInputStream(actualJsonString, StandardCharsets.UTF_8);

        try (expectedJsonToIS; actualJsonToIS) {
            assertThat(actualJsonToIS).isEqualTo(expectedJsonString);

            expectedJsonToIS.close();
            actualJsonToIS.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


