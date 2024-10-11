package traineeselenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbsComponents;

import java.util.List;

public class CataloguePage extends AbsComponents {

    WebDriver driver;

    public CataloguePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

//    PageFactory

    @FindBy(css = "div[class='item-box']")
    List<WebElement> products;

    By productList = By.cssSelector("div[class='item-box']");

    public List<WebElement> getProductList(){
        waitElementToAppear(productList);
        return products;
    }

    public void getProduct(String productName){
        getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("h2")).getText().equalsIgnoreCase(productName)).findAny().get().click();
    }
}
