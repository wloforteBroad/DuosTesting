package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import managers.FileReaderManager;
import pageObjects.DacConsolePage;
import pageObjects.HeaderPage;

public class DacConsoleSteps {
	TestContext testContext;
	DacConsolePage dacConsolePage;
	HeaderPage headerPage;
	WebDriver driver;
	
	public DacConsoleSteps(TestContext context) {
		testContext = context;
		dacConsolePage = testContext.getPageObjectManager().getDacConsolePage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
	}
	
	@Given("^The user is on Dac Console$")
	public void the_user_is_on_Dac_Console() throws Throwable {
		Thread.sleep(500);
	    dacConsolePage.isUserOnDacConsole();
	}
	
	@Given("^The user clicks on Request Help on the header$")
	public void the_user_clicks_on_Request_Help_on_the_header() throws Throwable {
	    headerPage.clickOn_RequestHelp();;
	}
	
	@Given("^The user clicks on Create a Report option$")
	public void the_user_clicks_on_Create_a_Report_option() throws Throwable {
	    headerPage.clickOn_CreateReport();
	}
	
	@Given("^The user search the consent$")
	public void the_user_search_the_consent() throws Throwable {
	    dacConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	}
	
	@Given("^The user clicks on Vote button$")
	public void the_user_clicks_on_Vote_button() throws Throwable {
	    dacConsolePage.clickOn_Dul_Vote();
	}
	
	@Given("^The user clicks on Collect Votes button$")
	public void the_user_clicks_on_Collect_Votes_button() throws Throwable {
	    dacConsolePage.clickOn_CollectVotes();
	}
	
	@Then("^the user navigates to Dac Console and consent is on Editable Status$")
	public void the_user_navigates_to_Dac_Console_and_consent_is_on_Editable_Status() throws Throwable {
		Thread.sleep(500);
	    dacConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	    dacConsolePage.isVoteEditable();
	}
	
	@Then("^the user navigates to Dac Console and Chaiperson can Collect Votes$")
	public void the_user_navigates_to_Dac_Console_and_chairperson_can_collect_votes() throws Throwable {
		Thread.sleep(500);
	    dacConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	    dacConsolePage.isCollectVotesDisplayed();
	}
	
	@Then("^user navigates to DAC Console$")
	public void user_navigates_to_DAC_Console() throws Throwable {
		Thread.sleep(500);
	    dacConsolePage.isUserOnDacConsole();
	}
	
	@Then("^the user navigates to Dac Console and Consent Election is closed$")
	public void the_user_navigates_to_Dac_Console_and_Consent_Election_is_closed() throws Throwable {
	    Thread.sleep(500);
	    dacConsolePage.isConsentClosed();
	}


}
