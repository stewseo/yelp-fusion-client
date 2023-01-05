package io.github.stewseo.clients.yelpfusion._types;


public class BusinessBuilders {

    private BusinessBuilders() {

    }

    /**
     * Creates a builder for the {@link Category geohash} {@code GeoLocation}
     * variant.
     */
    public static Category.Builder category() {
        return new Category.Builder();
    }

    /**
     * Creates a builder for the {@link Coordinates latlon}
     * {@code GeoLocation} variant.
     */
    public static Coordinates.Builder categories() {
        return new Coordinates.Builder();
    }
}
