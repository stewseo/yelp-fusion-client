package io.github.stewseo.yelp.fusion.client.yelpfusion.json;

import co.elastic.clients.elasticsearch.cat.NodesResponse;
import co.elastic.clients.elasticsearch.core.GetSourceResponse;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapperBase;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import jakarta.json.Json;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParsingException;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SerializationTest {

    //        @Test
    //        public void loadAllDeserializers() throws Exception {
    //
    //            ScanResult scan = new ClassGraph()
    //                    .acceptPackages("io.github.stewseo.yelp.fusion.client.yelpfusion.business")
    //                    .enableAnnotationInfo()
    //                    .enableFieldInfo()
    //                    .scan();
    //
    //            ClassInfoList withAnnotation = scan.getClassesWithAnnotation(JsonpDeserializable.class.getName());
    //
    //            assertFalse(withAnnotation.isEmpty(), "No JsonpDeserializable classes");
    //
    //            for (ClassInfo info : withAnnotation) {
    //                Class<?> clazz = Class.forName(info.getName());
    //                JsonpDeserializer<?> deserializer = JsonpMapperBase.findDeserializer(clazz);
    //                assertNotNull(deserializer);
    //
    //                // Deserialize something dummy to resolve lazy deserializers
    //                JsonParser parser = new JacksonJsonpMapper().jsonProvider().createParser(new StringReader("-"));
    //                assertThrows(JsonParsingException.class, () -> deserializer.deserialize(parser, new JacksonJsonpMapper()), info.getName());
    //            }
    //
    //            // Check that all classes that have a _DESERIALIZER field also have the annotation
    //            ClassInfoList withDeserializer = scan.getAllClasses().filter((c) -> c.hasDeclaredField("_DESERIALIZER"));
    //            assertFalse(withDeserializer.isEmpty(), "No classes with a _DESERIALIZER field");
    //
    //// Disabled for now, empty response classes still need a deserializer object
    //// e.g. ExistsIndexTemplateResponse, PingResponse, ExistsResponse, ExistsAliasResponse
    ////
    ////        Set<String> annotationNames = withAnnotation.stream().map(c -> c.getName()).collect(Collectors.toSet());
    ////        Set<String> withFieldNames = withDeserializer.stream().map(c -> c.getName()).collect(Collectors.toSet());
    ////
    ////        withFieldNames.removeAll(annotationNames);
    ////
    ////        assertFalse(
    ////            withFieldNames.size() + " classes with the field but not the annotation: " + withFieldNames,
    ////            !withFieldNames.isEmpty()
    ////        );
    //
    //        }

//        @Test
//        public void testArrayValueBody() {
//
//            NodesResponse nr = NodesResponse.of(_0 -> _0
//                    .valueBody(_1 -> _1.bulkTotalOperations("1"))
//                    .valueBody(_1 -> _1.bulkTotalOperations("2"))
//            );
//
//            checkJsonRoundtrip(nr, "[{\"bulk.total_operations\":\"1\"},{\"bulk.total_operations\":\"2\"}]");
//
//            assertEquals(2, nr.valueBody().size());
//            assertEquals("1", nr.valueBody().get(0).bulkTotalOperations());
//            assertEquals("2", nr.valueBody().get(1).bulkTotalOperations());
//        }

//        @Test
//        public void testGenericValueBody() {
//
//            GetSourceResponse<String> r = GetSourceResponse.of(_0 -> _0
//                    .valueBody("The value")
//            );
//
//            String json = toJson(r);
//            assertEquals("\"The value\"", json);
//
//            JsonpDeserializer<GetSourceResponse<String>> deserializer =
//                    GetSourceResponse.createGetSourceResponseDeserializer(JsonpDeserializer.stringDeserializer());
//
//            r = deserializer.deserialize(mapper.jsonProvider().createParser(new StringReader(json)), mapper);
//
//            assertEquals("The value", r.valueBody());
//
//        }

        @Test
        public void testJsonpValuesToString() {

            assertEquals("foo", JsonpUtils.toString(Json.createValue("foo")));
            assertEquals("42", JsonpUtils.toString(Json.createValue(42)));
            assertEquals("42.1337", JsonpUtils.toString(Json.createValue(42.1337)));
            assertEquals("true", JsonpUtils.toString(JsonValue.TRUE));
            assertEquals("false", JsonpUtils.toString(JsonValue.FALSE));
            assertEquals("null", JsonpUtils.toString(JsonValue.NULL));
            assertEquals("a,b,c", JsonpUtils.toString(Json.createArrayBuilder().add("a").add("b").add("c").build()));

            assertThrows(IllegalArgumentException.class, () -> {
                JsonpUtils.toString(Json.createObjectBuilder().build());
            });
        }
    }

