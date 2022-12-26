package io.github.stewseo.yelp.fusion.client;

import io.github.stewseo.yelp.fusion.client.connection.ElasticsearchConnection;
import io.github.stewseo.yelp.fusion.client.elasticsearch.ElasticsearchService;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class YelpFusionTestCase {

    private final static Logger logger = LoggerFactory.getLogger(YelpFusionTestCase.class);

    public static ElasticsearchService elasticsearchService;

    public String index = "yelp-fusion-businesses-restaurants-nyc";

    @BeforeAll
    static void beforeAll() {
        elasticsearchService = ElasticsearchConnection.createElasticsearchService();
    }
}
