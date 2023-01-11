package io.github.stewseo.clients.util;

import io.github.stewseo.clients.util.NoCopyByteArrayOutputStream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NoCopyByteArrayOutputStreamTest {

    private final NoCopyByteArrayOutputStream noCopyByteArrayOutputStream = new NoCopyByteArrayOutputStream();

    @Test
    void array() {
        NoCopyByteArrayOutputStream noCopyByteArrayOutputStream = new NoCopyByteArrayOutputStream();
        assertThat(noCopyByteArrayOutputStream.array().length).isEqualTo(32);
    }

    @Test
    void asInputStream() {
        assertThat(noCopyByteArrayOutputStream.asInputStream().read()).isEqualTo(-1);
    }
}