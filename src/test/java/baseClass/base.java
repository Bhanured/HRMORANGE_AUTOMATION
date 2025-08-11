package baseClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

public class base {
	
	public static WebDriver driver;
	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	@BeforeMethod
	
	/*public void setUp(String br)
	{
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver();
		break;
		case "edge" : driver=new EdgeDriver();
		break;
		case "firefox" : driver=new FirefoxDriver();
		break;
		default : System.out.println("Browser not supported, please use Chrome, Edge or Firefox.");
		return;
			
		}
		*/
		
	public void setUp() {
		
		 driver = new EdgeDriver();
		try
		{
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			Thread.sleep(4000);
			
		}
		catch(Exception e)
		{
			logger.error("Failed to initialize the WebDriver",e);
			e.getMessage();
		}
	}
	 @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
	 public String captureScreen(String testName) {
		    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		    String screenshotName = testName + "_" + timeStamp + ".png";

		    // Full path where screenshot is saved
		    String fullPath = System.getProperty("user.dir") + "/Screenshots/" + screenshotName;

		    try {
		        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		        File dest = new File(fullPath);
		        src.renameTo(dest);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    // ✅ Return relative path for ExtentReport
		    return fullPath;
		}
}
