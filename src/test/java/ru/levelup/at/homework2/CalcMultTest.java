package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcMultTest extends BaseCalcBeforeAfterHooksTest {

    @Test
    public void testMultLong() {
        long res = calculator.mult(2, 5);
        Assert.assertEquals(res, 10);
    }

    @Test
    public void testMultDouble() {
        double res = calculator.mult(8.0, 2.0);
        Assert.assertEquals(res, 16.0);
    }
}
