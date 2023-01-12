package io.github.stewseo.clients.json;


import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.SimpleJsonpMapper;
import jakarta.json.stream.JsonParser;

import java.io.InputStream;
import java.io.Reader;

public interface WithJson<T> {

    default T withJson(InputStream input) {
        JsonpMapper mapper = SimpleJsonpMapper.INSTANCE_REJECT_UNKNOWN_FIELDS;
        return withJson(mapper.jsonProvider().createParser(input), mapper);
    }


    default T withJson(Reader input) {
        JsonpMapper mapper = SimpleJsonpMapper.INSTANCE_REJECT_UNKNOWN_FIELDS;
        return withJson(mapper.jsonProvider().createParser(input), mapper);
    }

    T withJson(JsonParser parser, JsonpMapper mapper);
}
