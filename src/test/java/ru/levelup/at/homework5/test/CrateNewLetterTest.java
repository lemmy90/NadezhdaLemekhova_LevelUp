package ru.levelup.at.homework5.test;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.testng.annotations.Test;

public class CrateNewLetterTest extends AbstractBaseLettersTest {

    @SuppressWarnings("checkstyle:Indentation")
    @Test
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

