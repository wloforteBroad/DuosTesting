package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AddDulPage;
import pageObjects.AdminConsolePage;
import pageObjects.DulCollectVotesPage;
import pageObjects.DulResultRecordPage;
import pageObjects.ManageDulPage;
import pageObjects.ModalPage;
import pageObjects.DulPreviewResultsPage;

public class AdminManageDulSteps {
	TestContext testContext;
	ManageDulPage manageDulPage;
	ModalPage modalPage;
	AdminConsolePage adminConsolePage;
	DulCollectVotesPage dulCollectVotesPage;
	DulResultRecordPage dulResultRecordPage;
	AddDulPage addDulPage;
	DulPreviewResultsPage previewResultsPage;
	private String versionNumber;
	private String electionStatus;
	
	public AdminManageDulSteps(TestContext context) {
		testContext = context;
		manageDulPage = testContext.getPageObjectManager().getManageDulPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		addDulPage = testContext.getPageObjectManager().getAddDulPage();
		previewResultsPage = testContext.getPageObjectManager().getDulPreviewResultsPage();
		dulCollectVotesPage = testContext.getPageObjectManager().getDulCollectVotesPage();
		dulResultRecordPage = testContext.getPageObjectManager().getDulResultRecordPage();
	}
	
	
	@When("^The user clicks on Manage Data Use Limitations$")
	public void the_user_clicks_on_Manage_Data_Use_Limitations() throws Throwable {
	    adminConsolePage.clickOn_ManageDul();
	}

	@Then("^the user should see a list of Data Use Limitations$")
	public void the_user_should_see_a_list_of_Data_Use_Limitations() throws Throwable {
    assert manageDulPage.getAllData().size() > 0;
	}
	
	@Given("^The user clicks on Add Data Use Limitations$")
	public void the_user_clicks_on_Add_Data_Use_Limitations() throws Throwable {
	    manageDulPage.clickOn_AddDul();
	}
	
