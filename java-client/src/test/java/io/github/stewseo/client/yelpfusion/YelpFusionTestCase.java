package io.github.stewseo.client.yelpfusion;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.client.yelpfusion.business.Business;
import io.github.stewseo.client.yelpfusion.business.Coordinates;
import io.github.stewseo.client.yelpfusion.business.Location;
import io.github.stewseo.client.yelpfusion.categories.Category;
import io.github.stewseo.client.yelpfusion.json.YelpFusionJsonTestCase;
import io.github.stewseo.temporaldata.TemporalDataService;
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

    public Stream<Business> generateBusinessInstances(int size) {

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
        String id = "id-" + i;
        String phoneNumber = "000000000" + String.valueOf(i);

        return Business.of(e -> e
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
