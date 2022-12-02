package io.github.stewseo.yelp.fusion.client;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceFilter;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Elasticsearch {
    private static final Logger logger = LoggerFactory.getLogger(Elasticsearch.class);

    static {
        setOfIds = new HashSet<>();
        timestamp = "2022-11-10T07:16:35.187452598Z";
    }

    private static volatile Elasticsearch instance;
    private static ElasticsearchClient esClient;

    private static ElasticsearchAsyncClient esAsyncClient;

    private static final Object mutex = new Object();

    public static Elasticsearch getInstance() {
        Elasticsearch result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new Elasticsearch();
            }
        }
        return result;
    }


    public ElasticsearchClient client() {
        return esClient;
    }


    public ElasticsearchAsyncClient asyncClient() {
        return esAsyncClient;
    }


    public void createESAsyncClient(ElasticsearchTransport transport) {
        esAsyncClient = new ElasticsearchAsyncClient(transport);
    }

    public void createESClient(ElasticsearchTransport transport) {
        esClient = new ElasticsearchClient(transport);
    }

    public ElasticsearchTransport createTransport(RestClient restClient, JacksonJsonpMapper jacksonJsonpMapper) {
        return new RestClientTransport(restClient, jacksonJsonpMapper);
    }

    public RestClient createRestClient(String host, int port, String scheme, String apiKeyId, String apiKeySecret) {
        String apiKeyIdAndSecret = System.getenv("API_KEY_ID") + ":" + System.getenv("API_KEY_SECRET");

        String encodedApiKey = Base64.getEncoder() // The encoder maps the input to a set of characters in the A-Za-z0-9+/ character set
                .encodeToString((apiKeyIdAndSecret) // Encodes the specified byte array into a String using the Base64 encoding scheme.
                        .getBytes(StandardCharsets.UTF_8));

        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, scheme));

        Header[] defaultHeaders = {new BasicHeader("Authorization", "ApiKey " + encodedApiKey)};

        builder.setDefaultHeaders(defaultHeaders);

        return builder.build();
    }

    private static final Set<String> setOfIds;
    static String timestamp;

    public String getTimestamp(String timestamp, SortOrder sortOrder) {
        String field = "timestamp";
        Query byTimestamp = RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(timestamp))
        )._toQuery();

        SourceFilter sourceFilter = SourceFilter.of(source -> source
                .includes(field));

        SortOptions sortOptions = SortOptions.of(options -> options
                .field(f -> f
                        .field(field)
                        .order(sortOrder))
        );

        String returnTimestamp = "";
        try {
            List<Hit<ObjectNode>> list = esClient.search(s -> s
                                    .index("yelp-businesses-restaurants-nyc")
                                    .query(q -> q
                                            .bool(b -> b
                                                    .must(byTimestamp) // greater than or equal to range parameter
                                            )
                                    ).source(src -> src
                                            .filter(sourceFilter)

                                    ).sort(sortOptions)
                                    .size(1)

                            , ObjectNode.class
                    )
                    .hits()
                    .hits();

            for (Hit<ObjectNode> hit : list) {
                JsonNode node = Objects.requireNonNull(hit.source()).get("timestamp");
                if (node != null) {
                    returnTimestamp = node.asText();
                }
            }

            return returnTimestamp;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTimestamp() {
        return timestamp;
    }

//    public List<StringTermsBucket> getBusinessIds(String index, int size, String range) {
//
//    }

    public List<StringTermsBucket> getStringTermsBuckets() {


        // Dynamically build each unique bucket by field: categories alias
        TermsAggregation termsAggregation = TermsAggregation.of(t -> t
                .field("categories.alias.keyword")
                .size(350)
        );
        // match all documents containing the queryName: "categories"
        Query matchAll = MatchAllQuery.of(m -> m
                .queryName("location")
        )._toQuery();


        SearchResponse<Void> response = null;
        try {
            response = esClient.search(b -> b
                            .index("yelp-businesses-restaurants-nyc")
                            .size(0) // Set the number of matching documents to zero
                            .query(matchAll) // Set the query that will filter the businesses on which to run the aggregation (all contain categories)
                            .aggregations("categories-aggs", a -> a // Create an aggregation named "categories-aggs"
                                    .terms(termsAggregation) // Select the terms aggregation variant.
                            ),
                    Void.class // Using Void will ignore any document in the response.
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<StringTermsBucket> buckets = response.aggregations()
                .get("categories-aggs")
                .sterms()
                .buckets().array();

        return buckets;
    }


//        Map<String, List<String>> map = new LinkedHashMap<>();
//

//        logger.info("RangeQuery field = timestamp: greater than of equal to = " + range + " number of results = " + size);



//
//        } catch (Exception e) {
//
//            if(e instanceof RuntimeException) {
//                throw new RuntimeException(e);
//            }
//        }
//        return map;


    // return all business ids up to 10k, starting at specified timestamp.
    public List<Hit<ObjectNode>> getSetOfBusinessIds(String timestampRange) {

        Query byTimestamp = RangeQuery.of(r -> r
                .field("timestamp")
                .gte(JsonData.of(timestampRange))
        )._toQuery();

//        Query matchAllQuery = MatchAllQuery.of(r -> r
//                .queryName("id")
//        )._toQuery();

        SourceFilter sourceFilter = SourceFilter.of(source -> source
                .includes("id")
                .includes("timestamp"));

        SortOptions sortOptions = SortOptions.of(options -> options
                .field(f -> f
                        .field("timestamp")
                        .order(SortOrder.Asc))
        );

        SearchResponse<ObjectNode> response;

        List<Hit<ObjectNode>> nodes;
        try {

            response = esClient.search(s -> s
                            .index("yelp-businesses-restaurants-nyc")
                            .query(q -> q
                                    .bool(b -> b
                                            .must(byTimestamp)
                                    )
                            ).source(src -> src
                                    .filter(sourceFilter)
                            )
                            .sort(sortOptions)
                            .size(10000)
                    , ObjectNode.class
            );
            TotalHits total = response.hits().total();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.hits().hits();
    }

    public int getDocsCount(String index) {
        try {
            return Integer.parseInt(Objects.requireNonNull(esClient.cat().count(c -> c
                            .index(index)
                    ).valueBody()
                    .get(0)
                    .count()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
