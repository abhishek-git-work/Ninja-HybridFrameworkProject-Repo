package com.ninja.qa.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class garbage {
	
	public static void main(String[] args) throws IOException {
		
		ExtentReports extentreport = new ExtentReports();		
		File extentReportFile = new File("extentReport.html");		
		ExtentSparkReporter sparkreport = new ExtentSparkReporter(extentReportFile);
		extentreport.attachReporter(sparkreport);
		
//design report (optional)
		sparkreport.config().setTheme(Theme.DARK);
		sparkreport.config().setReportName("TutorialsNinja Test Project Report");
		sparkreport.config().setDocumentTitle("Ninja Report");
		sparkreport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
//giving info at the end of report (optional)		
		extentreport.setSystemInfo("Application URL", "www.amazon.com");
		extentreport.setSystemInfo("Browser Name", "chrome");
		extentreport.setSystemInfo("Email ID", "acbramhankar@gmail.com");
		extentreport.setSystemInfo("Password", "abhi123");
		extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreport.setSystemInfo("System User Name", System.getProperty("user.name"));
		extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
//creating test and giving status and description						
		extentreport.createTest("test1").log(Status.FAIL, "fail test");
		extentreport.createTest("test2").log(Status.PASS, "pass test");
		extentreport.createTest("test3").log(Status.SKIP, "Skip test");
		extentreport.createTest("test3").log(Status.WARNING, "warning test");
		extentreport.createTest("test3").log(Status.INFO, "info test");
			
//mandatory at the end of all report		
		extentreport.flush();
		
//to open report directly into default browser
		Desktop.getDesktop().browse(new File("extentReport.html").toURI());
	}

}
