package com.ninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	private WebElement myAccountDropMenu;
	
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	private WebElement myAccountLoginButton;
	
	public LoginPage clickSelectLoginOption()
	{
		myAccountLoginButton.click();
		return new LoginPage(driver);
	}
	
	@FindBy(linkText="Register")
	private WebElement myAccountRegisterButton;
	
	public RegisterPage clickSelectRegisterOption()
	{
		myAccountRegisterButton.click();
		return new RegisterPage(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement searchBoxField;
	
	public void enterProductNameInSearchField(String productText)
	{
		searchBoxField.sendKeys(productText);
	}
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchbutton;
	
	public SearchPage clickOnSearchButton()
	{
		searchbutton.click();
		return new SearchPage(driver);
	}

}
