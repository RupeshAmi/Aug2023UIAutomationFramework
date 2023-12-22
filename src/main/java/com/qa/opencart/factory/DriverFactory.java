package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
static //Driverfactory  follows sigle eresponsibility principle
	// this driver class is used for driver intilization or property intilization
	// not for incognito
	// create seperate class for driver factory and incognito

	// write the code for driver intialization

	WebDriver driver;// right now driver is null
	Properties prop;
	OptionsManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// instead of void , we have to write webdriver as driver is webdriver(return
	// driver)
//store it in properties reference only
	// call by reference
	public WebDriver initDriver(Properties prop) {// initialize driver on basis of launch browser
		
		
		// use prop.getproperty and pass the key form config file(browser)
		// it will return string

		String browserName = prop.getProperty("browser");

		// write now browser is not coming from property file
//if we want to suppply browser from environment, we have to use System class and pass the key
		// on command line write mvn -clean install -Dbrowser="chrome" Denv="qa"

		//String browserName = System.getProperty("browser");
		System.out.println("browsername name is:" + browserName);
		optionManager = new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//normal driver
			driver = new ChromeDriver(optionManager.getChromeOption());
			//threadlocal driver
			//everythread will get new copy of chromedriver with thread local
			//tlDriver.set(new ChromeDriver(optionManager.getChromeOption()));
			System.out.println("launch chrome browser");
			break;
		case "fireFox":
			driver = new FirefoxDriver(optionManager.getFirefoxOption());
			System.out.println("launch firefox browser");
			//tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOption()));
			break;
		case "edge":
			driver = new EdgeDriver();
			System.out.println("launch edgebrowser");
			
			break;
		case "safari":
			driver = new SafariDriver();
			System.out.println("launch safari browser");
			break;
		default:
			System.out.println("Plaese pass right browsername" + browserName);
			throw new FrameworkException("No browser found");

		}
		// remove below hard coded url value
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		// now do prop.getproperty(url property)
		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//when you use thread local
		//call getdriver method
		//give me copy of that url
		//getDriver().manage().deleteAllCookies();
		

		return driver; // we have driver available now and we will give it to you
		//we have to return getdriver in case of threadlocal
	//	return getDriver();
	}
	//for threadlocal , we have to create one method to get driver
	
	/*
	 * public static WebDriver getDriver() {
	 *  return tlDriver.get(); }
	 */

	// i have to intialise property and i have to read config.properties
	// we have to write java code, selenium can not read config.properties
	// we have to maitain one properies reference at class level
	// properties is a class , which is already in the java
	public Properties initProp() {
		// create object of property
		prop = new Properties();
		// we have to establish connection with the file, (config.property file)
		// we have to use Fileinputstream class and create object of that path and
		// provide path
		// whenever you have to make connection use fileinputstream class
		// right click on cofig.properties file, copy path from src
		// and paste it here in path and put . in stating
		// . means Root of the project
		// than it is saying while doing this any kind of exception is occur,
		// add with try catuch block
		// now connection is established now i have to load all the properties in this
		// object
		// use prop reference and load method is there
		// give me fileinputstream class object
		// file input stream class make the connection between file
		// once connection has been established, whatever key and value properties are
		// available
		// it will given to loaded class object
		// so in this all the properties has been loaded, in the form of key and value
		// formate

		// and while doing this loading process, any kind of exeception is comeing, pls
		// handle that
		// we can have multiple catch with one try
		// after all these is done return properties class object
		// where should i call these method

		// maven clean install= -Denv="qa"
		FileInputStream ip = null;
		prop = new Properties();
		// which class is responsible so we can check that env=qa
		// this can be done by System class
		// System.getProperty method is available

		// if you are passing any environment variable with the help of maven you can
		// read it
		// in this case key is env
		// getproperty method give us string and we have to store inside string
		String envName = System.getProperty("env");
		System.out.println("env name is:" + envName);

		// if you are not passing anything we are running on QA environment
		// so if envname is null than go to config.qa.properties and run command from
		// there
		try {
			if (envName == null) {
				System.out.println("Your environment is null...so running test on QA environment");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("Plaese pass valid environment name" + envName);
					// if my env is wrong i dont want to run my test on qa environment by default
					// we have to throw framework exception
					// we dont need break statement as throw and break can not be together
					// now fileinputstream saying surround with try catch block
					// so instaed of writing so many try catch just write one try on top and one on
					// bottom

					throw new FrameworkException("Wrong env Name" + envName);

				}
		} catch (FileNotFoundException e) {
		}
		try {
			// fileinput stream object will be intiliase with respective file name
			// than you have to load property with respective file name
			// than you return,it will return prop that means it will return respective
			// environment properties
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		/*
		 * try { FileInputStream ip = new
		 * FileInputStream("./src/test/resources/config/config.properties");
		 * prop.load(ip); } catch (FileNotFoundException e) { e.printStackTrace(); }
		 * catch (IOException e) { e.printStackTrace(); } return prop;
		 */
	
}
public static String getScreenshot(String methodName) {
	
	//Takescreenshot is interface coming from selenium
	//give me driver and we will typecast this driver into takescreenshot
	//(Takesscreenshot)driver()//entire thing will become takescreenshot
	//getscreenshot method is available-it says capture screenshot and store it in specified location
	//we have to provide file type
	//how will you get your project directory -using system.getproperty
	//user.dir//current user directory
	//property name is already predefined
	//got o your screenshot and under screenshot whatever you are passing
	//we are appending with unique methodname-System.currentTimeinMillis
	//screenshot will be taken in file object as a java memory
	//we have to move our file from one location to another location
	//how can i supply file object to String path
	//that why we are creating one more file object which is pointiong to this particular path
	//we use fileHandler-from source file to destination
	
	File srcFile=(File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String path=System.getProperty("user.dir")+"/screenshot/"+methodName+""+System.currentTimeMillis()+".png";
	
	File destination=new File(path);
	
	try {
		FileHandler.copy(srcFile, destination);
	}
	catch(IOException e) {
		e.printStackTrace();
	}

	return path;
}

public static WebDriver getDriver() {
	
	return null;
}







}
//we have to write code for different environment in such a way that we can execute some maven command
// public Properties initProp() {
// maven clean install and we have to pass some argument
// we have to tell maven that maven please run the command with argument i am
// passing
// that argument we have to pass it with -Denv="qa"
// i want to run my test cases on qa env- -Denv="qa"--we have to spply
// when we run this command, first it will go to pom.xml file
// it will check all the dependency
// it will go to your compiler plug-in, and it will comppile your code
// it will go to surefire plug-in, from sure fire plugin it will go to
// regression.xml
// from regression.xml it will try to run test cases
// loginpage test, to basetest, from basetest to the inti properties
//intialise property will decide from which property file , we want to get the configuration data
//how will you run

//go to cmd, termninal write mvn clean install -Denv(same variable name we have to pass it in your script)
//C:\Users\Ami\eclipse-workspace\Aug2023POMSeries>mvn clean install -Denv="qa"
//so that system class read the environment, system class is responsible to read environment

//Output
//env name is:qa
//launch chrome browser
//Loginpage title:Account Login
//loginpage url:route=account/login
//creds are:By.id: input-email:Kari@123
//user is logged in
//browsername name is:chrome

//swich case: classic example is multi environment set up
//developer write the same logic and  swtich the logic according to env you are passing
//How you make sure that your test are running on multienvironment set up
//we have to configure it propery
