package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import org.assertj.core.api.Assertions;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.SEARCH_BUSINESSES_REQUEST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class FieldValueTest extends ModelJsonTestCase {

    @TypeTest
    void testFieldValueLong() {
        FieldValue fieldValue = FieldValue.of(1L);
        assertThat(fieldValue).hasNoNullFieldsOrProperties();
        fieldValue = FieldValue.of(f -> f.longValue(Long.parseLong("111")));
        assertThat(fieldValue.longValue()).isExactlyInstanceOf(Long.class);
        fieldValue = checkJsonRoundtrip(fieldValue, "111");
    }

    @TypeTest
    void testFieldValueDouble() {
        FieldValue fieldValue = FieldValue.of(1.0);
        fieldValue = FieldValue.of(f -> f.doubleValue(1.0));
        Assertions.assertThat(fieldValue).hasNoNullFieldsOrProperties();
        Assertions.assertThat(fieldValue.doubleValue()).isExactlyInstanceOf(Double.class);
        fieldValue = checkJsonRoundtrip(fieldValue, "1.0");
    }

    @TypeTest
    void testFieldValueBoolean() {
        FieldValue fieldValue = FieldValue.of(f -> f.booleanValue(true));
        Assertions.assertThat(fieldValue).hasNoNullFieldsOrProperties();
        Assertions.assertThat(fieldValue.booleanValue()).isExactlyInstanceOf(Boolean.class);
        fieldValue = checkJsonRoundtrip(fieldValue, "true");
    }

    @TypeTest
    void testFieldValueString() {
        FieldValue fieldValue = FieldValue.of("stringValue");
        assertThat(fieldValue).hasNoNullFieldsOrProperties();
        fieldValue = checkJsonRoundtrip(fieldValue, "\"stringValue\"");
        assertThat(fieldValue.stringValue()).isNotNull();

    }

    @TypeTest
    void testFieldValueJsonData() {

        FieldValue fieldValue = FieldValue.of(f -> f.anyValue(JsonData.of(SEARCH_BUSINESSES_REQUEST)));
        assertThat(fieldValue).hasNoNullFieldsOrProperties();
        assertThat(fieldValue.anyValue()).isInstanceOf(JsonData.class);
        fieldValue = checkJsonRoundtrip(fieldValue, "{\"location\":{\"address1\":\"addressOneValue\",\"city\":\"cityValue\",\"country\":\"countryValue\",\"state\":\"stateValue\"},\"id\":\"id\",\"alias\":\"alias\",\"name\":\"name\",\"phone\":\"phoneValue\"," +
                "\"term\":[\"term\"],\"categories\":{\"alias\":\"alias\"}}");
    }


}