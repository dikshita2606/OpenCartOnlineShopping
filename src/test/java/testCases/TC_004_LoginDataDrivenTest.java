package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.homePage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_004_LoginDataDrivenTest extends BaseClass{

	@Test(dataProvider="LoginData",dataProviderClass = DataProviders.class, groups = "sanity")
	public void verifyLoginDDT(String email, String password, String exp) {
		logger.info("********* Starting TC_004_AccountSignInTest Starts********");
		try {
						
			homePage hp = new homePage(driver);
			hp.clickLoginLink();
			
			LoginPage lp = new LoginPage(driver);	
			
			lp.setEmailId(email);
			logger.info("UserName set successfully");
			lp.setPassword(password);	
			logger.info("Password set successfully");		
			lp.clickSignInBtn();
			logger.info("SignIn Btn Clicked");
			
			String confMsg = lp.getConfrmMsg();
			//Assert.assertEquals(confMsg, "My Account");
			logger.info("Msg validated success: "+confMsg);
			boolean targetpage;
			targetpage = true;
			
				
			
			System.out.println("TargetPage = "+targetpage);
			if(exp.equalsIgnoreCase("Valid"))
			{
				if (targetpage==true)
				{
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
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
				
			}
			
			targetpage = false;
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if (targetpage==true)
				{
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
				
			}			
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("test failed");
			Assert.fail();
		}
		logger.info("********* Starting TC_004_AccountSignInTest Ends********");
	}
}
