package io.github.stewseo.client.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import io.github.stewseo.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.client.yelpfusion.business.Business;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJson {

    private static final Logger logger = LoggerFactory.getLogger(TestJson.class);
    private static final JacksonJsonpMapper mapper = new JacksonJsonpMapper();

    private static final Gson gson = new Gson();


    /**
     * @param business Business instance
     *  @link <a href="https://www.baeldung.com/java-validate-json-string">...</a>
     *
     */
    public void testSerializeToJSON(Business business) {

        String businessToString = business.toString();

        // assert that business to String is valid JSON
        boolean validJsonByJackson = isValidJsonUsingJackson(businessToString);
        assertTrue(validJsonByJackson);

        boolean validJsonByGson = isValidJsonUsingGson(businessToString);
        assertTrue(validJsonByGson);

        // assert that expected data as input stream
        // is equal to actual data as input stream
        String toJson = gson.toJson(business);
        testByteArray(toJson, businessToString);
    }

    static AtomicInteger count = new AtomicInteger(0);
    public <T> int assertIsValidJson(T json) {
        String actualJsonString = json.toString();
        // assert that business to String is valid JSON
        boolean validJsonByJackson = isValidJsonUsingJackson(actualJsonString);
        assertTrue(validJsonByJackson);

        boolean validJsonByGson = isValidJsonUsingGson(actualJsonString);

        assertTrue(validJsonByGson);

        return count.getAndIncrement();
    }

    public boolean isValidJsonUsingJackson(String json) {

        try {
            mapper.objectMapper().readTree(json);

        } catch (JacksonException e) {
            return false;
        }
        return true;
    }

    public boolean isValidJsonUsingGson(String json) {

        final TypeAdapter<Business> strictAdapter = gson.getAdapter(Business.class);

        try {
            strictAdapter.fromJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private Stream<byte[]> testByteArray(String toJson, String businessToString) {

        // assert that expected data as input stream
        // is equal to actual data as input stream
//        // test as byte[]
//        List<byte[]> byteArrays = testByteArray(map.toString(), actualJsonString).toList();
//        byte[] businessToStringToByteArray = byteArrays.get(0);
//        byte[] toJsonToByteArray = byteArrays.get(1);
////        logger.info("businessToStringToByteArray: " + Arrays.toString(businessToStringToByteArray) + " toJsonToByteArray: " + Arrays.toString(toJsonToByteArray));
//        assertThat(businessToStringToByteArray).isEqualTo(toJsonToByteArray);
//
//        // test as input stream
//        List<InputStream> inputStreams = testInputStream(map.toString(), actualJsonString).toList();
//        InputStream businessToStringIs = inputStreams.get(0);
//        InputStream toJsonIs = inputStreams.get(1);
//        assertThat(toJsonIs).isEqualTo(businessToStringIs);

        final TypeAdapter<Business> strictAdapter = new Gson().getAdapter(Business.class);

        if(!toJson.equals(businessToString)) {
            assertThat(toJson).isEqualTo(businessToString);
            toJson = replaceInvalidJsonCharacters(toJson);
            businessToString = replaceInvalidJsonCharacters(businessToString);
        }
        assertThat(toJson).isEqualTo(businessToString);

        final byte[] toJsonToByteArray = toJson.getBytes(StandardCharsets.UTF_8);
        final byte[] businessToStringToByteArray = businessToString.getBytes(StandardCharsets.UTF_8);
        return Stream.of(toJsonToByteArray, businessToStringToByteArray);
    }


    private Stream<InputStream> testInputStream(String expectedJsonString, String actualJsonString){

        final InputStream expectedJsonToIS = IOUtils.toInputStream(expectedJsonString, StandardCharsets.UTF_8);
        final InputStream actualJsonToIS = IOUtils.toInputStream(actualJsonString, StandardCharsets.UTF_8);

        try(expectedJsonToIS; actualJsonToIS){

            expectedJsonToIS.close();

            actualJsonToIS.close();

            return Stream.of(expectedJsonToIS, actualJsonToIS);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String replaceInvalidJsonCharacters(String jsonString) {
        String validJson = "[^a-zA-Z0-9,{}:\"\n]";
        return jsonString.replaceAll(validJson, "");

    }

}
