package com.bipinet.pointsdistance.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;


public class DataReaderServiceImpl implements DataReaderService {

    @Override
    public short[] readPoints(String fileName, int numberOfPoints) throws IOException {
        short[] result = new short[numberOfPoints];
        try (FileInputStream stream = new FileInputStream(fileName)) {
            FileChannel inChannel = stream.getChannel();

            ByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

            buffer.order(ByteOrder.BIG_ENDIAN);
            ShortBuffer intBuffer = buffer.asShortBuffer();
            intBuffer.get(result);
        }
        return result;
    }
}
