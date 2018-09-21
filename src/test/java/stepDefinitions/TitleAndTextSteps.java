package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import pageObjects.AdminConsolePage;

public class TitleAndTextSteps {
	TestContext testContext;
	AdminConsolePage adminConsolePage;
	WebDriver driver;
	
	public TitleAndTextSteps(TestContext context) {
		testContext = context;
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
	}
	
	@Then("^All titles are correct$")
	public void all_titles_are_correct() throws Throwable {
	    assert adminConsolePage.compareTitles();
	}
	
	@Then("^All descriptions are correct$")
	public void all_descriptions_are_correct() throws Throwable {
	    assert adminConsolePage.compareDescriptions();
	}

}
