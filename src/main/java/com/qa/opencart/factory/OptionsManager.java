package com.qa.opencart.factory;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	// i will take care of oprtion//any headless or incognito

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

//so whenver somebody create object of optionsmanager i will suplly property here
	public OptionsManager(Properties prop ) {// give me prop here
		this.prop = prop;
	}

	public ChromeOptions getChromeOption() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
		}
		return co;
	}
	public FirefoxOptions getFirefoxOption() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			fo.addArguments("--incognito");
		}
		return fo;
	}
}