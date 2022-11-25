package org.example.yelp.fusion.client.business.model;

import org.example.yelp.fusion.client.business.search.Business;

import java.util.List;

public class Business_ {

    public List<Business> business;
    public Integer total;

    public Business_() {}

    public List<Business> getBusiness() {
        return business;
    }

    public void setBusiness(List<Business> business) {
        this.business = business;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
