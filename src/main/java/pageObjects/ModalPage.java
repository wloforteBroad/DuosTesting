package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalPage {
WebDriver driver;	
	
	public ModalPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Yes')]") 
	private WebElement btn_Yes;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'No')]") 
	private WebElement btn_No;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Ok')]") 
	private WebElement btn_Ok;
	
	@FindBy(how = How.CLASS_NAME, using = "regular-checkbox") 
	private WebElement chkbx_Archive;
	
	public void clickOn_Yes() {
		btn_Yes.click();
	}
	
	public void clickOn_No() {
		btn_No.click();
	}
	
	public void clickOn_Ok() {
		btn_Ok.click();
	}
	
	public void check_Archive() {
		chkbx_Archive.click();
	}
	
	public void waitForModalToLoad() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));
    }

}
