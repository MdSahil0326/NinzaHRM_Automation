package com.ninzahrm.Employees;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.Screenshot;
import PomUtilities.LoginPage;
import PomUtilities.EmployeesPage;

public class Employees_test extends BaseClass{
  Screenshot tks = new Screenshot();
    @Test( priority = 1,groups = {"Sys","IT"})
    public void verifyEmployeesURL() throws Exception {


        LoginPage log = new LoginPage(driver);
        log.login();

        EmployeesPage emp = new EmployeesPage(driver);
        emp.clickEmployees();

        String actualURL = emp.getEmployeesURL();
 
         Assert.assertTrue(actualURL.contains("dashboard/users"),"Employees_page not Loaded");
         
         Reporter.log("Employees_page -Validated ");
    }
    
    
    @Test( priority = 2,groups = {"FT"})
    public void AddEmployees() throws Exception {
    	
    	ExcelUtility eu= new ExcelUtility();
  
    	LoginPage log = new LoginPage(driver);
        log.login();
        
        EmployeesPage emp = new EmployeesPage(driver);
        emp.clickEmployees();
        emp.clickAddEmp();
        emp.SendName(eu.GetdatafromExcel("Sheet1", 1, 0));
        emp.SendEmail(eu.GetdatafromExcel("Sheet1", 1, 1));
        emp.SendPhone(eu.GetdatafromExcel("Sheet1", 1, 2));
        emp.SendUsername(eu.GetdatafromExcel("Sheet1", 1, 4));
        emp.SendDesignation(eu.GetdatafromExcel("Sheet1", 1, 3));
        emp.SendExp(eu.GetdatafromExcel("Sheet1", 1, 5));
        emp.selectProject(eu.GetdatafromExcel("Sheet1", 1, 6));
        
        emp.Addbtn();
        System.out.println("Employee added ");
        Thread.sleep(2000);
        driver.navigate().refresh();
        String email = eu.GetdatafromExcel("Sheet1", 1, 1);
        emp.selectSearchBy("Search by Email");
        emp.SearchTf(email);
        String tableText = emp.getTableText();

        Assert.assertTrue(tableText.contains(email),"Employee email is not present in the table");
    }
    
    
    
    @Test( priority = 3,groups = {"neg","FT"})
    public void DeleteEmployee() throws Exception {
    	ExcelUtility eu= new ExcelUtility();
    	LoginPage log = new LoginPage(driver);
        log.login();
        
        EmployeesPage emp = new EmployeesPage(driver);
        emp.clickEmployees();
        
        String email = eu.GetdatafromExcel("Sheet1", 1, 1);
        emp.selectSearchBy("Search by Email");
        emp.SearchTf(email);

        String tableText = emp.getTableText();

        if (!tableText.contains(email)) {
            Reporter.log("Employee not found. Nothing to delete.", true);
            Assert.assertTrue(true);
            return;
        }
        emp.ClickDel();
        emp.ClickCfmDel();
        
        driver.navigate().refresh();
        
        
        Thread.sleep(2000);
        emp.selectSearchBy("Search by Email");
        emp.SearchTf(email);
        String tableText1 = emp.getTableText();
        Assert.assertFalse(tableText1.contains(email),"Employee email is not present in the table");
        tks.takeScreenshot(driver, "file not present ");
        
        
    }
    
    
    
    
}