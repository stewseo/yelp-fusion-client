package org.example.yelp.fusion.client.businesses;

public class Coordinates {

    public Double latitude;

    public Double longitude;

<<<<<<< HEAD:yelp-fusion-client/src/main/java/org/example/yelp/fusion/client/businesses/Coordinates.java
    public Coordinates(double v, double v1) {
    }


=======
    public Coordinates() {

    }
    public Coordinates(Double longitude, Double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
>>>>>>> 80cbb9dac9c2996f7c50576fe9668568597878af:src/main/java/com/example/client/yelp_fusion/businesses/Coordinates.java
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
