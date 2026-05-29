package testCases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public WebDriver driver;
	public Logger logger; 
	public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException 
	{
		
		//Loading config.prpperties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase()) 
		{
		case "chrome" :  driver = new ChromeDriver(); break;
		case "edge" :  driver = new EdgeDriver(); break;
		case "firefox" :  driver = new FirefoxDriver(); break;
		default : System.out.println("invalid browser name"); return;
		
		}
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
//		    driver.get("http://localhost/opencartsite/");
            driver.get(p.getProperty("appURL"));  //getting value from config.peroperties file
		    driver.manage().window().maximize();
		    System.out.println(driver.getTitle());
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master","Datadriven"})
	public void teardown() {
		driver.close();
	}
	
	
	public String randomEmail() {
		long timestamp = System.currentTimeMillis();
        String email = "user" + timestamp + "@mail.com";
        return email;
	}

}
