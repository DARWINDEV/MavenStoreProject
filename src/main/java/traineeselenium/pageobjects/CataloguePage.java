package traineeselenium.pageobjects;

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

    @FindBy(id="addtocart_36_EnteredQuantity")
    WebElement itemQuantity;

    @FindBy (css = "div[class='add-to-cart-panel'] input:last-of-type")
    WebElement addToCardBtn;

    @FindBy(css = "li#topcartlink")
    WebElement cartBtn;

    @FindBy(id = "termsofservice")
    WebElement termsCheck;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    By productList = By.cssSelector("div[class='item-box']");

    By barNotification = By.cssSelector("#bar-notification");

    public List<WebElement> getProductList(){
        waitElementToAppear(productList);
        return products;
    }

    public void getProduct(String productName){
        getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("h2")).getText().equalsIgnoreCase(productName)).findAny().get().click();
    }

    public void productQuantity(String quantity){
        itemQuantity.clear();
        itemQuantity.sendKeys(quantity);
        addToCardBtn.click();
        waitElementToAppear(barNotification);

    }

    public CheckOutPage setCheckoutBtn(){
        checkoutBtn.click();
        return new CheckOutPage(driver);
    }

    public void cart(){
        cartBtn.click();
    }

    public void termsAccept(){
        termsCheck.click();
    }
}
