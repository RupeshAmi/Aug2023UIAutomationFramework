package com.qa.opencart.listeners;

	import java.lang.reflect.Constructor;
	import java.lang.reflect.Method;
	import org.testng.IAnnotationTransformer;
	import org.testng.annotations.ITestAnnotation;

	public class AnnotationTransformer implements IAnnotationTransformer {
		@Override
		public void transform(ITestAnnotation annotation, 
				Class testClass, 
				Constructor testConstructor, 
				Method testMethod) {
			annotation.setRetryAnalyzer(Retry.class);
		}
	}

//Allurereport listner
//allurereport is thirt party library, it is huge library	
	//you can generate with java, ruby, python and various other frameworks and tool
	//we have to add allure testNg dependecy in pom.xml
	//we have to configure aspectj also// one more dependency
	//we have to use it with maven and generate the report
	//we need listeners who keep listening tests and generate reports finally
	//TestAllureListner(you can give any name) implementing ITestListener interface
	//and we have number of things we have to overide-on start what you want,on finish what you want
	//once test is getting started what you want, once test is getting failed what you want
	//here screenshot method they haave given
	//it will not generate screenshot in form of png or jpg, we have to generate in form of byte type
	//will convert Byte screenshot into png screenshot
	
	//we have to add dependecy for allure and update the project
	//in our src/test java-go to specific folder and above @test-write one annotation @Description and wrire description
	//you can also define severity level-@Severity-blocker/minor
	//same description will be displayed in report also
	//AT TEST LEvel you can define description ,severity
	
	//you can define Epic,stoty and feature at class level
	//you take reference from Jira to write it: Under epic we have number of stories
	
	//at @step -we can define in page level on top of method
	//what that particular step is doing-@Step-getting login page title
	//if we have another method inside-
	//2Step- we can write waiting for the page title
	
	//same @Step you can write in element util
	//Allure report will not give html file directly
	//right click on project and refresh, it will generate Allure-results folder
	//@Step-
	
	//whatever userstory title you can give same title here
	//Allure report will not give html file directly
	//right click on project- refresh-it will give Allure-results folder
	//you can see json files under reports
	//container.json, some supportive json also
	//we need to install allure server in my system
	//so allure sever read this json file and read this json file
	//it is only one time activity later when we install jenkins
	//there is direct plugin available in jenkins
	//but if we want to see report right now without allure server
	//allure is a web server, that server we have to start in our laptop
	//that server will read this json file
	//we have scoop to install software
	//open powershell terminal(same like connad prompt)
	//in powesrshell run two commnad from scoop.sh
	//once install -write allure serve allure-results
	//allure please serve all reports available in allure-reports folder
	//allure you pick all json file and serve the report
	//it will open server with ip adress
	//this server is my loacl server ip adress, Port number, index.html will generate here
	//detail level reports are coming here
	//graph level-status graph is coming,
	//In severity we have one blocker, one critical,1 normal and 1 minor bug is showing
	//in behaviour tab- we have Epic, feature,userstory
	//this will be super easy for product owner and product manager
	//this is user story, from automation how many test cases are pass and failed here
	//we can not share this url, as it is in my local-no one can access that
	//Any client or manager we will share jenkins URL with them
	//everyone is having access on jenkins
	//every time when you run testNg you have to go to powershell and run the command again
	//to generate new report
	//aany x-path issue or any wait issue, than it will be mark as broken/broken is due to by locator
	//whenever any assertion is getting fail-expected is not equal to actual
	//if you use annotation it is good for documentation purpose
	//this reports are very powerful and easily integrated with jenkins
	//everyone has to follow same process, very good for debugging, very good for maintainance
	//good for junior tester, easy product Po to raed
	//Extend report does not have this many number of feature
	//allure report is dynamic
	//for retry logic it will create a seperate tab
	