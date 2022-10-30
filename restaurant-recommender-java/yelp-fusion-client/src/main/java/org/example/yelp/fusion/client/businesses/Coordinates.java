package org.example.yelp.fusion.client.businesses;

public class Coordinates {

    public Double latitude;

    public Double longitude;


    public Coordinates() {

    }
    public Coordinates(Double longitude, Double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
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
