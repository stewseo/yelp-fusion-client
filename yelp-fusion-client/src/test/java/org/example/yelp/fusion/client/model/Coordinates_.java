package org.example.yelp.fusion.client.model;

import org.example.yelp.fusion.client.business.model.Coordinates;

public class Coordinates_ {

    public Double latitude;

    public Double longitude;

    public Coordinates.GeoCoordinates geo_coordinates;

    public Coordinates_() {}

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Coordinates.GeoCoordinates getGeo_coordinates() {
        return geo_coordinates;
    }

    public void setGeo_coordinates(Coordinates.GeoCoordinates geo_coordinates) {
        this.geo_coordinates = geo_coordinates;
    }

    public static class GeoCoordinates {
        public Double latitude;

        public Double longitude;

        public GeoCoordinates(){}

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
}
