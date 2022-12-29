package io.github.stewseo.client.yelpfusion;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.yelpfusion.business.Business;
import io.github.stewseo.client.yelpfusion.business.Coordinates;
import io.github.stewseo.client.yelpfusion.business.Location;
import io.github.stewseo.client.yelpfusion.categories.Category;
import io.github.stewseo.client.yelpfusion.client.YelpFusionConnection;
import io.github.stewseo.client.yelpfusion.json.JsonTestCase;
import io.github.stewseo.client.yelpfusion.json.YelpFusionJsonTestCase;
import io.github.stewseo.temporaldata.TemporalDataService;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class YelpFusionTestCase {

    public static YelpFusionAsyncClient yelpFusionAsyncClient;

    private static JsonTestCase jsonTestCase;

    private static List<BreinTemporalDataResult> list;

    private static TemporalDataService temporalDataService;
    
    @BeforeAll
    static void beforeAll() {

        yelpFusionAsyncClient = YelpFusionConnection.createOrGetYelpFusionAsyncClient();
        
        temporalDataService = new TemporalDataService();

        jsonTestCase =  new YelpFusionJsonTestCase();
    }

    private static String state = "CA";

    private static String country = "USA";

    private int numCities;

    public Stream<Business> generateBusinessInstances(int size) {

        list = getStreamOfTemporalData().toList();

        numCities = list.size();

        return getStreamOfTemporalData().map(this::generateBusiness);

    }
    AtomicInteger ai = new AtomicInteger(0);
    public Business generateBusiness(BreinTemporalDataResult breinTemporalDataResult) {

        BreinLocationResult breinLocationResult = breinTemporalDataResult.getLocation();

        double longitude = breinLocationResult.getLat();

        double latitude = breinLocationResult.getLat();

        String city = breinLocationResult.getCity();

        String state = breinLocationResult.getState();

        String country = breinLocationResult.getCountry();

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

        String id = "id-"+ ai.get();

        String phoneNumber = "000000000" + String.valueOf(ai.getAndIncrement());

        return Business.of(e -> e
                .coordinates(coordinates)
                .location(location)
                .categories(category)
                .id(id)
                .phone(phoneNumber)
        );
    }

    public JsonTestCase getTestJson() {
        return jsonTestCase;
    }

    public Stream<BreinTemporalDataResult> getStreamOfTemporalData() {

        final Stream<String> citiesInCa =
                Stream.of("San Francisco", "Oakland", "San Jose", "Monterey", "Napa", "Sonoma", "Los Angeles","Carmel By The Sea", "San Diego", "Santa Barbara");
        // largest cities in the US
        final Stream<String> cities = Stream.of("NYC", "Las Vegas", "Honolulu", "Phoenix", "San Antonio", "Dallas", "Chicago", "Portland", "Minneapolis", "Seattle", "New Orleans", "Boston", "Philadelphia", "Miami");

        return Stream.concat(cities, citiesInCa).map(YelpFusionTestCase::locationByCity);

    }

    public static BreinTemporalDataResult locationByCity(String city) {

        return temporalDataService.temporalDataResult(city);
    }

    public static BreinTemporalDataResult locationByCity(String city, String state, String country) {

        return temporalDataService.temporalDataResult(city, state, country);
    }


}
