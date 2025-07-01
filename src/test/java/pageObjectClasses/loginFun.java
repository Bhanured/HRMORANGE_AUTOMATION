package pageObjectClasses;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginFun {
    WebDriver driver;
    WebDriverWait wait;
    public loginFun(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement username;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement password;
    

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginBtn;
    @FindBy(xpath="//span[@class='oxd-userdropdown-tab']") WebElement profile_click_;
    @FindBy(xpath ="//a[normalize-space()='Logout']") WebElement logoutbtn;

    public void loginPage(String name, String pass) {
       
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(username));
        js.executeScript("arguments[0].value='';", username);
        username.sendKeys(name);

        wait.until(ExpectedConditions.visibilityOf(password));
        js.executeScript("arguments[0].value='';", password);
        password.sendKeys(pass);
    }

    public void btn() {
        
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }
    
    public void logoutbtn()
    {
    

    	wait.until(ExpectedConditions.elementToBeClickable(profile_click_)).click();
    	wait.until(ExpectedConditions.elementToBeClickable(logoutbtn)).click();

    	
    }
}
