package org.example.yelp.fusion.client;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
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
import org.example.lowlevel.restclient.PrintUtils;
import org.example.yelp.fusion.client.exception.YelpRequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Elasticsearch {
    private static final Logger logger = LoggerFactory.getLogger(Elasticsearch.class);

    static {
        setOfIds = new HashSet<>();
        timestamp =  "2022-11-10T07:16:35.187452598Z";
        if (logger.isInfoEnabled()) {
            logger.warn("logger.isInfoEnabled ");
        }
        if (logger.isErrorEnabled()) {
            logger.warn("logger.isErrorEnabled");
        }
        if (logger.isDebugEnabled()) {
            logger.warn("logger.isDebugEnabled ");
        }
        if (logger.isWarnEnabled()) {
            logger.warn("logger.isWarnEnabled");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("logger.isTraceEnabled");
        }
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


    public ElasticsearchClient client(){
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

    public String getTimestamp(String range, SortOrder sortOrder) {
        String field = "timestamp";
        Query byTimestamp = RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(range))
        )._toQuery();

        SourceFilter sourceFilter = SourceFilter.of(source -> source
                .includes(field));

        SortOptions sortOptions = SortOptions.of(options -> options
                .field(f -> f
                        .field(field)
                        .order(sortOrder))
        );

        try {
            return esClient.search(s -> s
                                    .index("yelp-businesses-restaurants-nyc")
                                    .query(q -> q
                                            .bool(b -> b
                                                    .must(byTimestamp)
                                            )
                                    ).source(src -> src
                                            .filter(sourceFilter)

                                    ).sort(sortOptions)
                                    .size(1)

                            , ObjectNode.class
                    )
                    .hits()
                    .hits()
                    .stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(ObjectNode::fields)
                    .map(Iterator::next)
                    .map(Map.Entry::getValue)
                    .map(JsonNode::asText)
                    .limit(1)
                    .collect(Collectors.joining());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Map<String, List<String>> getCategoriesMap(String index, int size, String range) {

        Map<String, List<String>> map = new LinkedHashMap<>();

        Query byTimestamp = RangeQuery.of(r -> r
                .field("timestamp")
                .gte(JsonData.of(range))
        )._toQuery();

        Query matchAllQuery = MatchAllQuery.of(r -> r
                .queryName("categories")
        )._toQuery();

        SourceFilter sourceFilter = SourceFilter.of(source -> source
                .includes("categories.alias")
                .includes("id")
                .includes("timestamp"));

        SortOptions sortOptions = SortOptions.of(options -> options
                .field(f -> f
                        .field("timestamp")
                        .order(SortOrder.Asc))
        );

        logger.info("RangeQuery field = timestamp: greater than of equal to = " + range + " number of results = " + size);
        SearchResponse<ObjectNode> response;
        try {

            response = esClient.search(s -> s
                            .index(index)
                            .query(q -> q
                                    .bool(b -> b
                                            .must(byTimestamp)
                                            .must(matchAllQuery)
                                    )
                            ).source(src -> src
                                    .filter(sourceFilter)
                            ).sort(sortOptions)
                            .size(size)
                    , ObjectNode.class
            );

            TotalHits total = response.hits().total();
            AtomicInteger count = new AtomicInteger();
            JsonNode hitsNode;
            response
                    .hits()
                    .hits()
                    .forEach(hit -> {

                        JsonNode sourceNode = hit.source();

                        for (JsonNode source : sourceNode) {
                            String id = sourceNode.get("id").asText();

                            ArrayNode arrayNode = (ArrayNode) sourceNode.get("categories");
                            if (arrayNode == null) {
                                YelpRequestLogger.logResponse(logger, response);
                            } else {
                                for (JsonNode node : arrayNode) {
                                    String alias = node.get("alias").asText();
                                    map.computeIfAbsent(alias, k ->
                                            new ArrayList<>()).add(id);
                                }
                                setOfIds.add(id);
                            }
                            timestamp = sourceNode.get("timestamp").asText();
                        }
                    });

            if (Objects.requireNonNull(total).relation() == TotalHitsRelation.Eq) {
                logger.info(PrintUtils.green("Less than max results, total hits TotalHitsRelation.eq " + total.value() + " results"));
                timestamp = null;
            }
        } catch (Exception e) {
            if(e instanceof IOException) {
                YelpRequestLogger.logFailedYelpRequest(logger, e);
            }
            if(e instanceof RuntimeException) {
                YelpRequestLogger.logFailedYelpRequest(logger, e);
                throw new RuntimeException(e);
            }
        }
        return map;
    }

    public Set<String> getSetOfBusinessIds() {
        return setOfIds;
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
