package io.github.stewseo.clients.util;

import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import org.junit.jupiter.api.Tag;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("utils")

class ListBuilderTest {

    private final ListBuilder<BusinessDetails, ObjectBuilder<BusinessDetails>> lb =
            new ListBuilder<>(BusinessDetails.Builder::new);

    @UtilTest
    void testInstantiation() {

        assertThat(lb).isNotNull();

        final ListBuilder<BusinessDetails, ObjectBuilder<BusinessDetails>> testOf =
                ListBuilder.of(BusinessDetails.Builder::new);

        assertThat(testOf).isNotNull();
    }

    @UtilTest
    void add() {
        lb.add(BusinessDetails.of(b -> b.id(ID)));
        List<BusinessDetails> businessDetails = lb.build();
        String expectedAdd = "[{\"id\":\"id\"}]";
        assertThat(businessDetails).isNotNull();

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            assertNull(businessDetails.get(1));
        });
        assertThat(exception).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @UtilTest
    void addAll() {
        lb.addAll(List.of(
                BusinessDetails.of(b -> b.id(ID)),
                BusinessDetails.of(b -> b.id(ID+1)))
        );
        List<BusinessDetails> businessDetails = lb.build();
        assertThat(businessDetails).isNotNull();

    }
}