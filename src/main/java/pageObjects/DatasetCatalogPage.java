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
	
	@FindBy(how = How.ID, using = "txt_search_datasetCatalog") 
	private WebElement txtbx_SearchDataset;
	
	@FindBy(how = How.ID, using = "datasetCatalog_title")
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "datasetCatalog_description")
	private WebElement lbl_Description;
	
	@FindBy(how = How.ID, using = "all")
	private WebElement chkbx_All;
	
	@FindBy(how = How.CSS, using = ".glyphicon-trash") 
	private WebElement btn_Delete;
	
	@FindBy(how = How.CSS, using = ".glyphicon-ban-circle") 
	private WebElement btn_Enable;

	@FindBy(how = How.ID, using = "txt_translatedRestrictions") 
	private WebElement txt_translatedDul;
	
	@FindBy(how = How.CSS, using = ".glyphicon-ok-circle") 
	private WebElement btn_Disable;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Download selection')]") 
	private WebElement btn_Download;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Translated Use Restriction')]") 
	private WebElement btn_TranslatedDul;
	
	@FindBys(@FindBy(className="tableRow"))
	private List<WebElement> allDatasets;
	
	private String title = "Dataset Catalog";
	private String description = "Datasets with an associated DUL to apply for secondary use.";
	
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
	
	public String getTranslatedText() {
		return txt_translatedDul.getText();
	}
	
	public boolean isUserOnDatasetCatalog() {
		try {
			return lbl_Title.isDisplayed();
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
	
	public boolean isTitleOk() {
		try {
			return lbl_Title.getText().equals(title);
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isDescriptionOk() {
		try {
			return lbl_Description.getText().equals(description);
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
