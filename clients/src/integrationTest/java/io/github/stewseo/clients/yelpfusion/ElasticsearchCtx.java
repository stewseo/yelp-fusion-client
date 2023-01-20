package io.github.stewseo.clients.yelpfusion;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import io.github.stewseo.clients.elasticsearch.ElasticsearchConnection;
import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.yelpfusion._types.Result;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ElasticsearchCtx {


    private static volatile ElasticsearchCtx instance;

    private static final Object mutex = new Object();

    // Create the private constructor to avoid any new object creation with new operator.
    private ElasticsearchCtx() {
    }

    public static ElasticsearchCtx getInstance() {
        ElasticsearchCtx result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new ElasticsearchCtx();
            }
        }
        return result;
    }

    public static ElasticsearchAsyncClient getElasticsearchAsyncClient() {
        String host = "1ff0acb6626441789a7e846726159410.us-east-2.aws.elastic-cloud.com";
        int port = 443;
        String scheme = "https";
        String apiKey = System.getenv("API_KEY_ID");
        String apiKeySecret = System.getenv("API_KEY_SECRET");

        return ElasticsearchConnection.createElasticsearchAsyncClient();
    }

    private static final String TIMESTAMP_PIPELINE = "timestamp-pipeline";

    public static void indexRequest(String index, SearchBusinessesResult searchBusinessResult) {

        String businessId = searchBusinessResult
                .id().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");

        IndexRequest<JsonData> request = IndexRequest.of(i -> i
                .index(index)
                .id(businessId)
                .pipeline(TIMESTAMP_PIPELINE)
                .document(JsonData.of(searchBusinessResult.toString()))
        );

        IndexResponse response = null;
        try {
            response = getElasticsearchAsyncClient().index(request).get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        assertThat(response.result()).isNotEqualTo(Result.NotFound);

    }


}
