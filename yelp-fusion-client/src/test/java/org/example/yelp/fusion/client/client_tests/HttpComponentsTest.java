package org.example.yelp.fusion.client.client_tests;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class HttpComponentsTest {

    String uri = "http://api.yelp.com/v3/businesses/search?location=sf";
    @Test
    public void parseJsonStreamTest() throws IOException, ExecutionException, InterruptedException {

        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        client.start();
        HttpGet request = new HttpGet(uri);
        request.setHeader("Authorization", "Bearer: " + System.getenv("YELP_API_KEY"));
        Future<HttpResponse> future = client.execute(request, null);
        HttpResponse response = future.get();

        assertThat(response.getStatusLine().toString()).isEqualTo("HTTP/1.1 200 OK");
        client.close();

    }


}
