package com.qa.opencart.factory;

public class ThreadLocalConcept {

//Threadlocal
	// used in parallel exceution
	// when you run 10, 20, 30 ,50 test cases fine
	// when you run 300-400 test cases in pararlel mode
	// are you sure it will work fine most of the time
	// parrallel exceution is very unpredictable behaviour , sometimes some thread
	// will clash with other thread
	// intialise driver method we only write once'
	// we want to give mechanism in our framework that whenever any thread is coming
	// when we call intiliase driver method and returning driver, you always get
	// fresh driver
	// i really want to run my test cases on 15 thraeds
	// we want to make sure that every thread will get local copy/ indivisual copy
	// of driver
	// so advantage is that you have your own driver , you continue with login test,
	// you continue with registration page

	// so now you are making sure that your thread management is good
	// no chance of dead lock condition'
	// so when we generate exent reports or allure reports, this reports are nver be
	// overlaped
	// bacuse it might override some status sometimes

	// its only java feature
	// Threadlocal is classname already in java
	// ThreadLocal says you have to give me generics, on which entity or in which
	// class or interface
	// you want to apply thread local
	// public static Threadlocal<Webdriver>tlDriver=new Threadlocal<WebDriver>();
	// comimg from java.lang class
	// Thread local having two methods
	// set and get method
	// set method says thet younhave to give me webdriver
	// tldriver.set(new Chromedriver);

	// whenever you are using thread local , you have to make one method getdriver
	// method

//public static Webdriver getDriver getDriver(){
	// return tlDriver.get();

//whenver you use tthread local concept set the driver and get the driver 
	// both are static so we can ca;ll it from class name and will not impact your
	// parallel excecution

}
