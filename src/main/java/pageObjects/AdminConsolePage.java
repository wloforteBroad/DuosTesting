package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminConsolePage {
	
	public AdminConsolePage(WebDriver driver) {
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "adminConsole_title")
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "adminConsole_description")
	private WebElement lbl_Description;
	
	@FindBy(how = How.ID, using = "btn_manageDUL") 
	private WebElement btn_ManageDul;
	
	@FindBy(how = How.ID, using = "btn_addDUL") 
	private WebElement btn_AddDul;
	
	@FindBy(how = How.ID, using = "btn_manageUsers") 
	private WebElement btn_ManageUsers;
	
	@FindBy(how = How.ID, using = "btn_addUser") 
	private WebElement btn_AddUser;
	
	@FindBy(how = How.ID, using = "btn_manageDAR") 
	private WebElement btn_ManageDar;
	
	@FindBy(how = How.ID, using = "btn_addDataset") 
	private WebElement btn_AddDatasets;
	
	@FindBy(how = How.ID, using = "btn_addDataset") 
	private WebElement btn_ElectionTimeout;
	
	@FindBy(how = How.ID, using = "btn_addDataset") 
	private WebElement btn_InvalidRestrictions;
	
	@FindBy(how = How.ID, using = "btn_addDataset") 
	private WebElement btn_ManageOntologies;
	
	@FindBy(how = How.ID, using = "btn_addDataset") 
	private WebElement btn_AddOntologies;
	
	
	public boolean isUserOnAdminConsole() {
		try {
			return lbl_Title.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public void clickOn_ManageDul() {
		btn_ManageDul.click();
	}
	
	public void clickOn_AddDul() {
		btn_AddDul.click();
	}
	
	public void clickOn_ManageUsers() {
		btn_ManageUsers.click();
	}
	
	public void clickOn_AddUser() {
		btn_AddUser.click();
	}
	
	public void clickOn_ManageDar() {
		btn_ManageDar.click();
	}
	
	public void clickOn_AddDatasets() {
		btn_AddDatasets.click();
	}
	
}
