package io.github.stewseo.yelp.fusion.client.yelpfusion.business;

import io.github.stewseo.lowlevel.restclient.RestClient;
import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client.Elasticsearch;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.YelpFusionCategoriesClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.events.YelpFusionEventsClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.misc.AutoCompleteResponse;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.function.Function;

public class YelpFusionBusinessAsyncClient extends ApiClient<YelpFusionTransport, YelpFusionBusinessAsyncClient> {

    public YelpFusionBusinessAsyncClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionBusinessAsyncClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }
    @Override
    public YelpFusionBusinessAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionBusinessAsyncClient(this.transport, transportOptions);
    }

}
