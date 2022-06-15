package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcSqrtTest extends BaseCalcTest {

    @Test
    public void testSqrtDouble() {
        double res = calculator.sqrt(16.0);
        Assert.assertEquals(res, 4);
    }

}
