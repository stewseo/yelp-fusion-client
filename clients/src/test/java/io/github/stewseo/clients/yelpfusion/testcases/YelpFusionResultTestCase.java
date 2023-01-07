package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinates;
import io.github.stewseo.clients.yelpfusion._types.Location;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.StringWriter;
import java.util.List;

public abstract class YelpFusionResultTestCase implements ResultTestCase {

    public final String id = "id", alias = "alias", name = "name", image_url = "image_url", url = "url",
            display_phone = "display_phone", phone = "phone", price = "price";

    public final boolean is_closed = true;

    public final double distance = 1.0, rating = 3.5;

    public final int review_count = 5;

    public final List<String> transactions = List.of("transactions");

    public final Coordinates coordinates = Coordinates.of(c -> c.latitude(37.0).longitude(-122.0));

    public final Location location = Location.of(l -> l.city("San Francisco"));

    public final List<Category> categories = List.of(Category.of(c->c.alias("categories")));

    public JsonGenerator generator() {
        return mapper.jsonProvider().createGenerator(new StringWriter());
    }

    public JsonProvider jsonProvider() {
        return mapper.jsonProvider();
    }

    public JsonpMapper mapper() {
        return mapper;
    }

}
