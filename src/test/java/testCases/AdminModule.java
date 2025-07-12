package testCases;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseClass.base;
import jdk.internal.org.jline.utils.Log;
import pageObjectClasses.Admin_functionality;
import pageObjectClasses.loginFun;

public class AdminModule extends base {
	 SoftAssert softAssert = new SoftAssert();
	

	// This class is intended
	/*@Test(priority = 1)
	public void adminTest_TC21() throws IOException, InterruptedException {
		loginFun login = new loginFun(driver);
		System.out.println("login module is running");
		login.loginPage("Admin", "admin123");
		login.btn();
		Admin_functionality adminFunctionality = new Admin_functionality(driver);
		Thread.sleep(2000); // Wait for login page to redirect
		adminFunctionality.clickAdminTab();
		 String  currenturl= driver.getCurrentUrl();
		softAssert.assertEquals(currenturl,"https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers","URL IS NOT MACTHING THAT MEANS ITS NOT GOING INTO ADMIN SETTINGS");
	
		
		
	}
	
	 @Test(priority = 2)
	 public void adminTest_Tc_22to23() throws InterruptedException {
		
	     loginFun login = new loginFun(driver);
	     Admin_functionality adminFunctionality = new Admin_functionality(driver);
	     
	     System.out.println("Login module is running");

	     login.loginPage("Admin", "admin123");
	     login.btn();
	     Admin_functionality adminFunctionality2= new Admin_functionality(driver);
	     adminFunctionality2.clickAdminTab();
	     adminFunctionality.addBtn();
	     Thread.sleep(2000); // Wait for the Add button to be clickable
	     try {
	    	 System.out.println("selecting user role");
	    	 
	   
	         
	    	 adminFunctionality.selectUserRole("Admin");
	    //	 softAssert.assertTrue(adminFunctionality.adminRole.isDisplayed(), "User role dropdown is not displayed");
	         System.out.println("User role selected successfully");
	     }
	     catch (Exception e) {
	         System.out.println(" Failed to select user role");
	         e.printStackTrace();
	     }
	     
	     
	     
	    
	     

}
	

	 @Test(priority = 3)
	 public void adminTest_Tc_24_CreateAndSearchUser(ITestContext context) throws InterruptedException, TimeoutException {
	     // reuse driver if using hybrid setup

	     loginFun login = new loginFun(driver);
	     Admin_functionality adminFunctionality = new Admin_functionality(driver);

	     System.out.println("Login module is running");
	     login.loginPage("Admin", "admin123");
	     login.btn();

	     adminFunctionality.clickAdminTab();
	     adminFunctionality.addBtn();
	     Thread.sleep(2000);

	     adminFunctionality.selectUserRole("ESS", context); // 👈 now passing context
	     adminFunctionality.enterEmployeeName(context, 's'); // 👈 now passing context
	     adminFunctionality.status("Enabled", context);      // 👈 now passing context

	     String name = RandomStringUtils.randomAlphanumeric(6);
	     adminFunctionality.enterUserName(name);
	     adminFunctionality.enterPassword("Bhanu@123", "Bhanu@123");
	     adminFunctionality.saveBtn();
	     adminFunctionality.verifySuccessMessage();

	     // 🔁 Now directly verify that the user appears
	     adminFunctionality.systemUser(name,context); 
	     Thread.sleep(4000);// reuse same object/drivers
	     Log.info("" + name + " is created successfully");
	     
	     logger.info("Deleting user: " + name);
	     Assert.assertEquals(adminFunctionality.deleteUser(name),true); // 👈 now passing context
	     
	 }
	 */

	 @Test(priority =4 )
	 public void adminTest_Tc_Deleteuser(ITestContext context) throws InterruptedException, TimeoutException {
	     // reuse driver if using hybrid setup

	     loginFun login = new loginFun(driver);
	     Admin_functionality adminFunctionality = new Admin_functionality(driver);

	     System.out.println("Login module is running");
	     login.loginPage("Admin", "admin123");
	     login.btn();

	     adminFunctionality.clickAdminTab();
	     adminFunctionality.addBtn();
	     Thread.sleep(2000);

	     adminFunctionality.selectUserRole("ESS", context); // 👈 now passing context
	     adminFunctionality.enterEmployeeName(context, 's'); // 👈 now passing context
	     adminFunctionality.status("Enabled", context);      // 👈 now passing context

	     String name = RandomStringUtils.randomAlphanumeric(6);
	     adminFunctionality.enterUserName(name);
	     adminFunctionality.enterPassword("Bhanu@123", "Bhanu@123");
	     adminFunctionality.saveBtn();
	     adminFunctionality.verifySuccessMessage();

	     // 🔁 Now directly verify that the user appears
	     
	     logger.info("Deleting user: " + name);
	     Thread.sleep(4000);// reuse same object/drivers
	     adminFunctionality.deleteUser(name); // 👈 now passing context
	     
	 }


	 
}
