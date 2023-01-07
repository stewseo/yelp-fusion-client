package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.SerializeToJson;

public interface ResponseTestCase<ResponseT> extends SerializeToJson, DeserializeFromJson {
    ResponseT of();
}
