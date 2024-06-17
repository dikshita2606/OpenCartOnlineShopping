package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	String reportName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date()); //generate time stamp to be print
		reportName = "Test-Report-"+timeStamp+".html";
		System.out.println("Report Name ="+reportName);
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName); //path for storing reports
		
		sparkReporter.config().setDocumentTitle("Auomation Report");
		sparkReporter.config().setReportName("Luma Automation Testing Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Application", "Luma Automation");
		extentReports.setSystemInfo("Module", "Admin");
		extentReports.setSystemInfo("Sub Module", "Customers");
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environemnt", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("OS");
		extentReports.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("Browser");
		extentReports.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) 
		{
			extentReports.setSystemInfo("Groups", includedGroups.toString());
		}	
	}
	
	
	public void onTestSuccess(ITestResult result) {
		
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups()); // to display groups in report
		extentTest.log(Status.PASS,result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		
		extentTest.log(Status.FAIL,result.getName()+" got failed");
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}




	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		
		extentReports.flush();
		
		//automatically opn report on the chrome
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*sent an email to the team 
		  try {
			  URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);
		  
			  // Create the email message 
			  ImageHtmlEmail email = new ImageHtmlEmail();
			  email.setDataSourceResolver(new DataSourceUrlResolver(url));
			  //will work only for gmail these changes from server to server
			  email.setHostName("smtp.googlemail.com"); 
			  email.setSmtpPort(465);
			  email.setAuthenticator(new DefaultAuthenticator("joshi.dikshita26@gmail.com","7746917470")); 
			  email.setSSLOnConnect(true);
			  email.setFrom("joshi.dikshita26@gmail.com"); //Sender
			  email.setSubject("Test Results");
			  email.setMsg("Please find Attached Report....");
			  email.addTo("joshi.dikshita26@gmail.com"); //Receiver 
			  email.attach(url, "extent report", "please check report..."); 
			  email.send(); // send the email 
		  }
		  catch(Exception e) 
		  { 
			  e.printStackTrace(); 
		  }*/
		 
		 
	}

	
	
	
}
