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
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Open')]") 
	private WebElement lbl_electionOpened;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Canceled')]") 
	private WebElement lbl_electionCanceled;
	
	@FindBys(@FindBy(css=".ng-scope[ng-repeat*='searchDUL']"))
	private List<WebElement> allData;
	
	public void findConsent(String consentName) {
		txtbx_SearchConsent.clear();
		txtbx_SearchConsent.sendKeys(consentName);
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
	
	public boolean isElectionOpen() {
		try {
			return lbl_electionOpened.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isElectionCanceled() {
		try {
			return lbl_electionCanceled.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isNewDulDisplayed(String consentName) {
		try {
			WebElement newConsent = driver.findElement(By.xpath("//a[contains(text(),'"+ consentName +"')]"));
			return newConsent.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public List<WebElement> getAllData() {
        return allData;
    }

}
