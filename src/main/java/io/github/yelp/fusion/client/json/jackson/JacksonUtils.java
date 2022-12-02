package io.github.yelp.fusion.client.json.jackson;


import com.fasterxml.jackson.core.JsonParseException;
import jakarta.json.JsonException;
import jakarta.json.stream.JsonGenerationException;
import jakarta.json.stream.JsonParsingException;

import java.io.IOException;

public class JacksonUtils {
    public static JsonException convertException(IOException ioe) {
        if (ioe instanceof com.fasterxml.jackson.core.JsonGenerationException) {
            return new JsonGenerationException(ioe.getMessage(), ioe);

        } else if (ioe instanceof JsonParseException) {
            JsonParseException jpe = (JsonParseException) ioe;
            return new JsonParsingException(ioe.getMessage(), jpe, new JacksonJsonpLocation(jpe.getLocation()));

        } else {
            return new JsonException("Jackson exception", ioe);
        }
    }
}