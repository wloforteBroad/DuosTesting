package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DatasetCatalogPage {
WebDriver driver;
	
	public DatasetCatalogPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Dataset Catalog')]")
	private WebElement lbl_Title;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'SC-20659')]") 
	private WebElement lbl_DatasetId;
	
	public boolean isUserOnDatasetCatalog() {
		try {
			return lbl_Title.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isDatasetOnTable() {
		try {
			return lbl_DatasetId.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}

}
