package traineeselenium.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import traineeselenium.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory

    @FindBy(id = "Email")
    WebElement getEmail;

    @FindBy(id="Password")
    WebElement getPassword;

    @FindBy(css="input[value='Log in']")
    WebElement login;

    public void loginApp(String email, String password){
        getEmail.sendKeys(email);
        getPassword.sendKeys(password);
        login.click();
    }

    public void goTo(){
        driver.get("https://demowebshop.tricentis.com/electronics");
    }
}
