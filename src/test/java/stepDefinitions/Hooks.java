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
	AdminManageDulSteps adminSteps;
	MySQLDBHelper db = new MySQLDBHelper();
	MongoDBHelper mongodb = new MongoDBHelper();
	 
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
	
	@Before("@dacmemberDul")
	public void openElection() throws Throwable {
		db.addDulElectionOpened();
	}
	
	@After("@dacmemberDul")
	public void closeElection() throws Throwable {
		db.deleteDulElection();
	}
	
	@Before("@dacmemberDar")
	public void insertDocumentAndElection() throws Throwable {
		//Consent association between ORSP-628 and Dataset with Object-id SC-20660 previously inserted on DB
		mongodb.insertDocument();
		db.addDulElectionClosed();
		String darId = mongodb.getDocumentId();
		db.addDarElection(darId);	
	}
	
	@After("@dacmemberDar")
	public void deleteDocumentAndElection() throws Throwable {
		String darId = mongodb.getDocumentId();
		mongodb.deleteDocument();
		db.deleteDulElection();
		db.deleteDarElection(darId);
	}
	
	@Before("@deleteDul")
	public void AddDulToDelete() throws Throwable {
		db.addDulToDelete();
	}
	
	@After("@addDul, @deleteDul")
	public void deleteDul() throws Throwable {
		db.deletedULIfNotDeleted();
	}
	
	@Before("@changeRoles")
	@After("@changeRoles")
	public void memberToAlumni() throws InterruptedException {
		db.changeRoles();
	}
	
 
	@After (order = 0)
	public void AfterSteps() {
		System.out.println("CLOSING DRIVER---------------------------------------------------------");
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
