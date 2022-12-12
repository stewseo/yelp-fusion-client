package io.github.stewseo.lowlevel.restclient.response;

import io.github.stewseo.yelp.fusion.lowlevel.restclient.PrintUtils;
import io.github.stewseo.yelp.fusion.client.YelpConnection;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseBodyTest {
    private static final Logger tracer = LoggerFactory.getLogger("tracer");

    @Test
    void entityContentTest() throws IOException {

        YelpConnection.initYelpFusionClient();
        YelpFusionClient yelpClient = YelpConnection.getYelpClient();
        YelpRestClientTransport yelpTransport = (YelpRestClientTransport) yelpClient._transport();
        HttpHost host = yelpTransport.restClient().getHttpHost();

        HttpRequestBase requestBase = new HttpGet("businesses/" + "wu3w6IlUct9OvYmYXDMGJA");
        String traceReq = buildTraceRequest(requestBase, host);

        tracer.trace("Executing command in separate process: " + traceReq);
        assertThat(traceReq).isEqualTo("curl -H Authorization: Bearer dxAGGph7uj-XYO2ZY2qD2gOd9zEcKeNsp-Epg5SzducUwu65a9DU7kwI_0_MRqEkPuUPNYzGct-P4jrAKj8OFmMi9Tq_oyycqQb2_jGBHvb_amm5vqUzOGIP2Js1Y3Yx -X http://api.yelp.com:80/businesses/wu3w6IlUct9OvYmYXDMGJA");

        //Process provides control of native processes started by ProcessBuilder.start and Runtime.exec.
        // The class provides methods for performing input from the process, performing output to the process,
        // waiting for the process to complete, checking the exit status of the process, and destroying (killing) the process.
        Process process = Runtime.getRuntime().exec(traceReq);

        assertThat(process.children().count()).isEqualTo(1);
        assertThat(process.descendants().count()).isEqualTo(1);
        assertThat(process.info().user().isPresent()).isTrue();

        try(BufferedReader errorReader = process.errorReader())
        {
            if(errorReader.ready()) {
                tracer.trace(PrintUtils.tracer("errorReader: " + errorReader.readLine()));
            }
        }

        InputStream inputStream = process.getInputStream();
        tracer.trace(PrintUtils.tracer("response: " + IOUtils.toString(inputStream, StandardCharsets.UTF_8)));

        inputStream.close();
        process.destroy();
    }


    static String buildTraceRequest(HttpUriRequest request, HttpHost host) throws IOException {
//        String requestLine = "curl -X " + request.getMethod() + " '" + host + getUri(request.getRequestLine()) + "'";
        String requestLine = "curl -H Authorization: Bearer " + System.getenv("YELP_API_KEY");
        requestLine += " -X " + host + getUri(request.getRequestLine());

        if (request instanceof HttpEntityEnclosingRequest enclosingRequest) {
            if (enclosingRequest.getEntity() != null) {
                requestLine += " -d '";
                HttpEntity entity = enclosingRequest.getEntity();
                if (!entity.isRepeatable()) {
                    entity = new BufferedHttpEntity(enclosingRequest.getEntity());
                    enclosingRequest.setEntity(entity);
                }
                requestLine += EntityUtils.toString(entity, StandardCharsets.UTF_8) + "'";
            }
        }
        return requestLine;
    }
    private static String getUri(RequestLine requestLine) {
        if (requestLine.getUri().charAt(0) != '/') {
            return "/" + requestLine.getUri();
        }
        return requestLine.getUri();
    }
}
