package ru.levelup.at.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcSumTest extends BaseCalcTest {

    @Test (dataProvider = "Sum for long Data Provider", dataProviderClass = SumDataProvider.class)
    public void testAddLong(long a, long b, long result) {
        long res = calculator.sum(a, b);
        Assert.assertEquals(res, result);
    }

    @Test
    public void testAddDouble() {
        double res = calculator.sum(2.0D, 5.0D);
        Assert.assertEquals(res, 7.0D);
    }
}
