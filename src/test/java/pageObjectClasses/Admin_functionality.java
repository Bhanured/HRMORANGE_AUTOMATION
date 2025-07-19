package pageObjectClasses;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

public class Admin_functionality {

	WebDriver driver;
	static WebDriverWait wait;

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
	//@FindBy(xpath="//span[normalize-space()='Should not exceed 40 characters']") WebElement errorExceeding40Chars;
    @FindBy(xpath = "(//label[text()='Username']/following::input)[1]") WebElement userNameSystem;
    @FindBy(xpath = "(//div[@class='oxd-select-text--after']//i)[1]") WebElement SeletcroleSystem;
    @FindBy(xpath = "//input[@placeholder='Type for hints...']") WebElement employeeNameSystem;
    @FindBy(xpath = "(//div[@class='oxd-select-text--after']//i)[2]") WebElement selectStatusSystem;
    @FindBy(xpath = "//button[normalize-space()='Search']") WebElement searchSystem;
    @FindBy(xpath="(//span[@class='oxd-text oxd-text--span'])[1]") WebElement Records;
          @FindBy(xpath = "//div[@class='orangehrm-container']")  WebElement container;
    

    
    
    
    // ========================== Actions ===========================

	public void clickAdminTab() {
		try {
			System.out.println("🔵 Clicking on Admin tab");
			wait.until(ExpectedConditions.elementToBeClickable(adminTab)).click();
		} catch (Exception e) {
			System.out.println(" Failed to click Admin tab");
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

	public void selectUserRole(String role, ITestContext context) {
		try {
		
			System.out.println("Opening user role dropdown");
			wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

			if(role.equalsIgnoreCase("Admin")) {
				System.out.println("Selecting 'Admin' role");
				WebElement adminRole = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Admin')]")));
				adminRole.click();
				context.setAttribute("userRole", "Admin");
			} else {
				System.out.println("Selecting 'ESS' role");
				WebElement ESSRole = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option'])[3]")));
				ESSRole.click();
				context.setAttribute("userRole", "ESS");
			} 
			

			
		} catch (Exception e) {
			System.out.println("Failed to select user role: " + role);
			e.printStackTrace();
		}
	}
	public void enterEmployeeName(ITestContext context,char name) throws InterruptedException {
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
	        context.setAttribute("employename", suggestions.get(0).getText());
	        
	        suggestions.get(0).click();
	    } else {
	    	System.out.println("No employee suggestions found for input: " + name);
	    }
	}
	public void status(String statusOption,ITestContext context) {
		WebElement k=wait.until(ExpectedConditions.visibilityOf(selectStatus));
		k.click();
		
		if(statusOption.equalsIgnoreCase("Enabled")) {
			WebElement enabledOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Enabled']")));
			enabledOption.click();
			context.setAttribute("status", "Enabled");
			
		} else if(statusOption.equalsIgnoreCase("Disabled")) {
			WebElement disabledOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Disabled']")));
			disabledOption.click();
			context.setAttribute("status", "Disabled");
		} else {
			System.out.println("Invalid status option: " + statusOption);
		}

	
	}
	public void enterUserName(String userName) {
		try {
			System.out.println("Entering username: " + userName);
			wait.until(ExpectedConditions.visibilityOf(userNameInput)).sendKeys(userName);
			if(userName.length() > 40) {
				WebElement errorExceeding40Chars = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Should not exceed 40 characters']")));
				System.out.println("Error: Username exceeds 40 characters");
		         Assert.assertTrue(errorExceeding40Chars.isDisplayed(), "Error message for exceeding 40 characters is not displayed");
				
			} else {
				System.out.println(" Username entered successfully: " + userName);
			}
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


}

	public void systemUser(String name1,ITestContext context) throws InterruptedException {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
		WebElement s=wait.until(ExpectedConditions.visibilityOf(userNameSystem));
		s.click();
		s.sendKeys(name1);
		
		WebElement r=wait.until(ExpectedConditions.visibilityOf(SeletcroleSystem));
		r.click();
		if(context.getAttribute("userRole").toString().equalsIgnoreCase("Admin")) {
			WebElement adminOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option'])[2]")));
			adminOption.click();
		} else {
			WebElement essOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option'])[3]")));
			essOption.click();
		}
		
		String emplyename=context.getAttribute("employename").toString();
		WebElement e=wait.until(ExpectedConditions.visibilityOf(employeeNameSystem));
		Actions actions = new Actions(driver);
		e.sendKeys(emplyename);
		Thread.sleep(2000);
		WebElement firstSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option'])[1]")));
		actions.moveToElement(firstSuggestion).click().perform();
		
		
		
		//actions.moveToElement(e).perform();
		//actions.sendKeys(Keys.ENTER).perform();
		WebElement s1=wait.until(ExpectedConditions.visibilityOf(selectStatusSystem));
		s1.click();
		if(context.getAttribute("status").toString().equalsIgnoreCase("Enabled")) {
			WebElement enabledOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Enabled']")));
			enabledOption.click();
		} else {
			WebElement disabledOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disabled']")));
			disabledOption.click();

		}
		WebElement searchButton = wait.until(ExpectedConditions.visibilityOf(searchSystem));
		searchButton.click();
		Thread.sleep(2000);
		WebElement Records= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/span[@class='oxd-text oxd-text--span']")));
		String recordText = Records.getText();
		if (recordText.contains("No Records Found")) {
		    System.out.println("No records found for the search criteria.");
		} else {
		    System.out.println("Records found: " + recordText);
		    Thread.sleep(2000);
		    
		    WebElement firstRecord = wait.until(ExpectedConditions.visibilityOfElementLocated(
		    	    By.xpath("//div[@role='rowgroup']//div[contains(@class, 'oxd-table-card')][1]//div[contains(@class, 'oxd-table-cell')][2]")
		    	));

			String firstRecordText = firstRecord.getText();
		if(firstRecordText.equalsIgnoreCase(name1)) {
			System.out.println("User created successfully with username: " + name1);
		} else {
			System.out.println("User creation failed or username mismatch. Expected: " + name1 + ", Found: " + firstRecordText);
		}
		
}
		
		
}
	public static boolean deleteUser(String name) {
	    try {
	        boolean isDeleted = false;
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".orangehrm-container")));
	        // Get all rows in the user table
	        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border']")));
	        System.out.println("Total users found: " + rows.size());

	        for (int i = 1; i <= rows.size(); i++) {
	            // Get the username from column 2 of each row
	            WebElement usernameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("(//div[@role='row'])["+i+"]/div[2]")));

