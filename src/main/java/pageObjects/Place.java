package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Place {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor je;

    @FindBy(xpath = "//div[@id=\"place_target\"]/input")
    private WebElement placeTarget;

    @FindBy(xpath = "//div[@class=\"target-select-autocomplete\"]")
    private WebElement targetAutocomplete;

    @FindBy(xpath = "//div[@class=\"target-select-autocomplete\"]//span[@class=\"village-info\"]")
    private WebElement targetInfo;

    @FindBy(id = "target_attack")
    private WebElement attack;

    @FindBy(id = "troop_confirm_go")
    private WebElement attackConfirm;

    @FindBy(id = "selectAllUnits")
    private WebElement selectAllUnits;

    @FindBy(xpath = "(//table/tbody/tr//a[@class=\"troop_template_selector\"])[1]")
    private WebElement templateA;

    @FindBy(xpath = "(//table/tbody/tr//a[@class=\"troop_template_selector\"])[2]")
    private WebElement templateB;

    @FindBy(xpath = "//div[@class=\"error_box\"]/div")
    private WebElement error;

    public Place(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
        je = (JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }

    public void selectVillage(String village){
        je.executeScript("arguments[0].value='" + village + "';", placeTarget);
        //TODO kontrolovat jestli vesnce nebyla obsazena
    }

    public boolean selectTroopsAndAttack(){
        Boolean repeat = true;
        je.executeScript("arguments[0].click();", templateA);
        attack.click();

        try{
            attackConfirm.click();
        }catch (NoSuchElementException e){
            je.executeScript("arguments[0].click();", templateB);
            attack.click();
            try{
                attackConfirm.click();
            }catch(NoSuchElementException f){
                repeat = false;
            }
        }
        return repeat;
    }
}
