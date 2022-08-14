package ru.levelup.at.homework7.listener;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.levelup.at.homework7.utils.AttachmentUtils;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        var driver = (WebDriver) result.getTestContext().getAttribute("driver");
        attachScreenshot(driver);
        attachPageSource(driver);
    }

    @Attachment(type = "image/png", fileExtension = ".png")
    private byte[] attachScreenshot(final WebDriver driver) {
        return AttachmentUtils.makeScreenshot(driver);
    }

    private void attachPageSource(final WebDriver driver) {
        byte[] pageSource = AttachmentUtils.getPageSource(driver);
        Allure.addAttachment("page_source", "test/html",
            new ByteArrayInputStream(pageSource), ".html");
    }


}
