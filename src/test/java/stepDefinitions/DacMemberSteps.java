package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.DacConsolePage;
import pageObjects.DarVotingPage;
import pageObjects.DulVotingPage;
import pageObjects.ModalPage;

public class DacMemberSteps {
	TestContext testContext;
	DacConsolePage dacConsolePage;
	DulVotingPage dulVotingPage;
	DarVotingPage darVotingPage;
	ModalPage modalPage;
	MySQLDBHelper db = new MySQLDBHelper();
	
	
	public DacMemberSteps(TestContext context) {
		testContext = context;
		dacConsolePage = testContext.getPageObjectManager().getDacConsolePage();
		dulVotingPage = testContext.getPageObjectManager().getDulVotingPage();
		darVotingPage = testContext.getPageObjectManager().getDarVotingPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
	}
	
	@Given("^user clicks on vote for a given DUL Election$")
	public void user_clicks_on_vote_for_a_given_DUL_Election() throws Throwable {
	    dacConsolePage.findConsentAndVote(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	}
	
	@Given("^user has already voted on a given DUL Election$")
	public void user_has_already_voted_on_a_given_DUL_Election() throws Throwable {
		dacConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	    if (dacConsolePage.isVoteEditable() == false) {
	    	dacConsolePage.clickOn_Dul_Vote();
	    	dulVotingPage.voteYes("Because YES");
		    modalPage.clickOn_Ok();
	    }
	}
	
	@Given("^user clicks on vote for a given DAR Election$")
	public void user_clicks_on_vote_for_a_given_DAR_Election() throws Throwable {
	    dacConsolePage.findDar("DAR-1000");
	    dacConsolePage.clickOn_Dar_Vote();
	}
	
	@When("^user votes with a positive vote and submits DUL vote$")
	public void user_votes_with_a_positive_vote_and_submits_DUL_vote() throws Throwable {
	    dulVotingPage.voteYes("Because YES");
	    modalPage.clickOn_Ok();
	}
	
	@When("^user votes with a positive vote and submits DAR vote$")
	public void user_votes_with_a_positive_vote_and_submits_DAR_vote() throws Throwable {
	    darVotingPage.voteYes_Access("Because Yes");
	    modalPage.clickOn_Ok();
	}
	
	@When("^user votes again and edits the rationale$")
	public void user_votes_again_and_edits_the_rationale() throws Throwable {
	    dacConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	    dacConsolePage.clickOn_Dul_Edit();
	    dulVotingPage.enter_Rationale("Because YES EDITED");
	    dulVotingPage.clickOn_Vote();
	    modalPage.clickOn_Ok();
	}

	@Then("^the DUL vote status should be Editable$")
	public void the_DUL_vote_status_should_be_Editable() throws Throwable {
	    dacConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	    assert dacConsolePage.isVoteEditable();
	}
	
	@Then("^the DAR vote status should be Editable$")
	public void the_DAR_vote_status_should_be_Editable() throws Throwable {
	    dacConsolePage.findDar("DAR-1000");
	    assert dacConsolePage.isVoteEditable();
	}
	
	@Then("^the user should see the DUL vote with the new rationale$")
	public void the_user_should_see_the_DUL_vote_with_the_new_rationale() throws Throwable {
		dacConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
		dacConsolePage.clickOn_Dul_Edit();
		assert db.checkRationale(FileReaderManager.getInstance().getConfigReader().getConsentIdMember()).equals("Because YES EDITED");
	}
	
	@Then("^the Vote button should be disabled$")
	public void the_Vote_button_should_be_disabled() throws Throwable {
	    assert !dulVotingPage.isVoteEnabled();
	}
	
	@Then("^the User should see a list of Pending Cases for Review$")
	public void the_User_should_see_a_list_of_Pending_Cases_for_Review() throws Throwable {
	    dacConsolePage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	    assert dacConsolePage.isVotePending();
	}
	


}
