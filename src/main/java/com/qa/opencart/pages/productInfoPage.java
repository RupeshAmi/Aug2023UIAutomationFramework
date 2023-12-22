package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class productInfoPage {

	private WebDriver driver;
	ElementUtil util = new ElementUtil(driver);
	private By header = By.xpath("//div[@id='content']//h1[text()='MacBook Pro']");
	private By Poductimages = By.xpath("//ul[@class='thumbnails']//img()");
	private By productMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricingData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	// Hashmap will not maintaintain order
	// private Map<String, String> productmap = new HashMap<String, String>();
	// linked hashmap will maintain order, samemachanism as hashmap,only diff
	// maintain order
	// private Map<String, String> productmap = new LinkedHashMap<String, String>();
	// i have declare at global level so i can use it anywhere in class
	// if i want everything in sorted order for key, alphabetic order on basis of
	// key
	private Map<String, String> productmap = new TreeMap<String, String>();
//treemap, linkedhashmap and hashmap- are three child classes for Map interface
	// advantage of top casting with interfaces

	// we have start feeling information in product map
	// Map is a interface and Hapshmap is a class
	// Top casting-child class object refer by parent class reference variable
	// data is in key value formate- so we will use hashmap and data is string
	// key and value both are in string so <String, String>
	// Brand: Apple
	// Product Code: Product 18
	// Reward Points: 800
	// Availability: In Stock

	// page const...
	public productInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);

	}

	public String getProductHeaderName() {

		String headerText = util.doElementGetText(header);
		System.out.println("Product header is:" + headerText);
		return headerText;
	}

	public int getProductImagesCount() {
		int imagesCount = util.waitForVisibilityOfElements(Poductimages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		// int totalNumberOfImages=driver.findElements(Poductimages).size();
		System.out.println("Product" + getProductHeaderName() + "images count" + imagesCount);
		return imagesCount;
		// verify some metadata:product information

	}

	private void getProductMetaData() {
		// store it in list of webelement
		// as method is giving me list
		List<WebElement> metaDataList = util.waitForVisibilityOfElements(productMetaData,
				AppConstants.MEDIUM_DEFAULT_WAIT);
//These is list , first we have to start itrating the list

		for (WebElement e : metaDataList) {
			String metadata = e.getText();// it will provide Brand:Apple as first li is taht
			// now we have to break the value-we need to seperate Brand and color

			// use split here(string method)
			// split on basis of colon
			// Brand is on index 0 and Apple is on index 1
			// String[]spittedvalue=metadata.split(":") and also use trim-to remove space;

			// directly apply index
			// now for llop will start and break the key and value
			// and got to product code avaibility one by one
			String meataKey = metadata.split(":")[0].trim();
			String metaval = metadata.split(":")[1].trim();
			// now start feeling my hashmap

			productmap.put(meataKey, metaval);

		}
	}
	// i am using list and storing and converting inti map
	// Hashmap is a better data collection, which help us to write proper collection

	private void getProductPriceData() {
		List<WebElement> metaPriceList = util.waitForVisibilityOfElements(productMetaData,
				AppConstants.MEDIUM_DEFAULT_WAIT);
		// here first value we have , its not in key value format
		// so just get text of it
		String productPrice = metaPriceList.get(0).getText();
		String productTaxPrice = metaPriceList.get(1).getText().split(":")[1].trim();// Ex Tax: $2000.00
		productmap.put("price", productPrice);
		productmap.put("extraprice", productTaxPrice);
	}

	public Map<String, String> getProductDeatils() {
		// In this product hashmap- we have product headername, complete list of
		// metadata and complete pricelist
		productmap.put("productname", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		System.out.println();
		System.out.println(productmap);
		return productmap;

	}

}
