package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regSetUp() {

		registerPage = loginPage.navigateToRegisterPage();
	}
	
	//to generate random email, as everytime email get consume
	public String getRandomEmailID() {
		return "testautomation"+System.currentTimeMillis()+"@opencart.com";
		//it will generate random emailID everytime
		//testautomation121221@opencart.com
		//UUID-special class-import from java.util and append with gmail.com
		//return "testautomation" +UUID.randomUUID()+"gmail.com";
	}
	
	//This is hard coded data but hard coded data is in my dataprovider
	//Do you really want to maintain data in excel sheet??
	//How does it matter? as i keep data in excel sheet
	//data is hard coded in excel sheet
	//in mordern frameworks we dont maintain data in excel sheet
	//it is very old way of doing things
	//if you want to read data from excelfile, you need third party Api(Apache api)
	//you should have proper licence for microsoft excel
	//sometimes excel got currepted, somebody delete data from excel
	//sheet is mismatch, entire work sheet got deleted
	//there is no point of maintaing data in excel file
	//mordern framework data is maintaining in sheet only for easy maintainance
	//when multiple people are working on excel sheet while doing automation
	//there is always chance somebody delete data/ changing data
	//what if i want to maintain 10 userdata??
	//what is need of doing testing with 10 userdata? dumping in database.
	//application flow is working fine for three user will working fine for 10 user also
	//we always use delta approach- we always check important product
	//if imp product working fine for this category, it will work for other category
	
	
	
	
	/*
	 * @DataProvider
	 *  public Object[][] userRegTestData() {
	 * 
	 * return new Object[][] { {"ami","darji","8799908899", "B@nking1", "yes"},
	 * {"nikhil","shah","879990008899", "B@nking11", "yes"},
	 * {"Rupesh","darji","123344455", "B@nking111", "yes"},
	 * {"abc","xyz","879999999", "B@nking90", "yes"} };
	 * 
	 * }
	 */
	
	@DataProvider 
	//sheetname we will provide in appconstant, so everyone can use
	public Object[][] getUserRegTestExcelData() {
	Object regData[][]=	ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	return regData;
	}
	@Test(dataProvider="getUserRegTestExcelData")
	public void userRegTest(String firstname, String lastname, String phoneno, String password, String Subscribe) throws InterruptedException {
		//random emailId will be called always give random emailId
		boolean isRegDone=registerPage.userRegistration(firstname, lastname, getRandomEmailID(), phoneno, password, Subscribe);
Assert.assertTrue(isRegDone);
//Thread.sleep(5000);
	}

}









