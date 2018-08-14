package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AddUserPage;

public class AddUserSteps {
	TestContext testContext;
	AddUserPage addUserPage;
	WebDriver driver;
	
	public AddUserSteps(TestContext context) {
		testContext = context;
		addUserPage = testContext.getPageObjectManager().getAddUserPage();
	}
	
	@When("^user enters correct name, account and role$")
	public void user_enters_correct_name_account_and_role() throws Throwable {
	    addUserPage.enter_Name(FileReaderManager.getInstance().getConfigReader().getMockUserName());
	    addUserPage.enter_Mail(FileReaderManager.getInstance().getConfigReader().getMockUserMail());
	    Thread.sleep(5000);
	    addUserPage.clickOn_AdminRole();
	}
	
	@When("^clicks in add button$")
	public void clicks_in_add_button() throws Throwable {
	    addUserPage.clickOn_Add();
	}

}
