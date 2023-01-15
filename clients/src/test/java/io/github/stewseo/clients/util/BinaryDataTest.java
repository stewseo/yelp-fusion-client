package io.github.stewseo.clients.util;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.util.BinaryData;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BinaryDataTest {


    @UtilTest
    void size() {
        BinaryData binaryData = BinaryData.of(testBa);
        assertThat(binaryData.size()).isEqualTo(16L);

        ByteBuffer byteBuffer = binaryData.asByteBuffer();
        assertThat(byteBuffer.toString()).isEqualTo("java.nio.HeapByteBuffer[pos=0 lim=16 cap=16]");
    }

    byte[] testBa = "testBinaryDataOf".getBytes(StandardCharsets.UTF_8);

    @UtilTest
    void of() {
        BinaryData binaryData = BinaryData.of(testBa);
        assertThat(binaryData).isNotNull();
    }

    @UtilTest
    void testOf() {
        BinaryData binaryData = BinaryData.of(testBa, 2, 1);
        assertThat(binaryData).isNotNull();
    }

    @UtilTest
    void testOf1() {
        BinaryData binaryData = BinaryData.of(testBa, new JacksonJsonpMapper());
        assertThat(binaryData).isNotNull();
    }
}