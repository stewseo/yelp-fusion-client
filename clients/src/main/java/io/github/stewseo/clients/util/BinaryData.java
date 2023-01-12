package io.github.stewseo.clients.util;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.util.NoCopyByteArrayOutputStream;
import jakarta.json.stream.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public interface BinaryData {

    /**
     * Write this data to an output stream.
     */
    void writeTo(OutputStream out) throws IOException;

    /**
     * Return this data as a {@code ByteBuffer}
     */
    ByteBuffer asByteBuffer();

    /**
     * Get the estimated size in bytes of the data.
     *
     * @return the estimated size, or <code>-1</code> if the value cannot be estimated or if the data has already been
     *          consumed.
     */
    long size();

    /**
     * Create a {@code BinaryData} from a value and a JSON mapper. The binary content is the result of serializing
     * {@code value} with {@code mapper}. Returns {@code null} if {@code value} is null.
     */
    static BinaryData of(Object value, JsonpMapper mapper) {
        if (value == null) {
            return null;
        }

        if (value instanceof BinaryData) {
            return (BinaryData)value;
        }

        NoCopyByteArrayOutputStream out = new NoCopyByteArrayOutputStream();
        JsonGenerator generator = mapper.jsonProvider().createGenerator(out);
        mapper.serialize(value, generator);
        generator.close();

        return new ByteArrayBinaryData(out.array(), 0, out.size());
    }

    static BinaryData of(byte[] bytes) {
        return new ByteArrayBinaryData(bytes, 0, bytes.length);
    }

    static BinaryData of(byte[] value, int offset, int length) {
        return new ByteArrayBinaryData(value, offset, length);
    }

    class ByteArrayBinaryData implements BinaryData {

        private final byte[] bytes;
        private final int offset;
        private final int length;

        ByteArrayBinaryData(byte[] bytes, int offset, int length) {
            this.bytes = bytes;
            this.offset = offset;
            this.length = length;
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(bytes, offset, length);
        }

        @Override
        public long size() {
            return length;
        }

        @Override
        public ByteBuffer asByteBuffer() {
            return ByteBuffer.wrap(bytes, offset, length);
        }
    }
}