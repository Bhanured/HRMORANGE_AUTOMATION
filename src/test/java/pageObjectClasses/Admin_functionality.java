package pageObjectClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		
			System.out.println("🔽 Opening user role dropdown");
			wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

			if(role.equalsIgnoreCase("Admin")) {
				System.out.println("🔵 Selecting 'Admin' role");
				WebElement adminRole = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option'])[2]")));
				adminRole.click();
			} else {
				System.out.println("🔵 Selecting 'ESS' role");
				WebElement ESSRole = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option'])[2]")));
				ESSRole.click();
			} 
			

			
		} catch (Exception e) {
			System.out.println("❌ Failed to select user role: " + role);
			e.printStackTrace();
		}
	}
}
