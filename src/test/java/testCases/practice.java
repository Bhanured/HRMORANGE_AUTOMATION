package testCases;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class practice {

	
	@Test
	public void actionsPractice()
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://practice-automation.com/hover/");
		Actions actions = new Actions(driver);
		WebElement d=driver.findElement(By.xpath("//h3[text()='Mouse over me']"));
			
		actions.moveToElement(d).perform();
		System.out.println("Mouse hovered over the element :  "+d.getLocation());
		
		
		
		
	}
}
