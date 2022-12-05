package io.github.stewseo.yelp.fusion.client.yelpfusion.business;

import java.util.List;
import java.util.Map;

public class BusinessSearchPojo {

    public  String id;

    public  String alias;

    public  String name;

    public  String image_url;

    public  String url;

    public  String display_phone;

    public  String phone;
    public  String price;

    public  Boolean is_closed;
    public  Double distance;
    public  Double rating;

    public  Integer review_count;
    public  List<String> transactions;

    public Map<String, String> coordinates;

    public Map<String, String> location;

    public List<Map<String, String>>  categories;

    public BusinessSearchPojo(){}

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String alias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String image_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String url() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String display_phone() {
        return display_phone;
    }

    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }

    public String phone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String price() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean is_closed() {
        return is_closed;
    }

    public void setIs_closed(Boolean is_closed) {
        this.is_closed = is_closed;
    }

    public Double distance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double rating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer review_count() {
        return review_count;
    }

    public void setReview_count(Integer review_count) {
        this.review_count = review_count;
    }

    public List<String> transactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public Map<String, String> coordinates() {
        return coordinates;
    }

    public void setCoordinates(Map<String, String> coordinates) {
        this.coordinates = coordinates;
    }

    public Map<String, String> location() {
        return location;
    }

    public void setLocation(Map<String, String> location) {
        this.location = location;
    }

    public List<Map<String, String>> categories() {
        return categories;
    }

    public void setCategories(List<Map<String, String>> categories) {
        this.categories = categories;
    }
}
