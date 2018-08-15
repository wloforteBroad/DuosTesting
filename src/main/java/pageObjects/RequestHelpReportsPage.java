package pageObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RequestHelpReportsPage {
WebDriver driver;	
	
	public RequestHelpReportsPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Request Help Reports')]") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.CSS, using = ".users-search") 
	private WebElement txtbx_SearchReport;
	
	public boolean isUserOnReportsPage() {
		try {
			return lbl_Title.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public void find_Report() {
		txtbx_SearchReport.sendKeys();
	}
	
	public boolean isNewReportDisplayed() {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		    Date now = new Date();
		    String today = sdfDate.format(now);
			WebElement dateCell = driver.findElement(By.xpath("//div[contains(text(),'"+ today +"')]"));
			return dateCell.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	

}
