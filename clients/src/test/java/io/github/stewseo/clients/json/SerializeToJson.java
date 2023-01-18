package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.TestJsonp;
import jakarta.json.stream.JsonGenerator;


public interface SerializeToJson extends TestJsonp {

    JsonGenerator generator();

}
