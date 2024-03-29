package io.github.stewseo.clients.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("utils")

public class ObjectBuilderBaseTest extends ObjectBuilderBase {

    private final List<String> list = List.of("value1");

    @UtilTest
    void test_listAdd() {
        assertThat(ObjectBuilderBase._listAdd(list, "value2").toString())
                .isEqualTo("[value1, value2]");
    }

    @UtilTest
    void test_listAddAll() {
        assertThat(ObjectBuilderBase._listAddAll(list, List.of("value2", "value3")).toString())
                .isEqualTo("[value1, value2, value3]");
    }

    private final Map<String, String> map = Map.of("k1", "v1");

    @UtilTest
    void test_mapPut() {
        assertThat(ObjectBuilderBase._mapPut(map, "k2", "v2").toString())
                .isEqualTo("{k1=v1, k2=v2}");
    }

    @UtilTest
    void test_mapPutAll() {
        assertThat(ObjectBuilderBase._mapPutAll(map, Map.of("k2", "v2")).toString())
                .isEqualTo("{k1=v1, k2=v2}");
    }

    @UtilTest
    void test_checkSingleUseEmptyInternalList() {

        ObjectBuilderBase._listAddAll(list, List.of("value1"));
        _checkSingleUse();
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, this::_checkSingleUse);
        assertThat(illegalStateException.getMessage()).isEqualTo("Object builders can only be used once");
    }

    @UtilTest
    void testInternalList() {
        ObjectBuilderBase.InternalList<Object> internalList = new InternalList<>();
        internalList.add("item1");
        _checkSingleUse();
        assertThat(internalList.size()).isEqualTo(1);
        assertThat(internalList.toString()).isEqualTo("[item1]");

        internalList.addAll(List.of("item2", "item3"));

        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, this::_checkSingleUse);

        assertThat(illegalStateException.getMessage()).isEqualTo("Object builders can only be used once");
        assertThat(internalList.size()).isEqualTo(3);
        assertThat(internalList.toString()).isEqualTo("[item1, item2, item3]");
    }
}