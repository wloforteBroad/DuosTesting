package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Create')]") 
	private WebElement btn_Create;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Open')]") 
	private WebElement lbl_electionOpened;
	
	public void findConsent(String consentName) {
		txtbx_SearchConsent.sendKeys(consentName);
	} 
	
	public void clickOn_Create() {
		btn_Create.click();
	}
	
	public boolean isElectionOpen() {
		try {
			return lbl_electionOpened.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}

}
