package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DarVotingPage {
WebDriver driver;
	
	public DarVotingPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "access-yes") 
	private WebElement lbl_Access_Yes;
	
	@FindBy(how = How.ID, using = "access-no") 
	private WebElement lbl_Access_No;
	
	@FindBy(how = How.ID, using = "rp-yes") 
	private WebElement lbl_RP_Yes;
	
	@FindBy(how = How.ID, using = "rp-no") 
	private WebElement lbl_RP_No;
	
	@FindBy(how = How.ID, using = "access-rationale") 
	private WebElement txtbx_Access_Rationale;
	
	@FindBy(how = How.ID, using = "rp-rationale") 
	private WebElement txtbx_RP_Rationale;
	
	@FindBy(how = How.ID, using = "access-vote") 
	private WebElement btn_Access_Vote;
	
	@FindBy(how = How.ID, using = "rp-vote") 
	private WebElement btn_RP_Vote;
	
	public void clickOn_Access_Yes() {
		lbl_Access_Yes.click();
	}
	
	public void clickOn_Access_No() {
		lbl_Access_No.click();
	}
	
	public void clickOn_RP_Yes() {
		lbl_RP_Yes.click();
	}
	
	public void clickOn_RP_No() {
		lbl_RP_No.click();
	}
	
	public void enter_Access_Rationale(String rationale) {
		txtbx_Access_Rationale.clear();
		txtbx_Access_Rationale.sendKeys(rationale);
	}
	
	public void enter_RP_Rationale(String rationale) {
		txtbx_RP_Rationale.clear();
		txtbx_RP_Rationale.sendKeys(rationale);
	}
	
	public void clickOn_Access_Vote() {
		btn_Access_Vote.click();
	}
	
	public void clickOn_RP_Vote() {
		btn_RP_Vote.click();
	}
	
	public void voteYes_Access(String rationale) {
		clickOn_Access_Yes();
		enter_Access_Rationale(rationale);
		clickOn_Access_Vote();
	}
	
	public void voteNo_Access(String rationale) {
		clickOn_Access_No();
		enter_Access_Rationale(rationale);
		clickOn_Access_Vote();
	}
	
	public void voteYes_RP(String rationale) {
		clickOn_RP_Yes();
		enter_RP_Rationale(rationale);
		clickOn_RP_Vote();
	}
	
	public void voteNo_RP(String rationale) {
		clickOn_RP_No();
		enter_RP_Rationale(rationale);
		clickOn_RP_Vote();		
	}
	
	public String getAccessRationale() {
		return txtbx_Access_Rationale.getText();
	}
	
	public String getRPRationale() {
		return txtbx_RP_Rationale.getText();
	}
	
}
