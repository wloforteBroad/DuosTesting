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
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add')]") 
	private WebElement btn_Add;
	
	public void enter_Name(String name) {
		txtbx_Name.sendKeys(name);
	}
	
	public void enter_Mail(String mail) {
		txtbx_Mail.sendKeys(mail);
	}
	
	public void clickOn_AdminRole() {
		chkbx_Admin.click();
	}
	
	public void clickOn_Add() {
		btn_Add.click();
	}

}
