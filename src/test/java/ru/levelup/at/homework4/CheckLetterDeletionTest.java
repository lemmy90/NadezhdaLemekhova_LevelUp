package ru.levelup.at.homework4;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckLetterDeletionTest extends AbstractBaseLettersTest {

    @Test
    public void checkLetterDeletionTest() {

        MailRuHomePage homePage = new MailRuHomePage(driver);
        homePage.open();
        homePage.clickEnterButton();

        MailRuAuthorisationWindow authorisationWindow = new MailRuAuthorisationWindow(driver);
        authorisationWindow.switchToAuthorisationWindow();
        authorisationWindow.fillUsernameInputField(USERNAME);
        authorisationWindow.clickEnterPasswordButton();
        authorisationWindow.fillPasswordInputField(PASSWORD);
        authorisationWindow.clickSignInButton();

        MailRuMailboxHomePage mailboxHomePage = new MailRuMailboxHomePage(driver);
        assertTrue(mailboxHomePage.checkPersonalMenuShown());


        //init new letter
        mailboxHomePage.clickNewLetterButton();

        MailRuNewLetter newLetter = new MailRuNewLetter(driver);

        //enter address
        newLetter.fillInMailToField(EMAIL);

        //enter subject
        Random rn = new Random();
        int random = rn.nextInt();
        newLetter.fillInMailSubjectField(LETTER_TITLE + random);

        //enter body
        newLetter.fillInMailBody(LETTER_BODY);

        //send the letter
        newLetter.clickOnSendButton();
        newLetter.closeAdWindow();

        //go to inbound
        mailboxHomePage.goToInboxFolder();

        MailRuLetterList letterList = new MailRuLetterList(driver);

        //check that letter is shown in inbound folder
        Assert.assertTrue(letterList.isNewLetterDisplayed(LETTER_TITLE, random));

        //open letter
        letterList.openNewLetter(LETTER_TITLE, random);

        MailRuSentLetterPage sentLetter = new MailRuSentLetterPage(driver);

        //check address
        Assert.assertEquals(sentLetter.getSentLetterAddress(), EMAIL);

        //check title
        Assert.assertEquals(sentLetter.getSentLetterTitle(), LETTER_TITLE + random);

        //check body
        Assert.assertEquals(sentLetter.getSentLetterBody(), LETTER_BODY);

        //delete letter
        sentLetter.clickDeleteButton();

        //go to trash
        mailboxHomePage.goToTrashFolder();

        //check that letter is shown in the Test folder
        Assert.assertTrue(letterList.isNewLetterDisplayed(LETTER_TITLE, random));

        //open personal menu
        mailboxHomePage.openPersonalMenu();

        //logout user
        mailboxHomePage.clickLogoutButton();


    }

}

