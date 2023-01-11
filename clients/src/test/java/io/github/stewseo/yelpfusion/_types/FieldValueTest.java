package io.github.stewseo.yelpfusion._types;

import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.yelpfusion._types.FieldValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FieldValueTest {

    @Test
    void testFieldValueLong() {
        FieldValue fieldValueLong = FieldValue.of(1L);
        assertThat(fieldValueLong.longValue()).isInstanceOf(Long.class);
    }

    @Test
    void testFieldValueDouble() {
        FieldValue fieldValueDouble = FieldValue.of(1.0);
        assertThat(fieldValueDouble.doubleValue()).isInstanceOf(Double.class);
    }

    @Test
    void testFieldValueBoolean() {
        FieldValue fieldValueBoolean = FieldValue.of(false);
        assertThat(fieldValueBoolean.booleanValue()).isInstanceOf(Boolean.class);
    }

    @Test
    void testFieldValueString() {
        FieldValue fieldValueBoolean = FieldValue.of(false);
        assertThat(fieldValueBoolean.booleanValue()).isInstanceOf(Boolean.class);
    }

    @Test
    void testFieldValueJsonData() {

        JsonData data = JsonData.of("test");
        FieldValue fieldValueJsonData = FieldValue.of(data);
        assertThat(fieldValueJsonData.anyValue()).isInstanceOf(JsonData.class);
    }

}