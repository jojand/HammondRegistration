package com.jojand.hamreg;

import com.jojand.hamreg.logsum.LogSum;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LogSumUnitTest {
    @Test
    public void logSumWorks() {
        assertEquals(LogSum.logSum(2, 2), 3, 0);
        assertEquals(LogSum.logSum(4, 4), 5, 0);
    }

    @Test
    public void parseText_twoCompleteLines() {
        LogSum logSum = new LogSum();

        String testString = "012345678\n" +
                        "000000000\n";

        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0});
        testData.add(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        logSum.parseText(testString);
        ArrayList<Double[]> resultData = logSum.getData();
        assertArrayEquals(testData.toArray(), resultData.toArray());
    }

    @Test
    public void parseText_incompleteLine() {
        LogSum logSum = new LogSum();

        String testString = "01234567\n" +
                        "000000000\n";

        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 0.0});
        testData.add(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        logSum.parseText(testString);
        ArrayList<Double[]> resultData = logSum.getData();
        assertArrayEquals(testData.toArray(), resultData.toArray());
    }

    @Test
    public void parseText_wrongChar() {
        LogSum logSum = new LogSum();

        String testString = "01234567a\n" +
                        "000000000\n";

        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 0.0});
        testData.add(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        logSum.parseText(testString);
        ArrayList<Double[]> resultData = logSum.getData();
        assertArrayEquals(testData.toArray(), resultData.toArray());
    }

    @Test
    public void parseText_emptyLine() {
        LogSum logSum = new LogSum();

        String testString = "\n" +
                        "100000008\n";

        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        testData.add(new Double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 8.0});
        logSum.parseText(testString);
        ArrayList<Double[]> resultData = logSum.getData();
        assertArrayEquals(testData.toArray(), resultData.toArray());
    }

    @Test
    public void parseText_bigVal() {
        LogSum logSum = new LogSum();

        String testString = "\n" +
                        "900000009\n";

        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        testData.add(new Double[]{8.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 8.0});
        logSum.parseText(testString);
        ArrayList<Double[]> resultData = logSum.getData();
        assertArrayEquals(testData.toArray(), resultData.toArray());
    }

    @Test
    public void getText() {
        String testString = "012345678\n" +
                "000000000\n";

        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0});
        testData.add(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        LogSum logSum = new LogSum();
        logSum.setData(testData);
        String resultText = logSum.getText();
        assertEquals(resultText, testString);
    }

    @Test
    public void getResult_noNormalise() {
        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0});
        testData.add(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        Double[] expectedResult = new Double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};
        LogSum logSum = new LogSum();
        logSum.setData(testData);
        Double[] result = logSum.getResultSum();
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void getResult_noNormalise_1() {
        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0});
        testData.add(new Double[]{0.0, 1.0, 0.0, 0.0, 6.0, 0.0, 0.0, 0.0, 0.0});
        Double[] expectedResult = new Double[]{0.0, 2.0, 2.0, 3.0, 6.0, 5.0, 6.0, 7.0, 8.0};
        LogSum logSum = new LogSum();
        logSum.setData(testData);
        Double[] result = logSum.getResultSum();
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void getResult_normalise() {
        ArrayList<Double[]> testData = new ArrayList<>();
        testData.add(new Double[]{0.0, 0.0, 7.0, 6.0, 5.0, 6.0, 0.0, 8.0, 0.0});
        testData.add(new Double[]{0.0, 0.0, 0.0, 4.0, 0.0, 2.0, 0.0, 8.0, 0.0});
        Double[] expectedResult = new Double[]{0.0, 0.0, 6.0, 6.0, 4.0, 6.0, 0.0, 8.0, 0.0};
        LogSum logSum = new LogSum();
        logSum.setData(testData);
        Double[] result = logSum.getResultSum();
        assertArrayEquals(expectedResult, result);
    }
}
