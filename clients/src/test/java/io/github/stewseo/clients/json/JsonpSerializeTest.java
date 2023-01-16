package io.github.stewseo.clients.json;

import com.fasterxml.jackson.core.JacksonException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class JsonpSerializeTest extends FunctionalTestCase {

    int size = 10;

    @JsonTest
    public void jsonpSerializeTest() {

        final Stream<BusinessDetails> businesses = generateBusinessInstances(size);

        businesses.forEach(this::testSerializeToJSON);

    }

    /**
     * @param business Business instance
     * @link <a href="https://www.baeldung.com/java-validate-json-string">...</a>
     */
    private void testSerializeToJSON(BusinessDetails business) {

        String businessToString = business.toString();

        // assert that business to String is valid JSON
        boolean validJsonByJackson = isValidJsonUsingJackson(businessToString);
        assertTrue(validJsonByJackson);

        boolean validJsonByGson = isValidJsonUsingGson(businessToString);
        assertTrue(validJsonByGson);
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

        final TypeAdapter<BusinessDetails> strictAdapter = new Gson().getAdapter(BusinessDetails.class);

        try {
            strictAdapter.fromJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void testByteArray(BusinessDetails business) {

        final TypeAdapter<BusinessDetails> strictAdapter = new Gson().getAdapter(BusinessDetails.class);

        String toJson = new Gson().toJson(business);
        String businessToString = business.toString();

        if (!toJson.equals(businessToString)) {
            toJson = toJson.replaceAll("[^a-zA-Z0-9,{}:\"]", "");
            businessToString = businessToString.replaceAll("[^a-zA-Z0-9,{}:\"]", "");
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
            assertThat(actualJsonToIS).isEqualTo(expectedJsonToIS);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


