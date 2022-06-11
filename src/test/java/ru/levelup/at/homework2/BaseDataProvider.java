package ru.levelup.at.homework2;

import org.testng.annotations.DataProvider;

public class BaseDataProvider {

    @DataProvider (name = "Sum for long Data Provider")
    public Object[][] sumLongDataProvider() {
        return new Object[][] {
            {3, 5, 8},
            {1, 0, 1},
            {99, 1, 100}
        };
    }
}
