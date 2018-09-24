package pageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DulCollectVotesPage {
WebDriver driver;
	
	public DulCollectVotesPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "collectDul_title") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "lbl_positive_dulCollect") 
	private WebElement lbl_Yes;
	
	@FindBy(how = How.ID, using = "lbl_negative_dulCollect") 
	private WebElement lbl_No;
	
	@FindBy(how = How.ID, using = "txt_rationale_dulCollect") 
	private WebElement txtbx_Rationale;
	
	@FindBy(how = How.ID, using = "btn_submit_dulCollect") 
	private WebElement btn_Vote;
	
	@FindBy(how = How.CLASS_NAME, using = "vote-update") 
	private WebElement lbl_updated;
	
	@FindBy(how = How.CLASS_NAME, using = "vote-reminder") 
	private WebElement btn_Reminder;
	
	@FindBys(@FindBy(className="vote-reminder"))
	private List<WebElement> reminders;
	
	public boolean isUserOnDulCollectPage() {
		try {
			return lbl_Title.isDisplayed();
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
	
	public boolean isVoteUpdated() {
		try {
			return lbl_updated.isDisplayed();
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
		txtbx_Rationale.sendKeys(rationale);
	}
	
	public void clickOn_Vote() {
		btn_Vote.click();
	}
	
	public void clickOn_Reminder() {
		btn_Reminder.click();
	}
	
	public List<WebElement> getAllReminders() {
        return reminders;
    }

}
