package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DulPreviewResultsPage {
WebDriver driver;	
	
	public DulPreviewResultsPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "previewDul_title") 
	private WebElement lbl_Title;
	
	public boolean isUserOnPreviewResultsPage() {
		try {
			return lbl_Title.isDisplayed();
		}catch(NoSuchElementException e) {
			System.out.println(e);
			return false;
		}			
	}

}
