package ru.levelup.at.homework7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailRuSentLetterPage extends MailRuBasePage {

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
        super(driver);
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
