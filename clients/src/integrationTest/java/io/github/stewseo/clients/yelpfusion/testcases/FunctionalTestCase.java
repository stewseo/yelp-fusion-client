package io.github.stewseo.clients.yelpfusion.testcases;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.clients.elasticsearch.ElasticsearchService;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.yelpfusion.ElasticsearchCtx;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion.YelpFusionCtx;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinate;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.temporaldata.service.TemporalDataService;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// generates instances of YelpFusionResult and TemporalDataResult
public abstract class FunctionalTestCase {

    public static final String INDEX_NYC = "yelp-fusion-businesses-restaurants-sf";
    public final YelpFusionClient yelpFusionClient;

    public ElasticsearchAsyncClient esAsyncClient;

    private static int numCities;
    private static List<BreinTemporalDataResult> list;
    private static TemporalDataService temporalDataService;
    private final ElasticsearchService elasticsearchService;

    protected FunctionalTestCase() {
        YelpFusionTransport transport = YelpFusionCtx.getInstance().createYelpFusionTransport();
        yelpFusionClient = new YelpFusionClient(transport);

        ElasticsearchAsyncClient asyncClient = ElasticsearchCtx.getElasticsearchAsyncClient();
        elasticsearchService = new ElasticsearchService(asyncClient);

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
                .state("ca")
                .country("US")
        );

        Coordinate center = Coordinate.of(c -> c
                .latitude(latitude)
                .longitude(longitude)
        );
        String id = "id-" + i;
        String phoneNumber = "000000000" + i;

        return BusinessDetails.of(e -> e
                .center(c -> c
                        .latitude(38.000)
                        .longitude(-122.00))
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

    public List<StringTermsBucket> getStringTermsBucketsByCategory() {

        int numCategoriesWithParentRestaurant = 320;

        return elasticsearchService.termsAggregationByCategory(numCategoriesWithParentRestaurant);
    }
}
