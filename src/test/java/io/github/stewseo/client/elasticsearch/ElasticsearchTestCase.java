package io.github.stewseo.client.elasticsearch;

import org.junit.jupiter.api.BeforeAll;

public abstract class ElasticsearchTestCase {
    
    public static ElasticsearchService elasticsearchService;

    public String index = "yelp-fusion-businesses-restaurants-nyc";


    @BeforeAll
    static void beforeAll() {
        
        elasticsearchService = ElasticsearchConnection.createElasticsearchService();
    
    }
}
