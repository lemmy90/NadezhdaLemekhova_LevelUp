package ru.levelup.at.homework4;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuLetterList {

    WebDriver driver;
    WebDriverWait wait;

    public MailRuLetterList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
