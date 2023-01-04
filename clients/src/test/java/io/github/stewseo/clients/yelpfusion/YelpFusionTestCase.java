package io.github.stewseo.clients.yelpfusion;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.clients._types.TestBusiness;
import io.github.stewseo.clients._types.TestCategory;
import io.github.stewseo.clients._types.TestCoordinates;
import io.github.stewseo.clients._types.TestLocation;
import io.github.stewseo.clients.yelpfusion.json.YelpFusionJsonTestCase;
import io.github.stewseo.temporaldata.service.TemporalDataService;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class YelpFusionTestCase {

    private static final String state = "CA";

    private static final String country = "USA";

    public final YelpFusionServiceCtx yelpFusionServiceCtx;

    public final YelpFusionJsonTestCase jsonTestCase;

    private static int numCities;

    private static List<BreinTemporalDataResult> list;

    private static TemporalDataService temporalDataService;

    protected YelpFusionTestCase() {
        yelpFusionServiceCtx = new YelpFusionServiceCtx();
        jsonTestCase = new YelpFusionJsonTestCase();
        temporalDataService = new TemporalDataService();
    }

    @BeforeAll
    static void beforeAll() {
        list = loadCaliforniaCities().toList();
        numCities = list.size();
    }

    private static Stream<BreinTemporalDataResult> loadCaliforniaCities() {

        return Stream.of("San Francisco", "Oakland", "San Jose", "Carmel", "Monterey", "Napa", "Sonoma", "Los Angeles", "San Diego", "Santa Barbara")
                .map(YelpFusionTestCase::locationByCity);

    }

    public static BreinTemporalDataResult locationByCity(String city) {

        return temporalDataService.temporalDataResult(city);
    }

    public static BreinTemporalDataResult locationByCity(String city, String state, String country) {

        return temporalDataService.temporalDataResult(city, state, country);
    }

    public Stream<TestBusiness> generateBusinessInstances(int size) {

        return IntStream.range(0, size).mapToObj(this::generateBusiness);

    }

    public TestBusiness generateBusiness(int i) {

        int index = ThreadLocalRandom.current().nextInt(0, numCities);

        String city = list.get(index).getLocation().getCity();

        BreinLocationResult breinLocationResult = locationByCity(city).getLocation();

        double longitude = breinLocationResult.getLat();

        double latitude = breinLocationResult.getLat();

        TestCategory category = TestCategory.of(cat -> cat.alias("burgers"));

        TestLocation location = TestLocation.of(l -> l
                .city(city)
                .state(state)
                .country(country)
        );

        TestCoordinates coordinates = TestCoordinates.of(c -> c
                .latitude(latitude)
                .longitude(longitude)
        );
        String id = "id-" + i;
        String phoneNumber = "000000000" + i;

        return TestBusiness.of(e -> e
                .coordinates(coordinates)
                .location(location)
                .categories(category)
                .id(id)
                .phone(phoneNumber)
        );
    }

    public YelpFusionJsonTestCase getTestJson() {
        return jsonTestCase;
    }

    public Stream<BreinTemporalDataResult> getStreamOfTemporalData() {

        final Stream<String> citiesInCa =
                Stream.of("San Francisco", "Oakland", "San Jose", "Monterey", "Napa", "Sonoma", "Los Angeles", "Carmel By The Sea", "San Diego", "Santa Barbara");
        // largest cities in the US
        final Stream<String> cities = Stream.of("NYC", "Las Vegas", "Honolulu", "Phoenix", "San Antonio", "Dallas", "Chicago", "Portland", "Minneapolis", "Seattle", "New Orleans", "Boston", "Philadelphia", "Miami");

        return Stream.concat(cities, citiesInCa).map(YelpFusionTestCase::locationByCity);

    }


}
