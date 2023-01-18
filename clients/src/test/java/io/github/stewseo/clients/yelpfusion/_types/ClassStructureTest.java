package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.util.MissingRequiredPropertyException;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import io.github.stewseo.clients.yelpfusion.events.search.SearchEventsRequest;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassStructureTest extends ModelJsonTestCase {

    /**
     * Tests optional and required primitive types
     */
    @TypeTest
    public void testPrimitiveTypes() throws Exception {
        // Optional primitive should use the boxed type and be @Nullable
        {

            Method searchTransactionTransactionType = SearchTransactionRequest.class.getMethod("transaction_type");
            assertThat(searchTransactionTransactionType.getAnnotation(Nullable.class)).isNull();
            assertThat(searchTransactionTransactionType.getReturnType()).isEqualTo(String.class);
        }

        // Builder setter for optional primitive should use the boxed type and be @Nullable
        {
            Method searchTransactionBuilderTransactionType = SearchTransactionRequest.Builder.class.getMethod("transaction_type", String.class);
            assertThat(Arrays.stream(searchTransactionBuilderTransactionType.getParameterAnnotations()[0])
                    .anyMatch(a -> a.annotationType() == Nullable.class))
                    .isTrue();
        }

        // Required primitive should use the primitive type and not be @Nullable
        {
            Method eventM = Event.class.getMethod("latitude");
            assertThat(eventM.getAnnotation(Nullable.class)).isNotNull();
            assertThat(eventM.getReturnType()).isEqualTo(Double.class);
        }

        // Builder setter for required primitive should use the primitive type and not be @Nullable
        {
            Method termQueryParamBuilderValue = TermQueryParameter.Builder.class.getMethod("term", TermEnum.class);
            assertThat(Arrays.stream(termQueryParamBuilderValue.getParameterAnnotations()[0])
                    .anyMatch(a -> a.annotationType() == Nullable.class)).isFalse();
        }

        // Not setting a required primitive should throw an exception
        {
            assertThrows(MissingRequiredPropertyException.class, () -> TermQueryParameter.of(b -> b));
        }
    }

    @TypeTest
    public void testListSetters() {

        Category catA = Category.of(c -> c.alias("categoryAliasA"));
        Category catB = Category.of(c -> c.alias("categoryAliasB"));

        List<Category> categories = Arrays.asList(catA, catB);

        {
            // Appending doesn't modify the original collection
            SearchBusinessesResult searchBusinessesResult = SearchBusinessesResult.of(b -> b
                    .categories(categories)
                    .categories(catA)
                    .categories(catB)
            );
            assertEquals(Arrays.asList(catA, catB), categories);
            assertEquals(Arrays.asList(catA, catB, catA, catB), searchBusinessesResult.categories());
        }

        {
            List<String> cats = Arrays.asList("a", "b");
            // Appending doesn't modify the original collection (appending the same list twice)
            SearchEventsRequest search = SearchEventsRequest.of(b -> b
                    .categories(cats)
                    .categories(cats)
            );
            assertEquals(Arrays.asList("a", "b"), cats);
            assertEquals(Arrays.asList("a", "b", "a", "b"), search.categories());
        }


        {
            // List cannot be null
            List<String> nullFields = null;
            assertThrows(NullPointerException.class, () -> {
                SearchEventsRequest.of(b -> b
                        .categories(nullFields)
                );
            });
        }

        {
            // Combine value and builder
            LocationQueryParameter locationQueryParameter = LocationQueryParameter.of(l -> l.location(LocationEnum.City));

            QueryParameter qp = QueryParameter.of(b -> b
                    .location(locationQueryParameter));

//            assertEquals(Arrays.asList("a", "b", "c"), search.docvalueFields().stream()
//                    .map(FieldAndFormat::field).collect(Collectors.toList()));
        }
    }

    private void assertAncestorCount(int count, Object obj) {
        Class<?> clazz = obj.getClass();
        while(count-- >= 0) {
            clazz = clazz.getSuperclass();
        }
        assertEquals(Object.class, clazz);
    }

    private void checkSingleBuilderUse(int count, ObjectBuilder<?> builder) {
        assertAncestorCount(count, builder);

        // Building once should succeed
        builder.build();

        // Building twice should fail
        IllegalStateException ex = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Object builders can only be used once", ex.getMessage());

        // One more for good measure
        ex = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Object builders can only be used once", ex.getMessage());
    }
}