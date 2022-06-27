package ru.levelup.at.homework4;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;
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

        MailRuHomePage homePage = new MailRuHomePage(driver);
        homePage.open();
        homePage.clickEnterButton();

        MailRuAuthorisationWindow authorisationWindow = new MailRuAuthorisationWindow(driver);
        authorisationWindow.switchToAuthorisationWindow();
        authorisationWindow.fillUsernameInputField(USERNAME);
        authorisationWindow.clickEnterPasswordButton();
        authorisationWindow.fillPasswordInputField(PASSWORD);
        authorisationWindow.clickSignInButton();

        //check that login is successful
        MailRuMailboxHomePage mailboxHomePage = new MailRuMailboxHomePage(driver);
        assertTrue(mailboxHomePage.checkPersonalMenuShown());

        //init new letter
        mailboxHomePage.clickNewLetterButton();

        MailRuNewLetter newLetter = new MailRuNewLetter(driver);

        //enter address
        newLetter.fillInMailToField(EMAIL);

        //enter custom subject
        Random rn = new Random();
        int random = rn.nextInt();
        newLetter.fillInMailSubjectField(LETTER_TITLE_CUSTOM + random);

        //enter body
        newLetter.fillInMailBody(LETTER_BODY);

        //send the letter
        newLetter.clickOnSendButton();
        newLetter.closeAdWindow();

        //go to sent
        mailboxHomePage.goToSentFolder();

        //check that letter is shown in sent folder
        Assert.assertTrue(newLetter.isSelfNewLetterDisplayed(LETTER_TITLE_CUSTOM, random));

        //go to test
        mailboxHomePage.goToTestFolder();

        //check that letter is shown in the Test folder
        Assert.assertTrue(newLetter.isNewLetterDisplayed(LETTER_TITLE_CUSTOM, random));

        //open letter
        newLetter.openNewLetter(LETTER_TITLE_CUSTOM, random);

        MailRuSentLetterPage sentLetter = new MailRuSentLetterPage(driver);

        //check address
        Assert.assertEquals(sentLetter.getSentLetterAddress(), EMAIL);

        //check title
        Assert.assertEquals(sentLetter.getSentLetterTitle(), LETTER_TITLE_CUSTOM + random);

        //check body
        Assert.assertEquals(sentLetter.getSentLetterBody(), LETTER_BODY);

        //open personal menu
        mailboxHomePage.openPersonalMenu();

        //logout user
        mailboxHomePage.clickLogoutButton();


    }

}

