package pageObjects;

import org.openqa.selenium.NoSuchElementException;
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
	
	@FindBy(how = How.ID, using = "addUserModal_title") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "addUserModal_description") 
	private WebElement lbl_Description;
	
	@FindBy(how = How.ID, using = "txt_name") 
	private WebElement txtbx_Name;
	
	@FindBy(how = How.ID, using = "txt_email") 
	private WebElement txtbx_Mail;
	
	@FindBy(how = How.ID, using = "chk_admin") 
	private WebElement chkbx_Admin;
	
	@FindBy(how = How.ID, using = "chk_member") 
	private WebElement chkbx_Member;
	
	@FindBy(how = How.ID, using = "chk_chairperson") 
	private WebElement chkbx_Chairperson;
	
	@FindBy(how = How.ID, using = "chk_researcher") 
	private WebElement chkbx_Researcher;
	
	@FindBy(how = How.ID, using = "chk_alumni") 
	private WebElement chkbx_Alumni;
	
	@FindBy(how = How.ID, using = "chk_dataOwner") 
	private WebElement chkbx_DataOwner;
	
	@FindBy(how = How.ID, using = "btn_action") 
	private WebElement btn_Add;
	
	@FindBy(how = How.ID, using = "btn_action") 
	private WebElement btn_Save;
	
	@FindBy(how = How.ID, using = "btn_cancel") 
	private WebElement btn_Cancel;
	
	private String title = "Add User";
	private String description = "Catalog a new User in the system";
	
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
