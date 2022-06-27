package ru.levelup.at.homework4;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuDraftLetterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "Subject")
    private WebElement draftLetterTitle;

    @FindBy(xpath = "//div[@class='container--wpBSx']//span[1]")
    private WebElement draftLetterAddress;

    @FindBy(xpath = "//div[@role='textbox']/div[1]/div[1]/div[1]/div[1]/div[1]")
    private WebElement draftLetterBody;

    @FindBy(css = "[data-test-id = 'send']")
    private WebElement draftSendButton;

    public MailRuDraftLetterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void openDraftLetter(String title, int number) {
        wait
            .until(ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='" + title + number + "']"))).click();
    }

    public String getDraftLetterTitle() {
        return  wait.until(ExpectedConditions.elementToBeClickable(draftLetterTitle)).getAttribute("value");

    }

    public String getDraftLetterAddress() {
        return wait.until(ExpectedConditions.elementToBeClickable(draftLetterAddress)).getText();
    }

    public String getDraftLetterBody() {
        return wait.until(ExpectedConditions.elementToBeClickable(draftLetterBody)).getText();
    }

    public void clickDraftSendButton() {
        wait.until(ExpectedConditions.elementToBeClickable(draftSendButton)).click();
    }

}
