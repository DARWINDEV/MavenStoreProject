package traineeselenium.pageobjects;

import org.openqa.selenium.*;

import org.testng.annotations.Test;
import traineeselenium.AbstractComponents.AbsComponents;
import traineeselenium.TestComponents.BaseTest;

import java.io.IOException;



public class ShoppingTest extends BaseTest {
    String productName = "Blue Jeans";
    String prodQuantity = "5";

    @Test
    public void shopping() throws IOException{

        AbsComponents absComponent = new AbsComponents(driver);
        absComponent.logIn();
        absComponent.takeScreenshot();

        landingPage.loginApp("d12311203@gmail.com", "D4rwinTestFun");
        absComponent.takeScreenshot();

        CataloguePage cataloguePage =  landingPage.apparel();
        absComponent.takeScreenshot();

        absComponent.waitElementToAppear(By.cssSelector("div[class='item-box']"));

        cataloguePage.getProductList();
        cataloguePage.getProduct(productName);
        absComponent.takeScreenshot();

        cataloguePage.productQuantity(prodQuantity);
        absComponent.takeScreenshot();

        cataloguePage.cart();
        absComponent.takeScreenshot();

        cataloguePage.termsAccept();
        CheckOutPage checkout = cataloguePage.setCheckoutBtn();
        absComponent.takeScreenshot();


//      Billing address
        checkout.billingForm("Capgeminy", "Tuxtla Gutierrez", "Test 1", "Test 2", "Mexico",
                "29000", "555555", "666666");
        absComponent.takeScreenshot();

////        Shipping Address
        absComponent.waitElementToAppear(By.cssSelector("input[id='PickUpInStore']"));
        checkout.shippingForm();
        absComponent.takeScreenshot();


////        Shipping method and payment method
        checkout.paymentForm();
        absComponent.takeScreenshot();

////        Payment information

        checkout.infoForm("Darwin Jimenez Hernandez","5482340924944396", "699");
        absComponent.takeScreenshot();

//        Confirm order

        checkout.confirmInfo();
        absComponent.takeScreenshot();

////      Logout

        absComponent.logOut();
        absComponent.takeScreenshot();

        tearDown();


    }


}
