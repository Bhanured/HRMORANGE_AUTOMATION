package testCases;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class prcatice {
	WebDriver driver;
	
	@Test
	public void testscreenshop() throws InterruptedException
	{driver=new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		TakesScreenshot ts =(TakesScreenshot) driver;
		File sourceFile= ts.getScreenshotAs(OutputType.FILE);
		File targetFile=new File(System.getProperty("user.dir")+"\\Screenshots\\fullpage.png");
		sourceFile.renameTo(targetFile);
		Thread.sleep(2000);
		driver.quit();
	     
	}

}
