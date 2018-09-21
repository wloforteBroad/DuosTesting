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
	
	@FindBy(how = How.ID, using = "memberConsole_title") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "memberConsole_description") 
	private WebElement lbl_Description;
	
	@FindBy(how = How.ID, using = "memberConsoleDul_title") 
	private WebElement lbl_DulTitle;
	
	@FindBy(how = How.ID, using = "memberConsoleDul_description") 
	private WebElement lbl_DulDescription;
	
	@FindBy(how = How.ID, using = "memberConsoleAccess_title") 
	private WebElement lbl_AccessTitle;
	
	@FindBy(how = How.ID, using = "memberConsoleAccess_description") 
	private WebElement lbl_AccessDescription;
	
	@FindBy(how = How.ID, using = "txt_search_memberConsoleDul") 
	private WebElement txtbx_SearchConsent;
	
	@FindBy(how = How.ID, using = "txt_search_memberConsoleAccess") 
	private WebElement txtbx_SearchDar;
	
	@FindBy(how = How.NAME, using = "btn_voteDul") 
	private WebElement btn_Dul_Vote;
	
	@FindBy(how = How.NAME, using = "btn_voteDul") 
	private WebElement btn_Dul_Edit;
	
	@FindBy(how = How.NAME, using = "btn_voteAccess") 
	private WebElement btn_Dar_Vote;
	
	@FindBy(how = How.NAME, using = "btn_voteAccess") 
	private WebElement btn_Dar_Edit;
	
	
	@FindBy(how = How.NAME, using = "statusDul") 
	private WebElement lbl_Status;
	
	@FindBys(@FindBy(className="tableRowDul"))
	private List<WebElement> allDuls;
	
	@FindBys(@FindBy(className="tableRowAccess"))
	private List<WebElement> allDars;
	
	private String dulTitle = "Data Use Limitations Review";
	private String dulDescription = "Were data use limitations accurately converted to a structured format?";
	private String darTitle = "Data Access Request Review";
	private String darDescription = "Should data access be granted to this applicant?";
	
	public boolean isUserOnDacConsole() {
		try {
			return lbl_Title.isDisplayed();
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
			return lbl_Status.getText().equals("Editable");
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isVotePending() {
		try {
			return lbl_Status.getText().equals("Pending");
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public List<WebElement> getAllDuls() {
        return allDuls;
    }
	
	public List<WebElement> getAllDars() {
        return allDars;
    }

}
