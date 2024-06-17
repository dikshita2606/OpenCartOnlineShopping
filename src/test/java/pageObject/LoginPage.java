package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends basePage{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="email")  
	WebElement emailId;
	
	@FindBy(id="pass")
	WebElement password;
	
	@FindBy(id="send2")
	WebElement signInBtn;
	
	@FindBy(xpath="//div[@class='panel header']//li[contains(@class,\"greet welcome\")]")
	WebElement AccountName;
	
	public void setEmailId(String email)
	{
		emailId.sendKeys(email);
		System.out.println("Email Id is set");
	}
	
	public void setPassword(String pass)
	{
		password.sendKeys(pass);
		System.out.println("Password is set");
	}
	
	public void clickSignInBtn()
	{
		signInBtn.click();
		System.out.println("Email Id is set");
	}
	
	public String getConfrmMsg() {
		try {			
			return(AccountName.getText());
		}
		catch (Exception e) {
			return(e.getMessage());
		}
	}
}
