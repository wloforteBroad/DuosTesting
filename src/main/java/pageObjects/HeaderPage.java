package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
WebDriver driver;
	
	public HeaderPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'About')]") 
	private WebElement btn_About;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Help')]") 
	private WebElement btn_Help;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign In')]") 
	private WebElement btn_SignIn;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Join DUOS')]") 
	private WebElement btn_Join;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'DAC Console')]") 
	private WebElement btn_DacConsole;
	
	@FindBy(how = How.LINK_TEXT, using = "Admin Console") 
	private WebElement btn_AdminConsole;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Researcher Console')]") 
	private WebElement btn_ResearcherConsole;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Data Owner Console')]") 
	private WebElement btn_DataOwnerConsole;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Request Application')]") 
	private WebElement btn_RequestApplication;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Statistics')]") 
	private WebElement btn_Stats;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Votes Statistics')]") 
	private WebElement btn_VotesStats;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Reviewed Cases Record')]") 
	private WebElement btn_ReviewedCases;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Dataset Catalog')]") 
	private WebElement btn_DatasetCatalog;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Request Help')]") 
	private WebElement btn_RequestHelp;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Create a Report')]") 
	private WebElement btn_CreateReport;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'List of Reports')]") 
	private WebElement btn_ListReports;
	
	
	public void clickOn_About() {
		btn_About.click();
	}
	
	public void clickOn_Help() {
		btn_Help.click();
	}
	
	public void clickOn_SignIn() {
		btn_SignIn.click();
	}
	
	public void clickOn_DacConsole() {
		btn_DacConsole.click();
	}
	
	public void clickOn_AdminConsole() {
		btn_AdminConsole.click();
	}
	
	public void clickOn_ResearcherConsole() {
		btn_ResearcherConsole.click();
	}
	
	public void clickOn_DataOwnerConsole() {
		btn_DataOwnerConsole.click();
	}
	
	public void clickOn_RequestApplication() {
		btn_RequestApplication.click();
	}
	
	public void clickOn_VotesStatistics() throws InterruptedException {
		btn_Stats.click();
		Thread.sleep(500);
		btn_VotesStats.click();
	}
	
	public void clickOn_ReviewedCases() throws InterruptedException {
		btn_Stats.click();
		Thread.sleep(500);
		btn_ReviewedCases.click();
	}
	
	public void clickOn_DatasetCatalog() {
		btn_DatasetCatalog.click();
	}
	
	public void clickOn_CreateHelpReport() throws InterruptedException {
		btn_RequestHelp.click();
		Thread.sleep(500);
		btn_CreateReport.click();
	}
	
	public void clickOn_ListReports() throws InterruptedException {
		btn_RequestHelp.click();
		Thread.sleep(500);
		btn_ListReports.click();
	}

}
