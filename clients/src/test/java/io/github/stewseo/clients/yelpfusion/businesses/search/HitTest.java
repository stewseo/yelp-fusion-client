package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.NamedDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.EXPECTED_BUSINESS_DETAILS_RESULT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.EXPECTED_CATEGORIES;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.HIT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.PRICE_STRING;
import static org.assertj.core.api.Assertions.assertThat;

class HitTest extends ModelTestCase<Hit<BusinessDetails>> {

    private final JsonpSerializer<BusinessDetails> tDocumentSerializer = JacksonJsonpMapper.findSerializer(EXPECTED_BUSINESS_DETAILS_RESULT);

    @Override
    public Hit<BusinessDetails> of() {
        return Hit.of(hit -> hit
                .source(EXPECTED_BUSINESS_DETAILS_RESULT)
                .tDocumentSerializer(tDocumentSerializer)
        );
    }
    private final Hit<BusinessDetails> hit = of();

    @Test
    public void testBuilder() {

        Hit.Builder<BusinessDetails> builder = new Hit.Builder<BusinessDetails>()
                .source(EXPECTED_BUSINESS_DETAILS_RESULT)
                .tDocumentSerializer(tDocumentSerializer);
        assertThat(builder).isNotNull();
    }

    @Test
    public void testOf() {
        assertThat(hit.source()).isEqualTo(EXPECTED_BUSINESS_DETAILS_RESULT);
        assertThat(hit.tDocumentSerializer()).isEqualTo(tDocumentSerializer);
    }

    private final JsonGenerator generator = generator();

    @Test
    void tDocumentSerializer() {
        assertThat(hit.tDocumentSerializer()).isNotNull();
        hit.tDocumentSerializer().serialize(EXPECTED_BUSINESS_DETAILS_RESULT, generator, mapper);
    }

    @Test
    void testSource() {
        assertThat(hit.source()).isNotNull();
        assertThat(hit.source()).isInstanceOf(BusinessDetails.class);
        assertThat(hit.source().categories().toString()).isEqualTo(EXPECTED_CATEGORIES);
        assertThat(hit.source().center()).isEqualTo(CENTER);
        assertThat(hit.source().price()).isEqualTo(PRICE_STRING);
    }

    private final String expected = "{\"source\":{\"id\":\"id\",\"alias\":\"alias\",\"name\":\"name\",\"image_url\":\"imageUrlValue\",\"url\":\"url\",\"phone\":\"phoneValue\",\"price\":\"$\",\"display_phone\":\"display_phone\",\"is_claimed\":false,\"is_closed\":false,\"rating\":4.5,\"review_count\":1,\"location\":{\"address1\":\"addressOneValue\",\"city\":\"cityValue\",\"country\":\"countryValue\",\"state\":\"stateValue\"},\"center\":{\"latitude\":37.7829,\"longitude\":-122.4189},\"transactions\":[\"transactionValue\"],\"messaging\":{\"use_case_text\":\"use_case_text\"},\"photos\":[\"photos\",\"photos\"],\"hours\":[{\"hours_type\":\"hoursType\"},{\"hours_type\":\"hoursTypeValue\"},{\"hours_type\":\"hoursType\"}],\"categories\":[{\"alias\":\"alias\"},{\"alias\":\"alias\"},{\"alias\":\"alias\"}]}}";
    @Test
    public void testSerialize() {
        hit.serialize(generator, mapper);
        assertThat(hit.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        hit.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(hit.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserializer() {
        assertThat(Hit._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

        JsonpDeserializer<Hit<BusinessDetails>> jsonpDeserializer = JsonpDeserializer.lazy(() ->
                Hit.createHitDeserializer(new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.searchBusinesses._types.TDocument")));
        assertThat(jsonpDeserializer).isNotNull();
    }

    public JsonParser parser() {
        return parser(hit);
    }

    @Test
    public void testDeserialize() {

        JsonpDeserializer<Hit<BusinessDetails>> jsonpDeserializer = JsonpDeserializer.lazy(() ->
                Hit.createHitDeserializer(new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.searchBusinesses._types.TDocument")));

        JsonParser parser = parser();
        assertThat(parser).isNotNull();
        assertThat(parser.hasNext()).isTrue();
        assertThat(jsonpDeserializer).isInstanceOf(JsonpDeserializer.class);
    }

    @Test
    void createHitDeserializer() {

        JsonpDeserializer<BusinessDetails> tDocumentDeserializer = BusinessDetails._DESERIALIZER;

        JsonpDeserializer<Hit<BusinessDetails>> hitDeserializer = Hit.createHitDeserializer(tDocumentDeserializer);

        JsonpDeserializer<Hit<BusinessDetails>> testHitDeserializer = createTestHitDeserializer(tDocumentDeserializer);

        assertThat(testHitDeserializer.getClass()).isEqualTo(hitDeserializer.getClass());
    }

    @Test
    void setupHitDeserializer() {
        JsonpDeserializer<BusinessDetails> tDocumentDeserializer = BusinessDetails._DESERIALIZER;
        Hit.createHitDeserializer(tDocumentDeserializer);
    }

    private <TDocument> JsonpDeserializer<Hit<TDocument>> createTestHitDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Hit.Builder<TDocument>>) Hit.Builder::new,
                op -> Hit.setupHitDeserializer(op, tDocumentDeserializer));
    }

}