package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private static final String SCREENSHOTS_DIR = "Screenshots/";
    /**
     * Take screenshot of the currently displayed browser window
     *
     * @param driver   browser driver
     * @param testName name of the currently running test
     */
    public static void captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = SCREENSHOTS_DIR + "Screenshot_" + testName + "_" + timeStamp + ".png";
            File destination = new File(fileName);
            try {
                FileUtils.copyFile(source, destination);
            } catch (IOException ioe) {
                System.err.println("[ERROR] Could not save screenshot.");
                ioe.printStackTrace();
                //no need to throw exception
            }
        } catch (Throwable t) {
            System.err.println("[ERROR] Could not take screenshot.");
            t.printStackTrace();
            //no need to throw exception
        }
    }

    /**
     * Delete old screenshots
     */
    public static void deleteOldScreenshots() {
        File files[] = new File(SCREENSHOTS_DIR).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith("Screenshot_")
                        && file.getName().endsWith(".png")) {
                    file.delete();
                }
            }
        }
    }


}
