package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import managers.FileReaderManager;

public class AddDatasetPage {
WebDriver driver;
	
	public AddDatasetPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	String filePath = System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getCorrectDatasetPath();
	
	@FindBy(how = How.ID, using = "btn_uploadFile") 
	private WebElement btn_Upload;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Datasets')]") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "chk_overwrite") 
	private WebElement lbl_Overwrite;
	
	@FindBy(how = How.ID, using = "btn_action") 
	private WebElement btn_Add;
	
	public void upload_CorrectDataset() {
		btn_Upload.sendKeys(filePath);
		this.clickOn_Overwrite();
		this.clickOn_Add();
	}
	
	public void clickOn_Overwrite() {
		lbl_Overwrite.click();
	}
	
	public void clickOn_Add() {
		btn_Add.click();
	}
	
	public boolean isUserOnAddDatasetPage() {
		try {
			return lbl_Title.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	

}
