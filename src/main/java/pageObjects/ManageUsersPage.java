package pageObjects;

import java.util.List;

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
	
	@FindBy(how = How.CSS, using = ".users-search") 
	private WebElement txtbx_SearchUser;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Member')]") 
	private WebElement lbl_Member;
	
	@FindBy(how = How.CLASS_NAME, using = "admin-manage-buttons") 
	private WebElement btn_Edit;
	
	@FindBys(@FindBy(css=".ng-scope[ng-repeat*='searchUsers']"))
	private List<WebElement> allUsers;
	
	
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
	

}
