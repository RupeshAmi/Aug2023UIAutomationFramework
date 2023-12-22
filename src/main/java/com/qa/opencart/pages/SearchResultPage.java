package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	// for everypage class templet will remain same.
	// its having own constructor, locator, everything
	private WebDriver driver;
	ElementUtil util = new ElementUtil(driver);

	// page const...
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);

	}

	// whenever we have dynamic type of locator, we should not declare at class lvel
//because if have 100 product, we are not going to write 100 product name
	
	public productInfoPage selectProduct(String productName) {
		//create runtime by locator
	//	By productNameLocator=By.linkText(productName);
		//no need to store just aupply it
util.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFAULT_WAIT).click();;

//after clicking on link, it will land to new page

//so responsibility of this class is to return next class page object

return new productInfoPage(driver);
//to fulfil my test this is onether step i have to do on the fly
	}
}
