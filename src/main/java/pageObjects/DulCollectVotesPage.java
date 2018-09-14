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
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Collect votes for Data Use Limitations Congruence Review')]") 
	private WebElement lbl_dulCollectTitle;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"voteForm\"]/div[1]/div/div/label[1]") 
	private WebElement lbl_Yes;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"voteForm\"]/div[1]/div/div/label[2]") 
	private WebElement lbl_No;
	
	@FindBy(how = How.NAME, using = "inputRationale") 
	private WebElement txtbx_Rationale;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Vote')]") 
	private WebElement btn_Vote;
	
	@FindBy(how = How.CLASS_NAME, using = "vote-update") 
	private WebElement lbl_updated;
	
	@FindBy(how = How.CLASS_NAME, using = "sendReminder") 
	private WebElement btn_Reminder;
	
	@FindBys(@FindBy(css=".sendReminder"))
	private List<WebElement> reminders;
	
	public boolean isUserOnDulCollectPage() {
		try {
			return lbl_dulCollectTitle.isDisplayed();
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
