package traineeselenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import traineeselenium.AbstractComponents.AbstractComponents;

public class ProductDescriptionPage extends AbstractComponents {

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

    //
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bar-notification")));
//        String message = driver.findElement(By.cssSelector("#bar-notification p")).getText();
//        Assert.assertTrue(message.equalsIgnoreCase("The product has been added to your shopping cart"));
//        takeScreenshot(driver);
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#bar-notification"))));
}
