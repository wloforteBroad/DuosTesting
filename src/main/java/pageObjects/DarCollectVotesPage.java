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
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Collect votes for Data Access Congruence Review')]") 
	private WebElement lbl_darCollectTitle;
	
	public boolean isUserOnDarCollectPage() {
		try {
			return lbl_darCollectTitle.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
}
