package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.JsonTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public interface SerializeToJson extends JsonTestCase {

    JsonGenerator generator();

}
