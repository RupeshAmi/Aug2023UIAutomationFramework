package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;


public class AccountPageTests extends BaseTest{
// all the page test case are extending Basetest where we have all @beftetest and @aftertest methods
	
	//the main pre-condition for Accountpage is user should be logged in
	
	
	
	@BeforeClass
	public void accSetUp() {
		//we are doing this to get account page reference ,
		//the moment we have account page reference, we can use it in my
		//testng annotation, so i can call my account page method and i can verify
		//accPage=new AccountsPage(driver);
		//if somebody give me reference in which parameter i have to store
		//in account page we are using loginpage reference
		//when we design UI automation framework/ any framework, specially in POM
		//You have 10 pages in your application and 10 pages you have created
		//is it manadatory that for every page , we have to write seperate test class
		//we can create only three test classes,and from test class i have to call respected method in page class
		
		//module base test cases we have to write and complete my scenario
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		
	}
	
	@Test
	public void verifyAccountPageTitle() {
		Assert.assertEquals	(AppConstants.ACCOUNT_PAGE_TITLE,"My Account");
		
	}
	@Test
	public void verifyAccountPageURL() {
		Assert.assertTrue(AppConstants.ACC_PAGE_URL_FRACTION.contains("https://naveenautomationlabs.com/"));
		
	}
	@Test
	public void isLogoutLinkAvailable() {
		Assert.assertTrue(accPage.isLogoutLinkExists());
	}
	@Test
	public void isSearchLinkAvailable() {
		Assert.assertTrue(accPage.issearchFieldExists());
	}
	
	@Test
	public void accheadersCountTest() {
		List<String>actualAccPageHaederList=accPage.getaccountsHeaders();
		System.out.println(actualAccPageHaederList);
		Assert.assertEquals(actualAccPageHaederList.size(), AppConstants.Acc_PAGE_HEADERS_COUNT);
		//assert also provide one method to comapre two list
		Assert.assertEquals(actualAccPageHaederList, AppConstants.ACCOUTS_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void search() {
		SearchResultPage=accPage.dosearch("MacBook");
		productInfoPage=SearchResultPage.selectProduct("MacBook Pro");
	String actualParoductHeader=productInfoPage.getProductHeaderName();
	Assert.assertEquals(actualParoductHeader, "MacBook Pro");
	}
}
