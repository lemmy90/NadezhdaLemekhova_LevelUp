package ru.levelup.at.homework7.test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import java.io.IOException;
import org.testng.annotations.Test;

public class CheckLetterDeletionTest extends AbstractBaseLettersTest {

    @Test(description = "Delete existing letter")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check that existing letter can be successfully deleted")
    public void checkLetterDeletionTest() throws IOException {

        login();
        steps.checkThatLoginIsSuccessful();
        steps.createNewLetter(EMAIL, LETTER_TITLE, LETTER_BODY);
        steps.sendNewLetterFromTheEditWindow();
        steps.goToInboxFolder();
        steps.checkThatSentLetterIsShown(LETTER_TITLE);
        steps.openSentLetterAndCheckTheData(EMAIL, LETTER_TITLE, LETTER_BODY);
        steps.deleteLetterFromTheEditWindow();
        steps.goToTrashFolder();
        steps.checkThatSentLetterIsShown(LETTER_TITLE);
        steps.logout();
    }

}

