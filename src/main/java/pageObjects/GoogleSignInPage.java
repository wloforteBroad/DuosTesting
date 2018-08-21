package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GoogleSignInPage {
	WebDriver driver;
	
	public GoogleSignInPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "identifierId") 
	private WebElement txtbx_UserName;
	
	@FindBy(how = How.ID, using = "identifierNext") 
	private WebElement btn_Next;
	
	@FindBy(how = How.XPATH, using = "//input[@type='password']") 
	private WebElement txtbx_Password;
	
	@FindBy(how = How.ID, using = "passwordNext") 
	private WebElement btn_PasswordNext;
	
	
	public void enter_UserName(String name) {
		txtbx_UserName.sendKeys(name);
	}
	
	public void enter_Password(String pass) {
		txtbx_Password.sendKeys(pass);
	}
	
	public void clickOn_Next() {
		btn_Next.click();
	}
	
	public void clickOn_PasswordNext() {
		btn_PasswordNext.click();
	}
	
	public void signIn(String user, String password) throws InterruptedException {
		Thread.sleep(1000);
		enter_UserName(user);
		Thread.sleep(1000);
		clickOn_Next();
		Thread.sleep(3000);
		enter_Password(password);
		clickOn_PasswordNext();	
	}
	

}
