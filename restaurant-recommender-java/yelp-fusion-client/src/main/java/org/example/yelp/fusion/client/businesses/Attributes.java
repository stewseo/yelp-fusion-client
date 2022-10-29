package org.example.yelp.fusion.client.businesses;


import org.example.elasticsearch.client.json.*;

@JsonpDeserializable
public class Attributes {


    public Object[] attributes;

<<<<<<< HEAD:yelp-fusion-client/src/main/java/org/example/yelp/fusion/client/businesses/Attributes.java

=======
>>>>>>> 80cbb9dac9c2996f7c50576fe9668568597878af:src/main/java/com/example/client/yelp_fusion/businesses/Attributes.java
    public Attributes() {}

    public Object[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Object[] attributes) {
        this.attributes = attributes;
    }
}
