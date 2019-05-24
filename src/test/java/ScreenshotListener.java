import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static utils.ScreenshotUtil.captureScreenshot;
import static utils.ScreenshotUtil.deleteOldScreenshots;

public class ScreenshotListener extends TestListenerAdapter {

    @Override
    public void onStart(ITestContext testContext) {
        deleteOldScreenshots();
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        ITestContext context = testResult.getTestContext();
        Object currentClass = testResult.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();
        // take screenshot
        captureScreenshot(driver, testResult.getName());
    }
}

