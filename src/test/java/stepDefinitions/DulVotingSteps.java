package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import pageObjects.DulVotingPage;
import pageObjects.ModalPage;

public class DulVotingSteps {
	TestContext testContext;
	DulVotingPage dulVotingPage;
	ModalPage modalPage;
	WebDriver driver;
	
	public DulVotingSteps(TestContext context) {
		testContext = context;
		dulVotingPage = testContext.getPageObjectManager().getDulVotingPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
	}
	
	@When("^User is on voting page$")
	public void user_is_on_voting_page() throws Throwable {
	    Thread.sleep(500);
	    dulVotingPage.isUserOnVotingPage();
	}
	
	@When("^clicks yes and enters rationale$")
	public void clicks_yes_and_enters_rationale() throws Throwable {
	    dulVotingPage.clickOn_Yes();
	    dulVotingPage.enter_Rationale("Because reasons");
	}
	
	@When("^clicks Vote button$")
	public void clicks_Vote_button() throws Throwable {
	    dulVotingPage.clickOn_Vote();
	    Thread.sleep(1000);
	    modalPage.clickOn_Ok();
	}

}
