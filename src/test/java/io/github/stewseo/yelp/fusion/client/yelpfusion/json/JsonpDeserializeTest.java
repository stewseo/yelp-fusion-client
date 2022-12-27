package io.github.stewseo.yelp.fusion.client.yelpfusion.json;

import com.brein.api.Breinify;
import com.brein.domain.BreinConfig;
import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;
import com.brein.engine.BreinEngineType;
import io.github.stewseo.temporaldata.TemporalDataService;
import io.github.stewseo.yelp.fusion.client.YelpFusionTestCase;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.Category;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonpDeserializeTest extends YelpFusionTestCase {

    JacksonJsonpMapper mapper = new JacksonJsonpMapper();
    int size = 10;

    final BreinConfig config = new BreinConfig("938D-3120-64DD-413F-BB55-6573-90CE-473A", "utakxp7sm6weo5gvk7cytw==")
            .setRestEngineType(BreinEngineType.UNIREST_ENGINE);

    @Test
    public void testDeserialize() {

        Breinify.setConfig(config);

        try(Stream<Business> businesses = generateBusinessInstances(size)) {
            businesses.forEach(this::testDeserialize);
        }
    }
    JsonpDeserializer<Business> businessDeserializer = Business._DESERIALIZER;


    // Business toString format:
    //      List<String> list = List.of(
    //                "{\"location\":{\"city\":\"Carmel\",\"country\":\"USA\",\"state\":\"CA\"},\"coordinates\":{\"latitude\":0.0,\"longitude\":0.0},\"categories\":[{\"alias\":\"burgers\"}]}",
    //                "{\"location\":{\"city\":\"Los Angeles\",\"country\":\"USA\",\"state\":\"CA\"},\"coordinates\":{\"latitude\":34.0522342,\"longitude\":34.0522342},\"categories\":[{\"alias\":\"burgers\"}]}",
    //                "{\"location\":{\"city\":\"Oakland\",\"country\":\"USA\",\"state\":\"CA\"},\"coordinates\":{\"latitude\":37.8043722,\"longitude\":37.8043722},\"categories\":[{\"alias\":\"burgers\"}]}",
    //                "{\"location\":{\"city\":\"San Diego\",\"country\":\"USA\",\"state\":\"CA\"},\"coordinates\":{\"latitude\":32.7153292,\"longitude\":32.7153292},\"categories\":[{\"alias\":\"burgers\"}]}",
    //                "{\"location\":{\"city\":\"San Francisco\",\"country\":\"USA\",\"state\":\"CA\"},\"coordinates\":{\"latitude\":37.7749295,\"longitude\":37.7749295},\"categories\":[{\"alias\":\"burgers\"}]}"
    //        );
    private void testDeserialize(Business business) {

        String expectedBusinessString = business.toString();

        InputStream is = IOUtils.toInputStream(expectedBusinessString, StandardCharsets.UTF_8);

        // Creates a JSON parser from the specified byte stream. The character encoding of the stream is determined as defined in RFC 7159  .
        //Params: i/o stream from which JSON is to be read
        //Returns: a JSON parser
        JsonParser parser = mapper.jsonProvider().createParser(is);

        // Deserialize a value. The value starts at the next state in the JSON stream.
        String deserializedBusinessString = businessDeserializer.deserialize(parser, mapper).toString();

        assertThat(deserializedBusinessString).isEqualTo(expectedBusinessString);

    }
}
