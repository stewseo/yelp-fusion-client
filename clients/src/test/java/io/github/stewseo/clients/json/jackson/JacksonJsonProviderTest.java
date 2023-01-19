package io.github.stewseo.clients.json.jackson;

import io.github.stewseo.clients.json.JsonTest;
import jakarta.json.JsonObject;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGeneratorFactory;
import jakarta.json.stream.JsonParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JacksonJsonProviderTest {

    JsonProvider jsonProvider  = JacksonJsonProvider.provider();

    JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();

    @JsonTest
    void jacksonJsonFactory() {

        assertThat(JacksonJsonProvider.provider()).isNotNull();
    }


    @JsonTest
    void createParserFactory() {
        JsonParserFactory jsonParserFactory  = jacksonJsonProvider.createParserFactory(Map.of("k", "v"));
        assertThat(jsonParserFactory.createParser(JsonObject.EMPTY_JSON_OBJECT)).isNotNull();
    }

    @JsonTest
    void createParserTest() throws FileNotFoundException, UnsupportedEncodingException {
        FileNotFoundException fileNotFoundException =
                assertThrows(FileNotFoundException.class, () -> jacksonJsonProvider
                .createParser(new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8)));

        assertThat(fileNotFoundException).isNotNull();

    }

    @JsonTest
    void testCreateParser() throws FileNotFoundException {
        FileNotFoundException fileNotFoundException =
                assertThrows(FileNotFoundException.class, () -> jacksonJsonProvider.createParser(new FileReader("file.txt")));

        assertThat(fileNotFoundException.getMessage()).startsWith("file.txt (The system cannot");
    }

    @JsonTest
    void testCreateGenerator() {
        JsonGeneratorFactory jsonGeneratorFactory = jacksonJsonProvider.createGeneratorFactory(Map.of("k", "v"));
        assertThat(jsonGeneratorFactory.getConfigInUse()).isNotNull();
        assertThat(jsonGeneratorFactory.createGenerator(new StringWriter())).isNotNull();
        assertThat(jsonGeneratorFactory.createGenerator(new ByteArrayOutputStream())).isNotNull();
        assertThat(jsonGeneratorFactory.createGenerator(new ByteArrayOutputStream(), StandardCharsets.UTF_8)).isNotNull();
    }


    @JsonTest
    void testCreateReader() throws FileNotFoundException {

        FileNotFoundException fileNotFoundException =
                assertThrows(FileNotFoundException.class, () ->
                        jacksonJsonProvider.createReader(new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8)));

        assertThat(fileNotFoundException).isExactlyInstanceOf(FileNotFoundException.class);


        fileNotFoundException =
                assertThrows(FileNotFoundException.class, () ->
                        jacksonJsonProvider.createReader(new FileReader("file.txt")));

        assertThat(fileNotFoundException).isExactlyInstanceOf(FileNotFoundException.class);
    }

    @JsonTest
    void createWriter() {
        jacksonJsonProvider.jacksonJsonFactory();
    }

    @JsonTest
    void testCreateWriter() {

        UnsupportedOperationException unsupportedOperationException =
                assertThrows(UnsupportedOperationException.class, () -> jacksonJsonProvider.createWriter(new StringWriter()));

        UnsupportedOperationException unsupportedExcept =
                assertThrows(UnsupportedOperationException.class, () -> jacksonJsonProvider.createWriter(new ByteArrayOutputStream()));

    }

    @JsonTest
    void createWriterFactory() {
        UnsupportedOperationException unsupportedOperationException = assertThrows(UnsupportedOperationException.class,
                () -> jacksonJsonProvider.createWriterFactory(Map.of("k1", "v1")));

        assertThat(unsupportedOperationException).isNotNull();
    }

    @JsonTest
    void createReaderFactory() {

        UnsupportedOperationException unsupportedOperationException = assertThrows(UnsupportedOperationException.class, () ->
               jacksonJsonProvider.createReaderFactory(Map.of("k1", "v1"))
       );
        assertThat(unsupportedOperationException).isNotNull();
    }

    @JsonTest
    void createObjectBuilder() {
        UnsupportedOperationException unsupportedOperationException = assertThrows(UnsupportedOperationException.class, () ->
                jacksonJsonProvider.createObjectBuilder(JsonObject.EMPTY_JSON_OBJECT)
        );

        assertThat(unsupportedOperationException).isNotNull();
    }

    @JsonTest
    void createArrayBuilder() {
        UnsupportedOperationException unsupportedOperationException = assertThrows(UnsupportedOperationException.class, () ->
                jacksonJsonProvider.createArrayBuilder(List.of("value1"))
        );

        assertThat(unsupportedOperationException).isNotNull();

    }

    @JsonTest
    void createBuilderFactory() {
        UnsupportedOperationException unsupportedEncodingException =  assertThrows(UnsupportedOperationException.class, () ->
                jacksonJsonProvider.createBuilderFactory(Map.of("k", "v"))
        );
        assertThat(unsupportedEncodingException).isNotNull();
    }
}