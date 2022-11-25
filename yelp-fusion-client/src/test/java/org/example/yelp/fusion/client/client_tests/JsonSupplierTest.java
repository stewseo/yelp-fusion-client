//package org.example.yelp.fusion.client.client_tests;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.yelp.fusion.client.business.json.jackson.JacksonMapperTest;
//import org.example.yelp.fusion.client.business.json.jackson.JsonpDeserializerTest;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UncheckedIOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.function.Supplier;
//
//public class JsonSupplierTest {
//
//    @Test
//    void supplierTest() throws URISyntaxException, IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder(new URI("http://api.yelp.com/v3/businesses/search?location=sf"))
//                .header("Accept", "application/json")
//                .build();
//
//        JacksonMapperTest.TestData model = HttpClient.newHttpClient()
//                .send(request, new JsonpDeserializerTest.JsonBodyHandler<>(JacksonMapperTest.TestData.class))
//                .body()
//                .get();
//
//        System.out.println(model);
//    }
//
//    public static <W> HttpResponse.BodySubscriber<Supplier<W>> asJSON(Class<W> targetType) {
//        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
//
//        return HttpResponse.BodySubscribers.mapping(
//                upstream,
//                inputStream -> toSupplierOfType(inputStream, targetType));
//    }
//
//    public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<Supplier<W>> {
//
//        private final Class<W> wClass;
//
//        public JsonBodyHandler(Class<W> wClass) {
//            this.wClass = wClass;
//        }
//
//        @Override
//        public HttpResponse.BodySubscriber<Supplier<W>> apply(HttpResponse.ResponseInfo responseInfo) {
//            return asJSON(wClass);
//        }
//
//    }
//
//    public static <W> Supplier<W> toSupplierOfType(InputStream inputStream, Class<W> targetType) {
//        return () -> {
//            try (InputStream stream = inputStream) {
//                ObjectMapper objectMapper = new ObjectMapper();
//                return objectMapper.readValue(stream, targetType);
//            } catch (IOException e) {
//                throw new UncheckedIOException(e);
//            }
//        };
//    }
//
//}
