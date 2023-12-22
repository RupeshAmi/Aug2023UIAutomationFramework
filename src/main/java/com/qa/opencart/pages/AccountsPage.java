package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	ElementUtil util = new ElementUtil(driver);
	private ElementUtil eleUtil;// creating object of element util

	// creating By locator
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By accHeaders = By.xpath("//div[@id='content']//h2");
	private By SearchField = By.name("search");
	private By SearchIcon = By.xpath("//button[@type='button' and @class='btn btn-default btn-lg']");

	// page const...
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	// page actions

	public String accountPageTitleTest() {
		// String title = driver.getTitle();
		String title = AppConstants.ACCOUNT_PAGE_TITLE;
		System.out.println("My account title:" + title);
		return title;
	}

	public String accountPageURLTest() {
		// String Url = driver.getCurrentUrl();

		String Url = AppConstants.ACC_PAGE_URL_FRACTION;
		System.out.println("accountpage url:" + Url);
		return Url;
	}

	public boolean isLogoutLinkExists() {
		return driver.findElement(logoutLink).isDisplayed();

	}

	public void logout() {
		if (isLogoutLinkExists()) {
			eleUtil.doClick(logoutLink);
		}

	}

	public boolean issearchFieldExists() {
		return driver.findElement(searchField).isDisplayed();

	}

	public List<String> getaccountsHeaders() {
		List<WebElement> headerList = driver.findElements(accHeaders);
		List<String> headersvsList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headersvsList.add(text);
		}
		return headersvsList;
	}

	public SearchResultPage dosearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(SearchField, AppConstants.LONG_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(SearchField, AppConstants.LONG_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(SearchIcon);
		// once you click on serch, it will land on result serach page
		// so here we are returning searchresultpage object

		return new SearchResultPage(driver);

		// This is called TDD-Test driven development
		// when i start writing my test cases, and when i start writing methods
		// on the fly i start creating classes, methods, everything according to my
		//test requirement.-this is called my test driven approach
	}
}
