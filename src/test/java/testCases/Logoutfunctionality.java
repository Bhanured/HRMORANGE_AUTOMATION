package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectClasses.Logoutfun;

import baseClass.base;

public class Logoutfunctionality extends base {

	@Test(priority = 1, description = "Logout functionality test")
	public void TC_1_logout()
	{
		logger.info("starting the logout test INITIALY AT LOGIN PAGE");
		Logoutfun logout = new Logoutfun(driver);
		
		logout.logoutVerify("Admin", "admin123");
		logger.info("Valid credentials entered");
		logout.logoutbtn();
		logger.info("Clicked logout button");
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Logout failed or URL mismatch");
		
		
	}
	@Test(priority = 2, description = "Directlink paste")
	public void TC_2_AND_3() throws InterruptedException
	{
		logger.info("starting the direct link test");
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
		Thread.sleep(2000);
		driver.navigate().back();
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		String actualUrl = driver.getCurrentUrl();
		logger.info("Checking if the page is accessible without login");
		Thread.sleep(2000); // Wait for the page to load
		Assert.assertEquals(actualUrl, expectedUrl, "Its accessing the page without login");
		
	}
}
