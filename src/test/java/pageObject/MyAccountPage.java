package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends basePage{

	WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//div[@class=\"box-content\"]//p")
	WebElement AccountInfo;
		
	public String getAccountInfo() {
		try {
			return(AccountInfo.getText());
		}
		catch (Exception e) {
			return(e.getMessage());
		}
	}
	
	
	
}
