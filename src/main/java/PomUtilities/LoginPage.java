package PomUtilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtilities.FileUtility;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "username")
	private WebElement usern ;
	
	
	@FindBy(id="inputPassword")
	private WebElement pwtf;
	
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement login;
	
	@FindBy(className = "invalid-feedback")
	private WebElement errormsg;
	
	
	public void  getusern(String value ) {
		usern.sendKeys(value);
	}

	public void getPwtf(String value ) {
		 pwtf.sendKeys(value);
	}

	public void  getLogin() {
		login.click();
	}
	
	public String geterrormsg() {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(errormsg));
		return errormsg.getText();
	}
	
	public String getAlert() {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
		 String Alertmsg = driver.switchTo().alert().getText();
	     driver.switchTo().alert().accept();
		return Alertmsg;
	}
	
	public void login() throws Exception{
		FileUtility fu= new FileUtility();
		String user = fu.getDatafromPropertyFile("user");
		String pswd = fu.getDatafromPropertyFile("password");
		usern.sendKeys(user);
		pwtf.sendKeys(pswd);
		login.click();

	}
}
