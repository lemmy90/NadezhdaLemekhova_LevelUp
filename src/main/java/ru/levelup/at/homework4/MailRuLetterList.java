package ru.levelup.at.homework4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailRuLetterList extends MailRuBasePage {

    public MailRuLetterList(WebDriver driver) {
        super(driver);
    }

    public boolean isNewLetterDisplayed(String title, int number) {
        return wait
            .until(ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='" + title + number + "']"))).isDisplayed();
    }

    public boolean isSelfNewLetterDisplayed(String title, int number) {
        return wait
            .until(ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='Self: " + title + number + "']"))).isDisplayed();
    }

    public void openNewLetter(String title, int number) {
        wait
            .until(ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='" + title + number + "']"))).click();
    }


}
