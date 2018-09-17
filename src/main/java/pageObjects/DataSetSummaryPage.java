package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DataSetSummaryPage {
WebDriver driver;
	
	public DataSetSummaryPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Dataset Summary')]")
	private WebElement lbl_Title;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Close')]")
	private WebElement btn_Close;
	
	public boolean isUserOnDataSetSummary() {
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