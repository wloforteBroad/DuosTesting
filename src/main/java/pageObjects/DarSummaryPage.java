package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DarSummaryPage {
WebDriver driver;
	
	public DarSummaryPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "applicationSummaryModal_title")
	private WebElement lbl_AppSummaryTitle;
	
	@FindBy(how = How.ID, using = "txt_darCode")
	private WebElement lbl_DarId;
	
	@FindBy(how = How.ID, using = "txt_PI")
	private WebElement lbl_Pi;
	
//	@FindBy(how = How.ID, using = "TBD")
//	private WebElement lbl_Researcher;
	
	@FindBy(how = How.ID, using = "txt_institution")
	private WebElement lbl_Institution;
	
	@FindBy(how = How.ID, using = "txt_projectTitle")
	private WebElement lbl_Project;
	
//	@FindBys(@FindBy(id="TBD"))
//	private List<WebElement> allDatasets;
//	
//	@FindBys(@FindBy(id="TBD"))
//	private List<WebElement> allResearchTypes;
	
	@FindBy(how = How.ID, using = "btn_action")
	private WebElement btn_Close;
	
	public boolean isUserOnDarSummary() {
		try {
			return lbl_AppSummaryTitle.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean isUserOnCorrectDarSummary(String darId) {
		try {
			return lbl_DarId.getText().equals(darId);
		}catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	
	
	public void clickOn_Close() {
		btn_Close.click();
	}

}
