package com.bipinet.pointsdistance;

import com.bipinet.pointsdistance.main.MainApp;
import com.bipinet.pointsdistance.service.DataReaderServiceImpl;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;


public class TestDataReader {

    @Test
    public void testBinaryFileIsCorrect() throws IOException {
        short[] expectedPoints = new short[]{-715, 22165, 761, -23591, -194, 6014};
        short[] actualPoints = new DataReaderServiceImpl().readPoints(
                "src/test/resources/points", MainApp.NUMBER_OF_POINTS);
        for (int i = 0; i < expectedPoints.length; i += 2) {
            int xIndex = i;
            int yIndex = i + 1;
            assertEquals(String.format("x of point %d must be %d", xIndex, expectedPoints[xIndex]),
                    expectedPoints[xIndex], actualPoints[xIndex]);
            assertEquals(String.format("y of point %d must be %d", yIndex, expectedPoints[yIndex]),
                    expectedPoints[yIndex], actualPoints[yIndex]);
        }
    }
}
