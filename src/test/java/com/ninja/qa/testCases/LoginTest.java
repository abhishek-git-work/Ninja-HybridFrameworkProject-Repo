package com.ninja.qa.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninja.qa.baseClass.baseClass;
import com.ninja.qa.pageObjects.AccountPage;
import com.ninja.qa.pageObjects.HomePage;
import com.ninja.qa.pageObjects.LoginPage;
import com.ninja.qa.utilities.utils;

public class LoginTest extends baseClass{
	
	public WebDriver driver;
	LoginPage loginpage;
	HomePage homepage;
	AccountPage accountpage;
	
	@BeforeMethod
	public void setup() throws IOException
	{
		loadPropertiesFile();
		dataPropertiesFile();
		driver = openBrowserAndOpenURL(prop.getProperty("browserName"));
		
		homepage = new HomePage(driver);		
		homepage.clickOnMyAccount();
		loginpage = homepage.clickSelectLoginOption();
	}
	
	@Test(priority=1,dataProvider="excelDataForLogin")
	public void verifyLoginWithValidCredentials(String email, String password)
	{	
		loginpage.enterEmailAddress(email);
		loginpage.enterPassword(password);
		accountpage = loginpage.clickOnLoginButton();

		Assert.assertTrue(accountpage.getDisplayStatusOfEditAccountInfo());
	}
	
	@DataProvider(name="excelDataForLogin")
	public Object[][] exceldataprovider() throws IOException
	{
		Object[][] data = utils.testDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{
		loginpage.enterEmailAddress(utils.timestamp());
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		
		String actualmssg = loginpage.retrieveEmailPasswordNotMatchingWarningMssg();
		String expectedmssg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualmssg, expectedmssg);
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{
		loginpage.enterEmailAddress(utils.timestamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		
		String actualmssg = loginpage.retrieveEmailPasswordNotMatchingWarningMssg();
		String expectedmssg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualmssg, expectedmssg);
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		
		String actualmssg = loginpage.retrieveEmailPasswordNotMatchingWarningMssg();
		String expectedmssg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualmssg, expectedmssg);
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutCredentials()
	{
		loginpage.clickOnLoginButton();
		
		String actualmssg = loginpage.retrieveEmailPasswordNotMatchingWarningMssg();
		String expectedmssg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualmssg, expectedmssg);
	}
	

	@AfterMethod
	public void teardown() 
	{
		driver.quit();
	}
	
	
	
	
	
	
	
}
