package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import pageObjects.DulCollectVotesPage;
import pageObjects.ModalPage;

public class DulCollectVotesSteps {
	TestContext testContext;
	DulCollectVotesPage dulCollectVotesPage;
	ModalPage modalPage;
	WebDriver driver;
	
	public DulCollectVotesSteps(TestContext context) {
		testContext = context;
		dulCollectVotesPage = testContext.getPageObjectManager().getDulCollectVotesPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
	}
	
	@When("^User is on Collect votes page$")
	public void user_is_on_Collect_votes_page() throws Throwable {
	    Thread.sleep(500);
	    dulCollectVotesPage.isUserOnDulCollectPage();
	}
	
	@When("^clicks yes and enters Collect Votes rationale$")
	public void clicks_yes_and_enters_Collect_Votes_rationale() throws Throwable {
	    dulCollectVotesPage.clickOn_Yes();
	}
	
	@When("^clicks Vote button for Collecting votes$")
	public void clicks_Vote_button_for_Collecting_votes() throws Throwable {
	    dulCollectVotesPage.clickOn_Vote();
	    Thread.sleep(500);
	    modalPage.clickOn_Yes();
	}

}
