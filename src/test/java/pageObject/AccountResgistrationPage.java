package pageObject;

/*TEST213@TEST.COM;TEST@123*/
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountResgistrationPage extends basePage{

	WebDriver driver;
	
	public AccountResgistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="firstname")
	WebElement txtFirstName;
	
	@FindBy(id="lastname")
	WebElement txtLastName;
	
	@FindBy(id="email_address")
	WebElement txtEmail;
	
	@FindBy(id="password")
	WebElement txtPassword;
	
	@FindBy(id="password-confirmation")
	WebElement txtconfirmPassword;
	
	@FindBy(xpath="//button[contains(@class,'submit')]")
	WebElement btnContinue;
	
	@FindBy(xpath ="//span[normalize-space()='My Account']")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
		System.out.println("Fname done");
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
		System.out.println("Lname done");
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
		System.out.println("Email done");
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
		System.out.println("Password done");
	}
	
	public void setConfirmPassword(String password) {
		txtconfirmPassword.sendKeys(password);
		System.out.println("Password done");
	}
		
	public void clickContinue() {
		btnContinue.click();
		System.out.println("continue done");
		
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnContinue);
		
		Actions act = new Actions(driver);
		act.moveToElement(btnContinue).click().perform();*/
	}
	
	public String getConfirmationMsg() {
		try {
			return(msgConfirmation.getText());
		}
		catch (Exception e) {
			return(e.getMessage());
		}
	}

}



