package stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.FileReaderManager;
import pageObjects.AddUserPage;
import pageObjects.AdminConsolePage;
import pageObjects.GoogleSignInPage;
import pageObjects.HeaderPage;
import pageObjects.HomePage;
import pageObjects.ManageDulPage;
import pageObjects.ManageUsersPage;
import pageObjects.ModalPage;

public class Hooks {
	
	TestContext testContext;
	HomePage homePage;
	GoogleSignInPage signInPage;
	HeaderPage headerPage;
	AdminConsolePage adminConsolePage;
	ManageUsersPage manageUsersPage;
	AddUserPage addUserPage;
	ManageDulPage manageDulPage;
	ModalPage modalPage;
	 
	public Hooks(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		signInPage = testContext.getPageObjectManager().getGoogleSignInPage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		manageUsersPage = testContext.getPageObjectManager().getManageUsersPage();
		addUserPage = testContext.getPageObjectManager().getAddUserPage();
		manageDulPage = testContext.getPageObjectManager().getManageDulPage();
		modalPage = testContext.getPageObjectManager().getModalPage();
	}
 
	@Before
	public void BeforeSteps() {
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
	}
	
	@Before("@twoMembers")
	public void memberToAlumni() throws InterruptedException {
		homePage.navigateTo_HomePage();
		headerPage.clickOn_SignIn();
		homePage.clickOn_SignInGoogle();
		testContext.getWebDriverManager().changeWinSignIn(signInPage, FileReaderManager.getInstance().getConfigReader().getAdminUserName(), FileReaderManager.getInstance().getConfigReader().getAdminPassword());
		Thread.sleep(3000);
		headerPage.clickOn_AdminConsole();
		adminConsolePage.clickOn_ManageDul();
		manageDulPage.findConsent(FileReaderManager.getInstance().getConfigReader().getConsentId());
		if (manageDulPage.isElectionOpen()) {
			manageDulPage.clickOn_Cancel();
			modalPage.check_Archive();
			modalPage.clickOn_Yes();
		}
		headerPage.clickOn_AdminConsole();
		adminConsolePage.clickOn_ManageUsers();
		manageUsersPage.findUser(FileReaderManager.getInstance().getConfigReader().getMemberUserName1());
		manageUsersPage.clickOn_Edit();
		addUserPage.check_MemberRole();
		addUserPage.check_AlumniRole();
		addUserPage.clickOn_Save();
		Thread.sleep(1000);
		headerPage.clickOn_AdminConsole();
	}
	
	@After("@twoMembers")
	public void alumniToMember() {
		modalPage.clickOn_No();
		headerPage.clickOn_AdminConsole();
		adminConsolePage.clickOn_ManageUsers();
		manageUsersPage.findUser(FileReaderManager.getInstance().getConfigReader().getMemberUserName1());
		manageUsersPage.clickOn_Edit();
		addUserPage.check_AlumniRole();
		addUserPage.check_MemberRole();
		addUserPage.clickOn_Save();
		
	}
	
 
	@After (order = 0)
	public void AfterSteps() {
		System.out.println("CERRANDO EL DRIVER---------------------------------------------------------");
		testContext.getWebDriverManager().closeDriver();
	}
	
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				//This takes a screenshot from the driver at save it to the specified location
				File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
				
				//Building up the destination path for the screenshot to save
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
				
				//Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath);   
 
				//This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			} 
		}
	}

}
