package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DarSummaryPage {
WebDriver driver;
	
	public DarSummaryPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Application Summary')]")
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "btn_action")
	private WebElement btn_Close;
	
	public boolean isUserOnDarSummary() {
		try {
			return lbl_Title.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public void clickOn_Close() {
		btn_Close.click();
	}

}
