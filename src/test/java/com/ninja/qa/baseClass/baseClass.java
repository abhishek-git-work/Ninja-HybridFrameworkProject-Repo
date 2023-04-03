package com.ninja.qa.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class baseClass {
	
	public static WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public void loadPropertiesFile() throws IOException
	{
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\ninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(propfile);
		prop.load(fis);
	}
	
	public void dataPropertiesFile() throws IOException
	{
		dataprop = new Properties();
		File datapropfile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\ninja\\qa\\testData\\testData.properties");
		FileInputStream datafis = new FileInputStream(datapropfile);
		dataprop.load(datafis);
	}
	
	public WebDriver openBrowserAndOpenURL(String browserName)
	{	
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		}
		if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
