package io.github.stewseo.yelp.fusion.client.transport.endpoints;

@SuppressWarnings("ClassCanBeRecord")
public class BooleanResponse {
    private final boolean value;

    public BooleanResponse(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }
}
