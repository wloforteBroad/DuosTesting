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
	
	@FindBy(how = How.ID, using = "addDulModal_title") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "addDulModal_description") 
	private WebElement lbl_Description;
	
	@FindBy(how = How.ID, using = "txt_consentId") 
	private WebElement txtbx_UniqueId;
	
	@FindBy(how = How.ID, using = "txt_consentName") 
	private WebElement txtbx_ConsentName;
	
	@FindBy(how = How.ID, using = "btn_uploadFile") 
	private WebElement btn_Upload;
	
	@FindBy(how = How.ID, using = "txt_sdul") 
	private WebElement txtarea_Sdul;
	
	@FindBy(how = How.ID, using = "txt_dataUse") 
	private WebElement txtarea_DataUse;
	
	@FindBy(how = How.ID, using = "btn_action") 
	private WebElement btn_Add;
	
	@FindBy(how = How.ID, using = "btn_cancel") 
	private WebElement btn_Cancel;
	
	private String title = "Add Data Use Limitations";
	private String description = "Catalog a Data Use Limitations Record";
	
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
