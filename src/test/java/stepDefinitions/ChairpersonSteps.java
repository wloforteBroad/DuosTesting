package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.ChairConsolePage;
import pageObjects.DulCollectVotesPage;
import pageObjects.DulVotingPage;
import pageObjects.ModalPage;

public class ChairpersonSteps {
	TestContext testContext;
	ChairConsolePage chairConsolePage;
	DulCollectVotesPage dulCollectVotesPage;
	DulVotingPage dulVotingPage;
	ModalPage modalPage;
	MySQLDBHelper db = new MySQLDBHelper();
	
	
	public ChairpersonSteps(TestContext context) {
		testContext = context;
		chairConsolePage = testContext.getPageObjectManager().getChairConsolePage();
		dulCollectVotesPage = testContext.getPageObjectManager().getDulCollectVotesPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
		dulVotingPage = testContext.getPageObjectManager().getDulVotingPage();
	}
	
	@Given("^user clicks on Collect Votes for a given DUL Election$")
	public void user_clicks_on_Collect_Votes_for_a_given_DUL_Election() throws Throwable {
	    chairConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	    chairConsolePage.clickOn_DulCollectVotes();
	}
	
	@Given("^The user already voted as Member$")
	public void the_user_already_voted_as_Member() throws Throwable {
		chairConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	    chairConsolePage.clickOn_DulVote();
	    dulVotingPage.clickOn_Yes();
	    dulVotingPage.clickOn_Vote();
		modalPage.waitForModalToLoad();
	    modalPage.clickOn_Ok();
	}
	
	@When("^user votes with a positive vote and submits Collect DUL vote$")
	public void user_votes_with_a_positive_vote_and_submits_Collect_DUL_vote() throws Throwable {
	    dulCollectVotesPage.clickOn_Yes();
	    dulCollectVotesPage.enter_Rationale("FINAL VOTE!");
	    dulCollectVotesPage.clickOn_Vote();
		modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();
	}
	
	@When("^user click on Send a Reminder to a given user$")
	public void user_click_on_Send_a_Reminder_to_a_given_user() throws Throwable {
	    dulCollectVotesPage.getAllReminders().get(0).click();
		modalPage.waitForModalToLoad();
	    modalPage.clickOn_Ok();
	}
	
	@When("^one Member updates the vote$")
	public void one_Member_updates_the_vote() throws Throwable {
	    db.updateVote(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	}
	
	@Then("^the user should see a list of Pending cases for Review$")
	public void the_user_should_see_a_list_of_Pending_cases_for_Review() throws Throwable {
		chairConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	    assert chairConsolePage.getAllDuls().size() > 0;
	}
	
	@Then("^the review case for the given Consent is not present on the Console$")
	public void the_review_case_for_the_given_Consent_is_not_present_on_the_Console() throws Throwable {
	    chairConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	    assert chairConsolePage.getAllDuls().size() == 0;
	}
	
	@Then("^the Vote button is disabled until all members have voted$")
	public void the_Vote_button_is_disabled_until_all_members_have_voted() throws Throwable {
	    assert dulCollectVotesPage.isVoteEnabled() == false;
	}
	
	@Then("^the system sends a notification to selected user$")
	public void the_system_sends_a_notification_to_selected_user() throws Throwable {
	    assert db.checkReminder(FileReaderManager.getInstance().getConfigReader().getConsentIdMember()) == 1;
	}
	
	@Then("^the user should see a message saying Updated Vote!$")
	public void the_user_should_see_a_message_saying_Updated_Vote() throws Throwable {
	    assert dulCollectVotesPage.isVoteUpdated();
	}
	
	@Then("^the user should see a flag with the amount of cases for review$")
	public void the_user_should_see_a_flag_with_the_amount_of_cases_for_review() throws Throwable {
		Thread.sleep(1000);
		System.out.println(chairConsolePage.getPendingCases());
	    assert  chairConsolePage.getPendingCases().equalsIgnoreCase("1");
	}

	
}
