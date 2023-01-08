package io.github.stewseo.clients.util;

import co.elastic.clients.util.MapBuilder;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static org.assertj.core.api.Assertions.assertThat;

class MapBuilderTest {

    private final MapBuilder<String, BusinessDetails, ObjectBuilder<BusinessDetails>> mapBuilder =
            new MapBuilder<>(BusinessDetails.Builder::new);


    @Test
    void testInstanceOf() {
        assertThat(mapBuilder).isInstanceOf(MapBuilder.class);
    }

    private final BusinessDetails businessDetails = BusinessDetails.of(b -> b.id("idValue"));

    @Test
    void testPut() {

        MapBuilder<String, BusinessDetails, ObjectBuilder<BusinessDetails>> testPut =
                mapBuilder.put("key", businessDetails);

        assertThat(testPut.build().toString()).isEqualTo("{key={\"id\":\"idValue\"}}");
    }

    @Test
    void putAll() {
        mapBuilder.putAll(Map.of("key", businessDetails));
        assertThat(mapBuilder.build().toString()).isEqualTo("{key={\"id\":\"idValue\"}}");
    }

    @Test
    void of() {
        Map<String, BusinessDetails> map = MapBuilder.of("key", businessDetails);
        assertThat(map.get("key")).isEqualTo(businessDetails);
    }

    @Test
    void testOf1() {
        List<BusinessDetails> businessDetails = buildListOfBusinessDetails(2);

        Map<String, BusinessDetails> map = MapBuilder.of("key1", businessDetails.get(0),
                "key2", businessDetails.get(1));

        assertThat(map.get("key1")).isEqualTo(businessDetails.get(0));
        assertThat(map.get("key2")).isEqualTo(businessDetails.get(1));

    }

    @Test
    void testOf2() {
        List<BusinessDetails> businessDetails = buildListOfBusinessDetails(3);

        Map<String, BusinessDetails> map = MapBuilder.of("key1", businessDetails.get(0),
                "key2", businessDetails.get(1),
                "key3", businessDetails.get(2));

        assertThat(map.get("key1")).isEqualTo(businessDetails.get(0));
        assertThat(map.get("key2")).isEqualTo(businessDetails.get(1));
        assertThat(map.get("key3")).isEqualTo(businessDetails.get(2));

    }

    @Test
    void testOf3() {

        List<BusinessDetails> businessDetails = buildListOfBusinessDetails(4);

        Map<String, BusinessDetails> map = MapBuilder.of("key1", businessDetails.get(0),
                "key2", businessDetails.get(1),
                "key3", businessDetails.get(2),
                "key4", businessDetails.get(3)
        );

        assertThat(map.get("key1")).isEqualTo(businessDetails.get(0));
        assertThat(map.get("key2")).isEqualTo(businessDetails.get(1));
        assertThat(map.get("key3")).isEqualTo(businessDetails.get(2));
        assertThat(map.get("key4")).isEqualTo(businessDetails.get(3));

    }

    private List<BusinessDetails> buildListOfBusinessDetails(int size) {

        List<BusinessDetails> list = new ArrayList<>();

        for(int i = 0; i< size; i++) {
            list.add(buildBusinessDetails(ID + i));
        }
        return list;
    }

    private BusinessDetails buildBusinessDetails(String id) {
        return BusinessDetails.of(b -> b.id(id));
    }
}