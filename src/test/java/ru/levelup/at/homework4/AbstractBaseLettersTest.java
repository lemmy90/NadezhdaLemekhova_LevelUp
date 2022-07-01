package ru.levelup.at.homework4;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public abstract class AbstractBaseLettersTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final String USERNAME = "levelup_spring";
    protected static final String PASSWORD = "+-WSXqGeMt5!pFq";
    protected static final String EMAIL = "levelup_spring@mail.ru";
    protected static final String LETTER_TITLE = "Nadia ";
    protected static final String LETTER_TITLE_CUSTOM = "Test ";
    protected static final String LETTER_BODY = "kak menya dostal xpath";

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

    protected void login() throws IOException {
        InputStream input = new
            FileInputStream("/Users/nlemekhova/projects/NadezhdaLemekhova_LevelUp/"
            + "src/test/resources/config/mailru.properties");

        Properties prop = new Properties();
        prop.load(input);

        MailRuHomePage homePage = new MailRuHomePage(driver);
        homePage.open();
        homePage.clickEnterButton();

        MailRuAuthorisationWindow authorisationWindow = new MailRuAuthorisationWindow(driver);
        authorisationWindow.switchToAuthorisationWindow();
        authorisationWindow.fillUsernameInputField(prop.getProperty("username"));
        authorisationWindow.clickEnterPasswordButton();
        authorisationWindow.fillPasswordInputField(prop.getProperty("password"));
        authorisationWindow.clickSignInButton();
    }
}
