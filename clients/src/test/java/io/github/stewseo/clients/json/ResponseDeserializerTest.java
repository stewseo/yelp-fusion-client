package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;


public class ResponseDeserializerTest extends ModelJsonTestCase {

    final String json = "" +
            "{" +
                "\"id\": \"id\", " +
                "\"alias\": \"alias\" " +
            "}";


    @SuppressWarnings("unchecked")
    @JsonTest
    void responseDeserializerTest() {

        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ?> jsonEndpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ?>) BusinessDetailsRequest._ENDPOINT;

        JsonpDeserializer<BusinessDetailsResponse> responseParser = jsonEndpoint.responseDeserializer();

        InputStream content = IOUtils.toInputStream(json, StandardCharsets.UTF_8);

        JsonParser parser = mapper.jsonProvider().createParser(content);

        BusinessDetailsResponse response = responseParser.deserialize(parser, mapper);

        BusinessDetails business = response.result();

        assertThat(business.id()).isEqualTo(ID);

        assertThat(business.alias()).isEqualTo(ALIAS);

    }
}
