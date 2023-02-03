package io.github.stewseo.clients.json;

import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParsingException;

import java.util.EnumSet;

public class UnexpectedJsonEventException extends JsonParsingException {

    private static final String UNEXPECTED_JSON_EVENT = "Unexpected JSON event '";

    public UnexpectedJsonEventException(JsonParser parser, JsonParser.Event event) {
        super(UNEXPECTED_JSON_EVENT + event + "'", parser.getLocation());
    }

    public UnexpectedJsonEventException(JsonParser parser, JsonParser.Event event, JsonParser.Event expected) {
        super(UNEXPECTED_JSON_EVENT + event + "' instead of '" + expected + "'", parser.getLocation());
    }

    public UnexpectedJsonEventException(JsonParser parser, JsonParser.Event event, EnumSet<JsonParser.Event> expected) {
        super(UNEXPECTED_JSON_EVENT + event + "' instead of '" + expected + "'", parser.getLocation());
    }
}