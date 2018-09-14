package pageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
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
	
	@FindBy(how = How.ID, using = "btn_dul_vote") 
	private WebElement btn_Dul_Vote;
	
	@FindBy(how = How.ID, using = "btn_dul_edit") 
	private WebElement btn_Dul_Edit;
	
	@FindBy(how = How.ID, using = "btn_dar_vote") 
	private WebElement btn_Dar_Vote;
	
	@FindBy(how = How.ID, using = "btn_dar_edit") 
	private WebElement btn_Dar_Edit;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Collect Votes')]") 
	private WebElement btn_CollectVotes;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Editable')]") 
	private WebElement lbl_Editable;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Pending')]") 
	private WebElement lbl_Pending;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'ORSP-627')]") 
	private WebElement lbl_Consent;
	
	@FindBys(@FindBy(css=".ng-scope[ng-repeat*='searchDULCases']"))
	private List<WebElement> allDuls;
	
	public boolean isUserOnDacConsole() {
		try {
			return lbl_dulReview.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public void clickOn_Dul_Vote() {
		btn_Dul_Vote.click();
	}
	
	public void clickOn_Dul_Edit() {
		btn_Dul_Edit.click();
	}
	
	public void clickOn_Dar_Vote() {
		btn_Dar_Vote.click();
	}
	
	public void clickOn_Dar_Edit() {
		btn_Dar_Edit.click();
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
		btn_Dul_Vote.click();
	}
	
	public void findConsentAndCollect(String consent) {
		txtbx_SearchConsent.clear();
		txtbx_SearchConsent.sendKeys(consent);
		btn_Dul_Vote.click();
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
	
	public List<WebElement> getAllDuls() {
        return allDuls;
    }

}
