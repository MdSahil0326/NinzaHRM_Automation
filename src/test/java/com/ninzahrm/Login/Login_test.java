package com.ninzahrm.Login;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import GenericUtilities.FileUtility;
import GenericUtilities.Screenshot;
import PomUtilities.LoginPage;

public class Login_test extends BaseClass{
	Screenshot tks = new Screenshot();
	FileUtility fu= new FileUtility();
	@Test( groups = {"ST","FT"})
	public void Login() throws Exception {
		String user = fu.getDatafromPropertyFile("user");
		String pswd = fu.getDatafromPropertyFile("password");

		LoginPage log= new LoginPage(driver);
		log.getusern(user);
		log.getPwtf(pswd);
		log.getLogin();
		
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		Assert.assertTrue(driver.getCurrentUrl().contains(url),"User did not login");
		Reporter.log("SuccessFully -Logged in   ");
	}
	
	
	
	
	@Test( groups = {"neg","FT"})
	public void InvalidPassword() throws Exception {
	String user = fu.getDatafromPropertyFile("user");
	

	LoginPage log= new LoginPage(driver);
	log.getusern(user);
	log.getLogin();
	
	String error = log.geterrormsg();
	
	Assert.assertTrue(error.contains("Password is required"), "Error not displayed");
	Reporter.log("Password is required validated");
	tks.takeScreenshot(driver, "Password-Required");
	}
	
	
	
	@Test( groups = {"neg"})
	public void InvalidLogin() throws Exception {
		LoginPage log = new LoginPage(driver);
		log.getusern("invaliduser");
		log.getPwtf("invalidpwd");
		log.getLogin();
		
		String Alertmsg = log.getAlert();
		Assert.assertTrue(Alertmsg.contains("Invalid Credentials"),"Alert message not displayed");
		 Reporter.log("Alert message validated");
		 Thread.sleep(1000);
		 tks.takeScreenshot(driver," Invalid Credentials");
		
	}

	
	
	
	
}
