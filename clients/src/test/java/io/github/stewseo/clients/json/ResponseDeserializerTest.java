package io.github.stewseo.clients.json;

import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion._types.Business;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResponseDeserializerTest extends YelpFusionTestCase {

    final String json = "{\"id\": \"wu3w6IlUct9OvYmYXDMGJA\", " +
            "\"alias\": \"huitlacoche-taqueria-restaurant-ridgewood-2\", " +
            "\"name\": \"Huitlacoche Taqueria Restaurant\", " +
            "\"image_url\": \"https://s3-media3.fl.yelpcdn.com/bphoto/xi-xz-sPIEjQ5xCX4fPZ_Q/o.jpg\", " +
            "\"is_claimed\": true, " +
            "\"is_closed\": false, " +
            "\"url\": \"https://www.yelp.com/biz/huitlacoche-taqueria-restaurant-ridgewood-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw\", " +
            "\"phone\": \"+13479873581\", " +
            "\"display_phone\": \"(347) 987-3581\", " +
            "\"review_count\": 7, " +
            "\"all\": [{\"alias\": \"mexican\", \"title\": \"Mexican\"}], " +
            "\"rating\": 5.0," +
            " \"location\": {\"address1\": \"778 Seneca Ave\", \"address2\": null, \"address3\": null, \"city\": \"Ridgewood\", \"zip_code\": \"11385\", \"country\": \"US\", \"state\": \"NY\", \"display_address\": [\"778 Seneca Ave\", \"Ridgewood, NY 11385\"], \"cross_streets\": \"\"}, " +
            "\"coordinates\": {\"latitude\": 40.701692, \"longitude\": -73.906044}, " +
            "\"photos\": [\"https://s3-media3.fl.yelpcdn.com/bphoto/xi-xz-sPIEjQ5xCX4fPZ_Q/o.jpg\", \"https://s3-media3.fl.yelpcdn.com/bphoto/AZgTbL-Wd5YokNEzNpDUmQ/o.jpg\", \"https://s3-media3.fl.yelpcdn.com/bphoto/OySDFZAsznDVTPBt8Xe6ug/o.jpg\"], \"hours\": [{\"open\": [{\"is_overnight\": false, \"start\": \"1100\", \"end\": \"2230\", \"day\": 0}, {\"is_overnight\": false, \"start\": \"1100\", \"end\": \"2230\", \"day\": 1}, {\"is_overnight\": false, \"start\": \"1100\", \"end\": \"2230\", \"day\": 2}, {\"is_overnight\": false, \"start\": \"1100\", \"end\": \"2230\", \"day\": 3}, {\"is_overnight\": false, \"start\": \"1100\", \"end\": \"2330\", \"day\": 4}, {\"is_overnight\": false, \"start\": \"1100\", \"end\": \"2330\", \"day\": 5}, {\"is_overnight\": false, \"start\": \"1100\", \"end\": \"2230\", \"day\": 6}], \"hours_type\": \"REGULAR\", \"is_open_now\": true}], \"transactions\": [\"pickup\", \"delivery\"]}";

    @SuppressWarnings("unchecked")
    @Test
    void responseDeserializerTest() {

        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ?> jsonEndpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ?>) BusinessDetailsRequest._ENDPOINT;

        JsonpDeserializer<BusinessDetailsResponse> responseParser = jsonEndpoint.responseDeserializer();

        JacksonJsonpMapper mapper = new JacksonJsonpMapper();

        InputStream content = IOUtils.toInputStream(json, StandardCharsets.UTF_8);

        JsonParser parser = mapper.jsonProvider().createParser(content);

        BusinessDetailsResponse response = responseParser.deserialize(parser, mapper);

        Business business = response.result();

        assertThat(business.id()).isEqualTo("wu3w6IlUct9OvYmYXDMGJA");
        assertThat(business.alias()).isEqualTo("huitlacoche-taqueria-restaurant-ridgewood-2");

    }
}
