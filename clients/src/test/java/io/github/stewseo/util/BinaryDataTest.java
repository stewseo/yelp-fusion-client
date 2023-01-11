package io.github.stewseo.util;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.util.BinaryData;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BinaryDataTest {


    @Test
    void size() {
        BinaryData binaryData = BinaryData.of(testBa);
        assertThat(binaryData.size()).isNotNull();
        ByteBuffer byteBuffer = binaryData.asByteBuffer();
        assertThat(byteBuffer.toString()).isEqualTo("java.nio.HeapByteBuffer[pos=0 lim=16 cap=16]");
    }

    byte[] testBa = "testBinaryDataOf".getBytes(StandardCharsets.UTF_8);

    @Test
    void of() {
        BinaryData binaryData = BinaryData.of(testBa);
        assertThat(binaryData).isNotNull();
    }

    @Test
    void testOf() {
        BinaryData binaryData = BinaryData.of(testBa, 2, 1);
        assertThat(binaryData).isNotNull();
    }

    @Test
    void testOf1() {
        BinaryData binaryData = BinaryData.of(testBa, new JacksonJsonpMapper());
        assertThat(binaryData).isNotNull();
    }
}