	            String actualUsername = usernameCell.getText().toString();
	            System.out.println("Checking user: " + actualUsername);
	            

	            if (actualUsername.equalsIgnoreCase(name)) {
	                System.out.println(" User found: " + name);

	                // Click the delete button in column 6
	                WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("(//div[@role='row'])["+i+"]/div[6]/div/button[1]")));
	                deleteButton.click();

	                // Confirm deletion
	                WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//button[normalize-space()='Yes, Delete']")));
	                confirmDelete.click();

	                System.out.println("User deleted: " + name);
	                return true;
	            }
	        }

	        System.out.println("User not found: " + name);
	        return false;

	    } catch (Exception e) {
	        System.out.println("Failed to delete user: " + name);
	        e.printStackTrace();
	        return false;
	    }
	    
	}
	public static String countNumberOfRecords()
	{
		WebElement Recordnumber=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] span[class='oxd-text oxd-text--span']")));
		return Recordnumber.getText();
		
	}
	public void  deleteMultipleUsers()
	{
		wait.until(ExpectedConditions.visibilityOf(container));
		String numbverOfRecordsInDelete = countNumberOfRecords();
		numbverOfRecordsInDelete = numbverOfRecordsInDelete.substring(numbverOfRecordsInDelete.indexOf("(")+1,numbverOfRecordsInDelete.indexOf(")"));
		int numberOfRecords = Integer.parseInt(numbverOfRecordsInDelete);
		if(numberOfRecords == 0) {
			System.out.println("No users to delete.");
			return;
		}
		for(int j=1;j<numberOfRecords;j++)
		{
			WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@role='row'])[2+"+j+"]/div[1]")));
			deleteButton.click();
			
			
		
		}
		try {
		WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Delete Selected']")));
		confirmDelete.click();
		System.out.println("All users deleted successfully");
		}
		catch(Exception e) {
			System.out.println("No users to delete");
			e.printStackTrace();
		}
		
	}
	
}
