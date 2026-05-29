package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	//constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitBtn;
	
	//action methods
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}

	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}

	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void checkPolicy() {
		chkPolicy.click();
	}
	
	public void clickSubmit() {
		submitBtn.click();
	}

}
