package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenu {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "header_menu_link_map")
    private WebElement map;

    public MainMenu(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
        PageFactory.initElements(driver, this);
    }

    public void openMap(){
        map.click();
    }
}
