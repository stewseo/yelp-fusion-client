package io.github.stewseo.client;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;

import io.github.stewseo.client.connection.YelpFusionConnection;
import io.github.stewseo.client.json.TestJson;
import io.github.stewseo.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.temporaldata.TemporalDataService;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.yelpfusion.business.Business;
import io.github.stewseo.client.yelpfusion.business.Coordinates;
import io.github.stewseo.client.yelpfusion.business.Location;
import io.github.stewseo.client.yelpfusion.categories.Category;

import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class YelpFusionTestCase {
    
    public static YelpFusionAsyncClient yelpFusionAsyncClient;

    private static TestJson testJson;

    private static int numCities;

    private static List<BreinTemporalDataResult> list;

    private static TemporalDataService temporalDataService;
    
    @BeforeAll
    static void beforeAll() {

        yelpFusionAsyncClient = YelpFusionConnection.createOrGetYelpFusionAsyncClient();
        
        temporalDataService = new TemporalDataService();

        testJson = new TestJson();
        
        list = loadCaliforniaCities().toList();
        
        numCities = list.size();
    }

    private static Stream<BreinTemporalDataResult> loadCaliforniaCities() {

        return Stream.of("San Francisco", "Oakland", "San Jose", "Carmel", "Monterey", "Napa", "Sonoma", "Los Angeles", "San Diego", "Santa Barbara")
                .map(YelpFusionTestCase::locationByCity);

    }

    private static String state = "CA";

    private static String country = "USA";

    public Stream<Business> generateBusinessInstances(int size) {

        JsonpDeserializer<Business> businessJsonpDeserializer = Business._DESERIALIZER;

        List<Business> businesses = new ArrayList<>();

        return IntStream.range(0, size).mapToObj(this::generateBusiness);

    }

    public Business generateBusiness(int i) {

        int index = ThreadLocalRandom.current().nextInt(0, numCities);

        String city = list.get(index).getLocation().getCity();

        BreinLocationResult breinLocationResult = locationByCity(city).getLocation();

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

    public TestJson getTestJson() {
        return testJson;
    }
    public Stream<BreinTemporalDataResult> getStreamOfTemporalData() {

        final Stream<String> citiesInCa =
                Stream.of("San Francisco", "Oakland", "San Jose", "Monterey", "Napa", "Sonoma", "Los Angeles","Carmel By The Sea", "San Diego", "Santa Barbara");
        // largest cities in the US
        final Stream<String> cities = Stream.of("NYC", "Las Vegas", "Honolulu", "Phoenix", "San Antonio", "Dallas", "Chicago", "Portland", "Minneapolis", "Seattle", "New Orleans", "Boston", "Philadelphia", "Miami");

        return Stream.concat(cities, citiesInCa).map(YelpFusionTestCase::locationByCity);

    }

    public static BreinTemporalDataResult locationByCity(String city) {
        //
        return temporalDataService.temporalData(city);
    }

    public static BreinTemporalDataResult locationByCity(String city, String state, String country) {

        return temporalDataService.temporalData(city, state, country);
    }


}