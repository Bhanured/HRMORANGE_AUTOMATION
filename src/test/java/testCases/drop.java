package testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import baseClass.base;
import pageObjectClasses.Admin_functionality;
import pageObjectClasses.loginFun;

public class drop  {
	@Test(priority = 1)
	public void adminTest_TC21() throws IOException, InterruptedException {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement s= driver.findElement(By.xpath("//span[text()='Admin']"));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(s)).click();
		WebElement a=driver.findElement(By.xpath("//button[normalize-space()='Add']"));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(a)).click();
		WebElement userRoleDropdown = driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]"));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(userRoleDropdown)).click();
		WebElement adminRole = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='option'])[2]")));
		adminRole.click();


	}

}
