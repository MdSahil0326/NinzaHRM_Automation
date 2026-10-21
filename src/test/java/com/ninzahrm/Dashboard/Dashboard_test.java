package com.ninzahrm.Dashboard;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import PomUtilities.DashboardPage;
import PomUtilities.LoginPage;

public class Dashboard_test extends BaseClass {
	
	
	@Test( priority = 1,groups = {"Sys","FT"})
    public void verifyDashboardURL() throws Exception { 
		LoginPage log= new LoginPage(driver);
		log.login();
		DashboardPage dash = new DashboardPage(driver);
        String actualURL = dash.getDashboardURL();
        System.out.println(actualURL);
        Assert.assertTrue(actualURL.contains("dashboard/overview"),"Dashboard URL is not present");
        Reporter.log("Dashboard-validated");
    }
	
	
	
	 @Test( priority = 2,groups = {"FT","IT"})
	    public void dashboardToProjectsNavigationTest() throws Exception
	    {
		 
		 	LoginPage log= new LoginPage(driver);
			log.login();
		
	        DashboardPage dash = new DashboardPage(driver);
	        dash.clickProjects();

	        String currentUrl = driver.getCurrentUrl();
            System.out.println(currentUrl);
	        Assert.assertTrue(currentUrl.contains("project"),"Projects page did not open successfully");
	        
	        Reporter.log("dashboardToProjectsNavigationTest- validated ");
	    }
	
	 @Test( priority = 3,groups = {"ST","FT"})
	 public void dashboardRefreshAfterSessionExpiryTest() throws Exception
	 {
		 
	     LoginPage log = new LoginPage(driver);
	     log.login();

	     Thread.sleep(2000);
	     String dashboardURL =driver.getCurrentUrl(); 

	     driver.manage().deleteAllCookies();
	     driver.navigate().refresh();
	     
	     Thread.sleep(2000);
	     String actualURL = driver.getCurrentUrl();

	 
	     Assert.assertEquals(actualURL,dashboardURL,"Dashboard URL is not Accessable");
	     
	     Reporter.log("Defect: Dashboard URL did not change after clearing cookies");
	 }
	

}
