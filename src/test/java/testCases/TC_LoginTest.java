package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;

public class TC_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void account_login() 
	{
		logger.info("....started Login Test...");
		try {
			//Homepage
			HomePage hp = new HomePage(driver);
			hp.clickAccount();
			hp.clickLogin();
			
			//LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			MyAccount my = new MyAccount(driver);
			boolean displayed = my.isMyaccountDisplayed();
			Assert.assertEquals(displayed, true,"Login failed");
			
			logger.info("....Finished Login Test...");
			
		}catch(Exception e) {
			Assert.fail();
		}
		
	}

}
