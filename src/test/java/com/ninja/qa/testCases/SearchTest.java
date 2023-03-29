package com.ninja.qa.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.ninja.qa.baseClass.baseClass;
import com.ninja.qa.pageObjects.HomePage;
import com.ninja.qa.pageObjects.SearchPage;
import com.ninja.qa.utilities.ExtentSparkReport;
import com.ninja.qa.utilities.ScreenshotMethod;

public class SearchTest extends baseClass{
	
	public WebDriver driver;
	HomePage homepage; 
	SearchPage searchpage;
	
	@BeforeMethod
	public void setup() throws IOException
	{		
		loadPropertiesFile();
		dataPropertiesFile();
		driver = openBrowserAndOpenURL(prop.getProperty("browserName"));
		homepage = new HomePage(driver);
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
		homepage.enterProductNameInSearchField(dataprop.getProperty("validProduct"));
		searchpage = homepage.clickOnSearchButton();
				
		Assert.assertTrue(searchpage.verifyValidProduct());
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct()
	{
		homepage.enterProductNameInSearchField(dataprop.getProperty("invalidProduct"));
		searchpage = homepage.clickOnSearchButton();
				
		String actualsearchmssg = searchpage.enterProductNotFoundMssg();
		String expectedsearchmssg = "There is no product that matches the search criteria.";	
		Assert.assertEquals(actualsearchmssg, expectedsearchmssg);
		Assert.fail();
	}
	
	@Test(priority=3,dependsOnMethods= {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct()
	{
		searchpage = homepage.clickOnSearchButton();
				
		String actualsearchmssg = searchpage.enterProductNotFoundMssg();
		String expectedsearchmssg = "There is no product that matches the search criteria.";	
		Assert.assertEquals(actualsearchmssg, expectedsearchmssg);
		
	}
	
	@AfterMethod
	public void teardown() throws IOException 
	{
		driver.quit();
	}
	

}
