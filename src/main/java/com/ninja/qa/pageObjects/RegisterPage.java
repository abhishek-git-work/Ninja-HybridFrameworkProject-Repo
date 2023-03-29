package com.ninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstname;
	
	public void enterfirstname(String firstnameText)
	{
		firstname.sendKeys(firstnameText);
	}
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastname;
	
	public void enterlastname(String lastnameText)
	{
		lastname.sendKeys(lastnameText);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement email;
	
	public void enteremail(String emailIDText)
	{
		email.sendKeys(emailIDText);
	}
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement phonenumber;
	
	public void enterphonenumber(String phonenumberText)
	{
		phonenumber.sendKeys(phonenumberText);
	}
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement password;
	
	public void enterpassword(String Passwordext)
	{
		password.sendKeys(Passwordext);
	}
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement confirmpassword;
	
	public void enterconfirmpassword(String ConfirmPasswordext)
	{
		confirmpassword.sendKeys(ConfirmPasswordext);
	}
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreebutton;
	
	public void clickOnAgreebutton()
	{
		agreebutton.click();
	}
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continuebutton;
	
	public AccountSuccessPage clickOncontinuebutton()
	{
		continuebutton.click();
		return new AccountSuccessPage(driver);
	}
	
	@FindBy(xpath="//input[@value='0']")
	private WebElement newsletterOption;
	
	public void clickOnnewsletterOption()
	{
		newsletterOption.click();
	}
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement existingEmailWarning;
	
	public String emailAlreadyExistWarningMssg()
	{
		String emailexistmssg = existingEmailWarning.getText();
		return emailexistmssg;
	}
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacypolicymssg;
	
	public String mustClickOnPrivacyPolicyMssg()
	{
		String privacymssg = privacypolicymssg.getText();
		return privacymssg;
	}

}
