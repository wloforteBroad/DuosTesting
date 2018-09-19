package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.DarSummaryPage;
import pageObjects.DataOwnerConsolePage;
import pageObjects.DataOwnerVotePage;
import pageObjects.DataSetSummaryPage;
import pageObjects.ModalPage;

public class DataOwnerSteps {
	TestContext testContext;
	ModalPage modalPage;
	DataOwnerConsolePage dataOwnerConsolePage;
	DataOwnerVotePage dataOwnerVotePage;
	DarSummaryPage applicationSummarypage;
	DataSetSummaryPage dataSetSummaryPage;
	
	
	public DataOwnerSteps(TestContext context) {
		testContext = context;
		dataOwnerConsolePage = testContext.getPageObjectManager().getDataOwnerConsolePage();
		dataOwnerVotePage = testContext.getPageObjectManager().getDataOwnerVotePage();
		modalPage = testContext.getPageObjectManager().getModalPage();
		applicationSummarypage = testContext.getPageObjectManager().getDarSummaryPage();
		dataSetSummaryPage = testContext.getPageObjectManager().getDataSetSummaryPage();
	}
	
	@Given("^The user clicks on a given Dataset Access Review case$")
	public void the_user_clicks_on_a_given_Dataset_Access_Review_case() throws Throwable {
	    dataOwnerConsolePage.findCase("SC-20800");
	    dataOwnerConsolePage.clickOn_Vote();
	}
	
	@When("^user votes and approves the case$")
	public void user_votes_and_approves_the_case() throws Throwable {
	    dataOwnerVotePage.clickOn_Approve();
	    dataOwnerVotePage.enter_Rationale("I Approve because I can");
	    dataOwnerVotePage.clickOn_Vote();
		modalPage.waitForModalToLoad();
	    modalPage.clickOn_Ok();
	}
	
	@When("^the user clicks on Application Summary$")
	public void the_user_clicks_on_Application_Summary() throws Throwable {
	    dataOwnerVotePage.clickOn_AppSummary();
	}

	@When("^the user clicks on Dataset Summary$")
	public void the_user_clicks_on_Dataset_Summary() throws Throwable {
	    dataOwnerVotePage.clickOn_DatasetSummary();
	}

	@Then("^The selected case should not be present in the Console$")
	public void the_selected_case_should_not_be_present_in_the_Console() throws Throwable {
	    dataOwnerConsolePage.findCase("SC-20800");
	    assert dataOwnerConsolePage.getAllPendingCases().isEmpty();
	}
	
	@Then("^the user sees a list of Pending Dataset Access Review cases$")
	public void the_user_sees_a_list_of_Pending_Dataset_Access_Review_cases() throws Throwable {
	    dataOwnerConsolePage.findCase("SC-20800");
	    assert !dataOwnerConsolePage.getAllPendingCases().isEmpty();
	}
	
	@Then("^The user should see the correct Application Summary$")
	public void the_user_should_see_the_correct_Application_Summary() throws Throwable {
	    assert applicationSummarypage.isUserOnDarSummary();
	}
	
	@Then("^The user should see the correct Dataset Summary$")
	public void the_user_should_see_the_correct_Dataset_Summary() throws Throwable {
	    assert dataSetSummaryPage.isUserOnDataSetSummary();
	}
	
}
