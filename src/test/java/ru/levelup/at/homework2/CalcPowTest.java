package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcPowTest extends BaseCalcTest {

    @Test
    public void testPowDouble() {
        double res = calculator.pow(2.0, 3.0);
        Assert.assertEquals(res, 8.0);
    }
}
