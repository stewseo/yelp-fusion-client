package io.github.stewseo.clients.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class NoCopyByteArrayOutputStream extends ByteArrayOutputStream {

    public NoCopyByteArrayOutputStream() {
    }

    public NoCopyByteArrayOutputStream(int size) {
        super(size);
    }

    /**
     * Get the underlying buffer. Data was added to this buffer up to {@code size()}. Note that calling this method
     * again may return a different result if additional data was inserted and the buffer had to grow.
     */
    public byte[] array() {
        return this.buf;
    }

    /**
     * Get an {@code InputStream} view on this object, based on the current buffer and size.
     */
    public ByteArrayInputStream asInputStream() {
        return new ByteArrayInputStream(this.buf, 0, this.count);
    }
}
