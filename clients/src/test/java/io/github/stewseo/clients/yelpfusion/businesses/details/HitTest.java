package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.NamedDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static io.github.stewseo.clients.yelpfusion._types.TestData.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.TestData.COORDINATES;
import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.IMAGE_URL;
import static io.github.stewseo.clients.yelpfusion._types.TestData.NAME;
import static io.github.stewseo.clients.yelpfusion._types.TestData.PHOTOS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.PRICE_STRING;
import static org.assertj.core.api.Assertions.assertThat;

class HitTest extends ModelTestCase<Hit<BusinessDetails>> {

    private final BusinessDetails businessDetails = BusinessDetails.of(b -> b
            .id(ID)
            .name(NAME)
            .categories(CATEGORY)
            .coordinates(COORDINATES)
            .price(PRICE_STRING)
            .photos(PHOTOS)
            .image_url(IMAGE_URL));

    private final JsonpSerializer<BusinessDetails> tDocumentSerializer = JacksonJsonpMapper.findSerializer(businessDetails);

    @Override
    public Hit<BusinessDetails> of() {
        return Hit.of(hit -> hit
                .tDocumentSerializer(tDocumentSerializer)
                .source(businessDetails)
        );
    }
    private final Hit<BusinessDetails> hit = of();

    @Test
    public void testBuilder() {

        Hit.Builder<BusinessDetails> builder = new Hit.Builder<BusinessDetails>()
                .source(businessDetails)
                .tDocumentSerializer(tDocumentSerializer);
    }

    @Test
    public void testOf() {
        assertThat(hit.source()).isEqualTo(businessDetails);
        assertThat(hit.tDocumentSerializer()).isEqualTo(tDocumentSerializer);
    }

    private final JsonGenerator generator = generator();

    @Test
    void tDocumentSerializer() {
        assertThat(hit.tDocumentSerializer()).isNotNull();
        hit.tDocumentSerializer().serialize(businessDetails, generator, mapper);
    }

    @Test
    void testSource() {
        assertThat(hit.source()).isNotNull();
        assertThat(hit.source()).isInstanceOf(BusinessDetails.class);
        assertThat(hit.source().categories()).isEqualTo(List.of(CATEGORY));
        assertThat(hit.source().coordinates()).isEqualTo(COORDINATES);
        assertThat(hit.source().price()).isEqualTo(PRICE_STRING);
    }

    private final String expected = "" +
            "{\"_source\":{\"id\":\"id\",\"name\":\"name\"," +
            "\"image_url\":\"imageUrlValue\",\"price\":\"$\"," +
            "\"coordinates\":{\"latitude\":37.7829,\"longitude\":-122.4189}," +
            "\"photos\":[\"photos\"],\"categories\":[{\"alias\":\"catAlias\"}]}}";

    @Test
    void testToString() {
        assertThat(hit.toString()).isEqualTo(expected);
    }

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
        assertThat(hit.toString()).isNotNull();
    }

    @Test
    public void testDeserializer() {
        assertThat(Hit._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

        JsonpDeserializer<Hit<BusinessDetails>> jsonpDeserializer = JsonpDeserializer.lazy(() ->
                Hit.createHitDeserializer(new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search._types.TDocument")));

    }

    public JsonParser parser() {
        return parser(hit);
    }

    @Test
    public void testDeserialize() {

        JsonpDeserializer<Hit<BusinessDetails>> jsonpDeserializer = JsonpDeserializer.lazy(() ->
                Hit.createHitDeserializer(new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search._types.TDocument")));

        JsonParser parser = parser();
        assertThat(parser).isNotNull();
        assertThat(parser.hasNext()).isTrue();

        Hit<BusinessDetails> deserializedHit = jsonpDeserializer.deserialize(parser, mapper);

        assertThat(deserializedHit).isNotNull();
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
    };

}