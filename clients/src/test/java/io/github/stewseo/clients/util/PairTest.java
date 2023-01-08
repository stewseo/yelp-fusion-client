package io.github.stewseo.clients.util;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch.watcher.Condition;
import co.elastic.clients.elasticsearch.watcher.ConditionOp;
import io.github.stewseo.clients.json.testcases.TestJson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest extends TestJson {

    @Test
    public void testCondition() {

        String json = "{\"compare\":{\"foo\":{\"eq\":\"bar\"}}}";

        Condition c = Condition.of(b -> b
                .compare(co.elastic.clients.util.NamedValue.of("foo",
                        co.elastic.clients.util.Pair.of(ConditionOp.Eq, FieldValue.of("bar"))))
        );
        
        assertEquals("foo", c.compare().name());
        assertEquals(ConditionOp.Eq, c.compare().value().key());
        assertEquals("bar", c.compare().value().value().stringValue());
    }

    @Test
    void of() {
    }

    @Test
    void deserializer() {
    }

    @Test
    void key() {
    }

    @Test
    void value() {
    }
}