package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
	
	public boolean isUserOnDulCollectPage() {
		try {
			return lbl_dulCollectTitle.isDisplayed();
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

}
