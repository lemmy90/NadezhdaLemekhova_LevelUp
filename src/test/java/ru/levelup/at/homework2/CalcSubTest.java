package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcSubTest extends BaseCalcBeforeAfterHooksTest {

    @Test
    public void testSubLong() {
        long res = calculator.sub(7, 5);
        Assert.assertEquals(res, 2);
    }

    @Test
    public void testSubDouble() {
        double res = calculator.sub(8.0, 2.0);
        Assert.assertEquals(res, 6.0);
    }
}
