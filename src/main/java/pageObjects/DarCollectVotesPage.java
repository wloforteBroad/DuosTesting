package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DarCollectVotesPage {
WebDriver driver;
	
	public DarCollectVotesPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "collectAccess_title") 
	private WebElement lbl_darCollectTitle;
	
	@FindBy(how = How.ID, using = "btn_collapse_accessCollectVotes") 
	private WebElement btn_Collapse_Access;
	
	@FindBy(how = How.ID, using = "btn_collapse_rpCollectVotes") 
	private WebElement btn_Collapse_RP;
	
	public boolean isUserOnDarCollectPage() {
		try {
			return lbl_darCollectTitle.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
}
