package com.ninja.qa.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Listeners implements ITestListener {

	ExtentReports extentreport;
	ExtentTest test;
	
	public void onStart(ITestContext context) 
	{		
		try {
			extentreport = ExtentSparkReport.generateExtentReport();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onTestStart(ITestResult result) 
	{	
		test = extentreport.createTest(result.getName());
		test.log(Status.INFO, result.getName()+" started executing");
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, result.getName()+" successfuly executed");
	}
	
	public void onTestFailure(ITestResult result)
	{		
			String destination = null;
			try {
				destination = ScreenshotMethod.capturescreenshot(result.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		test.addScreenCaptureFromPath(destination);
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result)
	{		
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable());
	}
	
	public void onFinish(ITestContext context) 
	{		
		test.log(Status.INFO,"tests execution finished");		
		extentreport.flush();
		
		try {
			Desktop.getDesktop().browse(new File("extentReport.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		
	
}
