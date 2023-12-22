package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.AppCacheStatus;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By firstName=By.name("firstname");
	private By lastName=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	
    private By agreeCheckBox=By.name("agree");
    private By continueButton=By.xpath("//input[@type='submit' and @value='Continue']");
    
    private By subScribeYes=By.xpath("(//input[@type='radio' and @name='newsletter'])[1]");
    private By subScribeNo=By.xpath("//input[@type='radio' and @name='newsletter'])[2]");
    
    private By successMessg=By.xpath("//div[@id='content']/h1");
    private By logOutLink=By.linkText("Logout");
    private By RegisterLInk=By.linkText("Register");
    
    
  //create const.. for register page
  	public RegisterPage(WebDriver driver) {
  		this.driver=driver;
  		eleUtil=new ElementUtil(this.driver);
  		
  		
  	}
  	public boolean userRegistration(String firstName,String lastName,String email
  			,String telephone,String password,String subscribe) {
  		
  		eleUtil.waitForVisibilityOfElement(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstName);
  		eleUtil.doSendkeys(this.lastName, lastName);
  		eleUtil.doSendkeys(this.email, email);
  		eleUtil.doSendkeys(this.telephone, telephone);
  		eleUtil.doSendkeys(this.password, password);
  		eleUtil.doSendkeys(this.confirmPassword, password);
  	
  		if(subscribe.equalsIgnoreCase("yes")) {
  			eleUtil.doClick(subScribeYes);
  		}
  		else {
  			eleUtil.doClick(subScribeNo);
  		}
  		
  		eleUtil.doClick(agreeCheckBox);
  		eleUtil.doClick(RegisterLInk);
  		String sucessmesg=eleUtil.waitForVisibilityOfElement(successMessg, 10).getText();
  		System.out.println(sucessmesg);
  		
  		//before returning true, please logout so that next user will be enetr
		eleUtil.doClick(logOutLink);
		//after logout, please clcik on registration link
		eleUtil.doClick(RegisterLInk);
		 if(sucessmesg.contains(AppConstants.USER_REGISTRATION_SUCCESS_MESSAGE)) {
		 return true;
		 } 
		 else { return false; 
		 }
		 
  		
  	}
}
	
	
		

