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

    Coordinates coordinates = Coordinates.of(c -> c.latitude(37.0).longitude(-122.0));

    Location location = Location.of(l -> l.city("San Francisco"));

    List<Category> categories = List.of(Category.of(c->c.alias("categories")));

}
