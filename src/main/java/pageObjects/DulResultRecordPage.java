package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DulResultRecordPage {
WebDriver driver;
	
	public DulResultRecordPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Data Use Limitations - Results Record')]") 
	private WebElement lbl_Title;
	
	public boolean isUserOnDulResultRecordPage() {
		try {
			return lbl_Title.isDisplayed();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
