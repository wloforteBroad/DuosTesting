package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import managers.FileReaderManager;

public class AddDulPage {
WebDriver driver;
	
	public AddDulPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	String filePath = System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getCorrectDatasetPath();
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Data Use Limitations')]") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.NAME, using = "inputConsentId") 
	private WebElement txtbx_UniqueId;
	
	@FindBy(how = How.NAME, using = "inputName") 
	private WebElement txtbx_ConsentName;
	
	@FindBy(how = How.CSS, using = ".upload") 
	private WebElement btn_Upload;
	
	@FindBy(how = How.NAME, using = "inputSDUL") 
	private WebElement txtarea_Sdul;
	
	@FindBy(how = How.NAME, using = "inputDU") 
	private WebElement txtarea_DataUse;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add')]") 
	private WebElement btn_Add;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Cancel')]") 
	private WebElement btn_Cancel;
	
	@FindBy(how = How.CLASS_NAME, using = "alert") 
	private WebElement error_area;
	
	public void upload_File() {
		btn_Upload.sendKeys(filePath);
	}
	
	public void enter_Id(String id) {
		txtbx_UniqueId.sendKeys(id);
	}
	
	public void enter_Name(String name) {
		txtbx_ConsentName.sendKeys(name);
	}
	
	public void enter_Sdul(String sdul) {
		txtarea_Sdul.sendKeys(sdul);
	}
	
	public void enter_DataUse(String dataUse) {
		txtarea_DataUse.sendKeys(dataUse);
	}
	
	public void clickOn_Add() {
		btn_Add.click();
	}
	
	public void clickOn_Cancel() {
		btn_Cancel.click();
	}
	
	public boolean isUserOnAddDulPage() {
		try {
			return lbl_Title.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isErrorDisplayed() {
		try {
			return error_area.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isAddButtonDisabled() {
		try {
			return btn_Add.isEnabled() ? false : true;
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public void completeForm(String id, String name, String sdul, String dataUse) {
		enter_Id(id);
		enter_Name(name);
		upload_File();
		enter_Sdul(sdul);
		enter_DataUse(dataUse);
	}

}
