package pageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ManageUsersPage {
WebDriver driver;	
	
	public ManageUsersPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "txt_search_manageUsers") 
	private WebElement txtbx_SearchUser;
	
	@FindBy(how = How.ID, using = "manageUsers_title") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "manageUsers_description") 
	private WebElement lbl_Description;
	
	@FindBys(@FindBy(name="userRoles"))
	private List<WebElement> allRoles;
	
	@FindBy(how = How.NAME, using = "btn_editUser") 
	private WebElement btn_Edit;
	
	@FindBys(@FindBy(className="tableRow"))
	private List<WebElement> allUsers;
	
	private String title = "Manage Users";
	private String description = "Select and manage users and their roles";
	
	
	public void clickOn_Edit() {
		btn_Edit.click();
	}
	
	public void findUser(String searchTerm) {
		txtbx_SearchUser.clear();
		txtbx_SearchUser.sendKeys(searchTerm);
	}
	
	public List<WebElement> getAllMembers() {
        return allUsers;
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
