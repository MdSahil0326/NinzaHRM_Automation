package PomUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class EmployeesPage {

    WebDriver driver;
    public EmployeesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Employees']")
    private WebElement SelectEMP;
    
    
    @FindBy(xpath = "//button[@data-toggle='modal']")
    private WebElement AddEmpButton;
    
    
    @FindBy(xpath = "//label[text()='Name*']/following-sibling::input[@class='form-control']")
    private WebElement Addname;
    
    @FindBy(xpath = "//label[text()='Email*']/following-sibling::input")
    private WebElement AddEmail;
    
  
    
    @FindBy(xpath = "//label[text()='Phone*']/following-sibling::input")
    private WebElement AddPhone;
    
    
    @FindBy(xpath = "//label[text()='Username*']/following-sibling::input")
    private WebElement AddUsername;
    
    
    @FindBy(xpath = "//label[text()='Designation*']/following-sibling::input")
    private WebElement AddDesig;
    
     

    @FindBy(xpath = "//label[text()='Experience*']/following-sibling::input")
    private WebElement AddExp;
    
    
    @FindBy(xpath = "//select[@name='project']")
    private WebElement ProjectDD;
    
    
    @FindBy(xpath = "//input[@value='Add']")
    private WebElement Addbutton;
    
    
    @FindBy(xpath = "//select[@class='form-control']")
   private WebElement SearchDD;
    
    @FindBy(xpath = "//input[@placeholder='Search by Email']")
    private WebElement SearchBox;
    
    @FindBy(css = "table.table.table-striped.table-hover")
    private WebElement employeeTable;
    
    
    @FindBy(xpath = "//i[@title='Delete']")
    private WebElement DelBox;
    
    @FindBy(xpath = "//input[@value='Delete']")
    private WebElement Confirmdel;
    
    public void clickEmployees() {
        SelectEMP.click();
    } 
    
    public void clickAddEmp() {
    	AddEmpButton.click();
    }
    
    public void SendName(String value ) {
    	Addname.sendKeys(value);
    	
    }
    
    public void SendEmail(String value) {
    	AddEmail.sendKeys(value);
    }
    
    public void SendPhone(String value) {
    	AddPhone.sendKeys(value);
    }
    
    public void SendUsername(String value) {
    	AddUsername.sendKeys(value);
    }
    
    public void SendDesignation(String value) {
    	AddDesig.sendKeys(value);
    }
    
    public void SendExp(String value) {
    	AddExp.sendKeys(value);
    }
    
    public void selectProject(String ProjectName) {
      Select select= new Select(ProjectDD);
      select.selectByVisibleText(ProjectName);
    }
    
    public void Addbtn() {
    	Addbutton.click();
    }
    
    public void selectSearchBy(String name) {
    	Select select= new Select(SearchDD);
    	select.selectByVisibleText(name);
    	
    }
    
    public void SearchTf(String By) {
    	SearchBox.clear();
    	SearchBox.sendKeys(By);
    }
    
    
    public  void ClickDel() {
    	DelBox.click();
    }
    
    public void ClickCfmDel() {
    	Confirmdel.click();
    }
    
    
    public String getEmployeesURL() throws InterruptedException {
    	Thread.sleep(2000);
        return driver.getCurrentUrl();
    }
    
    public String getTableText() {
        return employeeTable.getText();
    }
    
   }