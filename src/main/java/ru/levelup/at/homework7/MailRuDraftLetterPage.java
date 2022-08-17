package ru.levelup.at.homework7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailRuDraftLetterPage extends MailRuBasePage {

    @FindBy(name = "Subject")
    private WebElement draftLetterTitle;

    @FindBy(xpath = "//div[@class='container--wpBSx']//span[1]")
    private WebElement draftLetterAddress;

    @FindBy(xpath = "//div[@role='textbox']/div[1]/div[1]/div[1]/div[1]/div[1]")
    private WebElement draftLetterBody;

    @FindBy(css = "[data-test-id = 'send']")
    private WebElement draftSendButton;

    public MailRuDraftLetterPage(WebDriver driver) {
        super(driver);
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
