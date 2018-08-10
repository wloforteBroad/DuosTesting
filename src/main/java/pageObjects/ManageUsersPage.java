package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ManageUsersPage {
WebDriver driver;	
	
	public ManageUsersPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'test.user@gmail.com')]") 
	private WebElement lbl_Mail;
	
	@FindBy(how = How.CSS, using = ".users-search") 
	private WebElement txtbx_SearchUser;
	
	public boolean isUserDisplayed() {
		try {
			return lbl_Mail.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public void findUser(String userMail) {
		txtbx_SearchUser.sendKeys(userMail);
	}
	

}
