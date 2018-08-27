package stepDefinitions;

import java.io.File;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AddDatasetPage;
import pageObjects.AddDulPage;
import pageObjects.AddUserPage;
import pageObjects.AdminConsolePage;
import pageObjects.DacConsolePage;
import pageObjects.DatasetCatalogPage;
import pageObjects.DulCollectVotesPage;
import pageObjects.DulResultRecordPage;
import pageObjects.DulVotingPage;
import pageObjects.GoogleSignInPage;
import pageObjects.HeaderPage;
import pageObjects.HomePage;
import pageObjects.ManageDulPage;
import pageObjects.ManageUsersPage;
import pageObjects.ModalPage;
import pageObjects.PreviewResultsPage;

public class AdminSteps {
	TestContext testContext;
	ManageDulPage manageDulPage;
	ManageUsersPage manageUsersPage;
	ModalPage modalPage;
	HomePage homePage;
	GoogleSignInPage signInPage;
	HeaderPage headerPage;
	AdminConsolePage adminConsolePage;
	DacConsolePage dacConsolePage;
	DulVotingPage dulVotingPage;
	DulCollectVotesPage dulCollectVotesPage;
	DulResultRecordPage dulResultRecordPage;
	AddDulPage addDulPage;
	PreviewResultsPage previewResultsPage;
	AddUserPage addUserPage;
	AddDatasetPage addDatasetPage;
	DatasetCatalogPage datasetCatalogPage;
	WebDriver driver;
	private String versionNumber;
	private String electionStatus;
	private String tmpFolderPath = "/src/test/resources/";
	private String expectedFileName = "datasets.tsv";
	private File file = new File(tmpFolderPath + expectedFileName);
	
	public AdminSteps(TestContext context) {
		testContext = context;
		manageDulPage = testContext.getPageObjectManager().getManageDulPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
		homePage = testContext.getPageObjectManager().getHomePage();
		signInPage = testContext.getPageObjectManager().getGoogleSignInPage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		dacConsolePage = testContext.getPageObjectManager().getDacConsolePage();
		dulVotingPage = testContext.getPageObjectManager().getDulVotingPage();
		addDulPage = testContext.getPageObjectManager().getAddDulPage();
		previewResultsPage = testContext.getPageObjectManager().getPreviewResultsPage();
		dulCollectVotesPage = testContext.getPageObjectManager().getDulCollectVotesPage();
		dulResultRecordPage = testContext.getPageObjectManager().getDulResultRecordPage();
		manageUsersPage = testContext.getPageObjectManager().getManageUsersPage();
		addUserPage = testContext.getPageObjectManager().getAddUserPage();
		addDatasetPage = testContext.getPageObjectManager().getAddDatasetPage();
		datasetCatalogPage = testContext.getPageObjectManager().getDatasetCatalogPage();
	}
	
	@Given("^The user is logged in and in the Admin Console$")
	public void the_user_is_logged_in_and_in_the_Admin_Console() throws Throwable {
		homePage.navigateTo_HomePage();
		headerPage.clickOn_SignIn();
		homePage.clickOn_SignInGoogle();
		testContext.getWebDriverManager().changeWinSignIn(signInPage, FileReaderManager.getInstance().getConfigReader().getAdminUserName(), FileReaderManager.getInstance().getConfigReader().getAdminPassword());
		Thread.sleep(3000);
		headerPage.clickOn_AdminConsole();
		assert adminConsolePage.isUserGreetDisplayed();
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
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
		if (manageDulPage.isElectionOpen()) {
			manageDulPage.clickOn_Cancel();
			modalPage.check_Archive();
			modalPage.clickOn_Yes();
		}
	}
	
