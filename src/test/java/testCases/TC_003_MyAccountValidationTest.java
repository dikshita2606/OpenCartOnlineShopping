package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pageObject.LoginPage;
import pageObject.MyAccountPage;
import pageObject.homePage;
import testBase.BaseClass;

public class TC_003_MyAccountValidationTest extends BaseClass {

	@Test(groups = {"sanity","regression","master"})
	public void verifyAccountLogIn(){
		
		logger.info("********* Starting TC_003_AccountLoginTest Starts********");
		//logger.debug("application logs ........");
		try {
			
			//loading config.properties File
			FileReader file = new FileReader("D:/eclipse/eclipse/eclipse-workspace/selenium/openCart/src/test/resources/config.properties");
			p= new Properties();
			p.load(file);
			
			homePage hp = new homePage(driver);
			hp.clickLoginLink();
			
			LoginPage lp = new LoginPage(driver);
					
			lp.setEmailId(p.getProperty("username"));
			logger.info("UserName set successfully");
			lp.setPassword(p.getProperty("password"));	
			logger.info("Password set successfully");		
			lp.clickSignInBtn();
			logger.info("SignIn Btn Clicked");
			
			
			
			
			MyAccountPage ma = new MyAccountPage(driver);	
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
			
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
			String accountName = ma.getAccountInfo();
			if(accountName.contains(p.getProperty("username"))) 
			{
				logger.info("Account Info success: "+accountName);
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Msg validated failed");
				Assert.fail();
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
			String accountInfo = ma.getAccountInfo();
			if(accountInfo.contains("TESTFA")) 
			{
				logger.info("Account Info success: "+accountInfo);
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Msg validated failed");
				Assert.fail();
			}
			
			driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']")).click();
			List<WebElement> options1 = driver.findElements(By.xpath("//div[@aria-hidden='false']//ul[@class='header links']//li"));
			for(WebElement option:options1)
			{
				//System.out.println(option.getText());
				if(option.getText().contains("Sign"))
				{
					option.click();
					System.out.println("Option Selected ");
					break;
				}
			}
			
			
			WebElement createAccount = driver.findElement(By.xpath("//a[normalize-space()='Create an Account']"));
			if(createAccount.isDisplayed())
			{
				logger.info("Sign Out Button Clicked");
				System.out.println("SignOut Clicked");
			}
			else
			{
				logger.info("Sign Out Button Clicked is not success");
				System.out.println("SignOut Clicked is not success");
			}
				
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("test failed");
			Assert.fail();
		}
		logger.info("********* Starting TC_003_AccountLoginTest Ends********");
	}
	

}
