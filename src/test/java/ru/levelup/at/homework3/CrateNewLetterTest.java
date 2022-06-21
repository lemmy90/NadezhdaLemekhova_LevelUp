package ru.levelup.at.homework3;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrateNewLetterTest extends AbstractBaseLettersTest {

    @SuppressWarnings("checkstyle:Indentation")
    @Test
    public void createNewLetterTest() throws InterruptedException {

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

        //enter subject
        Random rn = new Random();
        int random = rn.nextInt();
        WebElement subjectField = driver.findElement(By.xpath("//div[@class='subject__wrapper--2mk6m']//input"));
        subjectField.sendKeys(LETTER_TITLE + random);

        //enter body
        WebElement bodyField = driver.findElement(By.xpath("//div[@role='textbox']/div[1]"));
        bodyField.click();
        bodyField.sendKeys(LETTER_BODY);

        //save draft
        WebElement saveLetterButton = driver.findElement(By.cssSelector("[data-test-id='save']"));
        saveLetterButton.click();

        //close new letter window
        WebElement closeNewLetterWindow = driver.findElement(By.xpath("//div[@class='container--8PdPf']//button[3]"));
        closeNewLetterWindow.click();

        //go to drafts
        WebElement draftsFolder = driver.findElement(By.xpath("//a[@href='/drafts/']"));
        draftsFolder.click();

        WebElement newLetter = driver.findElement(By.xpath("//span[text()='" + LETTER_TITLE + random + "']"));
        Assert.assertTrue(newLetter.isDisplayed());

        newLetter.click();

        //check address
        WebElement letterAddress = driver.findElement(By.xpath("//div[@class='container--wpBSx']//span[1]"));
        Assert.assertEquals(letterAddress.getText(), EMAIL);

        //check title
        WebElement letterTitle = driver.findElement(By.name("Subject"));
        Assert.assertEquals(letterTitle.getAttribute("value"), LETTER_TITLE + random);

        //check body
        WebElement letterBody = driver
            .findElement(By.xpath("//div[@role='textbox']/div[1]/div[1]/div[1]/div[1]/div[1]"));
        Assert.assertEquals(letterBody.getText(), LETTER_BODY);

        //send the letter
        WebElement sendButton = driver.findElement(By.cssSelector("[data-test-id = 'send']"));
        sendButton.click();

        WebElement closeAd = wait
            .until(ExpectedConditions
                .elementToBeClickable(By
                    .xpath("//div[@class='layer-sent-page']/div/div/span[contains(@class, 'button2')]")));
        closeAd.click();

        //DONT FORGET TO UNCOMMENT THIS BOOLSHIT
        /*Assert.assertTrue(wait
             .until(ExpectedConditions
                 .invisibilityOfElementLocated(By.xpath("//span[text()='" + LETTER_TITLE + random + "']"))));*/



        //go to sent
        WebElement sentFolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/sent/']")));

        sentFolder.click();
        Assert.assertTrue(newLetter.isDisplayed());

        logoUserName.click();

        WebElement logoutButton = driver.findElement(By.xpath("//div[@data-click-counter='75068944']"));
        logoutButton.click();


    }

}

