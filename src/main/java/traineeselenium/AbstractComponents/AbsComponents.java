package traineeselenium.AbstractComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AbsComponents {

    WebDriver driver;

    public AbsComponents(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[href*='/login']")
    WebElement loginBtn;

    @FindBy(css = "a[href*='/logout']")
    WebElement logOutBtn;



    public void takeScreenshot() throws IOException {
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

    public void waitElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }


    public void logIn(){
        loginBtn.click();
    }


    public void logOut(){
        logOutBtn.click();
    }
}
