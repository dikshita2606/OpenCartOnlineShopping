package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountResgistrationPage;
import pageObject.homePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{ /*Inheritance is applied here*/

	@Test(groups = {"sanity","regression"})
	public void verify_account_registration() {
		
		logger.info("********* Starting TC_001_AccountRegistrationTest Starts********");
		logger.debug("application logs ........");
		try {
			homePage hp = new homePage(driver);
			
			hp.clickCreateAccount();
			
			AccountResgistrationPage rp = new AccountResgistrationPage(driver);
			
			rp.setFirstName(randomeString().toUpperCase());
			logger.info("First Name set");
			rp.setLastName(randomeString().toUpperCase());
			logger.info("Last Name set");
			rp.setEmail(randomeString().toUpperCase()+"@test.com");
			logger.info("Email set");
			String password = randomeAlphanumeric();
			rp.setPassword(password);
			logger.info("Password set");
			rp.setConfirmPassword(password);
			rp.clickContinue();
			logger.info("Continue btn clicked");
			
			String confMsg = rp.getConfirmationMsg();
			//Assert.assertEquals(confMsg, "My Account");
			if(confMsg.equals("My Account")) 
			{
				logger.info("Msg validated success: "+confMsg);
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Msg validated failed");
				Assert.fail();
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("test failed");
			Assert.fail();
		}
		logger.info("********* Starting TC_001_AccountRegistrationTest Ends********");
	}
	
	
}
