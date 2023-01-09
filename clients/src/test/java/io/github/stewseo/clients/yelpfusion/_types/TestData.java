package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinates;
import io.github.stewseo.clients.yelpfusion._types.Event;

import java.util.List;

public class TestData {

    public static final String ID = "id";
    public static final String LOCALE = "en_US";
    public static final String SORT_BY = "sort_by";
    public static final String ALIAS = "alias";
    public static final String TERM = "term";
    public static final String PHONE = "phoneValue";
    public static final String IMAGE_URL = "imageUrlValue";
    public static final String NAME = "name";
    public static final int TOTAL = 1;
    public static final int REVIEW_COUNT = 1;
    public static final int OFFSET = 5;
    public static final int LIMIT = 50;
    public static final int RADIUS = 20000;
    public static final int PRICE = 3;
    public static final int OPEN_AT = 1;

    public static final double DISTANCE = 1;

    public static final double RATING = 4.5;
    public static final double LATITUDE = 37.7829;
    public static final double LONGITUDE = -122.4189;
    public static final boolean OPEN_NOW = true;
    public static boolean IS_CLOSED = false;
    public static boolean IS_OVERNIGHT = false;

    public static final List<String> TRANSACTIONS = List.of("transactionValue");
    public static final List<Attribute> ATTRIBUTES = List.of(Attribute.of(a -> a.attribute("attribute")));

    public static final Event EVENT = Event.of(e -> e
            .name("nameValue"));

    public static final Category CATEGORY = Category.of(c -> c
            .alias("catAlias"));

    public static final Coordinates COORDINATES = Coordinates.of(c -> c
            .latitude(LATITUDE)
            .longitude(LONGITUDE));

    public static final Location LOCATION = Location.of(l -> l
            .address1("addressOneValue")
            .city("cityValue")
            .state("stateValue")
            .country("countryValue"));

}
