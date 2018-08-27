package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import pageObjects.AddUserPage;

public class AddUserSteps {
	TestContext testContext;
	AddUserPage addUserPage;
	WebDriver driver;
	
	public AddUserSteps(TestContext context) {
		testContext = context;
		addUserPage = testContext.getPageObjectManager().getAddUserPage();
	}

}
