package io.github.stewseo.yelpfusion._types.test_constants;

public class ErrorCodes {

    public static final String FQ_RESPONSE_EXCEPTION_CLASSNAME = "io.github.stewseo.lowlevel.restclient.ResponseException";

    public static final String SPECIFY_LOCATION_ERROR = "status line [HTTP/1.1 400 Bad Request]\n" +
            "{\"error\": {\"code\": \"VALIDATION_ERROR\", \"description\": \"Please specify a location or a latitude and longitude\"}}";

    public static final String LOCATION_NOT_FOUND = "status line [HTTP/1.1 400 Bad Request]\n" +
            "{\"error\": {\"code\": \"LOCATION_NOT_FOUND\", \"description\": \"Could not execute search, try specifying a more exact location.\"}}";

    public static final String LOCATION_MISSING = "status line [HTTP/1.1 400 Bad Request]\n" +
            "{\"error\": {\"code\": \"LOCATION_MISSING\", \"description\": \"You must specify either a location or a latitude and longitude to search.\"}}";

    public static final String BUSINESS_NOT_FOUND = "404 Not Found]\n" +
            "{\"error\": {\"code\": \"BUSINESS_NOT_FOUND\", \"description\": \"The requested business could not be found.\"}}";


    public static final String VALIDATION_ERROR_DOES_NOT_MATCH = "status line [HTTP/1.1 400 Bad Request]\n" +
            "{\"error\": {\"code\": \"VALIDATION_ERROR\", \"description\": \"'en_U' does not match '^[a-z]{2,3}_[A-Z]{2}$'\", \"field\": \"locale\", \"instance\": \"en_U\"}}";

    public static final String HOST_NAME = "host [https://api.yelp.com:443]";
}
