package ru.levelup.at.homework4;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CrateNewLetterTest extends AbstractBaseLettersTest {

    @SuppressWarnings("checkstyle:Indentation")
    @Test
    public void createNewLetterTest() throws InterruptedException {

        MailRuHomePage homePage = new MailRuHomePage(driver);

        //open mailru and click enter button
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

        //save draft
        newLetter.clickSaveButton();

        //close new letter window
        newLetter.closeNewLetter();

        //go to Drafts folder
        mailboxHomePage.goToDraftsFolder();

        MailRuLetterList letterList = new MailRuLetterList(driver);

        MailRuDraftLetterPage draftLetterPage = new MailRuDraftLetterPage(driver);

        Assert.assertTrue(letterList.isNewLetterDisplayed(LETTER_TITLE, random));

        //newLetter.click();
        draftLetterPage.openDraftLetter(LETTER_TITLE, random);

        //check address
        Assert.assertEquals(draftLetterPage.getDraftLetterAddress(), EMAIL);

        //check title
        Assert.assertEquals(draftLetterPage.getDraftLetterTitle(), LETTER_TITLE + random);

        //check body
        Assert.assertEquals(draftLetterPage.getDraftLetterBody(), LETTER_BODY);

        //send the letter
        draftLetterPage.clickDraftSendButton();

        //close Ad window
        newLetter.closeAdWindow();

        //check that letter isn't shown in drafts
        var drafts = driver.findElements(By.xpath("//span[text()='" + LETTER_TITLE + random + "']"));
        if (drafts.size() > 0) {
            sleep(100);
        } else {
            Assert.assertNull(drafts);
        }

        //go to sent
        mailboxHomePage.goToSentFolder();

        //check that letter is shown in sent folder
        Assert.assertTrue(letterList.isNewLetterDisplayed(LETTER_TITLE, random));

        //make a logout
        mailboxHomePage.openPersonalMenu();
        mailboxHomePage.clickLogoutButton();


    }

}

