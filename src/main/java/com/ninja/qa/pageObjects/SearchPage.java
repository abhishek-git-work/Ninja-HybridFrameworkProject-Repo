package com.ninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="HP LP3065")
	private WebElement validProduct;
	
	public boolean verifyValidProduct()
	{
		boolean validproductconfirm = validProduct.isDisplayed();
		return validproductconfirm;
	}
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement noProductFoundMssg;
	
	public String enterProductNotFoundMssg()
	{
		String invalidproduct = noProductFoundMssg.getText();
		return invalidproduct;
	}
	

}
