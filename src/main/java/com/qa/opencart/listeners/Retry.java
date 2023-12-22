package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 3;

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) { // Check if test not succeed
			if (count < maxTry) { // Check if maxtry count is reached
				count++; // Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
				return true; // Tells TestNG to re-run the test
			} else {
				iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return false;
	}
}
//retry logic- If specific  test case is getting failed , You can give one more attempt
//one more attempt until your test case is getting pass
//TestNg listener will do that
//add two ,listerner and also in xml file
//user is not going to retry 3times, so it is always debatable to perform retry logic in framework
//we have to find to find out in first attempt-x-path issue, script issue
//sometimes anti-pattern , wrong practices
//it is bad practice

