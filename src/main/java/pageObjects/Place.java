package pageObjects;

import org.openqa.selenium.*;
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

    @FindBy(xpath = "//div[@class=\"target-select-autocomplete\"]//span[@class=\"village-distance\"]")
    private WebElement targetDistance;

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
        placeTarget.sendKeys(Keys.ENTER);
        //TODO kontrolovat jestli vesnce nebyla obsazena
    }

    public int selectTroopsAndAttack(int repeat){
        double distance = Double.parseDouble(targetDistance.getText().substring(12, 14));
        if (distance <= 7 && repeat == 0){
            je.executeScript("arguments[0].click();", templateA);
            attack.click();

            try{
                attackConfirm.click();
            }catch (NoSuchElementException e){
                je.executeScript("arguments[0].click();", templateB);
                attack.click();
                try{
                    attackConfirm.click();
                    repeat = 1;
                }catch(NoSuchElementException f){
                }
            }
        } else {
            je.executeScript("arguments[0].click();", templateB);
            attack.click();
            try{
                attackConfirm.click();
            }catch(NoSuchElementException f){
                repeat = 2;
            }
        }

        return repeat;
    }
}
