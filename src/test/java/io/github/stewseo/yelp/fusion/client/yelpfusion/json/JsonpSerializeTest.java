package io.github.stewseo.yelp.fusion.client.yelpfusion.json;

import com.brein.api.Breinify;
import com.brein.domain.BreinConfig;
import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;
import com.brein.engine.BreinEngineType;
import com.fasterxml.jackson.core.JacksonException;
import com.google.gson.Gson;
import io.github.stewseo.temporaldata.TemporalDataService;
import io.github.stewseo.yelp.fusion.client.YelpFusionTestCase;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.Category;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonpSerializeTest extends YelpFusionTestCase {

    JacksonJsonpMapper mapper = new JacksonJsonpMapper();
    int size = 50;

    final BreinConfig config = new BreinConfig("938D-3120-64DD-413F-BB55-6573-90CE-473A", "utakxp7sm6weo5gvk7cytw==")
            .setRestEngineType(BreinEngineType.UNIREST_ENGINE);

    @Test
    public void jsonpSerializeTest() throws IOException {

        Breinify.setConfig(config);

        JsonpDeserializer<Business> businessJsonpDeserializer = Business._DESERIALIZER;

        TemporalDataService service = new TemporalDataService();

        List<String> list = List.of("San Francisco", "Oakland", "Carmel", "Napa", "Los Angeles", "San Diego");

        int max = list.size();
        String state = "CA";
        String country = "USA";

        for (int i = 0; i < size; i++) {

            int index = ThreadLocalRandom.current().nextInt(0, max);

            String city = list.get(i);

            BreinTemporalDataResult breinTemporalDataResult = service.temporalData(city, state, country);

            BreinLocationResult breinLocationResult = breinTemporalDataResult.getLocation();

            double longitude = breinLocationResult.getLat();

            double latitude = breinLocationResult.getLat();

            BreinWeatherResult s = breinTemporalDataResult.getWeather();

            final String seq = String.valueOf(i);

            final LocalDateTime epochDateTime =  breinTemporalDataResult.getEpochDateTime();

            String timestamp = epochDateTime + "-" + seq;
            System.out.println(timestamp);

            Category category = Category.of(c -> c.alias("burgers"));

            Business business = Business.of(e -> e
                    .coordinates(coord -> coord
                            .latitude(latitude)
                            .longitude(longitude))
                    .location(l -> l
                            .city(city)
                            .state(state)
                            .country(country))
                    .categories(category)
            );

            testValidJsonString(business);

            testByteArray(business);
        }
    }

    private void testValidJsonString(Business business) throws IOException {

        String businessToString = business.toString();

        Boolean isValid = testValidJsonString(businessToString);

        assertThat(isValid).isTrue();
    }

    public boolean testValidJsonString(String json) {
        try {
            mapper.objectMapper().readTree(json);
        } catch (JacksonException e) {
            return false;
        }
        return true;
    }

    private void testByteArray(Business business) throws IOException {

        String toJsonUsingGson = new Gson().toJson(business);
        System.out.println(toJsonUsingGson);

        String businessToString = business.toString();
        System.out.println(businessToString);

        testByteArray(toJsonUsingGson, businessToString);

    }

    private void testByteArray(String expected, String actual) throws IOException {

        int expectedEstimateAvailableBytes, actualEstimateAvailableBytes;
        byte[] expectedByteArray, actualByteArray;

        try (InputStream expectedIs = IOUtils.toInputStream(expected)) {
            expectedEstimateAvailableBytes = expectedIs.available();
            expectedByteArray = expectedIs.readAllBytes();
        }

        try (InputStream actualIs = IOUtils.toInputStream(actual)) {
            actualEstimateAvailableBytes = actualIs.available();
            actualByteArray = actualIs.readAllBytes();
        }

        assertThat(expectedEstimateAvailableBytes).isGreaterThanOrEqualTo(1);

        assertThat(expectedEstimateAvailableBytes).isEqualTo(actualEstimateAvailableBytes);

        Integer expectedByteArrayLength = expectedByteArray.length;

        Integer actualByteArrayLength = actualByteArray.length;

        assertThat(expectedByteArrayLength).isEqualTo(actualByteArrayLength);
    }

}


