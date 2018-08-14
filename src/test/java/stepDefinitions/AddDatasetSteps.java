package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import pageObjects.AddDatasetPage;

public class AddDatasetSteps {
	TestContext testContext;
	AddDatasetPage addDatasetPage;
	WebDriver driver;
	
	public AddDatasetSteps(TestContext context) {
		testContext = context;
		addDatasetPage = testContext.getPageObjectManager().getAddDatasetPage();
	}
	
	@When("^User is on Add Dataset Modal$")
	public void user_is_on_Add_Dataset_Modal() throws Throwable {
	    assert addDatasetPage.isUserOnAddDatasetPage();
	}
	
	@When("^clicks on Upload file button and selects file$")
	public void clicks_on_Upload_file_button_and_selects_file() throws Throwable {
	    addDatasetPage.upload_CorrectDataset();
	    addDatasetPage.clickOn_Overwrite();
	}
	
	@When("^clicks on Add button$")
	public void clicks_on_Add_button() throws Throwable {
	    addDatasetPage.clickOn_Add();
	}

}
