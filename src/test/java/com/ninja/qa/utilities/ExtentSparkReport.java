package com.ninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentSparkReport {
	
	public static ExtentReports generateExtentReport() throws IOException
	{
		
//main configuration part (mandatory)
		ExtentReports extentreport = new ExtentReports();		
		File extentReportFile = new File("extentReport.html");		
		ExtentSparkReporter sparkreport = new ExtentSparkReporter(extentReportFile);
		extentreport.attachReporter(sparkreport);
	
		
//design report (optional)
		sparkreport.config().setTheme(Theme.DARK);
		sparkreport.config().setReportName("TutorialsNinja Test Project Report");
		sparkreport.config().setDocumentTitle("Ninja Report");
		sparkreport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		Properties configprop = new Properties();
		File configfile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\ninja\\qa\\config\\config.properties");
		FileInputStream configfis = new FileInputStream(configfile);
		configprop.load(configfis);
		
//giving info at the end of report (optional)		
		extentreport.setSystemInfo("Application URL", configprop.getProperty("url"));
		extentreport.setSystemInfo("Browser Name", configprop.getProperty("browserName"));
		extentreport.setSystemInfo("Email ID", configprop.getProperty("validEmail"));
		extentreport.setSystemInfo("Password", configprop.getProperty("validPassword"));
		extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreport.setSystemInfo("System User Name", System.getProperty("user.name"));
		extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentreport;	
		
	}

}
