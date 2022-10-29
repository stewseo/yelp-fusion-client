package org.example.yelp.fusion.client;

import org.example.elasticsearch.client.json.*;

@JsonpDeserializable
public class Coordinates {

    public Double latitude;

    public GeoLocation geo_location;
    public Double longitude;

    public Coordinates() {}

    public Coordinates(double v, double v1) {
        this.latitude = v;
        this.longitude = v1;
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public GeoLocation geo_location() {
        return geo_location;
    }

    public void setGeo_location(GeoLocation geo_location) {
        this.geo_location = geo_location;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }

    public static class GeoLocation {
        public Double latitude;
        public Double longitude;
        public GeoLocation(){}

        public Double latitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double longitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
}
