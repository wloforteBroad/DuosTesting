package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.AdminConsolePage;
import pageObjects.HeaderPage;

public class AdminConsoleSteps {
	
	TestContext testContext;
	AdminConsolePage adminConsolePage;
	HeaderPage headerPage;
	WebDriver driver;
	
	public AdminConsoleSteps(TestContext context) {
		testContext = context;
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
	}
	
	@Then("^user navigates to Admin Console$")
	public void user_navigates_to_Admin_Console() throws InterruptedException {
		Thread.sleep(2000);
		headerPage.clickOn_AdminConsole();
		assert adminConsolePage.isUserGreetDisplayed();
	}
	
	@Given("^The user is in the Admin Console$")
	public void the_user_is_in_the_Admin_Console() throws Throwable {
		Thread.sleep(2000);
		headerPage.clickOn_AdminConsole();
		assert adminConsolePage.isUserGreetDisplayed();
	}
	
	@Given("^clicks in Add User button$")
	public void clicks_in_Add_User_button() throws Throwable {
		adminConsolePage.clickOn_AddUser();
	}
	
	@Given("^clicks in Manage DUL button$")
	public void clicks_in_Manage_DUL_button() throws Throwable {
	   adminConsolePage.clickOn_ManageDul();
	}

}
