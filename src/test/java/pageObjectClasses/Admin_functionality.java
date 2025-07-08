package pageObjectClasses;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Admin_functionality {

	WebDriver driver;
	WebDriverWait wait;

	public Admin_functionality(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement adminTab;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	private WebElement addbtn;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	private WebElement savebtn;

	@FindBy(xpath = "//span[text()='Required']")
	private List<WebElement> requiredFieldErrorMsgs;

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[1]")
	public WebElement userRoleDropdown;
	@FindBy(xpath="//input[@placeholder='Type for hints...']") WebElement employeeNameInput;
	@FindBy(xpath ="(//div[@class='oxd-select-text--after']//i)[2]")  WebElement selectStatus;
	@FindBy(xpath = "(//input[contains(@class,'oxd-input oxd-input--active')])[2]") WebElement userNameInput;
    @FindBy(xpath = "(//input[@type='password'])[1]") WebElement passwordInput;
    	@FindBy(xpath = "(//input[@type='password'])[2]") WebElement confirmPasswordInput;
	// ========================== Actions ===========================

	public void clickAdminTab() {
		try {
			System.out.println("🔵 Clicking on Admin tab");
			wait.until(ExpectedConditions.elementToBeClickable(adminTab)).click();
		} catch (Exception e) {
			System.out.println("❌ Failed to click Admin tab");
			e.printStackTrace();
		}
	}

	public void addBtn() {
		try {
			System.out.println("🔵 Clicking on Add button");
			wait.until(ExpectedConditions.elementToBeClickable(addbtn)).click();
		} catch (Exception e) {
			System.out.println("❌ Failed to click Add button");
			e.printStackTrace();
		}
	}

	public void saveBtn() {
		try {
			System.out.println("🔵 Clicking on Save button");
			wait.until(ExpectedConditions.elementToBeClickable(savebtn)).click();
		} catch (Exception e) {
			System.out.println("❌ Failed to click Save button");
			e.printStackTrace();
		}
	}

	public List<WebElement> getRequiredFieldErrorMsg() {
		try {
			System.out.println("🔍 Checking for required field error messages");
			By errorLocator = By.xpath("//span[text()='Required']");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(errorLocator));
			return driver.findElements(errorLocator);
		} catch (Exception e) {
			System.out.println("⚠️ No required field messages found: " + e.getMessage());
			return List.of(); // return empty list
		}
	}

	public void selectUserRole(String role) {
		try {
		
			System.out.println("Opening user role dropdown");
			wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

			if(role.equalsIgnoreCase("Admin")) {
				System.out.println("Selecting 'Admin' role");
				WebElement adminRole = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Admin')]")));
				adminRole.click();
			} else {
				System.out.println("Selecting 'ESS' role");
				WebElement ESSRole = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option'])[3]")));
				ESSRole.click();
			} 
			

			
		} catch (Exception e) {
			System.out.println("❌ Failed to select user role: " + role);
			e.printStackTrace();
		}
	}
	public void enterEmployeeName(char name) throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    Actions actions = new Actions(driver);
	    
	    
	    WebElement employeeField = driver.findElement(By.xpath("//input[contains(@placeholder, 'Type for hints...')]"));
	    employeeField.clear();
	    
	    employeeField.sendKeys(Character.toString(name));
	    actions.moveToElement(employeeField).perform();

	    Thread.sleep(2000); // Wait for suggestions to load
	    // Wait until at least one suggestion appears
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-autocomplete-option']/child::span")));

	    // Always re-fetch the element after wait to avoid stale reference
	    List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='oxd-autocomplete-option']/child::span"));
	    
	    if (!suggestions.isEmpty()) {
	        System.out.println("Selecting employee: " + suggestions.get(0).getText());
	        suggestions.get(1).click();
	    } else {
	    	System.out.println("No employee suggestions found for input: " + name);
	    }
	}
	public void status(String statusOption) {
		WebElement k=wait.until(ExpectedConditions.visibilityOf(selectStatus));
		k.click();
		
		if(statusOption.equalsIgnoreCase("Enabled")) {
			WebElement enabledOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Enabled']")));
			enabledOption.click();
		} else if(statusOption.equalsIgnoreCase("Disabled")) {
			WebElement disabledOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Disabled']")));
			disabledOption.click();
		} else {
			System.out.println("Invalid status option: " + statusOption);
		}

	
	}
	public void enterUserName(String userName) {
		try {
			System.out.println("Entering username: " + userName);
			wait.until(ExpectedConditions.visibilityOf(userNameInput)).sendKeys(userName);
		} catch (Exception e) {
			System.out.println("Failed to enter username: " + userName);
			e.printStackTrace();
		}
		
	}
	public void enterPassword(String password1, String password2) {
		try {
			System.out.println("Entering password1: " + password1);
			wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password1);
			System.out.println("Entering password2: " + password2);
			wait.until(ExpectedConditions.visibilityOf(confirmPasswordInput)).sendKeys(password2);
		} catch (Exception e) {
			System.out.println("Failed to enter u: " + password1 + " or " + password2);
			e.printStackTrace();
		}
}
	public void verifySuccessMessage() throws TimeoutException {
		WebElement toast = new WebDriverWait(driver, Duration.ofSeconds(5))
		    .until(ExpectedConditions.presenceOfElementLocated(
		        By.xpath("//p[contains(@class,'oxd-text--toast-message')]")));
		System.out.println("✅ Toast message found: " + toast.getText());


}}
