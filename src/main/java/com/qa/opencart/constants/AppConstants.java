package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	
	//these is constant class where we keep out homepage, loginpage title,username and password
	// we are only keep those things which will never change(remain constant) thoroughout application
	
	//static i am creating because i dont need to create object of this class, 
	//i can directly use them
	//naming convetion for final variable is capital letter with underscore
	
public static final String LOGIN_PAGE_TITLE="Account Login";
public static final String ACCOUNT_PAGE_TITLE="My Account";
public static final String LOGIN_PAGE_URL_FRACTION="route=account/login";
public static final String ACC_PAGE_URL_FRACTION="account/account";

public static final int SHORT_DEFAULT_WAIT=5;
public static final int MEDIUM_DEFAULT_WAIT=5;
public static final int LONG_DEFAULT_WAIT=5;


public static final int POLLING_DEFAULT_WAIT=2;
public static final Object Acc_PAGE_HEADERS_COUNT=4;

public static final String USER_REGISTRATION_SUCCESS_MESSAGE="Your Account Has Been Created!";
public static final List<String>ACCOUTS_PAGE_HEADERS_LIST=Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");

public static final String REGISTER_SHEET_NAME = "register";
public static final String PRODUCT_SEARCH_DATA = "product";
}
