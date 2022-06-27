package ru.levelup.at.homework4;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuNewLetter {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class='contacts--1ofjA']//input")
    WebElement mailToField;

    @FindBy(xpath = "//div[@class='subject__wrapper--2mk6m']//input")
    WebElement mailSubjectField;

    @FindBy(xpath = "//div[@role='textbox']/div[1]")
    WebElement mailBodyField;

    @FindBy(css = "[data-test-id= 'save']")
    WebElement draftButton;

    @FindBy(xpath = "//div[@class='container--8PdPf']//button[3]")
    WebElement closeNewLetterWindowButton;

    @FindBy(css = "[data-test-id = 'send']")
    WebElement sendButton;

    @FindBy(xpath = "//div[@class='layer-sent-page']/div/div/span[contains(@class, 'button2')]")
    WebElement closeAdButton;

    public MailRuNewLetter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void fillInMailToField(final String address) {
        wait.until(ExpectedConditions.elementToBeClickable(mailToField)).sendKeys(address);
    }

    public void fillInMailSubjectField(final String subject) {
        wait.until(ExpectedConditions.elementToBeClickable(mailSubjectField)).sendKeys(subject);
    }

    public void fillInMailBody(final String body) {
        wait.until(ExpectedConditions.elementToBeClickable(mailBodyField)).click();
        mailBodyField.sendKeys(body);
    }

    public void clickOnSendButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();
    }

    public void closeNewLetter() {
        wait.until(ExpectedConditions.elementToBeClickable(closeNewLetterWindowButton)).click();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(draftButton)).click();
    }

    public void closeAdWindow() {
        wait.until(ExpectedConditions.elementToBeClickable(closeAdButton)).click();
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
