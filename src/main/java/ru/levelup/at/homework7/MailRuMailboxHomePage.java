package ru.levelup.at.homework7;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailRuMailboxHomePage extends MailRuBasePage {

    @FindBy(xpath = "//span[text()='levelup_spring@mail.ru']")
    WebElement personalMenu;

    @FindBy(xpath = "//a[@href='/inbox/']")
    WebElement inboxFolder;

    @FindBy(xpath = "//a[@href='/sent/']")
    WebElement sentFolder;

    @FindBy(xpath = "//a[@href='/drafts/']")
    WebElement draftsFolder;

    @FindBy(xpath = "//a[@href='/1/']")
    WebElement testFolder;

    @FindBy(xpath = "//a[@href='/trash/']")
    WebElement trashFolder;

    @FindBy(css = ".compose-button__txt")
    WebElement newLetterButton;

    @FindBy(xpath = "//div[@data-click-counter='75068944']")
    WebElement logoutButton;

    public MailRuMailboxHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean checkPersonalMenuShown() {
        return wait.until(ExpectedConditions.elementToBeClickable(personalMenu)).isDisplayed();
    }

    public void openPersonalMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(personalMenu)).click();
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void goToInboxFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(inboxFolder)).click();
    }

    public void goToSentFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(sentFolder)).click();
    }

    public void goToDraftsFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(draftsFolder)).click();
    }

    public void goToTestFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(testFolder)).click();
    }

    public void goToTrashFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(trashFolder)).click();
    }

    public void clickNewLetterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newLetterButton)).click();
    }


}
