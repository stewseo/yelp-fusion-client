package io.github.stewseo.clients.json.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.jackson.JacksonJsonpGenerator;
import io.github.stewseo.clients.json.jackson.JacksonJsonpParser;
import io.github.stewseo.clients.json.jackson.JacksonUtils;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonReaderFactory;
import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonGeneratorFactory;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

public class JacksonJsonProvider extends JsonProvider {

    private final JsonFactory jsonFactory;
    private final ParserFactory defaultParserFactory = new ParserFactory(null);
    private final JsonGeneratorFactory defaultGeneratorFactory = new GeneratorFactory(null);

    public JacksonJsonProvider(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    //---------------------------------------------------------------------------------------------
    // Parser

    public JacksonJsonProvider() {
        this(new JsonFactory());
    }

    /**
     * Return the underlying Jackson {@link JsonFactory}.
     */
    public JsonFactory jacksonJsonFactory() {
        return this.jsonFactory;
    }

    @Override
    public JsonParserFactory createParserFactory(Map<String, ?> config) {
        return defaultParserFactory;
    }

    @Override
    public JsonParser createParser(Reader reader) {
        return defaultParserFactory.createParser(reader);
    }

    @Override
    public JsonParser createParser(InputStream in) {
        return defaultParserFactory.createParser(in);
    }

    //---------------------------------------------------------------------------------------------
    // Generator

    @Override
    public JsonGeneratorFactory createGeneratorFactory(Map<String, ?> config) {
        return defaultGeneratorFactory;
    }

    @Override
    public JsonGenerator createGenerator(Writer writer) {
        return defaultGeneratorFactory.createGenerator(writer);
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        return defaultGeneratorFactory.createGenerator(out);
    }

    @Override
    public JsonReader createReader(Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonReader createReader(InputStream in) {
        throw new UnsupportedOperationException();
    }

    //---------------------------------------------------------------------------------------------
    // Unsupported operations

    @Override
    public JsonWriter createWriter(Writer writer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonWriter createWriter(OutputStream out) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonWriterFactory createWriterFactory(Map<String, ?> config) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonReaderFactory createReaderFactory(Map<String, ?> config) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonObjectBuilder createObjectBuilder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonArrayBuilder createArrayBuilder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonBuilderFactory createBuilderFactory(Map<String, ?> config) {
        throw new UnsupportedOperationException();
    }

    private class ParserFactory implements JsonParserFactory {

        private final Map<String, ?> config;

        ParserFactory(Map<String, ?> config) {
            this.config = config == null ? Collections.emptyMap() : config;
        }

        @Override
        public JsonParser createParser(Reader reader) {
            try {
                return new JacksonJsonpParser(jsonFactory.createParser(reader));
            } catch (IOException ioe) {
                throw JacksonUtils.convertException(ioe);
            }
        }

        @Override
        public JsonParser createParser(InputStream in) {
            try {
                return new JacksonJsonpParser(jsonFactory.createParser(in));
            } catch (IOException ioe) {
                throw JacksonUtils.convertException(ioe);
            }
        }

        @Override
        public JsonParser createParser(InputStream in, Charset charset) {
            try {
                return new JacksonJsonpParser(jsonFactory.createParser(new InputStreamReader(in, charset)));
            } catch (IOException ioe) {
                throw JacksonUtils.convertException(ioe);
            }
        }

        /**
         * Not implemented.
         */
        @Override
        public JsonParser createParser(JsonObject obj) {
            return JsonpUtils.provider().createParserFactory(null).createParser(obj);
        }

        /**
         * Not implemented.
         */
        @Override
        public JsonParser createParser(JsonArray array) {
            return JsonpUtils.provider().createParserFactory(null).createParser(array);
        }

        /**
         * Not implemented.
         */
        @Override
        public Map<String, ?> getConfigInUse() {
            return config;
        }
    }

    private class GeneratorFactory implements JsonGeneratorFactory {

        private final Map<String, ?> config;

        GeneratorFactory(Map<String, ?> config) {
            this.config = config == null ? Collections.emptyMap() : config;
        }

        @Override
        public JsonGenerator createGenerator(Writer writer) {
            try {
                return new JacksonJsonpGenerator(jsonFactory.createGenerator(writer));
            } catch (IOException ioe) {
                throw JacksonUtils.convertException(ioe);
            }
        }

        @Override
        public JsonGenerator createGenerator(OutputStream out) {
            try {
                return new JacksonJsonpGenerator(jsonFactory.createGenerator(out));
            } catch (IOException ioe) {
                throw JacksonUtils.convertException(ioe);
            }
        }

        @Override
        public JsonGenerator createGenerator(OutputStream out, Charset charset) {
            try {
                return new JacksonJsonpGenerator(jsonFactory.createGenerator(new OutputStreamWriter(out, charset)));
            } catch (IOException ioe) {
                throw JacksonUtils.convertException(ioe);
            }

        }

        @Override
        public Map<String, ?> getConfigInUse() {
            return config;
        }
    }
}