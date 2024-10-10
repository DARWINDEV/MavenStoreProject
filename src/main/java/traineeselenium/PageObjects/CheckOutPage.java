package traineeselenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import traineeselenium.AbstractComponents.AbsComponents;

public class PaymentPage extends AbsComponents {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

  //  Billind page

    @FindBy(id="BillingNewAddress_Company")
    WebElement companyName;

    @FindBy(id="BillingNewAddress_City")
            WebElement cityName;

    @FindBy(id = "BillingNewAddress_Address1")
            WebElement primaryAddress;

    @FindBy(id = "BillingNewAddress_Address2")
    WebElement secondAddress;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement postalCode;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement phoneNumber;

    @FindBy(id = "BillingNewAddress_FaxNumber")
    WebElement faxNumber;

    @FindBy(xpath = "//div[@id='billing-buttons-container']/input")
    WebElement billingBtn;

    By countryID = By.id("BillingNewAddress_CountryId");

// Shipping Address

    @FindBy(id="PickUpInStore")
    WebElement pickUpCheck;

    @FindBy(xpath="//div[@id='shipping-buttons-container']/input")
    WebElement shippingBtn;

//  Shipping method and payment method

    @FindBy(xpath="//form[@id='co-payment-method-form']//input[@id='paymentmethod_2']")
    WebElement pickPayMent;

    @FindBy(xpath = "//div[@id='payment-method-buttons-container']/input")
    WebElement paymentBtn;


    public void countries(String country){
        Select countryMenu = new Select(driver.findElement(countryID));
        countryMenu.selectByVisibleText(country);
    }

    public void billingForm(String company, String city, String primAddress, String secAddress, String country, String code, String number, String fax){
        companyName.sendKeys(company);
        cityName.sendKeys(city);
        primaryAddress.sendKeys(primAddress);
        secondAddress.sendKeys(secAddress);
        countries(country);
        postalCode.sendKeys(code);
        phoneNumber.sendKeys(number);
        faxNumber.sendKeys(fax);
        billingBtn.click();
    }

    public void shippingForm(){
        pickUpCheck.click();
        shippingBtn.click();
    }

    public void paymentForm(

}
