package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import pageObjects.ManageUsersPage;

public class ManageUsersSteps {
	TestContext testContext;
	ManageUsersPage manageUsersPage;
	WebDriver driver;
	
	public ManageUsersSteps(TestContext context) {
		testContext = context;
		manageUsersPage = testContext.getPageObjectManager().getManageUsersPage();
	}

}
