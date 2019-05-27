import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pageObjects.LoginPage;
import pageObjects.VillagePage;


import java.util.concurrent.TimeUnit;

@Listeners({ScreenshotListener.class})

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected VillagePage villagePage;

    @BeforeSuite
    public void testSuiteSetup() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void testMethodSetUp() {
        driver = new ChromeDriver();
        driver.get(System.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        villagePage = new VillagePage(driver);
        loginPage.Login();
    }

    @AfterMethod
    public void testMethodTearDown() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
