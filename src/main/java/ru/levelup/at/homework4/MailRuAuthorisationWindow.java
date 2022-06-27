package ru.levelup.at.homework4;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuAuthorisationWindow {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "div > iframe[class ='ag-popup__frame__layout__iframe']")
    private WebElement singInWindow;

    @FindBy(name = "username")
    private WebElement usernameInputField;

    @FindBy(css = "[data-test-id='next-button']")
    private WebElement passwordButton;

    @FindBy(name = "password")
    private WebElement passwordInputField;

    @FindBy(css = "[data-test-id='submit-button']")
    private WebElement signInButton;

    public MailRuAuthorisationWindow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void switchToAuthorisationWindow() {
        driver.switchTo().frame(singInWindow);
    }

    public void fillUsernameInputField(final String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInputField)).sendKeys(username);
    }

    public void clickEnterPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordButton)).click();
    }

    public void fillPasswordInputField(final String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInputField)).sendKeys(password);
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }
}
