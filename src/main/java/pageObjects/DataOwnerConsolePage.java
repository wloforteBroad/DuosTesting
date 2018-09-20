package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DataOwnerConsolePage {
WebDriver driver;
	
	public DataOwnerConsolePage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "dataOwnerConsole_title") 
	private WebElement lbl_Title;
	
	@FindBy(how = How.ID, using = "txt_search_dataOwnerConsole") 
	private WebElement txtbx_SearchCase;
	
	@FindBy(how = How.ID, using = "dataOwnerConsole_btn_vote") 
	private WebElement btn_Vote;
	
	@FindBys(@FindBy(className="tableRow"))
	private List<WebElement> allCases;
	
	public void clickOn_Vote() {
		btn_Vote.click();
	}
	
	public void findCase(String datasetId) {
		txtbx_SearchCase.clear();
		txtbx_SearchCase.sendKeys(datasetId);
	}
	
	public List<WebElement> getAllPendingCases() {
        return allCases;
    }

}
