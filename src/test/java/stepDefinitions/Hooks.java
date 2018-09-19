package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.deps.net.iharder.Base64;
import managers.FileReaderManager;

public class Hooks {
	
	TestContext testContext;
	WebDriver driver;
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
		db.addElection(FileReaderManager.getInstance().getConfigReader().getConsentIdMember(),"Open", "TranslateDUL", null, null, null);
	}
	
	@After("@dacmemberDul, @chairPerson")
	public void closeElection() throws Throwable {
		db.deleteElection(FileReaderManager.getInstance().getConfigReader().getConsentIdMember());
	}
	
	@Before("@dacmemberDar")
	public void insertDocumentAndElection() throws Throwable {
		db.addDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdMember(), FileReaderManager.getInstance().getConfigReader().getConsentIdMember(), 0);
		db.addElection(FileReaderManager.getInstance().getConfigReader().getConsentIdMember(), "Closed", "TranslateDUL", 1, 1, null);
		mongodb.insertDocument(FileReaderManager.getInstance().getConfigReader().getObjectIdMember(), true);
		String darId = mongodb.getDocumentId(true);
		db.addElection(darId, "Open", "DataAccess", null, null, null);	
		db.addElection(darId, "Open", "RP", null, null, null);
	}
	
	@After("@dacmemberDar")
	public void deleteDocumentAndElection() throws Throwable {
		String darId = mongodb.getDocumentId(true);
		mongodb.deleteDocument(true);
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
	
	@Before("@adminDar")
	public void AddDarAdmin() throws Throwable {
		db.addDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin(), FileReaderManager.getInstance().getConfigReader().getConsentIdAdminDar(), 0);
		db.addElection(FileReaderManager.getInstance().getConfigReader().getConsentIdAdminDar(), "Closed", "TranslateDUL", 1, 1, null);
		mongodb.insertDocument(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin(), true);
		
	}
	
	@After("@adminDar, @adminDarApproval")
	public void deleteDarAdmin() throws Throwable {
		System.out.println("BORRANDO TODO---------------------------------------------------------");
		String darId = mongodb.getDocumentId(true);
		mongodb.deleteDocument(true);
		db.deleteElection(FileReaderManager.getInstance().getConfigReader().getConsentIdAdminDar());
		db.deleteElection(darId);
		db.deleteDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin());
	}
	

	@Before("@adminDarApproval")
	public void AddDarAdminApproval() throws Throwable {
		db.addDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin(), FileReaderManager.getInstance().getConfigReader().getConsentIdAdminDar(), 1);
		db.addElection(FileReaderManager.getInstance().getConfigReader().getConsentIdAdminDar(), "Closed", "TranslateDUL", 1, 1, null);
		mongodb.insertDocument(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin(), true);
		
	}
	
	@Before("@chairPerson")
	public void addChairElection() throws Throwable {
		db.addElection(FileReaderManager.getInstance().getConfigReader().getConsentIdMember(), "Open", "TranslateDUL", 1, 0, null);
	}
	
	@Before("@dataOwner")
	public void DataOwner() throws Throwable {
		db.addDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin(), FileReaderManager.getInstance().getConfigReader().getConsentIdAdminDar(), 1);
		db.addElection(FileReaderManager.getInstance().getConfigReader().getConsentIdAdminDar(), "Closed", "TranslateDUL", 1, 1, null);
		mongodb.insertDocument(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin(), true);
		String darId = mongodb.getDocumentId(true);
		db.dataOwnerDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin(), FileReaderManager.getInstance().getConfigReader().getAdminId());
		db.addElection(darId, "Closed", "DataAccess", 1, 1, 1);
		db.addElection(darId, "Open", "DataSet", null, null, null);
	}
	
	@After("@dataOwner")
	public void deleteDataOwner() throws Throwable {
		System.out.println("BORRANDO TODO---------------------------------------------------------");
		String darId = mongodb.getDocumentId(true);
		mongodb.deleteDocument(true);
		db.deleteElection(FileReaderManager.getInstance().getConfigReader().getConsentIdAdminDar());
		db.deleteElection(darId);
		db.deleteDataOwnerDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin(), FileReaderManager.getInstance().getConfigReader().getAdminId());
		db.deleteDataset(FileReaderManager.getInstance().getConfigReader().getObjectIdAdmin());
	}
 
	@After (order = 0)
	public void AfterSteps() {
		System.out.println("CLOSING DRIVER---------------------------------------------------------");
		testContext.getWebDriverManager().closeDriver();
	}
	
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				Reporter.addScreenCaptureFromPath(addScreenshot(testContext.getWebDriverManager().getDriver()));
			} catch (IOException e) {
			} 
		}
	}
	
	private static String addScreenshot(WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int) scrFile.length()];
			fileInputStreamReader.read(bytes);

			encodedBase64 = new String(Base64.encodeBytes(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64," + encodedBase64;
	}

}
