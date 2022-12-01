<<<<<<<< HEAD:yelp-fusion-client/src/test/java/io/github/yelp/fusion/client/JsonpUtilsTest.java
package org.example.yelp.fusion.client.json;
========
package io.github.yelp.fusion.client.json;
>>>>>>>> maven-publish:yelp-fusion-client/src/test/java/io/github/yelp/fusion/client/json/JsonpUtilsTest.java

import io.github.yelp.fusion.client.util.AllowForbiddenApis;
import jakarta.json.JsonException;
import jakarta.json.spi.JsonProvider;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonpUtilsTest {

    @Test
    @AllowForbiddenApis("Testing JsonpUtil.provider()")
    public void testProviderLoading() {
        // See https://github.com/elastic/elasticsearch-java/issues/163

        // Create an empty non-delegating classloader and set it as the context classloader. It simulates a
        // plugin system that doesn't set the context classloader to the plugins classloader.
        ClassLoader emptyLoader = new ClassLoader() {
            @Override
            public Enumeration<URL> getResources(String name) {
                return Collections.emptyEnumeration();
            }
        };

        ClassLoader savedLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(emptyLoader);

            assertThrows(JsonException.class, () -> assertNotNull(JsonProvider.provider()));

            assertNotNull(JsonpUtils.provider());

        } finally {
            Thread.currentThread().setContextClassLoader(savedLoader);
        }
    }

}
