package io.github.stewseo.yelp.fusion.client.yelpfusion.business;

import java.util.List;

public class MetaBusiness {

    public List<Business_> businesses;
    public String _index;
    public String _id;

    public MetaBusiness(){}

    public List<Business_> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business_> businesses) {
        this.businesses = businesses;
    }

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
