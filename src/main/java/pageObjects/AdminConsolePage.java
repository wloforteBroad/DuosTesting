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
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Welcome Walter Lo Forte!')]")
	private WebElement lbl_UserName;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Manage Data Use Limitations')]") 
	private WebElement btn_ManageDul;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Data Use Limitations')]") 
	private WebElement btn_AddDul;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Manage Users')]") 
	private WebElement btn_ManageUsers;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add User')]") 
	private WebElement btn_AddUser;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Manage Data Access Request')]") 
	private WebElement btn_ManageDar;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Datasets')]") 
	private WebElement btn_AddDatasets;
	
	public boolean isUserGreetDisplayed() {
		try {
			return lbl_UserName.isDisplayed();
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
