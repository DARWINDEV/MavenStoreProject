package traineeselenium.PageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import traineeselenium.AbstractComponents.AbsComponents;

import java.io.IOException;
import java.time.Duration;


public class ModelPage {
    public static void main(String[] args) throws IOException {

        String productName = "Blue Jeans";
        String prodQuantity = "5";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();

        AbsComponents absComponent = new AbsComponents(driver);
        absComponent.logIn();
        absComponent.takeScreenshot(driver);

        landingPage.loginApp("d12311203@gmail.com", "D4rwinTestFun");
        absComponent.takeScreenshot(driver);

        absComponent.apparel();
        absComponent.takeScreenshot(driver);

        absComponent.waitElementToAppear(By.cssSelector("div[class='item-box']"));

        CataloguePage catalogue = new CataloguePage(driver);
        catalogue.getProductList();
        catalogue.getProduct(productName);
        absComponent.takeScreenshot(driver);

        ProductDescriptionPage prodPage = new ProductDescriptionPage(driver);
        prodPage.productQuantity(prodQuantity);
        absComponent.takeScreenshot(driver);

        absComponent.cart();
        absComponent.takeScreenshot(driver);

        absComponent.termsAccept();
        absComponent.setCheckoutBtn();
        absComponent.takeScreenshot(driver);


//      Billing address
        CheckOutPage checkout = new CheckOutPage(driver);

        checkout.billingForm("Capgeminy", "Tuxtla Gutierrez", "Test 1", "Test 2", "Mexico",
                "29000", "555555", "666666");
        absComponent.takeScreenshot(driver);

////        Shipping Address
        absComponent.waitElementToAppear(By.cssSelector("input[id='PickUpInStore']"));
        checkout.shippingForm();
        absComponent.takeScreenshot(driver);


////        Shipping method and payment method
        checkout.paymentForm();
        absComponent.takeScreenshot(driver);

////        Payment information

        checkout.infoForm("Darwin Jimenez Hernandez","5482340924944396", "699");
        absComponent.takeScreenshot(driver);

//        Confirm order

        checkout.confirmInfo();
        absComponent.takeScreenshot(driver);

////      Logout

        absComponent.logOut();
        absComponent.takeScreenshot(driver);

        driver.quit();


    }


}
