package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RequestHelpPage {
WebDriver driver;	
	
	public RequestHelpPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Request Help')]") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.NAME, using = "helpSubject") 
	private WebElement txtbx_Subject;
	
	@FindBy(how = How.NAME, using = "textarea") 
	private WebElement txtbx_Description;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Submit')]") 
	private WebElement btn_Submit;
	
	public boolean isUserOnRequestHelpPage() {
		try {
			return lbl_Title.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public void enter_Subject(String subject) {
		txtbx_Subject.sendKeys(subject);
	}
	
	public void enter_Description(String description) {
		txtbx_Description.sendKeys(description);
	}
	
	public void clickOn_Submit() {
		btn_Submit.click();
	}
	

}
