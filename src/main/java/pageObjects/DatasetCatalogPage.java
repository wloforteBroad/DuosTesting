package pageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DatasetCatalogPage {
WebDriver driver;
	
	public DatasetCatalogPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".users-search") 
	private WebElement txtbx_SearchDataset;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Dataset Catalog')]")
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "check-all")
	private WebElement chkbx_All;
	
	@FindBy(how = How.CSS, using = ".glyphicon-trash") 
	private WebElement btn_Delete;
	
	@FindBy(how = How.CSS, using = ".glyphicon-ban-circle") 
	private WebElement btn_Enable;

	@FindBy(how = How.ID, using = "translatedDul") 
	private WebElement txt_translatedDul;
	
	@FindBy(how = How.CSS, using = ".glyphicon-ok-circle") 
	private WebElement btn_Disable;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'SC-20659')]") 
	private WebElement lbl_DatasetId;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Download selection')]") 
	private WebElement btn_Download;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Translated Use Restriction')]") 
	private WebElement btn_TranslatedDul;
	
	@FindBys(@FindBy(css=".ng-scope[ng-repeat*='searchDataset']"))
	private List<WebElement> allDatasets;
	
	public void findDataset(String searchTerm) {
		txtbx_SearchDataset.clear();
		txtbx_SearchDataset.sendKeys(searchTerm);
	}
	
	public void check_All() {
		chkbx_All.click();
	}
	
	public void clickOn_Download() {
		btn_Download.click();
	}
	
	public void clickOn_Delete() {
		btn_Delete.click();
	}
	
	public void clickOn_Disable() {
		btn_Disable.click();
	}
	
	public void clickOn_Enable() {
		btn_Enable.click();
	}
	
	public void clickOn_ViewTranslatedDul() {
		btn_TranslatedDul.click();
	}
	
	public String getText() {
		return txt_translatedDul.getText();
	}
	
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
	
	public boolean isDatasetDisabled() {
		try {
			return btn_Enable.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isDatasetEnabled() {
		try {
			return btn_Disable.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public List<WebElement> getAllDatasets() {
        return allDatasets;
    }

}
