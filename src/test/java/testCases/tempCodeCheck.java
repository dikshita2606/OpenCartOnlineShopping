package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class tempCodeCheck {
	@Test
	public void temp() {
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();    /*delete all cookies from driver*/
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));   /*Wait for 10 seconds before execution*/
		driver.manage().window().maximize();
		
		driver.get("https://magento.softwaretestingboard.com");
		
		driver.findElement(By.xpath("//div[contains(@class,\"header\")]//li[@class=\"authorization-link\"]/a[contains(text(),\"Sign In\")]")).click();
		
		driver.findElement(By.id("email")).sendKeys("TESTING123@TEST.COM");
		driver.findElement(By.id("pass")).sendKeys("TESTING@123");
		driver.findElement(By.id("send2")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']")).click();
		List<WebElement> options = driver.findElements(By.xpath("//div[@aria-hidden='false']//ul[@class='header links']//li"));

		for(WebElement option:options)
		{
			//System.out.println(option.getText());
			if(option.getText().equals("My Account"))
			{
				option.click();
				System.out.println("Option Selected ");
				break;
			}
		}
	
	}
	
	
}
