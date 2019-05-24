package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VillagePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//tr[@id=\"menu_row2\"]/td[@class=\"box-item\"]/b")
    private WebElement coordinates;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-barracks\")]")
    private WebElement barracks;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-main\")]")
    private WebElement main;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-smith\")]")
    private WebElement smith;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-place\")]")
    private WebElement place;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-statue\")]")
    private WebElement statue;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-market\")]")
    private WebElement market;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-wood\")]")
    private WebElement wood;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-stone\")]")
    private WebElement stone;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-iron\")]")
    private WebElement iron;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-farm\")]")
    private WebElement farm;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-storage\")]")
    private WebElement storage;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-hide\")]")
    private WebElement hide;

    @FindBy(xpath = "//div[contains(@class, \"visual-label-wall\")]")
    private WebElement wall;

    public VillagePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
        PageFactory.initElements(driver, this);
    }

    public String getCoordinates(){
        String coords = coordinates.getText().substring(1, 8);


        return coords;
    }

    public MainBuilding goMain(){
        main.click();
        return new MainBuilding(driver);
    }

    public Place goPlace(){
        place.click();
        return new Place(driver);
    }
}
