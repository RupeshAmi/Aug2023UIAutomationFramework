package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {

		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	// lets say if user wants to sercah for another product
	// we have to provide one testNg annotation here
	// we use @dataProvider and whivh will be attach with one method
	// dataprovider in testNg always return/provide 2D object array
	// because in object array we can mantain any kind of data: int,string
	// we will maitain two column-one for search key(which product),
	// productname(macbook pro),images

	// data provider method says that i am going to provide you data
	// you can use these entire dataset in your test
	// we have to do mapping between dataprovider and @test annotations

	@DataProvider
	public Object[][] getSearchData() {// we are returning 2d object array
		return new Object[][] {

				{ "MacBook", "MacBook Pro", 4 }, // row1//column 1// search key(which product), productname(macbook
													// pro),images
				{ "MacBook", "MacBook Air", 4 }, // row 1//column2
				{ "iMac", "iMac", 3 },
				{ "samsung", "Samsung SynMaster", 1 }
				};
	}
	
	@DataProvider
	public Object[][] getSearchExcelTestData(){
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SEARCH_DATA);
		
	}
	// we have to write dataprovider method name besides test
	// 4 times these method will be running on basis of given data
	// we have to provide parameers
	// number of parameters depends on number of column you have maintain

	@Test
	(dataProvider = "getSearchExcelTestData")
	public void productImagesTest(String searchKey, String productName, int imageCount) {
		SearchResultPage = accPage.dosearch(searchKey);// MacBook
		productInfoPage = SearchResultPage.selectProduct(productName);// MacBook Pro
		// getproductUmagescount is not available
		// so we can create method now, to fulfill my test i need to create
		// number of pages that i have created
		// inside page we have to create method
		// this is again called test driven development approach
		// int actProductImages = productInfoPage.getProductImagesCount();
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);// 4
	}

	@Test
	public void productInfoTest() {
		SearchResultPage = accPage.dosearch("MacBook");// MacBook
		productInfoPage = SearchResultPage.selectProduct("MacBook Pro");// MacBook Pro
		Assert.assertEquals(productInfoPage.getProductImagesCount(), 4);

		Map<String, String> productDeatailsMap = productInfoPage.getProductDeatils();
		// in hashmap-put method to add the value
		// add method to add the value

		// theseare called Hard assertion
		// the moment assertion gets failed, it will not got to next assertion
		// if Brand:apple is failed, it will not go to check avaibility, it will
		// terminate

		// Testng provide one more assertion that is softassertion
		// Differrece between softAssert and assert class
		// Assert class and all methods available in assert class is the hard assertion,
		// all the methods available in assert class is static method
		// softasserrt you have to create object and use reference and same method
		// available in softassertion also
		// softassertion will go to each and everyassertion , dosent matter pass or fail
		// and finally
		// it is manadatory to write assertAll
		SoftAssert.assertEquals(productDeatailsMap.get("Brand"), "Apple");
		SoftAssert.assertEquals(productDeatailsMap.get("Availability"), "In Stock");
		SoftAssert.assertEquals(productDeatailsMap.get("Product Code"), "Product 18");
		SoftAssert.assertEquals(productDeatailsMap.get("Reward Point"), "800");

		SoftAssert.assertEquals(productDeatailsMap.get("price"), "$2,000.00l");
		SoftAssert.assertEquals(productDeatailsMap.get("extraprice"), "@2,000.00");

		SoftAssert.assertAll();// it will tell how many got fail

		// Hard Assertion we have to write when we have to write only one assertion'
		// single one thing we are going to verify

		// when we have multiple check for same functionality, and i dont want to write
		// multiple test for that
		// in that case we use soft assertion
		// in test driven approach, developer start wrting code from test
		// i have to verify something on product page
		// first have to search, on basis of that i start creating methods

	}
}
