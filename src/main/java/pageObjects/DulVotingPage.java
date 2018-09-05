package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DulVotingPage {
WebDriver driver;
	
	public DulVotingPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Data Use Limitations Congruence Review')]") 
	private WebElement lbl_dulVotingTitle;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Yes')]") 
	private WebElement lbl_Yes;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'No')]") 
	private WebElement lbl_No;
	
	@FindBy(how = How.NAME, using = "inputRationale") 
	private WebElement txtbx_Rationale;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Vote')]") 
	private WebElement btn_Vote;
	
	public boolean isUserOnVotingPage() {
		try {
			return lbl_dulVotingTitle.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isVoteEnabled() {
		try {
			return btn_Vote.isEnabled();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	
	public void clickOn_Yes() {
		lbl_Yes.click();
	}
	
	public void clickOn_No() {
		lbl_No.click();
	}
	
	public void enter_Rationale(String rationale) {
		txtbx_Rationale.clear();
		txtbx_Rationale.sendKeys(rationale);
	}
	
	public void clickOn_Vote() {
		btn_Vote.click();
	}
	
	public void voteYes(String rationale) {
		lbl_Yes.click();
		txtbx_Rationale.sendKeys(rationale);
		btn_Vote.click();
	}
	
	public void voteNo(String rationale) {
		lbl_No.click();
		txtbx_Rationale.sendKeys(rationale);
		btn_Vote.click();
	}
	
	public String getRationale() {
		return txtbx_Rationale.getText();
	}	

}
