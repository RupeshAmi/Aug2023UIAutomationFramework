package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	// maintaining excel sheet path(right click copy path from src, put . ahead of
	// it)
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	// when you import use poi.ss model
	// Data driven approach with Excel file/excel sheet
	// excel sheet are flexible, excel sheet are easy to handle
	// user-friendly, everyone knows about excel sheet
	// it should be microsoft excel file
	// sheet1/sheet2 are called worksheets
	// entire book is called work book
	// excelshhet should be part of your framework
	// as you have transfer these whole source code to git repository//you have to
	// suplly data as well
	// we have created test data folder under resources
	// copy and paste the excel file in test data folder
	// we can not read from selenium or testNg or we dont have any direct supprot
	// from java
	// In that case we have to go by third party API , which is Apache poi API
	// very stable and old library, we have to add that dependency in POM.xml file
	// go to excel util in oprncart.utils

	public static Object[][] getTestData(String sheetName) {// create one method
		// get the data from sheet, i will give you sheet name and give me data for that
		// sheet
		// we are going to use two dimentional object array
		// as data is in form of rows and column
		System.out.println("reading test data from sheet" + sheetName);

		Object data[][] = null;
		// fileInput stream class which is help us to connect with excel sheet
		// that says give me file path of excel sheet
		// FileInputStream Ip=new FileInputStream(file);

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);

			// one class is thre WorkbookFactory which is coming from Apache poi api
			// with this class one method is there create method
			// we have to give fileinputstream class object there
			// whatever stream you have connected , give me object of that stream
			// so that we will create excel sheet inside java
			// WorkbookFactory.create(ip) says any exception is coming
			// add one more catch block
			// we can add multiple catch block with one try

			// create method says that /return type is workbook(entore excel sheet)
			try {
				book = WorkbookFactory.create(ip);
				// now form this book we have to go to specific sheet
				// we have to use sheet method

				sheet = book.getSheet(sheetName);

				data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

				for (int i = 0; i <= sheet.getLastRowNum(); i++) {

					for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

						data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
					}
				}

			} catch (InvalidFormatException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return data;
	}

}
