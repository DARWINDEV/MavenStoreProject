package traineeselenium.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import traineeselenium.AbstractComponents.AbsComponents;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws IOException {

        String productName = "Blue Jeans";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();

        AbsComponents absComponent = new AbsComponents(driver);
        absComponent.logIn();
        takeScreenshot(driver);

        landingPage.loginApp("d12311203@gmail.com", "D4rwinTestFun");
        takeScreenshot(driver);

        driver.findElement(By.xpath("//div[@class='header-menu']//a[@href='/apparel-shoes']")).click();
        takeScreenshot(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='item-box']")));

        List<WebElement> products = driver.findElements(By.cssSelector("div[class='item-box']"));

        products.stream().filter(product ->
                product.findElement(By.cssSelector("h2")).getText().equalsIgnoreCase(productName)).findAny().get().click();
        takeScreenshot(driver);

        WebElement cantidad = driver.findElement(By.id("addtocart_36_EnteredQuantity"));
        cantidad.clear();
        cantidad.sendKeys("5");
        driver.findElement(By.cssSelector("div[class='add-to-cart-panel'] input:last-of-type")).click();
        takeScreenshot(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bar-notification")));
        String message = driver.findElement(By.cssSelector("#bar-notification p")).getText();
        Assert.assertTrue(message.equalsIgnoreCase("The product has been added to your shopping cart"));
        takeScreenshot(driver);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#bar-notification"))));


        driver.findElement(By.cssSelector("li#topcartlink")).click();
        takeScreenshot(driver);

        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.id("checkout")).click();
        takeScreenshot(driver);

        //        Billing address
        driver.findElement(By.id("BillingNewAddress_Company")).sendKeys("Capgemini");

        Select countryMenu = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
        countryMenu.selectByVisibleText("Mexico");


        driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys("Tuxtla Gutierrez");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys("Test 1");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address2']")).sendKeys("test2");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("99999");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']")).sendKeys("555555");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_FaxNumber']")).sendKeys("888888");
        driver.findElement(By.xpath("//div[@id='billing-buttons-container']/input")).click();
        takeScreenshot(driver);

//        Shipping Address
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='PickUpInStore']")));
        driver.findElement(By.cssSelector("input[id='PickUpInStore']")).click();
        driver.findElement(By.xpath("//div[@id='shipping-buttons-container']/input")).click();
        takeScreenshot(driver);

//        Shipping method and payment method
        driver.findElement(By.xpath("//form[@id='co-payment-method-form']//input[@id='paymentmethod_2']")).click();
        driver.findElement(By.xpath("//div[@id='payment-method-buttons-container']/input")).click();
        takeScreenshot(driver);

//        Payment information
        driver.findElement(By.xpath("//input[@id='CardholderName']")).sendKeys("Darwin Jimenez Hernandez");
        driver.findElement(By.xpath("//input[@id='CardNumber']")).sendKeys("5482340924944396");
        driver.findElement(By.xpath("//input[@id='CardCode']")).sendKeys("699");
        driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/input")).click();
        takeScreenshot(driver);


        //confirm order
//        String total = driver.findElement(By.cssSelector("span[class*='product-price order-total']")).getText();
        driver.findElement(By.xpath("//div[@id='confirm-order-buttons-container']/input")).click();
//        Assert.assertTrue(total.equalsIgnoreCase(String.valueOf(5.00)));
        takeScreenshot(driver);

        //Validar mensaje

        String confirmMessage = driver.findElement(By.xpath("//div[@class='title']")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Your order has been successfully processed!"));
        takeScreenshot(driver);

//        Cerrar sesion
        driver.findElement(By.cssSelector("[href*='/logout']")).click();
        takeScreenshot(driver);



    }

    public static void takeScreenshot(WebDriver driver) throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String date = getDate();
        FileUtils.copyFile(src, new File("Evidencias\\screenshot_"+ date +".png"));
    }

    public static String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
        LocalDateTime now = LocalDateTime.now();
        String formated = dtf.format(now);
        System.out.println("Formated: " + formated);
        return formated;
    }
}
