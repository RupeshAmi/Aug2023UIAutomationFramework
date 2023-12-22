package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//Testng classes are only and only for tests
//martin flower who designed page object model and other frameworks

@Epic("Epic 100:Design open cart login page")
@Story("US 101: Login page Features")
@Feature("F50:Feature login page")
public class LoginPageTest extends BaseTest{
	
	@Description("login page title tset")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String acttitle=loginPage.getLoginpageTitle();
		Assert.assertEquals(acttitle, AppConstants.LOGIN_PAGE_TITLE);	
	}
	@Description("LoginpageURL test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageURLTest() {
		String acuturl=loginPage.getCurrentUrl();
		Assert.assertTrue(acuturl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	@Description("Verify forgotPassword Link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotPasswordExistTest() {
		Assert.assertTrue(loginPage.isforgotPasswordLinkExist());
		
	}
	@Description("verifying App logo exist test")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(priority=4)
	public void ApplogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
		
	}
	@Description("Verifying user is able to login with correct credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExists());
	}
	//IQ:what is page chaining model/ zic zac pattern
	//because it is creating pattern called zic zac pattern
	//loginpage-click-landing on account page-landing on results page-landing on cart page 
}
