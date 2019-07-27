package com.jojand.hamreg;

import com.jojand.hamreg.logsum.LogSum;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LogSumUnitTest {
    @Test
    public void logsumWorks() {
        assertEquals(LogSum.logSum(2, 2), 3, 0);
    }
}