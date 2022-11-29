package io.github.elasticsearch.client._types;


public class GeoLocationBuilders {
    private GeoLocationBuilders() {
    }

    public static GeoHashLocation.Builder geohash() {
        return new GeoHashLocation.Builder();
    }


    public static LatLonGeoLocation.Builder latlon() {
        return new LatLonGeoLocation.Builder();
    }

}