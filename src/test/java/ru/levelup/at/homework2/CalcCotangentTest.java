package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcCotangentTest extends BaseCalcBeforeAfterHooksTest {

    @Test
    public void testCotangentDouble() {
        double res = calculator.ctg(Math.toRadians(45.0));
        Assert.assertEquals(res, 1.0);
    }

}
