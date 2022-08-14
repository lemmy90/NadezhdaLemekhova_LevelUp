package ru.levelup.at.homework7.steps;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertTrue;

import io.qameta.allure.Step;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.levelup.at.homework7.MailRuAuthorisationWindow;
import ru.levelup.at.homework7.MailRuDraftLetterPage;
import ru.levelup.at.homework7.MailRuHomePage;
import ru.levelup.at.homework7.MailRuLetterList;
import ru.levelup.at.homework7.MailRuMailboxHomePage;
import ru.levelup.at.homework7.MailRuNewLetter;
import ru.levelup.at.homework7.MailRuSentLetterPage;

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

    @Step("Check that personal menu is shown")
    public void checkThatLoginIsSuccessful() {
        assertTrue(mailboxHomePage.checkPersonalMenuShown());
    }

    @Step("Create new letter with address: '{address}' and title: '{title}'")
    public void createNewLetter(final String address, final String title, final String body) {
        mailboxHomePage.clickNewLetterButton();

        newLetter.fillInMailToField(address); //enter address
        newLetter.fillInMailSubjectField(title + random); //enter subject
        newLetter.fillInMailBody(body); //enter body
    }

    @Step("Click on the save as draft button")
    public void saveLetterAsDraft() {
        newLetter.clickSaveButton();
        newLetter.closeNewLetter(); //close new letter window
    }

    @Step("Proceed to the drafts folder")
    public void goToDraftsFolder() {
        mailboxHomePage.goToDraftsFolder();
    }

    @Step("Check, that draft letter is shown in folder")
    public void checkThatDraftLetterIsShownInTheDraftsList(final String title) {
        Assert.assertTrue(letterList.isNewLetterDisplayed(title, random));
    }

    @Step("Open draft letter and check its data like address, title and body")
    public void openDraftLetterAndCheckTheData(final String address, final String title, final String body) {
        draftLetterPage.openDraftLetter(title, random);

        Assert.assertEquals(draftLetterPage.getDraftLetterAddress(), address); //check address
        Assert.assertEquals(draftLetterPage.getDraftLetterTitle(), title + random); //check title
        Assert.assertEquals(draftLetterPage.getDraftLetterBody(), body); //check body
    }

    @Step("Open sent letter and check its data like address, title and body")
    public void openSentLetterAndCheckTheData(final String address, final String title, final String body) {
        letterList.openNewLetter(title, random);

        Assert.assertEquals(sentLetterPage.getSentLetterAddress(), address); //check address
        Assert.assertEquals(sentLetterPage.getSentLetterTitle(), title + random); //check title
        Assert.assertEquals(sentLetterPage.getSentLetterBody(), body); //check body
    }

    @Step("Click on the Send button")
    public void sendDraftLetterFromTheDraftEditWindow() {
        draftLetterPage.clickDraftSendButton();
        newLetter.closeAdWindow(); //close Ad window
    }

    @Step("Click on the Send button")
    public void sendNewLetterFromTheEditWindow() {
        newLetter.clickOnSendButton();
        newLetter.closeAdWindow();
    }

    @Step("Click on the delete button")
    public void deleteLetterFromTheEditWindow() {
        sentLetterPage.clickDeleteButton();
    }

    @Step("Check that letter isn't shown in drafts folder")
    public void checkThatLetterIsNotShownInDrafts(final String title) throws InterruptedException {
        var drafts = driver.findElements(By.xpath("//span[text()='" + title + random + "']"));
        if (drafts.size() > 0) {
            sleep(100);
        } else {
            Assert.assertNull(drafts);
        }
    }

    @Step("Check that letter is shown")
    public void checkThatSentLetterIsShown(final String title) {
        Assert.assertTrue(letterList.isNewLetterDisplayed(title, random));
    }

    @Step("Check that the letter is shown in the Sent folder")
    public void checkThatSelfSentLetterIsShownInTheSentFolder(final String title) {
        Assert.assertTrue(letterList.isSelfNewLetterDisplayed(title, random));
    }

    @Step("Check that new letter is shown in the Test folder")
    public void checkThatSentLetterIsShownInTheTestFolder(final String title) {
        Assert.assertTrue(letterList.isNewLetterDisplayed(title, random));
    }

    @Step("Proceed to Sent folder")
    public void goToSentFolder() {
        mailboxHomePage.goToSentFolder();
    }

    @Step("Proceed to Test folder")
    public void goToTestFolder() {
        mailboxHomePage.goToTestFolder();
    }

    @Step("Proceed to Inbox folder")
    public void goToInboxFolder() {
        mailboxHomePage.goToInboxFolder();
    }

    @Step("Proceed to Trash folder")
    public void goToTrashFolder() {
        mailboxHomePage.goToTrashFolder();
    }

    @Step("Logout user")
    public void logout() {
        mailboxHomePage.openPersonalMenu();
        mailboxHomePage.clickLogoutButton();
    }

}
