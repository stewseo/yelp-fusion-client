package io.github.stewseo.clients.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoCopyByteArrayOutputStreamTest {

    private final NoCopyByteArrayOutputStream noCopyByteArrayOutputStream = new NoCopyByteArrayOutputStream();

    @Test
    void testArray() {
        NoCopyByteArrayOutputStream noCopyByteArrayOutputStream = new NoCopyByteArrayOutputStream();
        assertThat(noCopyByteArrayOutputStream.array().length).isEqualTo(32);
    }

    @Test
    void testAsInputStream() {
        assertThat(noCopyByteArrayOutputStream.asInputStream().read()).isEqualTo(-1);

    }
}