package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class practice {
	public static void main(String[] args) {
		System.out.println("This is a practice class.");
		WebDriver driver = new ChromeDriver();
		driver.get("https://en.wikipedia.org/wiki/MS_Dhoni");
		WebElement element = driver.findElement(By.xpath("//div//h2[text()='Domestic career']"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

}
