package io.github.stewseo.clients.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ObjectBuilderBaseTest {

    private final List<String> list = List.of("value1");
    private final ObjectBuilderBase ob = new ObjectBuilderBase();

    @Test
    void _listAdd() {
        ob._checkSingleUse();
        List<String> l = ObjectBuilderBase._listAdd(list, "value2");
        assertThat(l.toString()).isEqualTo("[value1, value2]");
    }

    @Test
    void _listAddAll() {
        ob._checkSingleUse();
        List<String> l = ObjectBuilderBase._listAddAll(list, List.of("value2", "value3"));
        assertThat(l.toString()).isEqualTo("[value1, value2, value3]");
    }

    private final Map<String, String> map = Map.of("k1", "v1");

    @Test
    void _mapPut() {
        Map<String, String> m = ObjectBuilderBase._mapPut(map, "k2", "v2");
        assertThat(m.toString()).isEqualTo("{k1=v1, k2=v2}");
    }


    @Test
    void _mapPutAll() {
        Map<String, String> m = ObjectBuilderBase._mapPutAll(map, Map.of("k2", "v2"));
        assertThat(m.toString()).isEqualTo("{k1=v1, k2=v2}");
    }
}