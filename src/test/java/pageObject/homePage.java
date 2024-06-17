package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage {

	WebDriver driver;
	
	public homePage(WebDriver driver) {
		super(driver); /*invoking parent class*/
	}
	
	/*Used Page Factory Framework*/
	//Object for create Account Link
	@FindBy(xpath="//a[normalize-space()='Create an Account']")
	WebElement createAccount;
	
	//object for Login Link
	@FindBy(xpath="//div[contains(@class,\"header\")]//li[@class=\"authorization-link\"]/a[contains(text(),\"Sign In\")]")
	WebElement loginLink;
	
	/*Function to click on my account option*/
	public void clickCreateAccount() {
		createAccount.click();
	}
	
	public void clickLoginLink() {
		loginLink.click();
	}
	
}
