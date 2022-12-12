package io.github.stewseo.yelp.fusion.lowlevel.restclient;


public interface ResponseListener {

    void onSuccess(Response response);


    void onFailure(Exception exception);
}
