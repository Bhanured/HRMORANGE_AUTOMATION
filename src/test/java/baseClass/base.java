package baseClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

public class base {
	
	public static WebDriver driver;
	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	@BeforeSuite
	public void setUp()
	{
		try
		{
			driver=new EdgeDriver();
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
	 @AfterSuite
	    public void tearDown() {
	        driver.quit();
	    }
	  public String captureScreen(String name) {
		
		return null;
	 }

}
