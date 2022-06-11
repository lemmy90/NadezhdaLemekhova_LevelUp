package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcTangentTest extends BaseCalcBeforeAfterHooksTest {

    @Test
    public void testTangentDouble() {
        double res = calculator.tg(180.0);
        Assert.assertEquals(res, 0.0);
    }

}
