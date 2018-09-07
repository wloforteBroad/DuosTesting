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

public class ManageDarPage {
WebDriver driver;	
	
	public ManageDarPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".users-search") 
	private WebElement txtbx_SearchDar;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Create')]") 
	private WebElement btn_Create;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Cancel')]") 
	private WebElement btn_Cancel;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Summary')]") 
	private WebElement btn_Summary;
	
	@FindBy(how = How.ID, using = "previewOpen") 
	private WebElement btn_StatusOpen;
	
	@FindBy(how = How.ID, using = "previewCanceled") 
	private WebElement btn_StatusCanceled;
	
	@FindBy(how = How.ID, using = "previewReviewed") 
	private WebElement btn_StatusReviewed;
	
	@FindBy(how = How.ID, using = "previewUnReviewed") 
	private WebElement btn_StatusUnReviewed;
	
	@FindBys(@FindBy(css=".ng-scope[ng-repeat*='searchAccess']"))
	private List<WebElement> allData;
	
	public void findDar(String darId) {
		txtbx_SearchDar.clear();
		txtbx_SearchDar.sendKeys(darId);
	} 
	
	public void clickOn_Create() {
		btn_Create.click();
	}
	
	public void clickOn_Cancel() {
		btn_Cancel.click();
	}
	
	public void clickOn_Summary() {
		btn_Summary.click();
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
	
	public boolean isDarDisplayed(String darId) {
		try {
			WebElement consent = driver.findElement(By.xpath("//a[contains(text(),'"+ darId +"')]"));
			return consent.isDisplayed();
		}catch(NoSuchElementException e) {
			System.out.println(e);
			return false;
		}	
	}
	
	public boolean isDarNotDisplayed(String darId) {
		try {
			WebElement consent = driver.findElement(By.xpath("//a[contains(text(),'"+ darId +"')]"));
			return consent.isDisplayed()? false : true;
		}catch(NoSuchElementException e) {
			return true;
		}	
	}
	
	public List<WebElement> getAllData() {
        return allData;
    }
}
