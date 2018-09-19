package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Data Use Limitations Review')]") 
	private WebElement lbl_dulReview;
	
	@FindBy(how = How.ID, using = "txt_search_chairConsoleDul") 
	private WebElement txtbx_SearchConsent;
	
	@FindBy(how = How.ID, using = "txt_search_chairConsoleAccess") 
	private WebElement txtbx_SearchDar;
	
	@FindBy(how = How.ID, using = "dulPendingVoteCases") 
	private WebElement lbl_dulPendingVotesFlag;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Vote')]") 
	private WebElement btn_Vote;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Edit')]") 
	private WebElement btn_Edit;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Collect Votes')]") 
	private WebElement btn_CollectVotes;
	
	@FindBys(@FindBy(css=".ng-scope[ng-repeat*='searchDULCases']"))
	private List<WebElement> allDuls;
	
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

}
