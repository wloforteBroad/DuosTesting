package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AdminConsolePage;
import pageObjects.GoogleSignInPage;
import pageObjects.HeaderPage;
import pageObjects.HomePage;
import pageObjects.ManageDulPage;
import pageObjects.ModalPage;

public class ManageDulSteps {
	TestContext testContext;
	ManageDulPage manageDulPage;
	ModalPage modalPage;
	HomePage homePage;
	GoogleSignInPage signInPage;
	HeaderPage headerPage;
	AdminConsolePage adminConsolePage;
	WebDriver driver;
	
	public ManageDulSteps(TestContext context) {
		testContext = context;
		manageDulPage = testContext.getPageObjectManager().getManageDulPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
		homePage = testContext.getPageObjectManager().getHomePage();
		signInPage = testContext.getPageObjectManager().getGoogleSignInPage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
	}
	
	@When("^user searchs for consent$")
	public void user_searchs_for_consent() throws Throwable {
	    Thread.sleep(1000);
	    manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
	}
	
	@When("^clicks on create button$")
	public void clicks_on_create_button() throws Throwable {
	    manageDulPage.clickOn_Create();
	    Thread.sleep(500);
	    modalPage.clickOn_Yes();
	}
	
	@Then("^election status changes to Open$")
	public void election_status_changes_to_Open() throws Throwable {
	    Thread.sleep(2000);
	    assert manageDulPage.isElectionOpen();
	}

}
