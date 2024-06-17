package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	

	@BeforeClass(groups = {"sanity","regression","master"})
	@Parameters({"OS","Browser"})
	public void setup(String os, String br) throws IOException {
		//loading properties file 
		FileReader file = new FileReader("D:/eclipse/eclipse/eclipse-workspace/selenium/openCart/src/test/resources/config.properties");
		p= new Properties();
		p.load(file);
		
		//Loading log4j2 files
		logger = org.apache.logging.log4j.LogManager.getLogger(BaseClass.class);
		//PropertyConfigurator.configure("log4j2.properties");
		//PropertyConfigurator.configure("D:/eclipse/eclipse/eclipse-workspace/selenium/openCart/src/test/resources/log4j2.xml");
				
		
		//setting up for selenium grid execution
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		//setu for local machine
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
				case "chrome" : driver = new ChromeDriver();  logger.info("Chrome Opened successfully");  break;
				case "edge" : driver = new EdgeDriver();  logger.info("Edge Opened successfully");  break;
				default: System.out.println("No mtching browser."); break;			
			}
		}
		
		driver.manage().deleteAllCookies();    /*delete all cookies from driver*/
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));   /*Wait for 10 seconds before execution*/
		driver.manage().window().maximize();   /*maximizes the window chrome*/
		String url = p.getProperty("appurl");
		System.out.println("url : "+url);
		driver.get(url);
		//driver.navigate().to("https://magento.softwaretestingboard.com/");
	
	}
	
	@AfterClass	(groups = {"sanity","regression","master"})
	public void tearDown() {
		driver.quit();
		//driver.close();
	}
	
	
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return  generatedString;
	}
	
	public String randomeNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return  generatedNumber;
	}
	
	public String randomeAlphanumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		
		return  (generatedString+"@"+generatedNumber);
	}
	
	public String captureScreen(String tname) throws IOException {

		//String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		//String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		//File targetFile=new File(targetFilePath);
		String date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		File target = new File("D:/eclipse/eclipse/eclipse-workspace/selenium/openCart/Screenshots/Error"+date+".png");
		
		FileHandler.copy(sourceFile,target);
		
		//sourceFile.renameTo(targetFile);
		//FileHandler.copy(sourceFile, targetFile);	
		//return targetFilePath;
		return target.getPath();

	}


}
