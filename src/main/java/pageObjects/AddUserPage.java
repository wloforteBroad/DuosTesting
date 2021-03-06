package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddUserPage {
WebDriver driver;
	
	public AddUserPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.NAME, using = "inputName") 
	private WebElement txtbx_Name;
	
	@FindBy(how = How.NAME, using = "inputGoogleId") 
	private WebElement txtbx_Mail;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Admin')]") 
	private WebElement chkbx_Admin;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Member')]") 
	private WebElement chkbx_Member;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Chairperson')]") 
	private WebElement chkbx_Chairperson;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Researcher')]") 
	private WebElement chkbx_Researcher;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Alumni')]") 
	private WebElement chkbx_Alumni;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Data Owner')]") 
	private WebElement chkbx_DataOwner;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add')]") 
	private WebElement btn_Add;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Save')]") 
	private WebElement btn_Save;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Cancel')]") 
	private WebElement btn_Cancel;
	
	public void enter_Name(String name) {
		txtbx_Name.sendKeys(name);
	}
	
	public void enter_Mail(String mail) {
		txtbx_Mail.sendKeys(mail);
	}
	
	public void check_AdminRole() {
		chkbx_Admin.click();
	}
	
	public void check_MemberRole() {
		chkbx_Member.click();
	}
	
	public void check_ChairpersonRole() {
		chkbx_Chairperson.click();
	}
	
	public void check_ResearcherRole() {
		chkbx_Researcher.click();
	}
	
	public void check_AlumniRole() {
		chkbx_Alumni.click();
	}
	
	public void check_DataOwnerRole() {
		chkbx_DataOwner.click();
	}
	
	public void clickOn_Add() {
		btn_Add.click();
	}
	
	public void clickOn_Cancel() {
		btn_Cancel.click();
	}
	
	public void clickOn_Save() {
		btn_Save.click();
	}
	
	public void createMockUser(String name, String mail) {
		this.enter_Name(name);
		this.enter_Mail(mail);
		this.check_AdminRole();
		this.clickOn_Add();
	}
	
}
