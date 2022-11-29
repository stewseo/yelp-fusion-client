package io.github.elasticsearch.client.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ObjectBuilderBase {

    private static final Logger logger = LoggerFactory.getLogger(ObjectBuilderBase.class);
    private boolean _used = false;

    protected void _checkSingleUse() {
        if (this._used) {
            throw new IllegalStateException("Object builders can only be used once");
        }
        this._used = true;
    }

    //----- List utilities

    static final class InternalList<T> extends ArrayList<T> {
        InternalList() {
        }

        InternalList(Collection<? extends T> c) {
            super(c);
        }
    };

    private static <T> List<T> _mutableList(List<T> list) {

        if (list == null) {
            logger.info("returning new list " + list);
            return new InternalList<>();
        } else if (list instanceof InternalList) {
            logger.info("list instanceof InternalMap " + list.size());
            return list;
        } else {
            // Adding to a list we don't own: make a defensive copy, also ensuring it is mutable.
            return new InternalList<>(list);
        }
    }

    @SafeVarargs
    protected static <T> List<T> _listAdd(List<T> list, T value, T... values) {

        list = _mutableList(list);
        list.add(value);
        if (values.length > 0) {
            list.addAll(Arrays.asList(values));
        }
        logger.info("added " + values.length + " number of elements to list. new list: " + list);
        return list;
    }

    protected static <T> List<T> _listAddAll(List<T> list, List<T> values) {
        if (list == null) {
            // Keep the original list to avoid an unnecessary copy.
            // It will be copied if we add more values.
            return Objects.requireNonNull(values);
        } else {
            list = _mutableList(list);
            list.addAll(values);
            logger.info("list: " + list);
            return list;
        }

    }

    //----- Map utilities

    private static final class InternalMap<K, V> extends HashMap<K, V> {
        InternalMap() {
        }

        InternalMap(Map<? extends K, ? extends V> m) {
            super(m);
        }
    }

    private static <K, V> Map<K, V> _mutableMap(Map<K, V> map) {

        if (map == null) {
            logger.info("returning new map " + map);
            return new InternalMap<>();
        } else if (map instanceof InternalMap) {
            logger.info("map instanceof InternalMap " + map.keySet().size());
            return map;
        } else {
            // Adding to a map we don't own: make a defensive copy, also ensuring it is mutable.
            return new InternalMap<>(map);
        }

    }

    protected static <K, V> Map<K, V> _mapPut(Map<K, V> map, K key, V value) {
        map = _mutableMap(map);
        map.put(key, value);
        return map;
    }

    protected static <K, V> Map<K, V> _mapPutAll(Map<K, V> map, Map<K, V> entries) {
        if (map == null) {
            // Keep the original map to avoid an unnecessary copy.
            // It will be copied if we add more entries.
            return Objects.requireNonNull(entries);
        } else {
            map = _mutableMap(map);
            map.putAll(entries);
            logger.info("map after putting items: " + map);
            return map;
        }
    }
}