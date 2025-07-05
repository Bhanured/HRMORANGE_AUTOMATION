package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseClass.base;
import pageObjectClasses.Admin_functionality;
import pageObjectClasses.loginFun;

public class AdminModule extends base {
	 SoftAssert softAssert = new SoftAssert();
	

	// This class is intended
	@Test(priority = 1)
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
	     
	     
	     
	    softAssert.assertAll();

	     

}
}
