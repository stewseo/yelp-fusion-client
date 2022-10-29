module org.example.yelp.fusion.client {
    uses jakarta.json.spi.JsonProvider;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpasyncclient;
    requires org.apache.httpcomponents.httpclient;
    requires org.slf4j;
    requires jakarta.json;
    requires org.example.lowlevel.restclient;
    requires org.example.elasticsearch.client;
    requires java.compiler;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    exports org.example.yelp.fusion.client;
    exports org.example.yelp.fusion.client.businesses.search;

    exports org.example.yelp.fusion.client.exception;
    exports org.example.yelp.fusion.client.transport;
    exports org.example.yelp.fusion.client.businesses;
}