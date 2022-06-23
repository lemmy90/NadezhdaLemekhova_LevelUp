package ru.levelup.at.homework3;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
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
        WebElement enterButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='enter-mail-primary']")));
        enterButton.click();

        //switch to frame
        WebElement signInFrame = wait
            .until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("iframe[class ='ag-popup__frame__layout__iframe']")));
        driver.switchTo().frame(signInFrame);

        //enter user name
        WebElement userNameField = wait
            .until(ExpectedConditions.elementToBeClickable(By.name("username")));
        userNameField.sendKeys(USERNAME);

        // enter password
        WebElement enterPasswordButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test-id='next-button']")));
        enterPasswordButton.click();

        WebElement passwordField = wait
            .until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys(PASSWORD);

        WebElement signInButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test-id='submit-button']")));
        signInButton.click();

        //check that login is successful
        WebElement logoUserName = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='levelup_spring@mail.ru']")));
        assertTrue(logoUserName.isDisplayed());

        //init new letter
        WebElement newLetterButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".compose-button__txt")));
        newLetterButton.click();

        //enter address
        WebElement toField = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='contacts--1ofjA']//input")));
        toField.click();
        toField.sendKeys(EMAIL);

        //enter subject
        Random rn = new Random();
        int random = rn.nextInt();
        WebElement subjectField = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='subject__wrapper--2mk6m']//input")));
        subjectField.sendKeys(LETTER_TITLE + random);

        //enter body
        WebElement bodyField = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='textbox']/div[1]")));
        bodyField.click();
        bodyField.sendKeys(LETTER_BODY);

        //save draft
        WebElement saveLetterButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test-id='save']")));
        saveLetterButton.click();

        //close new letter window
        WebElement closeNewLetterWindow = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='container--8PdPf']//button[3]")));
        closeNewLetterWindow.click();

        //go to drafts
        WebElement draftsFolder = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/drafts/']")));
        draftsFolder.click();

        WebElement newLetter = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + LETTER_TITLE + random + "']")));
        Assert.assertTrue(newLetter.isDisplayed());

        newLetter.click();

        //check address
        WebElement letterAddress = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='container--wpBSx']//span[1]")));
        Assert.assertEquals(letterAddress.getText(), EMAIL);

        //check title
        WebElement letterTitle = wait
            .until(ExpectedConditions.elementToBeClickable(By.name("Subject")));
        Assert.assertEquals(letterTitle.getAttribute("value"), LETTER_TITLE + random);

        //check body
        WebElement letterBody = wait
            .until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@role='textbox']/div[1]/div[1]/div[1]/div[1]/div[1]")));
        Assert.assertEquals(letterBody.getText(), LETTER_BODY);

        //send the letter
        WebElement sendButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test-id = 'send']")));
        sendButton.click();

        WebElement closeAd = wait
            .until(ExpectedConditions
                .elementToBeClickable(By
                    .xpath("//div[@class='layer-sent-page']//span[contains(@class, 'button2')]")));
        closeAd.click();

        //DONT FORGET TO UNCOMMENT THIS BULLSHIT
        /* Assert.assertTrue(wait
             .until(ExpectedConditions
                 .invisibilityOfElementLocated(By.xpath("//span[text()='" + LETTER_TITLE + random + "']"))));

         */

        //var drafts = driver.findElements(By.xpath("//span[@class='ll-sj__normal'"]));

        var drafts = driver.findElements(By.xpath("//span[text()='" + LETTER_TITLE + random + "']"));
        if (drafts.size() > 0) {
            sleep(100);
        } else {
            Assert.assertNull(drafts);
        }

        //go to sent
        WebElement sentFolder = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/sent/']")));

        sentFolder.click();
        Assert.assertTrue(newLetter.isDisplayed());

        logoUserName.click();

        WebElement logoutButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-click-counter='75068944']")));
        logoutButton.click();

    }

}

