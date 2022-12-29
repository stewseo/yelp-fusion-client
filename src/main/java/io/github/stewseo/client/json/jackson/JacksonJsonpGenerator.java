package io.github.stewseo.client.json.jackson;

import com.fasterxml.jackson.core.JsonStreamContext;
import jakarta.json.JsonNumber;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonGenerationException;
import jakarta.json.stream.JsonGenerator;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class JacksonJsonpGenerator implements JsonGenerator {

    private final com.fasterxml.jackson.core.JsonGenerator generator;

    public JacksonJsonpGenerator(com.fasterxml.jackson.core.JsonGenerator generator) {
        this.generator = generator;
    }

    public com.fasterxml.jackson.core.JsonGenerator jacksonGenerator() {
        return generator;
    }

    @Override
    public JsonGenerator writeStartObject() {
        try {
            generator.writeStartObject();
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator writeStartObject(String name) {
        try {
            generator.writeFieldName(name);
            generator.writeStartObject();
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator writeStartArray() {
        try {
            generator.writeStartArray();
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator writeStartArray(String name) {
        try {
            generator.writeFieldName(name);
            generator.writeStartArray();
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator writeKey(String name) {
        try {
            generator.writeFieldName(name);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String name, JsonValue value) {
        try {
            generator.writeFieldName(name);
            writeValue(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String name, String value) {
        try {
            generator.writeFieldName(name);
            generator.writeString(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String name, BigInteger value) {
        try {
            generator.writeFieldName(name);
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String name, BigDecimal value) {
        try {
            generator.writeFieldName(name);
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String name, int value) {
        try {
            generator.writeFieldName(name);
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String name, long value) {
        try {
            generator.writeFieldName(name);
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String name, double value) {
        try {
            generator.writeFieldName(name);
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String name, boolean value) {
        try {
            generator.writeFieldName(name);
            generator.writeBoolean(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator writeNull(String name) {
        try {
            generator.writeFieldName(name);
            generator.writeNull();
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator writeEnd() {
        try {
            JsonStreamContext ctx = generator.getOutputContext();
            if (ctx.inObject()) {
                generator.writeEndObject();
            } else if (ctx.inArray()) {
                generator.writeEndArray();
            } else {
                throw new JsonGenerationException("Unexpected context: '" + ctx.typeDesc() + "'");
            }
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(JsonValue value) {
        try {
            writeValue(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(String value) {
        try {
            generator.writeString(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(BigDecimal value) {
        try {
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(BigInteger value) {
        try {
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(int value) {
        try {
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(long value) {
        try {
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(double value) {
        try {
            generator.writeNumber(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator write(boolean value) {
        try {
            generator.writeBoolean(value);
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public JsonGenerator writeNull() {
        try {
            generator.writeNull();
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
        return this;
    }

    @Override
    public void close() {
        try {
            generator.close();
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
    }

    @Override
    public void flush() {
        try {
            generator.flush();
        } catch (IOException e) {
            throw JacksonUtils.convertException(e);
        }
    }

    private void writeValue(JsonValue value) throws IOException {
        switch (value.getValueType()) {
            case OBJECT -> {
                generator.writeStartObject();
                for (Map.Entry<String, JsonValue> entry : value.asJsonObject().entrySet()) {
                    generator.writeFieldName(entry.getKey());
                    writeValue(entry.getValue());
                }
                generator.writeEndObject();
            }
            case ARRAY -> {
                generator.writeStartArray();
                for (JsonValue item : value.asJsonArray()) {
                    writeValue(item);
                }
                generator.writeEndArray();
            }
            case STRING -> generator.writeString(((JsonString) value).getString());
            case FALSE -> generator.writeBoolean(false);
            case TRUE -> generator.writeBoolean(true);
            case NULL -> generator.writeNull();
            case NUMBER -> {
                JsonNumber n = (JsonNumber) value;
                if (n.isIntegral()) {
                    generator.writeNumber(n.longValue());
                } else {
                    generator.writeNumber(n.doubleValue());
                }
            }
        }
    }
}