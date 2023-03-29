package com.ninja.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import com.ninja.qa.baseClass.baseClass;

public class ScreenshotMethod extends baseClass{
		
	public static String capturescreenshot(String testname) throws IOException
	{
		DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH mm ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dateformat.format(now);
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination = ("Screenshots//Test-"+ testname + " " +date + ".jpeg");
		FileHandler.copy(source, new File(destination));
		
		return destination;
	}

}
