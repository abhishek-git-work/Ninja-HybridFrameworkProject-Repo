package com.ninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAddressField;
	
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;

	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;

	public AccountPage clickOnLoginButton()
	{
		loginButton.click();
		return new AccountPage(driver);
	}
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailPasswordNotMatchingMssg;

	public String retrieveEmailPasswordNotMatchingWarningMssg()
	{
		String warningtext = emailPasswordNotMatchingMssg.getText();
		return warningtext;
	}
	

}
