package org.example.yelp.fusion.client;

import co.elastic.clients.elasticsearch.*;
import co.elastic.clients.elasticsearch._types.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.reindex.*;
import co.elastic.clients.elasticsearch.core.search.*;
import co.elastic.clients.json.*;
import co.elastic.clients.json.jackson.*;
import co.elastic.clients.transport.*;
import co.elastic.clients.transport.rest_client.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.apache.commons.logging.*;
import org.apache.http.*;
import org.apache.http.message.*;
import org.elasticsearch.client.*;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.example.lowlevel.restclient.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Elasticsearch {
    private static final Log logger = LogFactory.getLog(Elasticsearch.class);
    static {
        setOfIds = new HashSet<>();
        if (logger.isInfoEnabled()) {
            logger.warn("logger.isInfoEnabled ");
        }
        if (logger.isFatalEnabled()) {
            logger.warn("logger.isFatalEnabled");
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
    public void createESClient(ElasticsearchTransport transport) {
        esClient = new ElasticsearchClient(transport);
    }

    public ElasticsearchTransport createTransport(RestClient restClient, JacksonJsonpMapper jacksonJsonpMapper) {
        return new RestClientTransport(restClient, jacksonJsonpMapper);
    }
    public RestClient createRestClient(String host, int port, String scheme, String apiKeyId, String apiKeySecret) {
        String apiKeyIdAndSecret = System.getenv("API_KEY_ID") + ":" + System.getenv("API_KEY_SECRET");

        String encodedApiKey = java.util.Base64.getEncoder() // The encoder maps the input to a set of characters in the A-Za-z0-9+/ character set
                .encodeToString((apiKeyIdAndSecret) // Encodes the specified byte array into a String using the Base64 encoding scheme.
                        .getBytes(StandardCharsets.UTF_8));

        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, scheme));

        Header[] defaultHeaders = {new BasicHeader("Authorization", "ApiKey " + encodedApiKey)};

        builder.setDefaultHeaders(defaultHeaders);

        return builder.build();
    }

    private static final Set<String> setOfIds;
    static String timestamp;
    public String getTimestamp(){
        if(timestamp == null) {
            timestamp =  "2022-11-10T07:16:35.187452598Z";
        }
        logger.info(PrintUtils.green("returning timestamp = " + timestamp));
        return timestamp;
    }

    public Map<String, List<String>> getCategoriesMap(String index, int size, String range) {
        Map<String, List<String>> categoriesMap = new HashMap<>();

        Query byTimestamp = RangeQuery.of(r -> r
                .field("timestamp")
                .gte(JsonData.of(range))
        )._toQuery();

        logger.info("RangeQuery field = timestamp: greater than of equal to = " + range + " number of results = " + size);
        
        try {
            co.elastic.clients.elasticsearch.core.SearchResponse<ObjectNode> response = esClient.search(s -> s
                            .index(index)
                            .query(q -> q
                                    .bool(b -> b
                                            .must(m -> m
                                                            .range(t -> t
                                                                    .field("timestamp")
                                                                    .gte(JsonData.of(range)
                                                                    )
                                                            )
                                            ).must(m -> m
                                                    .matchAll(match -> match
                                                            .queryName("categories"))) // for now, not necessary
                                    )
                            ).source(src -> src
                                    .filter(f -> f
                                            .includes("categories.alias")
                                            .includes("id")
                                            .includes("timestamp")
                                    )
                            ).sort(sort -> sort
                                    .field(field -> field
                                            .field("timestamp")
                                            .order(SortOrder.Asc)
                                    )
                            ).size(size)
                    , ObjectNode.class
            );

            TotalHits total = response.hits().total();

            if (Objects.requireNonNull(total).relation() == TotalHitsRelation.Eq) {
                PrintUtils.green("There are " + total.value() + " results");
            }

            response
                    .hits()
                    .hits()
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(hit -> {
                        ObjectNode hitSource = hit.source();
                        if(hitSource != null) {

                            JsonNode idNode = hitSource.get("id");
                            if (idNode != null) {
                                setOfIds.add(idNode.asText());

                                JsonNode categoriesNode = hitSource.get("categories");

                                if (categoriesNode.isArray()) {
                                    ArrayNode arrayNode = (ArrayNode)categoriesNode;

                                    for (JsonNode jsonNode : categoriesNode) {
                                        String alias = jsonNode.get("alias").asText();
                                        categoriesMap.computeIfAbsent(alias,
                                                k -> new ArrayList<>()).add(idNode.asText());
                                    }
                                }
                            }
                            JsonNode node = hitSource.get("timestamp");
                            timestamp = node.asText();
                        }
                    });
            PrintUtils.red("timestamp: " + timestamp);
        } catch (Exception e) {
            if(e instanceof IOException) {
                RequestLogger.logFailedRequest(logger, e);
            }
            if(e instanceof RuntimeException) {
                throw new RuntimeException(e);
            }
        }
        return categoriesMap;
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

    public void getAllBusinessIds() {

    }
}
