package io.github.stewseo.yelp.fusion.client;

import com.brein.api.Breinify;
import com.brein.domain.BreinConfig;
import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;
import com.brein.engine.BreinEngineType;
import io.github.stewseo.temporaldata.TemporalDataService;
import io.github.stewseo.yelp.fusion.client.connection.ElasticsearchConnection;
import io.github.stewseo.yelp.fusion.client.elasticsearch.ElasticsearchService;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Coordinates;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Coordinates_;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Location;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.Category;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class YelpFusionTestCase {

    private final static Logger logger = LoggerFactory.getLogger(YelpFusionTestCase.class);

    public static ElasticsearchService elasticsearchService;

    public String index = "yelp-fusion-businesses-restaurants-nyc";

    @BeforeAll
    static void beforeAll() {
        elasticsearchService = ElasticsearchConnection.createElasticsearchService();
    }

    final BreinConfig config = new BreinConfig("938D-3120-64DD-413F-BB55-6573-90CE-473A", "utakxp7sm6weo5gvk7cytw==")
            .setRestEngineType(BreinEngineType.UNIREST_ENGINE);

    List<String> list = List.of("San Francisco", "Oakland", "Carmel", "Napa", "Los Angeles", "San Diego");

    int numCities = list.size();

    public Stream<Business> generateBusinessInstances(int size) {

        Breinify.setConfig(config);

        JsonpDeserializer<Business> businessJsonpDeserializer = Business._DESERIALIZER;

        List<Business> businesses = new ArrayList<>();

        return IntStream.range(0, size).mapToObj(this::generateBusiness);

    }

    TemporalDataService service = new TemporalDataService();
    String state = "CA";
    String country = "USA";
    public Business generateBusiness(int i) {

        int index = ThreadLocalRandom.current().nextInt(0, numCities);

        String city = list.get(index);

        BreinLocationResult breinLocationResult = breinLocationResult(city);

        double longitude = breinLocationResult.getLat();

        double latitude = breinLocationResult.getLat();

        Category category = Category.of(cat -> cat.alias("burgers"));

        Location location = Location.of(l -> l
                .city(city)
                .state(state)
                .country(country)
        );

        Coordinates coordinates = Coordinates.of(c -> c
                .latitude(latitude)
                .longitude(longitude)
        );
        String id = "id-"+i;
        String phoneNumber = "000000000" + String.valueOf(i);

        return Business.of(e -> e
                .coordinates(coordinates)
                .location(location)
                .categories(category)
                .id(id)
                .phone(phoneNumber)
        );
    }

    private BreinLocationResult breinLocationResult(String city) {

        return service.temporalData(city, state, country).getLocation();
    }
}
