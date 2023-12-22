
public class MavenInstallation {
// we have to install maven
//we have created maven project but that maven is already there in our eclipse.
	// thatz why we are able to craete Pom.xml file and everything

	// From command line we have to exceute some test cases , i have to exceute some
	// maven command
	//when we really need to run test cases from command line than we need Maven also here.
	
	
	// we have to configure maven in our machine just like we configure java
	//when ever you join new company eclipse, JDk and MAven is also manadatory.
	//whatever Maven we have that maven is only for that eclipse.
	
	//open cmd and write mvn -version
	//if some Apache maven version is coming, it means yes , you have MAven available
	//make sure you have JDK version available in your system
	//if not install watch Naveen vedio
	//Apache MAven(3.8.6)-Java version-18.0.1.1
	/*
	 * Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63) Maven home:
	 * C:\Program Files\apache-maven-3.8.6 Java version: 18.0.1.1, vendor: Oracle
	 * Corporation, runtime: C:\Program Files\Java\jdk-18.0.1.1 Default locale:
	 * en_CA, platform encoding: UTF-8 OS name: "windows 11", version: "10.0", arch:
	 * "amd64", family: "windows"
	 */
	
	
	//What is purpose of having MAven
	//Maven is build automation tool, it is actually building the project.
	//Maven can create your jar, run test cases, 
	// compile your entire code,whatever source code you have written under main java or test java
	
	
	//right click on project in eclipse, right click, go to properties-copy path
	//go to cmd write cd( change directory) and paste the path
	//when you run tree command, you will see that all these java file we have created
	//to clear console in command prompt- write cls and press enter
	
	//lets say when you right click in eclpise , and  some build and jars are missing
	//how to do excatly same thing from command line
	
	//mvn clean install will check entiremaven  project , it will compile your project and test cases
	
	//any dependency availble in pom.xml file it will read those dependency 
	//if something is missing it will download according to version mention in pom.xml file for all dependency
	//it will up to date your project
	
	//when you run maven clean install command
	//it will clean the project(clean is plugin which is already there in maven)
	//when you write clean with maven
	//if you have any target folder, we will delete this target folder
	//when ever you create maven project , by defalut target folder is craeted
	//first it will clean that(previous output file or result is there it will clean everything)
	//with compiler plugin , it will try to compile entire project
	//it will collect all you test resources(excel file)
	//it will comile test code also(whatever you write in test java)-it will copile
	//with help of sure fire plug in, it will try to run your test
	
	//Maven clean intall-delete target forlder
	//-compile plugin-ompile code-collect resources and comile test code
	//from test resource(excel file)-with interanl surefire plugin , it will run your test
	
	
	//i have added pom.xml file or some test cases, do you really want to run your test cases everytime
	//if you have 500 test cases and you write mvn clean install
	//everytime all those 500 test cases will be running and i have to wait for build
	// i have added some utilities, i have updated some code
	//please simple clean the project and download all the dependecy
	//in that case we have to ignore or skip the test cases
	//for that we have command maven clean install -DskipTests=true(T capital, -D is command line argument)
	// to read this command- maven please install  everything clean the target forlder,pls ignore the test cases
	//don't run the test cases, just build the project
	
	//any kind of compilation error you are getting or any dependency eclipse could be able to download.
	//this process will do it.
	//right click on project refresh go to target folder one jar file will be craeted
	//that is normal jar, i can send/publish these jar to anyone and they can start using my project with these jar
	
	//whenever we run this command maven clean install, it will comile the code, 
	//it will go to src/test/java and whatever tsets you are having it it will try to run
	// we have test cases and we have dependency under base test
	//we dont need to run base test as we dont have any test under base test
	//we have to run it from individual classes
	// we have to create test runner
	
	//we have to create our test runner under resources, because there we have our xml file
	//create regression suite there
	
	
	//when you run code from command line -maven clean install
	//it will first go and check do you have pom.xml file
	//in pom.xml file, it will go and check do you have plug in-two plugin
	                                                         //  1.compiler plugin
	                                                          // 2.sure fire plugin
	//compiler will compile the code
	//IQ:sure fire plugin is responsible for running test cases in maven
	//uder surefire plugin , we will give path of testrunner.xml file
	//surefire plugin say that we will go to this testng.xml  runnerfile
	//it will go to regression.xml file we have created
	//under that we have created different test block according to their respective test
	//it will go to src/test/java-where all the test classes we have created
	//registration page, accountpage test like wise
	//they all are extending baseclass(testbase calss)
	//execution will start in this manner
	
	//go to pom.xml and go to plugin section
	//plugin section we will write under build section
	
	
	//sequesnce 
	//pom.xml
	    //-create surefire plugin
	      //-create testNg.xml file-
	         //multiple test blocks
	           //test block will got to individuall test classes- 4 test block(everyone has parent base class)
	//when you run code from command line 
	
	
	
	//how to check pom.xml is there or not: with dir command
	
	
	
	
	
	
	
	
}
