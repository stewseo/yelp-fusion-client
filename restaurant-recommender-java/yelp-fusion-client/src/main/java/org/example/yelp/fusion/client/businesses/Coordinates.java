package org.example.yelp.fusion.client.businesses;

public class Coordinates {

    public Double latitude;

    public Double longitude;

    public GeoLocation geoLocation;

    public Coordinates() {

    }
    public Coordinates(Double longitude, Double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public Coordinates setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Coordinates setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double latitude() {
        return latitude;
    }


    public Double longitude() {
        return longitude;
    }

    public GeoLocation geoLocation() {
        return geoLocation;
    }

    public Coordinates setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
        return this;
    }

    public static class GeoLocation{
        public Double latitude;
        public Double longitude;

        public GeoLocation(){}

        public Double latitude() {
            return latitude;
        }

        public GeoLocation setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Double longitude() {
            return longitude;
        }

        public GeoLocation setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }
    }
}
