package com.ninja.qa.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.qa.baseClass.baseClass;
import com.ninja.qa.pageObjects.AccountSuccessPage;
import com.ninja.qa.pageObjects.HomePage;
import com.ninja.qa.pageObjects.RegisterPage;
import com.ninja.qa.utilities.utils;

public class RegisterTest extends baseClass{
	
		public WebDriver driver;
		RegisterPage registerpage;
		HomePage homepage; 
		AccountSuccessPage accountsuccess;
		
		@BeforeMethod
		public void setup() throws IOException
		{
			loadPropertiesFile();
			dataPropertiesFile();
			driver = openBrowserAndOpenURL(prop.getProperty("browserName"));
			
			homepage = new HomePage(driver);
			homepage.clickOnMyAccount();
			registerpage = homepage.clickSelectRegisterOption();
		}
		
		@Test(priority=1)
		public void verifyRegisterAccountWithMandatoryFields()
		{			
			registerpage.enterfirstname(dataprop.getProperty("firstname"));
			registerpage.enterlastname(dataprop.getProperty("lastname"));
			registerpage.enteremail(utils.timestamp());
			registerpage.enterphonenumber(dataprop.getProperty("phoneNumber"));
			registerpage.enterpassword("12345");
			registerpage.enterconfirmpassword("12345");
			registerpage.clickOnAgreebutton();
			accountsuccess = registerpage.clickOncontinuebutton();			
			
			String actualsuccessmssg = accountsuccess.checkAccountSuccessMssg();
			String expectedseccessmssg = "Your Account Has Been Created!";			
			Assert.assertEquals(actualsuccessmssg, expectedseccessmssg);
		}
		
		@Test(priority=2)
		public void verifyRegisterAccountWithAllFields()
		{	
			registerpage.enterfirstname(dataprop.getProperty("firstname"));
			registerpage.enterlastname(dataprop.getProperty("lastname"));
			registerpage.enteremail(utils.timestamp());
			registerpage.enterphonenumber(dataprop.getProperty("phoneNumber"));
			registerpage.enterpassword("12345");
			registerpage.enterconfirmpassword("12345");
			registerpage.clickOnnewsletterOption();
			registerpage.clickOnAgreebutton();
			accountsuccess = registerpage.clickOncontinuebutton();
			
			String actualsuccessmssg = accountsuccess.checkAccountSuccessMssg();
			String expectedseccessmssg = "Your Account Has Been Created!";			
			Assert.assertEquals(actualsuccessmssg, expectedseccessmssg);
		}
		
		@Test(priority=3)
		public void verifyRegisterAccountWithExistingEmail()
		{
			registerpage.enterfirstname(dataprop.getProperty("firstname"));
			registerpage.enterlastname(dataprop.getProperty("lastname"));
			registerpage.enteremail(prop.getProperty("validEmail"));
			registerpage.enterphonenumber(dataprop.getProperty("phoneNumber"));
			registerpage.enterpassword("12345");
			registerpage.enterconfirmpassword("12345");
			registerpage.clickOnAgreebutton();
			registerpage.clickOncontinuebutton();
			
			String actualsuccessmssg = registerpage.emailAlreadyExistWarningMssg();
			String expectedseccessmssg = "Warning: E-Mail Address is already registered!";			
			Assert.assertEquals(actualsuccessmssg, expectedseccessmssg);
		}
		
		@Test(priority=4)
		public void verifyRegisterAccountWithoutFillingDetails()
		{
			registerpage.clickOncontinuebutton();
			
			String actualprivacymssg = registerpage.mustClickOnPrivacyPolicyMssg();
			String expectedprivacymssg = "Warning: You must agree to the Privacy Policy!";		
			Assert.assertEquals(actualprivacymssg, expectedprivacymssg);
					
		}
		
		
		@AfterMethod
		public void teardown() 
		{
			driver.quit();
		}
	

}
