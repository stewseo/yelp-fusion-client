package io.github.stewseo.yelp.fusion.client.json;

import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParsingException;

import java.util.EnumSet;

public class UnexpectedJsonEventException extends JsonParsingException {
    public UnexpectedJsonEventException(JsonParser parser, JsonParser.Event event) {
        super("Unexpected JSON event '" + event + "'", parser.getLocation());
    }

    public UnexpectedJsonEventException(JsonParser parser, JsonParser.Event event, JsonParser.Event expected) {
        super("Unexpected JSON event '" + event + "' instead of '" + expected + "'", parser.getLocation());
    }

    public UnexpectedJsonEventException(JsonParser parser, JsonParser.Event event, EnumSet<JsonParser.Event> expected) {
        super("Unexpected JSON event '" + event + "' instead of '" + expected + "'", parser.getLocation());
    }
}