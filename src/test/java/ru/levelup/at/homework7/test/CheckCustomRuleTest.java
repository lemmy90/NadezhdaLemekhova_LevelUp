package ru.levelup.at.homework7.test;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import java.io.IOException;
import org.testng.annotations.Test;

public class CheckCustomRuleTest extends AbstractBaseLettersTest {

    @Test(description = "Work of custom rule for inbound letters")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("CCI-63")
    @Description("Check that new inbound letter proceed to custom folder according the rule")
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

