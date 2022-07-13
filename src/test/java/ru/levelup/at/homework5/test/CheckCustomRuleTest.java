package ru.levelup.at.homework5.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.levelup.at.homework4.MailRuLetterList;
import ru.levelup.at.homework4.MailRuSentLetterPage;

public class CheckCustomRuleTest extends AbstractBaseLettersTest {

    @Test
    public void checkCustomRuleTest() throws IOException {

        login();
        steps.checkThatLoginIsSuccessful();
        steps.createNewLetter(EMAIL, LETTER_TITLE_CUSTOM, LETTER_BODY);
        steps.sendNewLetterFromTheEditWindow();
        steps.goToSentFolder();
        steps.checkThatSelfSentLetterIsShownInTheSentFolder(LETTER_TITLE_CUSTOM);
        steps.goToTestFolder();
        steps.checkThatSentLetterIsShownInTheTestFolder(LETTER_TITLE_CUSTOM);
        steps.openSentLetterAndCheckTheData(EMAIL, LETTER_TITLE_CUSTOM, LETTER_BODY);
        steps.logout();
    }

}

