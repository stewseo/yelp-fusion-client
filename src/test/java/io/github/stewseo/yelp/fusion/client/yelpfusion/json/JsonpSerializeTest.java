package io.github.stewseo.yelp.fusion.client.yelpfusion.json;

import com.fasterxml.jackson.core.JacksonException;
import com.google.gson.Gson;
import io.github.stewseo.temporaldata.TemporalDataService;
import io.github.stewseo.yelp.fusion.client.YelpFusionTestCase;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonpSerializeTest extends YelpFusionTestCase {

    JacksonJsonpMapper mapper = new JacksonJsonpMapper();
    int size = 100;

    @Test
    public void jsonpSerializeTest() throws IOException {

        JsonpDeserializer<Business> businessJsonpDeserializer = Business._DESERIALIZER;

        TemporalDataService service = new TemporalDataService();

        List<String> list = List.of("San Francisco", "Oakland", "Carmel", "Napa", "Los Angeles", "San Diego");

        int max = list.size();
        String state = "CA";
        String country = "USA";


        try(Stream<Business> businesses = generateBusinessInstances(size)) {
            businesses.forEach(this::testSerializeToJSON);
        }

    }

    private void testSerializeToJSON(Business business) {

        String businessToString = business.toString();

        boolean isValid = testValidJsonString(businessToString);

        // assert that business to String is valid JSON
        assertTrue(isValid);

        // assert that expected data as input stream
        // is equal to actual data as input stream
        testByteArray(business);
    }

    public boolean testValidJsonString(String json) {

        try {
            mapper.objectMapper().readTree(json);

        } catch (JacksonException e) {
            return false;
        }
        return true;
    }

    private void testByteArray(Business business) {

        final InputStream businessToStringToIS = IOUtils.toInputStream(business.toString(), StandardCharsets.UTF_8);

        final InputStream toJsonToIS = IOUtils.toInputStream(new Gson().toJson(business), StandardCharsets.UTF_8);

        try(businessToStringToIS; toJsonToIS){

            assertThat(toJsonToIS).isNotEmpty();

            byte[] expected = toJsonToIS.readAllBytes();

            byte[] actual = businessToStringToIS.readAllBytes();

            assertThat(expected.length).isEqualTo(actual.length);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


