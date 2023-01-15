package io.github.stewseo.clients.util;

import io.github.stewseo.clients.yelpfusion._types.QueryParam;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("utils")

class MapBuilderTest {

    private final MapBuilder<String, BusinessDetails, ObjectBuilder<BusinessDetails>> mapBuilder =
            new MapBuilder<>(BusinessDetails.Builder::new);


    @UtilTest
    void testInstantiation() {
        assertThat(mapBuilder).isNotNull();
        assertThat(mapBuilder).isInstanceOf(MapBuilder.class);

        final MapBuilder<String, BusinessDetails, ObjectBuilder<BusinessDetails>> testOf =
                new MapBuilder<>(BusinessDetails.Builder::new);

        assertThat(testOf).isNotNull();
    }

    private final BusinessDetails businessDetails = BusinessDetails.of(b -> b.id("idValue"));

    @UtilTest
    void testPut() {

        MapBuilder<String, BusinessDetails, ObjectBuilder<BusinessDetails>> testPut =
                mapBuilder.put("key", businessDetails);

        assertThat(testPut.build().size()).isEqualTo(1);
        assertThat(testPut.build().containsKey("key")).isTrue();
        assertThat(testPut.build().containsValue(businessDetails)).isTrue();
    }

    @UtilTest
    void putAll() {

        MapBuilder<String, BusinessDetails, ObjectBuilder<BusinessDetails>> testPutAll =
                mapBuilder.putAll(Map.of("key", businessDetails));

        assertThat(testPutAll.build().size()).isEqualTo(1);
        assertThat(testPutAll.build().containsKey("key")).isTrue();
        assertThat(testPutAll.build().containsValue(businessDetails)).isTrue();
    }

    @UtilTest
    void testMapBuilderOfOneElement() {

        Map<String, BusinessDetails> testMapBuilder = MapBuilder.of("key", businessDetails);
        assertThat(testMapBuilder.size()).isEqualTo(1);
        assertThat(testMapBuilder.get("key")).isEqualTo(businessDetails);
    }

    @UtilTest
    void testMapBuilderOfTwoElements() {
        List<BusinessDetails> businessDetails = generateListOfBusinessDetails(2);

        Map<String, BusinessDetails> map = MapBuilder.of("key1", businessDetails.get(0),
                "key2", businessDetails.get(1));

        assertThat(map.get("key1")).isEqualTo(businessDetails.get(0));
        assertThat(map.get("key2")).isEqualTo(businessDetails.get(1));

    }

    @UtilTest
    void testMapBuilderOfThreeElements() {
        List<BusinessDetails> businessDetails = generateListOfBusinessDetails(3);

        Map<String, BusinessDetails> map = MapBuilder.of("key1", businessDetails.get(0),
                "key2", businessDetails.get(1),
                "key3", businessDetails.get(2));

        assertThat(map.get("key1")).isEqualTo(businessDetails.get(0));
        assertThat(map.get("key2")).isEqualTo(businessDetails.get(1));
        assertThat(map.get("key3")).isEqualTo(businessDetails.get(2));

    }

    @UtilTest
    void testMapBuilderOfFourElements() {

        List<BusinessDetails> businessDetails = generateListOfBusinessDetails(4);

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

    private List<BusinessDetails> generateListOfBusinessDetails(int size) {

        List<BusinessDetails> list = new ArrayList<>();

        for(int i = 0; i< size; i++) {
            list.add(generateBusinessDetails(QueryParam.ID.name() + i));
        }
        return list;
    }

    private BusinessDetails generateBusinessDetails(String id) {
        return BusinessDetails.of(b -> b.id(id));
    }


}