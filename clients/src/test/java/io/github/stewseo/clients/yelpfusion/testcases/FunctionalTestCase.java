package io.github.stewseo.clients.yelpfusion.testcases;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.clients.json.testcases.TestJson;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinates;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.testcases.context.YelpFusionTestService;
import io.github.stewseo.temporaldata.service.TemporalDataService;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// generates instances of YelpFusionResult and TemporalDataResult
public abstract class FunctionalTestCase {


    private static final String state = "CA";

    private static final String country = "USA";

    public final YelpFusionTestService yelpFusionService;

    public final TestJson testJson;

    private static int numCities;

    private static List<BreinTemporalDataResult> list;

    private static TemporalDataService temporalDataService;

    protected FunctionalTestCase() {
        yelpFusionService = new YelpFusionTestService();
        testJson = new TestJson();
        temporalDataService = new TemporalDataService();
        list = loadCaliforniaCities().toList();
        numCities = list.size();
    }

    private static Stream<BreinTemporalDataResult> loadCaliforniaCities() {

        return Stream.of("San Francisco", "Oakland", "San Jose", "Carmel", "Monterey", "Napa", "Sonoma", "Los Angeles", "San Diego", "Santa Barbara")
                .map(FunctionalTestCase::locationByCity);

    }

    public static BreinTemporalDataResult locationByCity(String city) {

        return temporalDataService.temporalDataResult(city);
    }

    public Stream<BusinessDetails> generateBusinessInstances(int size) {

        return IntStream.range(0, size).mapToObj(this::generateBusiness);
    }

    public BusinessDetails generateBusiness(int i) {

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
        String phoneNumber = "000000000" + i;

        return BusinessDetails.of(e -> e
                .coordinates(coordinates)
                .location(location)
                .categories(category)
                .id(id)
                .phone(phoneNumber)
        );
    }

    public Stream<BreinTemporalDataResult> getStreamOfTemporalData() {

//        final Stream<String> citiesInCa =
//                Stream.of("San Francisco", "Oakland", "San Jose", "Monterey", "Napa", "Sonoma", "Los Angeles", "Carmel By The Sea", "San Diego", "Santa Barbara");
//
//        // largest cities in the US
//        final Stream<String> cities = Stream.of("NYC", "Las Vegas", "Honolulu", "Phoenix", "San Antonio", "Dallas", "Chicago", "Portland", "Minneapolis", "Seattle", "New Orleans", "Boston", "Philadelphia", "Miami");

        final Stream<String> citiesInCa =
                Stream.of("San Francisco");
        // largest cities in the US
        final Stream<String> cities = Stream.of("NYC");

        return Stream.concat(cities, citiesInCa).map(FunctionalTestCase::locationByCity);

    }


}
