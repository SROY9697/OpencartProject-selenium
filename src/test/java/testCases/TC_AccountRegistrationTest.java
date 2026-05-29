 package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression","Master"})
	public void account_registration() 
	{
		logger.info("...started testing TC_AccountRegistrationTest...");
		//creating obj of homepage & registrartion page class
		try {
			HomePage hp = new HomePage(driver);
			hp.clickAccount();
			logger.info("...clicked on Account link...");
			hp.clickRegister();
			logger.info("...clicked on Register link...");
			
			AccountRegistrationPage reg = new AccountRegistrationPage(driver);
			
			logger.info("...started registering user...");
			reg.setFirstName("john");
			reg.setLastName("Doe");
			reg.setEmail(randomEmail());
			reg.setPassword("123456");
			reg.checkPolicy();
			reg.clickSubmit();
			
			logger.info("...Done registering user...");
			Assert.assertTrue(true);
			
		}catch(Exception e) {
			logger.error("test failed...");
			//logger.debug("Debug logs...");
			Assert.fail();
		}
		
		
	}
	
	

}
