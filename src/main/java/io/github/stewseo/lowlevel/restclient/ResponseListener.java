package io.github.stewseo.lowlevel.restclient;


public interface ResponseListener {

    void onSuccess(Response response);


    void onFailure(Exception exception);
}
