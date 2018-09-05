package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import managers.FileReaderManager;
import pageObjects.AdminConsolePage;
import pageObjects.GoogleSignInPage;
import pageObjects.HeaderPage;
import pageObjects.HomePage;

public class LoginHelperSteps {
	TestContext testContext;
	HomePage homePage;
	GoogleSignInPage signInPage;
	HeaderPage headerPage;
	AdminConsolePage adminConsolePage;
	WebDriver driver;
	
	
	public LoginHelperSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		homePage = testContext.getPageObjectManager().getHomePage();
		signInPage = testContext.getPageObjectManager().getGoogleSignInPage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
	}
	
	@Given("^The user is logged in and in the Admin Console$")
	public void the_user_is_logged_in_and_in_the_Admin_Console() throws Throwable {
		homePage.navigateTo_HomePage();
		headerPage.clickOn_SignIn();
		homePage.clickOn_SignInGoogle();
		testContext.getWebDriverManager().changeWinSignIn(signInPage, FileReaderManager.getInstance().getConfigReader().getAdminUserName(), FileReaderManager.getInstance().getConfigReader().getAdminPassword());
		headerPage.waitForElementToLoad();
		headerPage.clickOn_AdminConsole();
		assert adminConsolePage.isUserGreetDisplayed();
	}
	
	@Given("^The DAC Member is logged in and in the DAC Console$")
	public void the_DAC_Member_is_logged_in_and_in_the_DAC_Console() throws Throwable {
		homePage.navigateTo_HomePage();
		headerPage.clickOn_SignIn();
		homePage.clickOn_SignInGoogle();
		testContext.getWebDriverManager().changeWinSignIn(signInPage, FileReaderManager.getInstance().getConfigReader().getMemberUserName1(), FileReaderManager.getInstance().getConfigReader().getMemberPassword());
		Thread.sleep(3000);
	}

}
