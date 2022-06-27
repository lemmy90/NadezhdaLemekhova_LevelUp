package ru.levelup.at.homework4;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuSentLetterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//h2[@class = 'thread-subject']")
    private WebElement sentLetterTitle;

    @FindBy(xpath = "//span[@class = 'letter-contact']")
    private WebElement sentLetterAddress;

    @FindBy(xpath = "//div[@class='letter-body__body']"
        + "/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")
    private WebElement sentLetterBody;

    @FindBy(css = "[data-title-shortcut = 'Del']")
    private WebElement sentLetterDeleteButton;

    public MailRuSentLetterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String getSentLetterAddress() {
        return wait.until(ExpectedConditions.elementToBeClickable(sentLetterAddress)).getAttribute("title");
    }

    public String getSentLetterTitle() {
        return wait.until(ExpectedConditions.elementToBeClickable(sentLetterTitle)).getText();

    }

    public String getSentLetterBody() {
        return wait.until(ExpectedConditions.elementToBeClickable(sentLetterBody)).getText();
    }

    public void clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sentLetterDeleteButton)).click();
    }

}
