package PomUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResourcePage {

    WebDriver driver;

    public ResourcePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[normalize-space()='Resources']")
    private WebElement resourcesMenu;

    @FindBy(xpath = "//*[contains(normalize-space(),'View Resources')]")
    private WebElement resourcesHeading;

    @FindBy(xpath = "//input[@placeholder='Search by File Name']")
    private WebElement searchFileName;

    @FindBy(xpath = "//span[text()='Upload File']")
    private WebElement uploadFileButton;

    @FindBy(id = "upload-file")
    private WebElement chooseFile;

    @FindBy(xpath = "//button[normalize-space()='Upload File']")
    private WebElement uploadFileSubmit;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement cancelUpload;

    public void getResourcesMenu() {
        resourcesMenu.click();
    }

    public boolean isResourcesDisplayed() {
        return resourcesHeading.isDisplayed();
    }

    public void searchFile(String fileName) {
        searchFileName.clear();
        searchFileName.sendKeys(fileName);
    }

    public void clickUploadFile() {
        uploadFileButton.click();
    }

    public void chooseFile(String filePath) {
        chooseFile.sendKeys(filePath);
    }

    public void clickUploadFileSubmit() {
        uploadFileSubmit.click();
    }

    public void cancelUpload() {
        cancelUpload.click();
    }
}