	@Given("^All elections are closed$")
	public void all_elections_are_closed() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
		if (manageDulPage.isElectionOpen()) {
			manageDulPage.clickOn_Cancel();
			modalPage.waitForModalToLoad();
			modalPage.check_Archive();
			modalPage.clickOn_Yes();
		}
		Thread.sleep(1000);
	}
	
	@Given("^One election is opened$")
	public void one_election_is_opened() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
		if (!manageDulPage.isElectionOpen()) {
			manageDulPage.clickOn_Create();
			modalPage.waitForModalToLoad();
			modalPage.clickOn_Yes();
		}
		Thread.sleep(1000);
	}
	
	@When("^The user completes the form with wrong sDul Json format and submits$")
	public void the_user_completes_the_form_with_wrong_sDul_Json_format_and_submits() throws Throwable {
	    addDulPage.completeForm("001", "001_Name", "{\"type\"::\"everything\"}", "{\"generalUse\":true}");
	    addDulPage.clickOn_Add();
	}
	
	@When("^The user completes the form with wrong dataUse format and submits$")
	public void the_user_completes_the_form_with_wrong_dataUse_format_and_submits() throws Throwable {
		addDulPage.completeForm("001", "001_Name", "{\"type\":\"everything\"}", "{\"generalUses\":true}");
	    addDulPage.clickOn_Add();
	}
	
	@When("^The user completes the form and submits$")
	public void the_user_completes_the_form_and_submits() throws Throwable {
	    addDulPage.completeForm("idMock", "ORSP-Delete", "{\"type\":\"everything\"}", "{\"generalUse\":true}");
	    addDulPage.clickOn_Add();
	    Thread.sleep(2000);
	}
	
	@When("^The user click on create button for a given Consent$")
	public void the_user_click_on_create_button_for_a_given_Consent() throws Throwable {
	    manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	    manageDulPage.clickOn_Create();
	    modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();
	    Thread.sleep(2500);
		this.versionNumber = manageDulPage.getVersion();
	}
	
	@When("^The user cancel a given election$")
	public void the_user_cancel_a_given_election() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
		manageDulPage.clickOn_Cancel();
		modalPage.waitForModalToLoad();
		modalPage.check_Archive();
		modalPage.clickOn_Yes();
	}
	
	@When("^The user archive a given election$")
	public void the_user_archive_a_given_election() throws Throwable {
	    Thread.sleep(1000);
		manageDulPage.clickOn_Archive();
		modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user delete a given election$")
	public void the_user_delete_a_given_election() throws Throwable {
	    manageDulPage.findConsent("ORSP-Delete");
	    manageDulPage.clickOn_Delete();
	    modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();
	    Thread.sleep(1000);
	}
	
	@When("^The user click on create button for the same Consent again$")
	public void the_user_click_on_create_button_for_the_same_Consent_again() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	    manageDulPage.clickOn_Create();
	    modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user clicks on Consent Id for a given Consent$")
	public void the_user_clicks_on_Consent_Id_for_a_given_Consent() throws Throwable {
	    manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	    manageDulPage.clickOn_PreviewDul();
	}
	
	@When("^The user clicks on Election Status for a given Consent$")
	public void the_user_clicks_on_Election_Status_for_a_given_Consent() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	    if (manageDulPage.isElectionOpen()) {
	    	this.electionStatus = "Open";
			manageDulPage.clickOn_StatusOpen();
		} else if (manageDulPage.isElectionCanceled()) {
			this.electionStatus = "Canceled";
			manageDulPage.clickOn_StatusCanceled();
		} else if (manageDulPage.isElectionReviewed()) {
			this.electionStatus = "Reviewed";
			manageDulPage.clickOn_StatusReviewed();
		} else {
			this.electionStatus = "UnReviewed";
			manageDulPage.clickOn_StatusUnReviewed();
		}
	}
	
	@Then("^the user should see the new DUL on the Manage Dul list$")
	public void the_user_should_see_the_new_DUL_on_the_Manage_Dul_list() throws Throwable {
	    assert manageDulPage.isDulDisplayed("ORSP-Delete");
	}
	
	@Then("^The user should see the error message$")
	public void the_user_should_see_the_error_message() throws Throwable {
	    assert addDulPage.isErrorDisplayed();
	}
	
	@Then("^cancel button should be disabled$")
	public void cancel_button_should_be_disabled() throws Throwable {
	    assert addDulPage.isAddButtonDisabled();
	}
	
	@Then("^The Election Status should be Open$")
	public void the_Election_Status_should_be_Open() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	    assert manageDulPage.isElectionOpen();
	}
	
	@Then("^The Election Status should be Canceled$")
	public void the_Election_Status_should_be_Canceled() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	    assert manageDulPage.isElectionCanceled();
	}
	
	@Then("^The Election should be Archived$")
	public void the_Election_should_be_Archived() throws Throwable {
	    assert manageDulPage.isElectionArchived();
	}
	
	@Then("^The Election should no longer appear on the list$")
	public void the_Election_should_no_longer_appear_on_the_list() throws Throwable {
		manageDulPage.findConsent("ORSP-Delete");
	    assert manageDulPage.isDulNotDisplayed("ORSP-Delete");
	}
	
	@Then("^The Election Status should be Open and the new version should be increased by one$")
	public void the_Election_Status_should_be_Open_and_the_new_version_should_be_increased_by_one() throws Throwable {
	    manageDulPage.waitForElementToLoad();
	    assert manageDulPage.isElectionOpen();
		assert Integer.valueOf(manageDulPage.getVersion()) == Integer.valueOf(this.versionNumber)+1; 
	}
	
	@Then("^The user should see the preview page of that Dul$")
	public void the_user_should_see_the_preview_page_of_that_Dul() throws Throwable {
	    assert previewResultsPage.isUserOnPreviewResultsPage();
	}
	
	@Then("^The user should see the preview page of that Election depending on the status$")
	public void the_user_should_see_the_preview_page_of_that_Election_depending_on_the_status() throws Throwable {
	    if (this.electionStatus == "Open") {
			assert dulCollectVotesPage.isUserOnDulCollectPage();
		} else if (this.electionStatus == "Canceled") {
			assert previewResultsPage.isUserOnPreviewResultsPage();
		} else if (this.electionStatus == "Reviewed") {
			assert dulResultRecordPage.isUserOnDulResultRecordPage();
		} else {
			assert previewResultsPage.isUserOnPreviewResultsPage();
		}
	}

}
