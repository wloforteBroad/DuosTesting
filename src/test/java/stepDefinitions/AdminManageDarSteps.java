package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddDulPage;
import pageObjects.AdminConsolePage;
import pageObjects.DarCollectVotesPage;
import pageObjects.DarSummaryPage;
import pageObjects.DulCollectVotesPage;
import pageObjects.DulResultRecordPage;
import pageObjects.ManageDarPage;
import pageObjects.ModalPage;
import pageObjects.DarPreviewResultsPage;

public class AdminManageDarSteps {
	TestContext testContext;
	ManageDarPage manageDarPage;
	ModalPage modalPage;
	AdminConsolePage adminConsolePage;
	DulCollectVotesPage dulCollectVotesPage;
	DulResultRecordPage dulResultRecordPage;
	AddDulPage addDulPage;
	DarPreviewResultsPage darPreviewResultsPage;
	DarSummaryPage darSummaryPage;
	DarCollectVotesPage darCollectVotesPage;
	private String electionStatus;
	
	public AdminManageDarSteps(TestContext context) {
		testContext = context;
		manageDarPage = testContext.getPageObjectManager().getManageDarPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		addDulPage = testContext.getPageObjectManager().getAddDulPage();
		darPreviewResultsPage = testContext.getPageObjectManager().getDarPreviewResultsPage();
		dulCollectVotesPage = testContext.getPageObjectManager().getDulCollectVotesPage();
		dulResultRecordPage = testContext.getPageObjectManager().getDulResultRecordPage();
		darSummaryPage = testContext.getPageObjectManager().getDarSummaryPage();
		darCollectVotesPage = testContext.getPageObjectManager().getDarCollectVotesPage();
	}
	
	@Given("^One DAR election is opened$")
	public void one_DAR_election_is_opened() throws Throwable {
		manageDarPage.findDar("DAR-1000");
		manageDarPage.clickOn_Create();
		modalPage.clickOn_Yes();
	}
	
	@When("^The user clicks on Manage Data Access Request$")
	public void the_user_clicks_on_Manage_Data_Access_Request() throws Throwable {
	    adminConsolePage.clickOn_ManageDar();
	}
	
	@When("^The user click on create button for a given Dar$")
	public void the_user_click_on_create_button_for_a_given_Dar() throws Throwable {
		manageDarPage.findDar("DAR-1000");
		Thread.sleep(1000);
	    manageDarPage.clickOn_Create();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user cancel a given DAR election$")
	public void the_user_cancel_a_given_DAR_election() throws Throwable {
	    manageDarPage.findDar("DAR-1000");
	    manageDarPage.clickOn_Cancel();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user clicks on Summary button$")
	public void the_user_clicks_on_Summary_button() throws Throwable {
	    manageDarPage.findDar("DAR-1000");
	    manageDarPage.clickOn_Summary();
	}
	
	@When("^The user clicks on Election Status for a given DAR$")
	public void the_user_clicks_on_Election_Status_for_a_given_DAR() throws Throwable {
		manageDarPage.findDar("DAR-1000");
	    if (manageDarPage.isElectionOpen()) {
	    	this.electionStatus = "Open";
	    	manageDarPage.clickOn_StatusOpen();
		} else if (manageDarPage.isElectionCanceled()) {
			this.electionStatus = "Canceled";
			manageDarPage.clickOn_StatusCanceled();
		} else if (manageDarPage.isElectionReviewed()) {
			this.electionStatus = "Reviewed";
			manageDarPage.clickOn_StatusReviewed();
		} else {
			this.electionStatus = "UnReviewed";
			manageDarPage.clickOn_StatusUnReviewed();
		}
	}
	
	@Then("^the user should see a list of Data Access Requests$")
	public void the_user_should_see_a_list_of_Data_Access_Requests() throws Throwable {
		assert manageDarPage.getAllData().size() > 0;
	}
	
	@Then("^The DAR Election Status should be Open$")
	public void the_DAR_Election_Status_should_be_Open() throws Throwable {
		manageDarPage.findDar("DAR-1000");
	    assert manageDarPage.isElectionOpen();
	}
	
	@Then("^The DAR Election Status should be Canceled$")
	public void the_DAR_Election_Status_should_be_Canceled() throws Throwable {
	    manageDarPage.findDar("DAR-1000");
	    assert manageDarPage.isElectionCanceled();
	}
	
	@Then("^The user should see the Application Summary Modal$")
	public void the_user_should_see_the_Application_Summary_Modal() throws Throwable {
	    assert darSummaryPage.isUserOnDarSummary();
	}
	
	@Then("^The user should see the preview page of that DAR Election depending on the status$")
	public void the_user_should_see_the_preview_page_of_that_DAR_Election_depending_on_the_status() throws Throwable {
		if (this.electionStatus == "Open") {
			assert darCollectVotesPage.isUserOnDarCollectPage();
		} else if (this.electionStatus == "Canceled") {
			assert darPreviewResultsPage.isUserOnPreviewResultsPage();
		} else if (this.electionStatus == "Reviewed") {
			//TODO THIS ASSERTION
			assert dulResultRecordPage.isUserOnDulResultRecordPage();
		} else {
			assert darPreviewResultsPage.isUserOnPreviewResultsPage();
		}
	}
	
	@Then("^The user should see the DAR flagged with an icon saying that it Needs Approval$")
	public void the_user_should_see_the_DAR_flagged_with_an_icon_saying_that_it_Needs_Approval() throws Throwable {
	    manageDarPage.findDar("DAR-1000");
	    assert manageDarPage.getApprovalTooltip().equals("Needs Approval");
	}


}
