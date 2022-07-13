package ru.levelup.at.homework5.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.levelup.at.homework4.MailRuLetterList;
import ru.levelup.at.homework4.MailRuSentLetterPage;

public class CheckLetterDeletionTest extends AbstractBaseLettersTest {

    @Test
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

