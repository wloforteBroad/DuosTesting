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

}
