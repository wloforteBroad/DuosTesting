package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AdminConsolePage;
import pageObjects.DacConsolePage;
import pageObjects.DulVotingPage;
import pageObjects.GoogleSignInPage;
import pageObjects.HeaderPage;
import pageObjects.HomePage;
import pageObjects.ManageDulPage;
import pageObjects.ModalPage;

public class DulElectionSteps {
	TestContext testContext;
	ManageDulPage manageDulPage;
	ModalPage modalPage;
	HomePage homePage;
	GoogleSignInPage signInPage;
	HeaderPage headerPage;
	AdminConsolePage adminConsolePage;
	DacConsolePage dacConsolePage;
	DulVotingPage dulVotingPage;
	WebDriver driver;
	
	public DulElectionSteps(TestContext context) {
		testContext = context;
		manageDulPage = testContext.getPageObjectManager().getManageDulPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
		homePage = testContext.getPageObjectManager().getHomePage();
		signInPage = testContext.getPageObjectManager().getGoogleSignInPage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		dacConsolePage = testContext.getPageObjectManager().getDacConsolePage();
		dulVotingPage = testContext.getPageObjectManager().getDulVotingPage();
	}
	
	@Given("^the Admin user opens a DUL election$")
	public void the_Admin_user_opens_a_DUL_election() throws Throwable {
		adminConsolePage.clickOn_ManageDul();
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
		manageDulPage.clickOn_Create();
		modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();
	    Thread.sleep(1000);
		headerPage.signOut();
	}
	
	@When("^all DAC users votes on it$")
	public void all_DAC_users_votes_on_it() throws Throwable {
		Thread.sleep(1000);
		userVoting("Because Yes", FileReaderManager.getInstance().getConfigReader().getMemberUserName1(), FileReaderManager.getInstance().getConfigReader().getMemberPassword(),FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
		Thread.sleep(1000);
		userVoting("Because Yes", FileReaderManager.getInstance().getConfigReader().getMemberUserName2(), FileReaderManager.getInstance().getConfigReader().getMemberPassword(),FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
		Thread.sleep(1000);
		userVoting("Because Yes", FileReaderManager.getInstance().getConfigReader().getMemberUserName3(), FileReaderManager.getInstance().getConfigReader().getMemberPassword(),FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
		Thread.sleep(1000);
		userVoting("Because Yes", FileReaderManager.getInstance().getConfigReader().getAdminUserName(), FileReaderManager.getInstance().getConfigReader().getAdminPassword(),FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
		Thread.sleep(1000);
	}
	
	private void userVoting(String consent, String user, String password, String consentId) throws InterruptedException {
		testContext.getWebDriverManager().deleteAllCookies();
		testContext.getWebDriverManager().restartDriver();
		testContext.getWebDriverManager().navigateToUrl(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		headerPage.clickOn_SignIn();
		testContext.getWebDriverManager().changeWinSignIn(signInPage, FileReaderManager.getInstance().getConfigReader().getMemberUserName1(), FileReaderManager.getInstance().getConfigReader().getMemberPassword());
		Thread.sleep(1000);
		testContext.getWebDriverManager().navigateToUrl("http://localhost:3000/member_console");
		dacConsolePage.findConsentAndVote(consentId);
		dulVotingPage.voteYes("Because Yes");
		modalPage.clickOn_Ok();
		Thread.sleep(1000);
		headerPage.signOut();
	}

}
