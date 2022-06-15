package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcCosineTest extends BaseCalcTest {

    @Test
    public void testCosineDouble() {
        double res = calculator.cos(Math.toRadians(0.0));
        Assert.assertEquals(res, 1.0);
    }

}
