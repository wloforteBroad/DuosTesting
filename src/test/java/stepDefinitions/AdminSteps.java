package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AddDulPage;
import pageObjects.AdminConsolePage;
import pageObjects.DacConsolePage;
import pageObjects.DulVotingPage;
import pageObjects.GoogleSignInPage;
import pageObjects.HeaderPage;
import pageObjects.HomePage;
import pageObjects.ManageDulPage;
import pageObjects.ModalPage;

public class AdminSteps {
	TestContext testContext;
	ManageDulPage manageDulPage;
	ModalPage modalPage;
	HomePage homePage;
	GoogleSignInPage signInPage;
	HeaderPage headerPage;
	AdminConsolePage adminConsolePage;
	DacConsolePage dacConsolePage;
	DulVotingPage dulVotingPage;
	AddDulPage addDulPage;
	WebDriver driver;
	
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
			modalPage.clickOn_Yes();
		}
	}
	
	@Given("^One election is opened$")
	public void one_election_is_opened() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
		if (!manageDulPage.isElectionOpen()) {
			System.out.println("---------------------------------" + manageDulPage.isElectionOpen());
			manageDulPage.clickOn_Create();
			modalPage.clickOn_Yes();
		}
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
	}
	
	@When("^The user cancel a given election$")
	public void the_user_cancel_a_given_election() throws Throwable {
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
		manageDulPage.clickOn_Cancel();
		modalPage.clickOn_Yes();
	}
	
	@Then("^the user should see the new DUL on the Manage Dul list$")
	public void the_user_should_see_the_new_DUL_on_the_Manage_Dul_list() throws Throwable {
	    assert manageDulPage.isNewDulDisplayed("001_Name");
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
	
	

}