	@Given("^One election is opened$")
	public void one_election_is_opened() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
		if (!manageDulPage.isElectionOpen()) {
			manageDulPage.clickOn_Create();
			modalPage.clickOn_Yes();
		}
	}
	
	@Given("^clicks in Add User button$")
	public void clicks_in_Add_User_button() throws Throwable {
		adminConsolePage.clickOn_AddUser();
	}
	
	@Given("^The user clicks on Add Dataset button$")
	public void the_user_clicks_on_Add_Dataset_button() throws Throwable {
	    adminConsolePage.clickOn_AddDatasets();
	}
	
	@Given("^The user clicks on Dataset Catalog$")
	public void the_user_clicks_on_Dataset_Catalog() throws Throwable {
	    headerPage.clickOn_DatasetCatalog();
	}
	
	@Given("^there are datasets in the Catalog$")
	public void there_are_datasets_in_the_Catalog() throws Throwable {
		adminConsolePage.clickOn_AddDatasets();
		addDatasetPage.upload_CorrectDataset();
	}
	
	@Given("^the dataset is enabled$")
	public void the_dataset_is_enabled() throws Throwable {
	    datasetCatalogPage.findDataset("SC-20660");
	    if (datasetCatalogPage.isDatasetDisabled() ) {
	    	datasetCatalogPage.clickOn_Enable();
	    	modalPage.clickOn_Yes();
	    }
	}
	
	@Given("^the dataset is disabled$")
	public void the_dataset_is_disabled() throws Throwable {
		datasetCatalogPage.findDataset("SC-20660");
	    if (datasetCatalogPage.isDatasetEnabled() ) {
	    	datasetCatalogPage.clickOn_Disable();
	    	modalPage.clickOn_Yes();
	    }
	}
	
	@When("^The user clicks on the bin icon and accepts prompt$")
	public void the_user_clicks_on_the_bin_icon_and_accepts_prompt() throws Throwable {
	    datasetCatalogPage.findDataset("SC-20660");
	    datasetCatalogPage.clickOn_Delete();
	    Thread.sleep(1000);
	    modalPage.clickOn_Yes();

	}
	
	@When("^The user complete and submits the User form$")
	public void the_user_complete_and_submits_the_user_form() throws Throwable {
	    addUserPage.createMockUser(FileReaderManager.getInstance().getConfigReader().getMockUserName(), FileReaderManager.getInstance().getConfigReader().getMockUserMail());
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
	    addDulPage.completeForm("001", "001_Name", "{\"type\":\"everything\"}", "{\"generalUse\":true}");
	    addDulPage.clickOn_Add();
	}
	
	@When("^The user click on create button for a given Consent$")
	public void the_user_click_on_create_button_for_a_given_Consent() throws Throwable {
	    manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
	    manageDulPage.clickOn_Create();
	    modalPage.clickOn_Yes();
		this.versionNumber = manageDulPage.getVersion();
	}
	
	@When("^The user cancel a given election$")
	public void the_user_cancel_a_given_election() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
		manageDulPage.clickOn_Cancel();
		modalPage.check_Archive();
		modalPage.clickOn_Yes();
	}
	
	@When("^The user archive a given election$")
	public void the_user_archive_a_given_election() throws Throwable {
	    manageDulPage.clickOn_Archive();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user delete a given election$")
	public void the_user_delete_a_given_election() throws Throwable {
	    manageDulPage.findConsent("ORSP-Delete");
	    manageDulPage.clickOn_Delete();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user click on create button for the same Consent again$")
	public void the_user_click_on_create_button_for_the_same_Consent_again() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
	    manageDulPage.clickOn_Create();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user clicks on Consent Id for a given Consent$")
	public void the_user_clicks_on_Consent_Id_for_a_given_Consent() throws Throwable {
	    manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
	    manageDulPage.clickOn_PreviewDul();
	}
	
	@When("^The user clicks on Election Status for a given Consent$")
	public void the_user_clicks_on_Election_Status_for_a_given_Consent() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
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
	
	@When("^The user clicks on Manage Users$")
	public void the_user_clicks_on_Manage_Users() throws Throwable {
	    adminConsolePage.clickOn_ManageUsers();
	}
	
	@When("^The user edits a given user$")
	public void the_user_edits_a_given_user() throws Throwable {
	    manageUsersPage.findUser(FileReaderManager.getInstance().getConfigReader().getMemberUserName3());
	    manageUsersPage.clickOn_Edit();
	    addUserPage.enter_Name("_edited");
	    addUserPage.clickOn_Save();
	}
	
	@When("^The user selects the file and clicks Add button$")
	public void the_user_selects_the_file_and_clicks_Add_button() throws Throwable {
	    addDatasetPage.upload_CorrectDataset();
	}
	
	@When("^The user selects all Datasets and clicks Download Selection$")
	public void the_user_selects_all_Datasets_and_clicks_Download_Selection() throws Throwable {
		System.out.println("FILE EXISTS:" + file.exists());
		System.out.println("FILE:" + file);
		if (file.exists()) { file.delete();}
		System.out.println("FILE EXISTS AFTER DELETE:" + file.exists());
		Thread.sleep(2000);
		datasetCatalogPage.check_All();
	    datasetCatalogPage.clickOn_Download();
	}
	
	@When("^The user clicks on the Disable Dataset icon and accepts prompt$")
	public void the_user_clicks_on_the_Disable_Dataset_icon_and_accepts_prompt() throws Throwable {
		datasetCatalogPage.findDataset("SC-20660");
	    datasetCatalogPage.clickOn_Disable();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user clicks on the Enable Dataset icon and accepts prompt$")
	public void the_user_clicks_on_the_Enable_Dataset_icon_and_accepts_prompt() throws Throwable {
		datasetCatalogPage.findDataset("SC-20660");
	    datasetCatalogPage.clickOn_Enable();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user clicks on View translated DUL$")
	public void the_user_clicks_on_View_translated_DUL() throws Throwable {
		datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    datasetCatalogPage.clickOn_ViewTranslatedDul();
	    Thread.sleep(500);
	}
	
	@Then("^the user should see the new DUL on the Manage Dul list$")
	public void the_user_should_see_the_new_DUL_on_the_Manage_Dul_list() throws Throwable {
	    assert manageDulPage.isDulDisplayed("001_Name");
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
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
	    assert manageDulPage.isElectionOpen();
	}
	
	@Then("^The Election Status should be Canceled$")
	public void the_Election_Status_should_be_Canceled() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
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
	
	@Then("^The user should see a list of Users$")
	public void the_user_should_see_a_list_of_Users() throws Throwable {
	     assert manageUsersPage.getAllMembers().size() > 0;
	}
	
	@Then("^new user appears in Manage Users Page$")
	public void new_user_appears_in_Manage_Users_Page() throws Throwable {
		Thread.sleep(1000);
		manageUsersPage.findUser(FileReaderManager.getInstance().getConfigReader().getMockUserMail());
		assert manageUsersPage.getAllMembers().size() > 0;
	}
	
	@Then("^edited user appears in Manage Users Page$")
	public void edited_user_appears_in_Manage_Users_Page() throws Throwable {
	    manageUsersPage.findUser("_edited");
	    assert manageUsersPage.getAllMembers().size() > 0;
	}
	
	@Then("^the uploaded dataset is shown in Dataset Catalog Page$")
	public void the_uploaded_dataset_is_shown_in_Dataset_Catalog_Page() throws Throwable {
		assert datasetCatalogPage.isDatasetOnTable();
	}
	
	@Then("^the file should be downloaded$")
	public void the_file_should_be_downloaded() throws Throwable {
		Thread.sleep(5000);
		assert file.exists();	
	}
	
	@Then("^dataset is no longer shown in Dataset Catalog Page$")
	public void dataset_is_no_longer_shown_in_Dataset_Catalog_Page() throws Throwable {
		Thread.sleep(1000);
	    datasetCatalogPage.findDataset("SC-20660");
	    assert datasetCatalogPage.getAllDatasets().size() == 0;
	}

	@Then("^dataset appears as disabled in Dataset Catalog Page$")
	public void dataset_appears_as_disabled_in_Dataset_Catalog_Page() throws Throwable {
	    datasetCatalogPage.findDataset("SC-20660");
	    assert datasetCatalogPage.isDatasetDisabled();
	}
	
	@Then("^dataset appears as enabled in Dataset Catalog Page$")
	public void dataset_appears_as_enabled_in_Dataset_Catalog_Page() throws Throwable {
		datasetCatalogPage.findDataset("SC-20660");
	    assert datasetCatalogPage.isDatasetEnabled();
	}
	
	@Then("^correct translated DUL should appear in the Modal$")
	public void correct_translated_DUL_should_appear_in_the_Modal() throws Throwable {
	    assert FileReaderManager.getInstance().getConfigReader().getStructuredDul().replaceAll("<br */?>", "\n").equals(datasetCatalogPage.getText());
	}

}
