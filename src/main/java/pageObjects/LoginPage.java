package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "// a[@class = \"world-select\"]")
    private WebElement world;

    @FindBy(id = "user")
    private WebElement user;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "remember-me")
    private WebElement remember;

    @FindBy(xpath = "//a[@class=\"btn-login\"]")
    private WebElement login;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
        PageFactory.initElements(driver, this);
    }

    public void Login(){
        user.sendKeys(System.getProperty("user"));
        password.sendKeys(System.getProperty("pwd"));
        if(remember.getAttribute("checked").equals("checked")){
            remember.click();
        }
        login.click();
        world.click();
    }
}
