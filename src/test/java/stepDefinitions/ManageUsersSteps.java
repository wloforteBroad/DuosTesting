package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import pageObjects.ManageUsersPage;

public class ManageUsersSteps {
	TestContext testContext;
	ManageUsersPage manageUsersPage;
	WebDriver driver;
	
	public ManageUsersSteps(TestContext context) {
		testContext = context;
		manageUsersPage = testContext.getPageObjectManager().getManageUsersPage();
	}
	
	@Then("^new user appears in Manage Users Page$")
	public void new_user_appears_in_Manage_Users_Page() throws Throwable {
		Thread.sleep(1000);
		manageUsersPage.findUser("test.user@gmail.com");
		Thread.sleep(2000);
		assert manageUsersPage.isUserDisplayed();
	}

}
