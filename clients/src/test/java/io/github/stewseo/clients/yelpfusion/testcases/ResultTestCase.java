package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.SerializeToJson;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinates;
import io.github.stewseo.clients.yelpfusion._types.Location;
import org.junit.jupiter.api.Test;

import java.util.List;

// method to test a result
public interface ResultTestCase extends DeserializeFromJson, SerializeToJson {

    String id = "id", alias = "alias", name = "name", image_url = "image_url", url = "url",
            display_phone = "display_phone", phone = "phone", price = "price";

    boolean is_closed = true;

    double distance = 1.0, rating = 3.5;

    int review_count = 5;

    List<String> transactions = List.of("transactions");

    Coordinates coordinates = Coordinates.of(c -> c.latitude(37.0).longitude(-122.0));

    Location location = Location.of(l -> l.city("San Francisco"));

    List<Category> categories = List.of(Category.of(c->c.alias("categories")));

    @Test
    void testDeserializer();
}
