package io.github.stewseo.clients.yelpfusion._types.test_constants;

public class ErrorMessages {

    public static final String EXECUTION_EXCEPTION_ERROR_MESSAGE = "{\"error\": {\"code\": \"NOT_FOUND\", \"description\": \"Resource could not be found.\"}}";
    public static final String FQ_RESPONSE_EXCEPTION_CLASS_NAME = "io.github.stewseo.lowlevel.restclient.ResponseException";

    public static final String LOCATION_MISSING_ERROR = "{\"error\": {\"code\": \"LOCATION_MISSING\", \"description\": \"You must specify either a location or a latitude and longitude to search.\"}}";
    public static final String VALIDATION_SPECIFY_LOCATION_ERROR = "{\"error\": {\"code\": \"VALIDATION_ERROR\", \"description\": \"Please specify a location or a latitude and longitude\"}}";

    public static final String RESOURCE_COULD_NOT_BE_FOUND = "{\"error\": {\"code\": \"NOT_FOUND\", \"description\": \"Resource could not be found.\"}}";
    public static final String SPECIFY_LOCATION_ERROR = "status line [HTTP/1.1 400 Bad Request]\n" +
            "{\"error\": {\"code\": \"VALIDATION_ERROR\", \"description\": \"Please specify a location or a latitude and longitude\"}}";

    public static final String LOCATION_NOT_FOUND = "{\"error\": {\"code\": \"LOCATION_NOT_FOUND\", \"description\": \"Could not execute searchBusinesses, try specifying a more exact location.\"}}";

    public static final String NOT_OF_TYPE_INTEGER = "{\"error\": {\"code\": \"VALIDATION_ERROR\", \"description\": \"'$' is not of type 'integer'\", \"field\": \"[0]\", \"instance\": \"$\"}}";

    public static final String LOCATION_MISSING = "{\"error\": {\"code\": \"LOCATION_MISSING\", \"description\": \"You must specify either a location or a latitude and longitude to searchBusinesses.\"}}";
    public static final String BUSINESS_NOT_FOUND = "404 Not Found]\n" +
            "{\"error\": {\"code\": \"BUSINESS_NOT_FOUND\", \"description\": \"The requested business could not be found.\"}}";


    public static final String VALIDATION_ERROR_DOES_NOT_MATCH = "status line [HTTP/1.1 400 Bad Request]\n" +
            "{\"error\": {\"code\": \"VALIDATION_ERROR\", \"description\": \"'en_U' does not match '^[a-z]{2,3}_[A-Z]{2}$'\", \"field\": \"locale\", \"instance\": \"en_U\"}}";

    public static final String HOST_NAME = "host [https://api.yelp.com:443]";
}
