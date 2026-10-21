package Baseclass;



import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericUtilities.FileUtility;

public class BaseClass {
			
	protected WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void Bs() {
		System.out.println("establish database connectivity");
	}
	
	@BeforeTest(alwaysRun = true)
	public void Bt() {
		System.out.println("Pre-Conditions");
	}
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void Bc() throws Exception {
		System.out.println("Launching-Browser");
		
		FileUtility fu= new FileUtility();
		String browser = fu.getDatafromPropertyFile("browser");
		
		 if (browser.equalsIgnoreCase("chrome")) {
			 ChromeOptions opt= new ChromeOptions();
			 opt.addArguments("--incognito");
			 driver=new ChromeDriver(opt);
			 driver.manage().window().maximize();			 	 
		 }
		 else if (browser.equalsIgnoreCase("edge")) {
			 driver=new EdgeDriver();
			 driver.manage().window().maximize();
		}
		 else if (browser.equalsIgnoreCase("firefox")) {
			 driver=new FirefoxDriver();
			 driver.manage().window().maximize();
		} 	
	}
	
	@BeforeMethod(alwaysRun = true)
	public void bm() throws Exception {
  
		FileUtility fu= new FileUtility();
		String url = fu.getDatafromPropertyFile("url");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	}
    
	@AfterMethod(alwaysRun = true)
	public void Am() {
		System.out.println("Logout-Executed");
	}
	
	@AfterClass(alwaysRun = true)
	public void Ac() {
		driver.quit();
		System.out.println("Browser-Closed");
	}
	
	@AfterTest(alwaysRun = true)
	public void At() {
		System.out.println("Post-Conditions");
	}
	
	@AfterSuite(alwaysRun = true)
	public void As() {
		System.out.println("close database connectivity");
	}
	
	
	
	
	
	
	
	
	
	

}
