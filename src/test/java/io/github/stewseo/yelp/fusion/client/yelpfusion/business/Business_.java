package io.github.stewseo.yelp.fusion.client.yelpfusion.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;

@JsonIgnoreProperties(value={ "_closed", "_claimed" }, allowGetters=true)
public class Business_ {

    private final static Logger logger = LoggerFactory.getLogger(Business.class);

    // ------------------------------ Fields ------------------------------------ //
    private  String id;
    private  String alias;
    private  String name;
    private  String image_url;
    private  String url;
    private  String phone;
    private  String display_phone;
    public Double open_at;
    private  String price;

    private String timestamp;
    private  Boolean is_claimed;
    public Boolean open_now;
    private  Boolean is_closed;

    private  Integer review_count;

    private  Double rating;

    private  Location_ location;

    private  Coordinates_ coordinates;

    private  List<String> transactions;

    @Nullable
    private  List<String> photos;

    private  List<Hours_> hours;

    private  List<Categories_> categories;

    @Nullable
    private  List<Attribute_> attributes;

    public Business_() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(@Nullable String image_url) {
        this.image_url = image_url;
    }

    public Double getOpen_at() {
        return open_at;
    }

    public void setOpen_at(Double open_at) {
        this.open_at = open_at;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Nullable
    public String getDisplay_phone() {
        return display_phone;
    }

    public void setDisplay_phone(@Nullable String display_phone) {
        this.display_phone = display_phone;
    }

    public Boolean getOpen_now() {
        return open_now;
    }

    public void setOpen_now(Boolean open_now) {
        this.open_now = open_now;
    }

    @Nullable
    public String getPrice() {
        return price;
    }

    public void setPrice(@Nullable String price) {
        this.price = price;
    }

    public Boolean getIs_claimed() {
        return is_claimed;
    }

    public void setIs_claimed(Boolean is_claimed) {
        this.is_claimed = is_claimed;
    }

    public Boolean getIs_closed() {
        return is_closed;
    }

    public void setIs_closed(Boolean is_closed) {
        this.is_closed = is_closed;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public void setReview_count(Integer review_count) {
        this.review_count = review_count;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Location_ getLocation() {
        return location;
    }

    public void setLocation(Location_ location) {
        this.location = location;
    }

    public Coordinates_ getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates_ coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    @Nullable
    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(@Nullable List<String> photos) {
        this.photos = photos;
    }

    public List<Hours_> getHours() {
        return hours;
    }

    public void setHours(List<Hours_> hours) {
        this.hours = hours;
    }

    public List<Categories_> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories_> categories) {
        this.categories = categories;
    }

    @Nullable
    public List<Attribute_> getAttributes() {
        return attributes;
    }

    public void setAttributes(@Nullable List<Attribute_> attributes) {
        this.attributes = attributes;
    }
}