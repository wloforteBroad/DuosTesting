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

public class Hooks {
	
	TestContext testContext;
	MySQLDBHelper db = new MySQLDBHelper();
	MongoDBHelper mongodb = new MongoDBHelper();
	 
	public Hooks(TestContext context) {
		testContext = context;
	}
 
	@Before
	public void BeforeSteps() {
	}
	
	@Before("@dacmemberDul")
	public void openElection() throws Throwable {
		db.addElection(FileReaderManager.getInstance().getConfigReader().getConsentIdMember(),"Open", "TranslateDUL", null);
	}
	
	@After("@dacmemberDul")
	public void closeElection() throws Throwable {
		db.deleteElection(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	}
	
	@Before("@dacmemberDar")
	public void insertDocumentAndElection() throws Throwable {
		db.addDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdMember(), FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
		db.addElection(FileReaderManager.getInstance().getConfigReader().getConsentIdMember(), "Closed", "TranslateDUL", 1);
		mongodb.insertDocument(FileReaderManager.getInstance().getConfigReader().getObjectIdMember());
		String darId = mongodb.getDocumentId();
		db.addElection(darId, "Open", "DataAccess", null);	
		db.addElection(darId, "Open", "RP", null);
	}
	
	@After("@dacmemberDar")
	public void deleteDocumentAndElection() throws Throwable {
		String darId = mongodb.getDocumentId();
		mongodb.deleteDocument();
		db.deleteElection(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
		db.deleteElection(darId);
		db.deleteDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdMember());
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
	
	@Before("@deleteUser")
	@After("@deleteUser")
	public void deleteUser() throws InterruptedException {
		db.deleteUser();
	}
	
	@After("@adminDul")
	public void adminDul() throws InterruptedException {
		db.deleteElection(FileReaderManager.getInstance().getConfigReader().getConsentIdAdmin());
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
