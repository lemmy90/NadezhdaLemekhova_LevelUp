package ru.levelup.at.homework5.steps;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.levelup.at.homework5.MailRuAuthorisationWindow;
import ru.levelup.at.homework5.MailRuDraftLetterPage;
import ru.levelup.at.homework5.MailRuHomePage;
import ru.levelup.at.homework5.MailRuLetterList;
import ru.levelup.at.homework5.MailRuMailboxHomePage;
import ru.levelup.at.homework5.MailRuNewLetter;
import ru.levelup.at.homework5.MailRuSentLetterPage;

public class MailRuSteps {

    private WebDriver driver;

    private MailRuHomePage homePage;
    private MailRuMailboxHomePage mailboxHomePage;
    private MailRuLetterList letterList;
    private MailRuAuthorisationWindow authorisationWindow;
    private MailRuNewLetter newLetter;
    private MailRuSentLetterPage sentLetterPage;
    private MailRuDraftLetterPage draftLetterPage;

    public MailRuSteps(WebDriver driver) {
        this.driver = driver;
        this.homePage = new MailRuHomePage(driver);
        this.mailboxHomePage = new MailRuMailboxHomePage(driver);
        this.letterList = new MailRuLetterList(driver);
        this.authorisationWindow = new MailRuAuthorisationWindow(driver);
        this.newLetter = new MailRuNewLetter(driver);
        this.sentLetterPage = new MailRuSentLetterPage(driver);
        this.draftLetterPage = new MailRuDraftLetterPage(driver);
    }

    Random rn = new Random();
    int random = rn.nextInt();

    public void checkThatLoginIsSuccessful() {
        assertTrue(mailboxHomePage.checkPersonalMenuShown());
    }

    public void createNewLetter(final String address, final String title, final String body) {
        mailboxHomePage.clickNewLetterButton();

        newLetter.fillInMailToField(address); //enter address
        newLetter.fillInMailSubjectField(title + random); //enter subject
        newLetter.fillInMailBody(body); //enter body
    }

    public void saveLetterAsDraft() {
        newLetter.clickSaveButton();
        newLetter.closeNewLetter(); //close new letter window
    }

    public void goToDraftsFolder() {
        mailboxHomePage.goToDraftsFolder();
    }

    public void checkThatDraftLetterIsShownInTheDraftsList(final String title) {
        Assert.assertTrue(letterList.isNewLetterDisplayed(title, random));
    }

    public void openDraftLetterAndCheckTheData(final String address, final String title, final String body) {
        draftLetterPage.openDraftLetter(title, random);

        Assert.assertEquals(draftLetterPage.getDraftLetterAddress(), address); //check address
        Assert.assertEquals(draftLetterPage.getDraftLetterTitle(), title + random); //check title
        Assert.assertEquals(draftLetterPage.getDraftLetterBody(), body); //check body
    }

    public void openSentLetterAndCheckTheData(final String address, final String title, final String body) {
        letterList.openNewLetter(title, random);

        Assert.assertEquals(sentLetterPage.getSentLetterAddress(), address); //check address
        Assert.assertEquals(sentLetterPage.getSentLetterTitle(), title + random); //check title
        Assert.assertEquals(sentLetterPage.getSentLetterBody(), body); //check body
    }

    public void sendDraftLetterFromTheDraftEditWindow() {
        draftLetterPage.clickDraftSendButton();
        newLetter.closeAdWindow(); //close Ad window
    }

    public void sendNewLetterFromTheEditWindow() {
        newLetter.clickOnSendButton();
        newLetter.closeAdWindow();
    }

    public void deleteLetterFromTheEditWindow() {
        sentLetterPage.clickDeleteButton();
    }

    public void checkThatLetterIsNotShownInDrafts(final String title) throws InterruptedException {
        var drafts = driver.findElements(By.xpath("//span[text()='" + title + random + "']"));
        if (drafts.size() > 0) {
            sleep(100);
        } else {
            Assert.assertNull(drafts);
        }
    }

    public void checkThatSentLetterIsShown(final String title) {
        Assert.assertTrue(letterList.isNewLetterDisplayed(title, random));
    }

    public void checkThatSelfSentLetterIsShownInTheSentFolder(final String title) {
        Assert.assertTrue(letterList.isSelfNewLetterDisplayed(title, random));
    }

    public void checkThatSentLetterIsShownInTheTestFolder(final String title) {
        Assert.assertTrue(letterList.isNewLetterDisplayed(title, random));
    }

    public void goToSentFolder() {
        mailboxHomePage.goToSentFolder();
    }

    public void goToTestFolder() {
        mailboxHomePage.goToTestFolder();
    }

    public void goToInboxFolder() {
        mailboxHomePage.goToInboxFolder();
    }

    public void goToTrashFolder() {
        mailboxHomePage.goToTrashFolder();
    }

    public void logout() {
        mailboxHomePage.openPersonalMenu();
        mailboxHomePage.clickLogoutButton();
    }

}
