package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	// all the common locators..senkeys and driver.finelement we store it here
	private WebDriver driver;
	// write now driver is null
	// so we create construor and pass driver
	// to use local variable, we use this.driver

	// private variable use in public constructor , encapsulation concept

	public By getBy(String locatorType, String locatorvalue) throws Exception {
		By by = null;

		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorvalue);
			break;
		case "name":
			by = By.name(locatorvalue);
			break;
		case "class":
			by = By.className(locatorvalue);
			break;
		case "xpath":
			by = By.xpath(locatorvalue);
			break;
		case "css":
			by = By.cssSelector(locatorvalue);
			break;
		case "linktext":
			by = By.linkText(locatorvalue);
			break;
		case "partialinktext":
			by = By.partialLinkText(locatorvalue);
			break;
		case "tagname":
			by = By.tagName(locatorvalue);
			break;
		default:
			System.out.println("wrong locator type is passed" + locatorType);
			throw new Exception("wrong locator type");

		}
		return by;

	}

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

//instead of oveloading all the methods we can overload getElement method
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement getElement(String locatorType, String locatorValue) throws Exception {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

	// for string type
	// locator type="id", locatorvalue="input-email",value="tom@gmail.com"
	public void doSendkeys(String locatorType, String locatorvalue, String value) throws Exception {
		getElement(locatorType, locatorvalue).sendKeys(value);
	}

	public void doSendkeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorvalue) throws Exception {
		getElement(locatorType, locatorvalue).click();
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doElementGetText(String locatorType, String locatorvalue) throws Exception {
		return getElement(locatorType, locatorvalue).getText();
	}

	// we have to fetch multiple elements// we have to use List <webelemet> method

	// List<WebElemet>count=driver.findElements(By.tagName("a"));

	public List<WebElement> getElemets(By locator) {
		return driver.findElements(locator);
	}

	public int GetTotalCount(By locator) {
		return getElemets(locator).size();
	}

	// to fetch all text from links
	public List<String> getElemetText(By locator) {
		List<WebElement> elelist = getElemets(locator);
		ArrayList<String> listOfElements = new ArrayList<String>();

		for (WebElement list : elelist) {
			String text = list.getText();
			if (text.length() != 0) {
				listOfElements.add(text);
			}
		}
		return listOfElements;
	}

	// to fetch all attributes from link
	public List<String> getElementAttribute(By locator, String Value) {
		List<WebElement> listOfAttr = getElemets(locator);
		ArrayList<String> al = new ArrayList<String>();
		for (WebElement attriList : listOfAttr) {
			String newList = attriList.getAttribute(Value);
			al.add(newList);

		}
		return al;
	}

	public void doSelectDropdownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropdownByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);

	}

	public void doSelectDropdownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);

	}

	// select partucular value from dropdown
	public void selectDropdownOption(By locator, String drodownvalue) {
		Select select = new Select(getElement(locator));// passing webelement

		List<WebElement> OptionsList = select.getOptions();// in build method of select class to get all options
		System.out.println(OptionsList.size());
		for (WebElement e : OptionsList) {
			String text = e.getText();
			System.out.println("alldropdowntext:" + text);// giving all text from dropdown
			if (text.equalsIgnoreCase("India")) {
				e.click();
				break;
			}
		}
	}

	// now generic mathod for size

	public List<String> getDropdownOption(By locator) {
		Select select = new Select(getElement(locator));// passing webelement

		List<WebElement> OptionsList1 = select.getOptions();// in build method of select class to get all options
		List<String> OptionsList = new ArrayList<String>();
		System.out.println(OptionsList1.size());
		for (WebElement e : OptionsList1) {
			String text = e.getText();
			OptionsList.add(text);
		}
		return OptionsList;
	}

	public boolean checkSingleElementPresent(By locator) {
		return driver.findElements(locator).size() == 1 ? true : false;

	}

	public boolean checkElementPresent(By locator) {
		return driver.findElements(locator).size() >= 1 ? true : false;
	}

	// *----------wait method-----------------
	// whenever you want wait, use doclick method with wait , whenever you dont need
	// wait use normal doclick method
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement waitForVisibilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		// Difference between presenceOfelement and VisibilityOfelement
		// presenceOf element-checking inside DOM
		// VisibilityOfelement-checking on DOM and also on Page and height and width is
		// greater than 0.
	}

	public void doClickWithwait(By locator, int timeout) {
		waitForVisibilityOfElement(locator, timeout).click();
	}

	public void doSendkeysWithwait(By locator, String value, int timeout) {
		waitForVisibilityOfElement(locator, timeout).sendKeys(value);
	}

	public List<WebElement> waitForPresenceOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	// below methos will check fraction value of title

	public String waitFortitleContains(String titleFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		// if condition is failed, it will not go and check else part,
		// it will give you timeout exception
		// we dont need to write else part because until method never return false

		try {
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
			/*
			 * else {
			 */ System.out.println("tittle value is not present");
		} catch (TimeoutException e) {
			System.out.println("title value is not present");
			e.printStackTrace();

		}
		return null;

	}
	// below methos will full value of title
	// If you want to get full value

	public String waitforTitleIs(String title, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {

				return driver.getTitle();
			}

		} catch (TimeoutException e) {
			System.out.println(title + "title value is not present..");
		}
		return null;

	}

	// to check the fraction of url
	public String waitForURLContains(String URLFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		// if condition is failed, it will not go and check else part,
		// it will give you timeout exception
		// we dont need to write else part because until method never return false

		try {
			if (wait.until(ExpectedConditions.urlContains(URLFraction))) {
				return driver.getCurrentUrl();
			}
			/*
			 * else {
			 */ System.out.println("URL value is not present");
		} catch (TimeoutException e) {
			System.out.println("URL value is not present");
			e.printStackTrace();

		}
		return null;

	}
	// to validate full URL value

	public String waitforURLIs(String URL, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlToBe(URL))) {

				return driver.getCurrentUrl();
			}

		} catch (TimeoutException e) {
			System.out.println("title value is not present..");
		}
		return null;

	}

	// In real time we always wait for the alert otherwise it will always give No
	// such Alert exception
	// Alert is not only check but also swtich to alert, so no need to write
	// driver.switch to
	public Alert waitForJsAlert(int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void AcceptJsAlert(int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		waitForJsAlert(timeOut).accept();
	}

	public void disMissJsAlert(int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		waitForJsAlert(timeOut).dismiss();
	}

	public String getJsJsAlertText(int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return waitForJsAlert(timeOut).getText();
		// System.out.println(alertText);
	}

	public void enterVAlueOnJsAlert(int timeOut, String value) {
		waitForJsAlert(timeOut).sendKeys(value);

	}
	// frame method by using wait

	public void waitForFrameByLocator(String locator, int timeOuts) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrameByindex(int farmeIndex, int timeOuts) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(farmeIndex));
	}

	public void waitForFrameByIDOrName(int IDOrName, int timeOuts) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}

	public void waitForFrameByElement(WebElement frameElement, int timeOuts) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	// Browser window handle
	public boolean ChecknewWindowexist(int timeOut, int expectedNumberOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
				return true;
			}
		} catch (TimeoutException e) {
			System.out.println("number of windows are not same");
		}
		return false;

	}

//An expectation of checkimg an eleemnt is visble and enabled such that you can click it
	public void clickElementwhenReady(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		} catch (TimeoutException e) {
			System.out.println("element is not clickable");
		}
	}

	// fluentwait utility

	public  WebElement waitForElementWithFluentwait(By locator, int timeOut, int intervaTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervaTime)).withMessage("timeout is  done...element is not found")
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}}

// eog
