package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// for everypage we have webdriver and webdriver is private
	// so no other class can access it
	private WebDriver driver;
	
	//now we are calling generic methods from element util
	private ElementUtil eleUtil;//import it

	// by locators or object repository or page object repository

	// we have to maintain by locators and by locators we have to maintain with
	// private
//i am writing my locators as private
	// as these locators are specific to my page
	// if i make it public any one can access and do modification of it
	// i want to retrict the use of locators
	// otherwise any one can make object of loginpage and access it
	// within the class you can access , outside class no one can access
//all the page classes are classic example of encapsulation
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password11");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By registeLink=By.linkText("Register");
//Pageclass is representing page locators and page behavior
	
	// how will you supply the driver
	// as write now driver is null-private WebDriver driver;
	// for everypage i can not write driver=new chromedriver

	// page constructor we will create it, and we will make it public
	// otherwise no one can create constructor of this class
	// when i make object of loginpage , somebody will give me driver and same
	// driver
	// i will give it to my private driver

	// loginpage says that you give me driver same driver we will assign it to
	// private driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		//create object of element util and pass the constructor
		
		eleUtil=new ElementUtil(driver);
	}
//pagefactory @findby is about to deprecate, @findby creating webelemnt in begning itself
	//@findby-page classes loaded, all webelemnet created, you are hiting server back to back
	// and if you are not using all the webelmet , it will give error, 
	
	// page actions/methods: which will define behaviour of specific feature
	// you should not write any testng code or testng aassertion here
	
	//now we are using private variables in public layer-Encaplsulation
	//page classes are classic example of encapsulation
	//all by locators are maintain with private keyword and page actions
	//any one call this method just to check behaviour of the page
	//and internally they are using private By locators
	
	//object oriented encapulation concept in POM.
	
	//encpsulation is not only getter and setter, private variable called by
	//public method is also encapsulation.
//now remove hard coded value 
	
	@Step("getting login page title")
	public String getLoginpageTitle() {
		//String title = driver.getTitle();
		String title=AppConstants.LOGIN_PAGE_TITLE;
		System.out.println("Loginpage title:" + title);
		return title;
	}
@Step("loginpage URL test")
	public String getCurrentUrl() {
		//String Url = driver.getCurrentUrl();
		String Url=AppConstants.LOGIN_PAGE_URL_FRACTION;
		System.out.println("loginpage url:" + Url);
		return Url;
	}
@Step("checking forgot password link exist")
public void ClickOnregisterLink() {
	
}
	public boolean isforgotPasswordLinkExist() {
		return driver.findElement(forgotPwdLink).isDisplayed();

	}
	
	
	@Step("checking logo exist")
	public boolean isLogoExist() {
		return driver.findElement(logo).isDisplayed();
	}
	@Step("username is :{0} and password :{1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("creds are:"+username+":"+pwd);
		driver.findElement(username).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		

		System.out.println("user is logged in");
		//after click on login button we are landing on Accountpage
		//whenever you are writing page classes or page objects concept
		//if you are clicking on something and landing on new page than
		//its dologin method responsibility to return the nexe landing page class object
		
		return new AccountsPage(driver);
	}
		public RegisterPage navigateToRegisterPage() {
		//we are returning next landing page class object
		//so return type will be classname.
		eleUtil.waitForVisibilityOfElement(registeLink,AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
		}
	
	
	
}