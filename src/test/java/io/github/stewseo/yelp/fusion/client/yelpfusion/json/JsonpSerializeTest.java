package io.github.stewseo.yelp.fusion.client.yelpfusion.json;

import com.fasterxml.jackson.core.JacksonException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.temporaldata.TemporalDataService;
import io.github.stewseo.yelp.fusion.client.YelpFusionTestCase;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonpSerializeTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(JsonpSerializeTest.class);
    JacksonJsonpMapper mapper = new JacksonJsonpMapper();
    int size = 10;

    @Test
    public void jsonpSerializeTest() throws IOException {

        logger.info("testing {} instances of Business", size);

        JsonpDeserializer<Business> businessJsonpDeserializer = Business._DESERIALIZER;

        TemporalDataService service = new TemporalDataService();

        List<String> list = List.of("San Francisco", "Oakland", "Carmel", "Napa", "Los Angeles", "San Diego");

        int max = list.size();
        String state = "CA";
        String country = "USA";

        try(Stream<Business> businesses = generateBusinessInstances(size)) {
            businesses.forEach(this::testSerializeToJSON);
        }
        logger.info("jsonpSerializeTest complete.");

    }

    /**
     * @param business Business instance
     *  @link <a href="https://www.baeldung.com/java-validate-json-string">...</a>
     *
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
            mapper.objectMapper().readTree(json);

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

        if(!toJson.equals(businessToString)) {
            toJson = toJson.replaceAll("[^a-zA-Z0-9,{}:\"]", "");
            businessToString = businessToString.toString().replaceAll("[^a-zA-Z0-9,{}:\"]", "");
        }
        assertThat(toJson).isEqualTo(businessToString);

        final byte[] toJsonToByteArray = toJson.getBytes(StandardCharsets.UTF_8);
        final byte[] businessToStringToByteArray = businessToString.getBytes(StandardCharsets.UTF_8);
        assertThat(businessToStringToByteArray).isEqualTo(toJsonToByteArray);
    }

    private void testInputStream(String expected, String actual){

        final InputStream toJsonToIS = IOUtils.toInputStream(expected, StandardCharsets.UTF_8);
        final InputStream businessToStringToIS = IOUtils.toInputStream(actual, StandardCharsets.UTF_8);

        try(businessToStringToIS; toJsonToIS){
            assertThat(toJsonToIS).isNotEmpty();
            assertThat(businessToStringToIS).isNotEmpty();

            businessToStringToIS.close();
            toJsonToIS.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


