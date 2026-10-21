package com.ninzahrm.Resources;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import GenericUtilities.Screenshot;
import PomUtilities.LoginPage;
import PomUtilities.ResourcePage;

public class Resources_test extends BaseClass {

    String fileName = "cts traning.txt";
    Screenshot tks= new Screenshot();

    @Test(priority = 1, groups = {"FT","ST","IT"})
    public void uploadValidFileTest() throws Exception {
   
    	LoginPage log= new LoginPage(driver);
    	log.login();
    	
    	
    	ResourcePage resources = new ResourcePage(driver);

        resources.getResourcesMenu();
        resources.clickUploadFile();

        resources.chooseFile("D:\\cts traning.txt");

        resources.clickUploadFileSubmit();

        Thread.sleep(2000);

        Assert.assertTrue(driver.getPageSource().contains(fileName),"Uploaded file was not found");

        Reporter.log("Valid file upload test passed", true);
        
    }

    @Test(priority = 2, groups = {"FT"})
    public void searchExistingFileTest() throws Exception {

    	
    	LoginPage log= new LoginPage(driver);
    	log.login();
    	
        ResourcePage resources = new ResourcePage(driver);

        resources.getResourcesMenu();
        resources.searchFile(fileName);

        Thread.sleep(2000);

        Assert.assertFalse(driver.findElements(By.xpath("//*[normalize-space()='" + fileName + "']")).isEmpty(),"Existing file was not found");

        Reporter.log("Existing file search test passed", true);
        
    }

    @Test(priority = 3,groups = {"neg"})
    public void searchInvalidFileTest() throws Exception {

    	LoginPage log= new LoginPage(driver);
    	log.login();
    	
        ResourcePage resources = new ResourcePage(driver);
        String invalidFile = "file_not_found_12345.pdf";

        resources.getResourcesMenu();
        resources.searchFile(invalidFile);


        Assert.assertTrue(driver.findElements(By.xpath("//*[normalize-space()='" + invalidFile + "']")).isEmpty(), "Unexpected matching file found");

        Reporter.log("Invalid file search test passed", true);
        Screenshot tks= new Screenshot();
        tks.takeScreenshot(driver, "Search_Invalid_File");
    }

    @Test(priority = 4,groups = {"neg","FT"})
    public void uploadWithoutFileTest() throws Exception {
    	LoginPage log= new LoginPage(driver);
    	log.login();
        ResourcePage resources = new ResourcePage(driver);

        resources.getResourcesMenu();
        resources.clickUploadFile();
        resources.clickUploadFileSubmit();

        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.id("upload-file")).isDisplayed(),"Upload form was not displayed");

        Reporter.log("Upload without file test executed", true);
        tks.takeScreenshot(driver, "Upload_Without_File");
    }
}