package ru.levelup.at.homework7.test;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import java.io.IOException;
import org.testng.annotations.Test;

public class CrateNewLetterTest extends AbstractBaseLettersTest {

    @SuppressWarnings("checkstyle:Indentation")
    @Test(description = "Create new letter")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("BUG-6834")
    @Description("Check that new letter can be successfully created")
    public void createNewLetterTest() throws IOException, InterruptedException {

        login();
        steps.checkThatLoginIsSuccessful();
        steps.createNewLetter(EMAIL, LETTER_TITLE, LETTER_BODY);
        steps.saveLetterAsDraft();
        steps.goToDraftsFolder();
        steps.checkThatDraftLetterIsShownInTheDraftsList(LETTER_TITLE);
        steps.openDraftLetterAndCheckTheData(EMAIL, LETTER_TITLE, LETTER_BODY);
        steps.sendDraftLetterFromTheDraftEditWindow();
        steps.checkThatLetterIsNotShownInDrafts(LETTER_TITLE);
        steps.goToSentFolder();
        steps.checkThatSentLetterIsShown(LETTER_TITLE);
        steps.logout();


    }

}

