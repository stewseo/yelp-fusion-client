package io.github.stewseo.clients.json.testcases;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import jakarta.json.spi.JsonProvider;

public interface JsonTestCase {

    JsonpMapper mapper = new JacksonJsonpMapper();
}
