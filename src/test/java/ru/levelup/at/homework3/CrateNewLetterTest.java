package ru.levelup.at.homework3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import javax.swing.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CrateNewLetterTest {

    private WebDriver driver;

    private static final String MAILRU_URL = "https://mail.ru/";

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();

    }

    @SuppressWarnings("checkstyle:Indentation")
    @Test
    public void createNewLetterTest() throws InterruptedException {
        driver.navigate().to(MAILRU_URL);

        WebElement enterButton = driver.findElement(By.cssSelector("[data-testid='enter-mail-primary']"));
        enterButton.click();

        WebElement signInFrame = driver
            .findElement(By.cssSelector("div > iframe[class ='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(signInFrame);

        WebElement userNameField = driver.findElement(By.name("username"));
        userNameField.sendKeys("levelup_spring");

        WebElement enterPasswordButton = driver.findElement(By.cssSelector("[data-test-id='next-button']"));
        enterPasswordButton.click();

        WebElement passwordField = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys("+-WSXqGeMt5!pFq");

        WebElement signInButton = driver
            .findElement(By.cssSelector("[data-test-id='submit-button']"));
        signInButton.click();

        WebElement logoUserName = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='levelup_spring@mail.ru']")));
        assertTrue(logoUserName.isDisplayed());

        WebElement draftsFolder = driver.findElement(By.xpath("//a[@href='/drafts/']"));
        draftsFolder.click();

        int listBefore = 0;
        listBefore = driver
            .findElements(By
                .xpath("//div[contains(@class,'application-mail__layout')]"
                    + "/span/div[2]/div/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[1]/a"))
            .size();

        WebElement newLetterButton = driver.findElement(By.cssSelector(".compose-button__txt"));
        newLetterButton.click();

        WebElement toField = driver.findElement(By.xpath("//div[@class='contacts--1ofjA']//input"));
        toField.sendKeys("test@mail.com");

        WebElement subjectField = driver.findElement(By.xpath("//div[@class='subject__wrapper--2mk6m']//input"));
        subjectField.sendKeys("Test");

        WebElement saveLetterButton = driver.findElement(By.cssSelector("[data-test-id='save']"));
        saveLetterButton.click();

        WebElement closeNewLetterWindow = driver.findElement(By.xpath("//div[@class='container--8PdPf']//button[3]"));
        closeNewLetterWindow.click();

        draftsFolder.click();

        int listAfter = 0;
        listAfter = driver
            .findElements(By
                .xpath("//div[contains(@class,'application-mail__layout')]/"
                    + "span/div[2]/div/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[1]/a"))
            .size();

        listBefore = listBefore + 1;

        assertEquals(listAfter, listBefore);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

