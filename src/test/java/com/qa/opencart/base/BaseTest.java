package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.pages.productInfoPage;

public class BaseTest {

	protected WebDriver driver;
	//there is no realtion between basetest and driverfactory
	//these are two independent classes
	//they are not parent child, not coming from same family
	
	DriverFactory df;
	//we have to maintain all page references inside basetest
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultPage SearchResultPage;
	protected productInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	protected Properties prop;
	//if we make it public anyone can access it
			//but i make protected, you can go directly in your child class 
			//or in a different package if your child class is available, 
			//i just want to give access to those classes
	
	//softassert is a class
	
	protected SoftAssert SoftAssert;
	
	@Parameters({"browser" })
	@BeforeTest
	public void setup(String browserName) {
		df=new DriverFactory();
		prop=df.initProp();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			
		}
		//provide prop ref and fetach whatever you want to fetch
		driver=df.initDriver(prop);
		SoftAssert=new SoftAssert();
		//if we make it public anyone can access it
		//but i make protected, you can go directly in your child class 
		//or in a different package if your child class is available, 
		//i just want to give access to those classes
		loginPage=new LoginPage(driver);
		
	}
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}
}
