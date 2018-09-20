package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DataOwnerVotePage {
WebDriver driver;
	
	public DataOwnerVotePage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "appSummary") 
	private WebElement btn_AppSummary;
	
	@FindBy(how = How.ID, using = "datasetSummary") 
	private WebElement btn_DatasetSummary;
	
	@FindBy(how = How.ID, using = "rad_dataOwnerReview_0") 
	private WebElement btn_Approve;
	
	@FindBy(how = How.ID, using = "rad_dataOwnerReview_1")
	private WebElement btn_Disapprove;
	
	@FindBy(how = How.ID, using = "rad_dataOwnerReview_2") 
	private WebElement btn_RaiseConcern;
	
	@FindBy(how = How.NAME, using = "txt_rationale_dataOwnerReview") 
	private WebElement txtbx_Rationale;
	
	@FindBy(how = How.ID, using = "btn_submit_dataOwnerReview") 
	private WebElement btn_Vote;
	
	public void clickOn_AppSummary() {
		btn_AppSummary.click();
	}
	
	public void clickOn_DatasetSummary() {
		btn_DatasetSummary.click();
	}
	
	public void clickOn_Approve() {
		btn_Approve.click();
	}
	
	public void clickOn_Disapprove() {
		btn_Disapprove.click();
	}
	
	public void clickOn_RaiseConcern() {
		btn_RaiseConcern.click();
	}
	
	public void enter_Rationale(String rationale) {
		txtbx_Rationale.clear();
		txtbx_Rationale.sendKeys(rationale);
	}
	
	public void clickOn_Vote() {
		btn_Vote.click();
	}
	

}
