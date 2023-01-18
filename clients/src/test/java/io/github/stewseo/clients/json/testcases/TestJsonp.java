package io.github.stewseo.clients.json.testcases;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;

public interface TestJsonp {

    JsonpMapper mapper = new JacksonJsonpMapper();

}
