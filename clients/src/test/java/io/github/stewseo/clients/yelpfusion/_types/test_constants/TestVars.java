package io.github.stewseo.clients.yelpfusion._types.test_constants;

import io.github.stewseo.clients._type.QueryParameter;
import io.github.stewseo.clients._type.Term;
import io.github.stewseo.clients._type.TermQueryParameter;
import io.github.stewseo.clients.json.JsonpMapperBase;
import io.github.stewseo.clients.yelpfusion._types.Attribute;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Center;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion._types.Hours;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion._types.Messaging;
import io.github.stewseo.clients.yelpfusion._types.Region;
import io.github.stewseo.clients.yelpfusion._types.SpecialHours;
import io.github.stewseo.clients.yelpfusion._types.User;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.businesses.search.Hit;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;

import java.util.List;

public class TestVars {

    public static final String INDEX_SF_RESTAURANTS = "yelp-fusion-restaurants-sf";

    public static final String INDEX_SEARCH_SF_RESTAURANTS = "yelp-fusion-businesses-restaurants-sf-s";

    public static final String METHOD = "method [GET]";

    public static final String SORT_BY = "sort_by";

    public static final String ALIAS = "alias";

    public static final String LOCALE = "en_US";

    public static final String BAD_LOCALE = "en_U";

    public static final String ID = "id";
    public static final String COUNTRY_WHITELIST = "country_whitelist";
    public static final String COUNTRY_BLACKLIST = "country_blacklist";
    public static final String TITLE = "title";
    public static final String PARENT_ALIAS = "parents";
    public static final String TERM = "term";
    public static final String PHONE = "phoneValue";
    public static final String IMAGE_URL = "imageUrlValue";
    public static final String FIELD_URL = "url";
    public static final String NAME = "name";
    public static final String PHOTOS = "photos";
    public static final String PRICE_STRING = "$";
    public static final String DISPLAY_PHONE = "display_phone";
    public static final String EXPECTED_CATEGORIES = "[{\"alias\":\"alias\"}, {\"alias\":\"alias\"}, {\"alias\":\"alias\"}]";
    public static final int TOTAL = 1;
    public static final int REVIEW_COUNT = 1;
    public static final int OFFSET = 5;
    public static final int LIMIT = 50;
    public static final int RADIUS = 20000;
    public static final int PRICE = 3;
    public static final int OPEN_AT = 1;
    public static final int MAX_RESULTS = 10000;
    public static final double DISTANCE = 1;
    public static final double RATING = 4.5;
    public static final double LATITUDE = 37.7829;
    public static final double LONGITUDE = -122.4189;
    public static final boolean OPEN_NOW = true;
    public static final boolean IS_CLOSED = false;
    public static final boolean IS_CLAIMED = false;
    public static final boolean IS_OVERNIGHT = false;
    public static final String TRANSACTION_TYPE = "delivery";

    public static final String HOURS_START = "0800";

    public static final String HOURS_END = "1700";
    public static final Center CENTER = Center.of(c -> c.latitude(LATITUDE).longitude(LONGITUDE));
    public static final Region REGION = Region.of(r -> r.center(CENTER));
    public static final List<String> TRANSACTIONS = List.of("transactionValue");
    public static final List<Attribute> ATTRIBUTES = List.of(Attribute.of(a -> a.attribute("attribute")));
    public static final User USER = User.of(u -> u.id("idValue").name(NAME));
    public static final Messaging MESSAGING = Messaging.of(m -> m.use_case_text("use_case_text"));
    public static final Hours HOURS = Hours.of(h -> h.hours_type("hoursType"));

    public static final SpecialHours SPECIAL_HOURS = SpecialHours.of(s -> s
            .is_closed(IS_CLOSED)
            .start(HOURS_START)
            .end(HOURS_END));
    public static final Event EVENT = Event.of(e -> e.name(NAME));
    public static final Category CATEGORY = Category.of(c -> c.alias(ALIAS));
    public static final Location LOCATION = Location.of(l -> l
            .address1("addressOneValue")
            .city("cityValue")
            .state("stateValue")
            .country("countryValue"));

    public static final BusinessDetails EXPECTED_BUSINESS_DETAILS_RESULT = BusinessDetails.of(b -> b
            .id(ID)
            .alias(ALIAS)
            .center(CENTER)
            .hours(HOURS)
            .hours(h -> h.hours_type("hoursTypeValue"))
            .hours(List.of(HOURS))
            .location(LOCATION)
            .review_count(REVIEW_COUNT)
            .rating(RATING)
            .categories(CATEGORY)
            .categories(List.of(CATEGORY))
            .categories(cat -> cat.alias(ALIAS))
            .phone(PHONE)
            .price(PRICE_STRING)
            .is_closed(IS_CLOSED)
            .display_phone(DISPLAY_PHONE)
            .is_claimed(IS_CLAIMED)
            .name(NAME)
            .url(FIELD_URL)
            .messaging(MESSAGING)
            .transactions(TRANSACTIONS)
            .photos(PHOTOS)
            .photos(List.of(PHOTOS))
    );
    public static final SearchBusinessResult EXPECTED_SEARCH_BUSINESS_RESULT = SearchBusinessResult.of(s -> s
            .id(ID)
            .center(CENTER)
            .rating(RATING));
    public static final Hit<SearchBusinessResult> HIT = Hit.of(h -> h
            .source(EXPECTED_SEARCH_BUSINESS_RESULT)
            .tDocumentSerializer(JsonpMapperBase.findSerializer(EXPECTED_SEARCH_BUSINESS_RESULT)));

    public static final SearchResponse<SearchBusinessResult> EXPECTED_SEARCH_RESPONSE = SearchResponse.of(s -> s
            .hits(HIT)
            .region(REGION)
            .total(TOTAL));

    public static final TermQueryParameter TERMS_QUERY_FIELD = TermQueryParameter.of(variant -> variant.term(Term.Restaurants));

    public static final QueryParameter QUERY_FIELD = QueryParameter.of(qf -> qf.term(TERMS_QUERY_FIELD));

    public static final List<Boolean> TRUE_FALSE_LIST = List.of(true, false);

}
