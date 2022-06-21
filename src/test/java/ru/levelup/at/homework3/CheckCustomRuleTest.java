package ru.levelup.at.homework3;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckCustomRuleTest extends AbstractBaseLettersTest {

    @Test
    public void checkCustomRuleTest() {

        // open mail.ru
        driver.navigate().to(MAILRU_URL);

        //open login page
        WebElement enterButton = driver.findElement(By.cssSelector("[data-testid='enter-mail-primary']"));
        enterButton.click();

        //switch to frame
        WebElement signInFrame = driver
            .findElement(By.cssSelector("div > iframe[class ='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(signInFrame);

        //enter user name
        WebElement userNameField = driver.findElement(By.name("username"));
        userNameField.sendKeys(USERNAME);

        // enter password
        WebElement enterPasswordButton = driver.findElement(By.cssSelector("[data-test-id='next-button']"));
        enterPasswordButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys(PASSWORD);

        WebElement signInButton = driver
            .findElement(By.cssSelector("[data-test-id='submit-button']"));
        signInButton.click();

        //check that login is successful
        WebElement logoUserName = wait.until(ExpectedConditions
            .elementToBeClickable(By.xpath("//span[text()='levelup_spring@mail.ru']")));
        assertTrue(logoUserName.isDisplayed());

        //init new letter
        WebElement newLetterButton = driver.findElement(By.cssSelector(".compose-button__txt"));
        newLetterButton.click();

        //enter address
        WebElement toField = driver.findElement(By.xpath("//div[@class='contacts--1ofjA']//input"));
        toField.sendKeys(EMAIL);

        //enter custom subject
        Random rn = new Random();
        int random = rn.nextInt();
        WebElement subjectField = driver.findElement(By.xpath("//div[@class='subject__wrapper--2mk6m']//input"));
        subjectField.sendKeys(LETTER_TITLE_CUSTOM + random);

        //enter body
        WebElement bodyField = driver.findElement(By.xpath("//div[@role='textbox']/div[1]"));
        bodyField.click();
        bodyField.sendKeys(LETTER_BODY);

        //send the letter
        WebElement sendButton = driver.findElement(By.cssSelector("[data-test-id = 'send']"));
        sendButton.click();

        WebElement closeAd = wait
            .until(ExpectedConditions
                .elementToBeClickable(By
                    .xpath("//div[@class='layer-sent-page']/div/div/span[contains(@class, 'button2')]")));
        closeAd.click();

        //go to sent
        WebElement sentFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/sent/']")));
        sentFolder.click();

        //check that letter is shown in sent folder
        WebElement selfLetter = driver
            .findElement(By.xpath("//span[text()='Self: " + LETTER_TITLE_CUSTOM + random + "']"));
        Assert.assertTrue(selfLetter.isDisplayed());

        //go to test
        WebElement customFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/1/']")));
        customFolder.click();

        //check that letter is shown in the Test folder
        WebElement newLetter = driver.findElement(By.xpath("//span[text()='" + LETTER_TITLE_CUSTOM + random + "']"));
        Assert.assertTrue(newLetter.isDisplayed());

        //open letter
        newLetter.click();

        //check address
        WebElement letterAddress = driver.findElement(By.xpath("//span[@class = 'letter-contact']"));
        Assert.assertEquals(letterAddress.getAttribute("title"), EMAIL);

        //check title
        WebElement letterTitle = driver.findElement(By.xpath("//h2[@class = 'thread-subject']"));
        Assert.assertEquals(letterTitle.getText(), LETTER_TITLE_CUSTOM + random);

        //check body
        WebElement letterBody = driver
            .findElement(By
                .xpath("//div[@class='letter-body__body']"
                    + "/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"));
        Assert.assertEquals(letterBody.getText(), LETTER_BODY);

        //open personal menu
        logoUserName.click();

        //logout user
        WebElement logoutButton = driver.findElement(By.xpath("//div[@data-click-counter='75068944']"));
        logoutButton.click();


    }

}

