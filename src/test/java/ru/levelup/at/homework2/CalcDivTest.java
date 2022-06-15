package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcDivTest extends BaseCalcTest {

    @Test
    public void testDivLong() {
        long res = calculator.div(10, 5);
        Assert.assertEquals(res, 2);
    }

    @Test
    public void testDivDouble() {
        double res = calculator.div(8.0, 2.0);
        Assert.assertEquals(res, 4.0);
    }
}
