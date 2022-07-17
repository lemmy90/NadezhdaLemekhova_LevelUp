package ru.levelup.at.homework4;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuHomePage extends MailRuBasePage {

    private static final String MAILRU_URL = "https://mail.ru/";

    @FindBy(css = "[data-testid='enter-mail-primary']")
    private WebElement enterButton;

    public MailRuHomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.navigate().to(MAILRU_URL);
    }

    public void clickEnterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButton)).click();
    }
}
