package com.ninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	public AccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Edit your account information")
	private WebElement editYourAccountInfo;
	
	public boolean getDisplayStatusOfEditAccountInfo() 
	{
		boolean displayStatus = editYourAccountInfo.isDisplayed();
		return displayStatus;
	}

}
