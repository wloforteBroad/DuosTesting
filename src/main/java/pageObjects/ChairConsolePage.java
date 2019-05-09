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

public class ChairConsolePage {
WebDriver driver;
	
	public ChairConsolePage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "chairConsole_title") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "chairConsole_description") 
	private WebElement lbl_Description;
	
	@FindBy(how = How.ID, using = "chairConsoleDul_title") 
	private WebElement lbl_DulTitle;
	
	@FindBy(how = How.ID, using = "chairConsoleDul_description") 
	private WebElement lbl_DulDescription;
	
	@FindBy(how = How.ID, using = "chairConsoleAccess_title") 
	private WebElement lbl_AccessTitle;
	
	@FindBy(how = How.ID, using = "chairConsoleAccess_description") 
	private WebElement lbl_AccessDescription;
	
	@FindBy(how = How.ID, using = "txt_search_chairConsoleDul") 
	private WebElement txtbx_SearchConsent;
	
	@FindBy(how = How.ID, using = "txt_search_chairConsoleAccess") 
	private WebElement txtbx_SearchDar;
	
	@FindBy(how = How.ID, using = "dulPendingVoteCases") 
	private WebElement lbl_dulPendingVotesFlag;
	
	@FindBy(how = How.NAME, using = "btn_voteDul") 
	private WebElement btn_DulVote;
	
	@FindBy(how = How.NAME, using = "btn_voteDul") 
	private WebElement btn_DulEdit;
	
	@FindBy(how = How.NAME, using = "btn_collectDul") 
	private WebElement btn_DulCollectVotes;
	
	@FindBys(@FindBy(className="tableRow"))
	private List<WebElement> allDuls;
	
	private String dulTitle = "Data Use Limitations Review";
	private String dulDescription = "Were data use limitations accurately converted to a structured format?";
	private String darTitle = "Data Access Request Review";
	private String darDescription = "Should data access be granted to this applicant?";
	
	public void clickOn_DulVote() {
		btn_DulVote.click();
	}
	
	public void clickOn_DulEdit() {
		btn_DulEdit.click();
	}
	
	public void clickOn_DulCollectVotes() {
		btn_DulCollectVotes.click();
	}
	
	public void findConsent(String consent) {
		txtbx_SearchConsent.clear();
		txtbx_SearchConsent.sendKeys(consent);
	}
	
	public void findDar(String dar) {
		txtbx_SearchDar.clear();
		txtbx_SearchDar.sendKeys(dar);
	}
	
	public String getPendingCases() {
		String pending = lbl_dulPendingVotesFlag.getText();
		return pending;
	}
	
	public List<WebElement> getAllDuls() {
        return allDuls;
    }
	
	public void waitForFlag() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("dulPendingVoteCases")));
    }
	
	public boolean isDulTitleOk() {
		try {
			return lbl_Title.getText().equals(dulTitle);
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isDulDescriptionOk() {
		try {
			return lbl_Description.getText().equals(dulDescription);
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isDarTitleOk() {
		try {
			return lbl_Title.getText().equals(darTitle);
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isDarDescriptionOk() {
		try {
			return lbl_Description.getText().equals(darDescription);
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
