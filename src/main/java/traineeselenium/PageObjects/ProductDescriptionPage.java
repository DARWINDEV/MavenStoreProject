package traineeselenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbsComponents;


public class ProductDescriptionPage extends AbsComponents {

    WebDriver driver;

    public ProductDescriptionPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="addtocart_36_EnteredQuantity")
            WebElement itemQuantity;

    @FindBy (css = "div[class='add-to-cart-panel'] input:last-of-type")
            WebElement addToCardBtn;

    By barNotification = By.cssSelector("#bar-notification");


    public void productQuantity(String quantity){
        itemQuantity.clear();
        itemQuantity.sendKeys(quantity);
        addToCardBtn.click();
        waitElementToAppear(barNotification);

    }

}
