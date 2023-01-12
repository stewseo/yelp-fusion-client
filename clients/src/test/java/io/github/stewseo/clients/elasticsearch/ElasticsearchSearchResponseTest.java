package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.json.jackson.JacksonJsonpParser;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

public class ElasticsearchSearchResponseTest {

    @Test
    public void testESSearchResponse() {
        SearchResponse<Object> esS
                = SearchResponse.of(s -> s.maxScore(1.0).took(1L).timedOut(false));


        JsonParser parser = new JacksonJsonpMapper().jsonProvider().createParser(new StringReader(esS.toString()));

        co.elastic.clients.elasticsearch.core.SearchResponse._DESERIALIZER.deserialize(parser, new JacksonJsonpMapper());

    }
}
