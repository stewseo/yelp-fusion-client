package io.github.yelp.fusion.restclient;


public interface ResponseListener {

    void onSuccess(Response response);


    void onFailure(Exception exception);
}
