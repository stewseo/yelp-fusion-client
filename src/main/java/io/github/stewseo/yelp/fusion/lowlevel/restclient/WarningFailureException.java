package io.github.stewseo.yelp.fusion.lowlevel.restclient;


import java.io.IOException;


public class WarningFailureException extends RuntimeException {
        private final Response response;

        public WarningFailureException(Response response) throws IOException {
            super(ResponseException.buildMessage(response));
            this.response = response;
        }


        WarningFailureException(WarningFailureException e) {
            super(e.getMessage(), e);
            this.response = e.getResponse();
        }


        public Response getResponse() {
            return response;
        }
    }

