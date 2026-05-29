package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import utlities.DataProviders;

public class TC_LoginDDT extends BaseClass {
	
	//we are providing "dataProviderClass=DataProviders.class" because dataProvider are present in diff class i.e under utils/DataProviders.java
	//if it was in same class, then we don't need to provide
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")  
	public void verify_loginDDT(String email,String pwd,String exp) {
		try {
			//Homepage
			logger.info("....started LoginDDT Test...");
			HomePage hp = new HomePage(driver);
			hp.clickAccount();
			hp.clickLogin();
			
			//LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
			
			MyAccount my = new MyAccount(driver);
			boolean displayed = my.isMyaccountDisplayed();
			
//			Data is valid -> login pass -> test pass -> logout
//			Data is invalid -> login fail -> test fail 
			
			 if(exp.equalsIgnoreCase("valid"))
		        {
		            Assert.assertTrue(displayed);

		            if(displayed)
		            {
		                my.clickLogout();
		            }
		        }
		        else if(exp.equalsIgnoreCase("invalid"))
		        {
		            Assert.assertFalse(displayed);
		        }
			
			logger.info("....Finished LoginDDT Test...");
			
		}catch(Exception e) {
			Assert.fail();
		}

	}
}
