package pageObjectClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Logoutfun{
	WebDriver driver;
	public Logoutfun(WebDriver driver) {
		// Constructor to initialize the elements
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Username']")
    WebElement username;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement password;
    @FindBy(xpath = "//button[normalize-space()='Login']") WebElement loginBtn;
    
  @FindBy(xpath="//span[@class='oxd-userdropdown-tab']") WebElement profile_click;
    @FindBy (xpath="//a[normalize-space()='Logout']") WebElement logoutbtn;
    
    public void logoutVerify(String name,String pass)
    {
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(name);
    	wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
    	wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    	
    
    	
    }
    public void logoutbtn()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(profile_click)).click();
		wait.until(ExpectedConditions.elementToBeClickable(logoutbtn)).click();
		
	}

}
