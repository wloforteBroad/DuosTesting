package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageDulPage {
WebDriver driver;	
	
	public ManageDulPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".users-search") 
	private WebElement txtbx_SearchConsent;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Data Use Limitations')]") 
	private WebElement btn_AddDul;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Create')]") 
	private WebElement btn_Create;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Cancel')]") 
	private WebElement btn_Cancel;
	
	@FindBy(how = How.ID, using = "previewOpen") 
	private WebElement btn_StatusOpen;
	
	@FindBy(how = How.ID, using = "previewCanceled") 
	private WebElement btn_StatusCanceled;
	
	@FindBy(how = How.ID, using = "previewReviewed") 
	private WebElement btn_StatusReviewed;
	
	@FindBy(how = How.ID, using = "previewUnReviewed") 
	private WebElement btn_StatusUnReviewed;
	
	@FindBy(how = How.CLASS_NAME, using = "flagged") 
	private WebElement lbl_Archived;
	
	@FindBy(how = How.CLASS_NAME, using = "glyphicon-inbox") 
	private WebElement btn_Archive;

	@FindBy(how = How.CLASS_NAME, using = "glyphicon-trash") 
	private WebElement btn_Delete;
	
	@FindBy(how = How.ID, using = "versionNumber") 
	private WebElement lbl_versionNumber;
	
	@FindBy(how = How.ID, using = "previewDul") 
	private WebElement btn_PreviewDul;
	
	@FindBys(@FindBy(css=".ng-scope[ng-repeat*='searchDUL']"))
	private List<WebElement> allData;
	
	public void findConsent(String consentName) {
		txtbx_SearchConsent.clear();
		txtbx_SearchConsent.sendKeys(consentName);
	} 
	
	public void clickOn_Archive() {
		btn_Archive.click();
	}
	
	public void clickOn_Delete() {
		btn_Delete.click();
	}
	public void clickOn_AddDul() {
		btn_AddDul.click();
	}
	
	public void clickOn_Create() {
		btn_Create.click();
	}
	
	public void clickOn_Cancel() {
		btn_Cancel.click();
	}
	
	public void clickOn_StatusOpen() {
		btn_StatusOpen.click();
	}
	
	public void clickOn_StatusCanceled() {
		btn_StatusCanceled.click();
	}
	
	public void clickOn_StatusReviewed() {
		btn_StatusReviewed.click();
	}
	
	public void clickOn_StatusUnReviewed() {
		btn_StatusUnReviewed.click();
	}
	
	public void clickOn_PreviewDul() {
		btn_PreviewDul.click();
	}
	
	public boolean isElectionOpen() {
		try {
			return btn_StatusOpen.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isElectionCanceled() {
		try {
			return btn_StatusCanceled.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isElectionReviewed() {
		try {
			return btn_StatusReviewed.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isElectionUnReviewed() {
		try {
			return btn_StatusUnReviewed.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isElectionArchived() {
		try {
			return lbl_Archived.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isDulDisplayed(String consentName) {
		try {
			WebElement consent = driver.findElement(By.xpath("//a[contains(text(),'"+ consentName +"')]"));
			return consent.isDisplayed();
		}catch(NoSuchElementException e) {
			System.out.println(e);
			return false;
		}	
	}
	
	public boolean isDulNotDisplayed(String consentName) {
		try {
			WebElement consent = driver.findElement(By.xpath("//a[contains(text(),'"+ consentName +"')]"));
			return consent.isDisplayed()? false : true;
		}catch(NoSuchElementException e) {
			return true;
		}	
	}
	
	public List<WebElement> getAllData() {
        return allData;
    }
	
	public String getVersion() {
		return lbl_versionNumber.getText();
	}
	
	public void waitForElementToLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("versionNumber")));
    }

}
