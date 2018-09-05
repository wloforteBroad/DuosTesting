package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DacConsolePage {
WebDriver driver;
	
	public DacConsolePage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Data Use Limitations Review')]") 
	private WebElement lbl_dulReview;
	
	@FindBy(how = How.ID, using = "searchDul") 
	private WebElement txtbx_SearchConsent;
	
	@FindBy(how = How.ID, using = "searchDar") 
	private WebElement txtbx_SearchDar;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Vote')]") 
	private WebElement btn_Vote;
	
	@FindBy(how = How.ID, using = "btn_Edit") 
	private WebElement btn_Edit;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Collect Votes')]") 
	private WebElement btn_CollectVotes;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Editable')]") 
	private WebElement lbl_Editable;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Pending')]") 
	private WebElement lbl_Pending;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'ORSP-627')]") 
	private WebElement lbl_Consent;
	
	public boolean isUserOnDacConsole() {
		try {
			return lbl_dulReview.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public void clickOn_Vote() {
		btn_Vote.click();
	}
	
	public void clickOn_Edit() {
		btn_Edit.click();
	}
	
	public void clickOn_CollectVotes() {
		btn_CollectVotes.click();
	}
	
	public void findConsent(String consent) {
		txtbx_SearchConsent.clear();
		txtbx_SearchConsent.sendKeys(consent);
	}
	
	public void findDar(String dar) {
		txtbx_SearchDar.clear();
		txtbx_SearchDar.sendKeys(dar);
	}
	
	public void findConsentAndVote(String consent) {
		txtbx_SearchConsent.clear();
		txtbx_SearchConsent.sendKeys(consent);
		btn_Vote.click();
	}
	
	public void findConsentAndCollect(String consent) {
		txtbx_SearchConsent.clear();
		txtbx_SearchConsent.sendKeys(consent);
		btn_Vote.click();
	}
	
	public boolean isVoteEditable() {
		try {
			return lbl_Editable.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isVotePending() {
		try {
			return lbl_Pending.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isCollectVotesDisplayed() {
		try {
			return btn_CollectVotes.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isConsentClosed() {
		try {
			return lbl_Consent.isDisplayed();
		}catch(NoSuchElementException e) {
			return true;
		}	
	}

}
