package com.qa.opencart.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.factory.DriverFactory;

public class ExtentReportListener implements ITestListener {

	private static final String OUTPUT_FOLDER = "./reports/";
	private static final String FILE_NAME = "TestExecutionReport.html";

	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;

	private static ExtentReports init() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}

		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("Open Cart Automation Test Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", "MAC");
		extentReports.setSystemInfo("Author", "Naveen AutomationLabs");
		extentReports.setSystemInfo("Build#", "1.1");
		extentReports.setSystemInfo("Team", "OpenCart QA Team");
		extentReports.setSystemInfo("Customer Name", "NAL");
		extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));

		return extentReports;
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");

	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		/*
		 * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
		 * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
		 */
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	public synchronized void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " passed!"));
		test.get().pass("Test passed");
	 test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName),
	 methodName).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		String methodName = result.getMethod().getMethodName();
		test.get().fail("Test failed");
test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		String methodName = result.getMethod().getMethodName();

		test.get().skip("Test skipped");

test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName),methodName).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}

//Extendreportliste is class(classname can be anything), internally it is implimenting Itesrlistner
//ItestListener is interface, coming from testNg library
//During execution listener will be activated(mouce hover-A listner for test running)
//we have created couple of variables
//"/.Reports/-go to my project directory and genrate report folder
//File_NAME-this is file name-"TestExecutionfilename will be created"
//threadlocal concept they have applied
//any parallel execution is happening or multiple test cases got pass or failed
//so make sure thread local can manage thread properly, and it will generate report without 
//any overriding or overlaping issue
//intialise method they have written-some custom informtion you can pass
//report name-"as title or header it will be display-"open cart Automation test results"
//system or mac whtever you use
//Author name
//build name
//environment name
//any custom action you want to pass you can pass in key and value formate

//ITestListerners have methods dclaration so Extendreportlistner responsibility to define all the methods
//of interface
//so we have override methods 
//when you go to that interface so many abstrate methods are availble
//on test start what you want, on test failure what you want, test finish what you want



//After jdk 1.8-default method with method body we can have it in interface'
//but they have not implimented-totally blank
//we can override default method
//all default method present in interface is overriden in class with public keyword

//once execution start -we want msg-test suit started
//once execution done- we want msg-test suite is ending

//extent.flush, test .remove-all object please flush it and remove test object from the momory

//once specific test start, i want informtion about that specific test
//methodname, qualifiedname, classname
//setstarttime-how much time specific test is taking
//once test is successfully done -we end the time
//if test is failed-we want screenshot
//if test is skipped-test got skipped, again take the screenshot

//we dont need to call this method, we just need to add java file as xml file
//we just need to amke entry in testnG.xml







//so all this method is overriden with public keyword
//we need to add listner tag in testNg.xml(regression)
//writE classname.packagename in listEner tag
//Report will be generated the way you configure in your templet
//./reports- automatically generated in my root project

//run from testNg
//right click on project, report folder got generated

//right click on report , go to properties and copy path
//paste it in browser
//you can see trile, graphical represntation, test cases pass/fail

//extend report and index.html report give similar kind of data but its all about visualization.
//extend report is better because we have proper graphs and better look and feel.
//you have to depend on third part tool
//if extend report is deprecated in future, thn you have to use some other report
//you can pass env from maven -Denv='qa' and run test case in that particular env also

//if any failure is there you want to attach screenshot also
//you want to attach screenshot for every test or every failure
//one screen is taking 1mb and tomorrow if you have 100 test cases, unecessary it will take 100mb
//we can take screenshot for every test case but it is not good practice
//we usually take screenshot for failure test cases

//who will take screenshot
//there is one medisentityBuilder and it coming from extendreport
//and it has method createscreencaptureFrommpath
//you create screenshot and give me path(string path, title)
//path of parameter
//slenium will take the screenshot of browser
//browser will be interected by selenium
//we will got to driverfactory and create one screenshot method there
//and than we will call that method here

//we are not going to suplly this report
//through jenkins everyone will try to access that
//in real time. Jenkins job will generate this report and screenshot will be attached there
//to get the screenshot we have to refresh it
//every screenshot is having title, it is coming because of listeners
//if you take screenshot for every test cases, than unecessary 200 file will be generated
//so generally we dont prefer that
//once you delete the screenshot we will get it as broken
//we have to decide which report we want-allure report, 


//if we exceuted 5 listener all listerner will keep checking the result-Pass, fail or what
//it will make our exceution slow
//sometimes we have 300-400test case-it might give false result


