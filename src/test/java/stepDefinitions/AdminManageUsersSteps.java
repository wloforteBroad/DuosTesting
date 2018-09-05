package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AddUserPage;
import pageObjects.AdminConsolePage;
import pageObjects.ManageUsersPage;

public class AdminManageUsersSteps {
	TestContext testContext;
	ManageUsersPage manageUsersPage;
	AdminConsolePage adminConsolePage;
	AddUserPage addUserPage;
	WebDriver driver;
	
	public AdminManageUsersSteps(TestContext context) {
		testContext = context;
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		manageUsersPage = testContext.getPageObjectManager().getManageUsersPage();
		addUserPage = testContext.getPageObjectManager().getAddUserPage();
	}
	
	@Given("^clicks in Add User button$")
	public void clicks_in_Add_User_button() throws Throwable {
		adminConsolePage.clickOn_AddUser();
	}
	
	@When("^The user clicks on Manage Users$")
	public void the_user_clicks_on_Manage_Users() throws Throwable {
	    adminConsolePage.clickOn_ManageUsers();
	}
	
	@When("^The user complete and submits the User form$")
	public void the_user_complete_and_submits_the_user_form() throws Throwable {
	    addUserPage.createMockUser(FileReaderManager.getInstance().getConfigReader().getMockUserName(), FileReaderManager.getInstance().getConfigReader().getMockUserMail());
	}
	
	@When("^The user edits a given user$")
	public void the_user_edits_a_given_user() throws Throwable {
	    manageUsersPage.findUser(FileReaderManager.getInstance().getConfigReader().getMemberUserName3());
	    manageUsersPage.clickOn_Edit();
	    addUserPage.enter_Name("_edited");
	    addUserPage.clickOn_Save();
	}
	
	@Then("^The user should see a list of Users$")
	public void the_user_should_see_a_list_of_Users() throws Throwable {
	     assert manageUsersPage.getAllMembers().size() > 0;
	}
	
	@Then("^new user appears in Manage Users Page$")
	public void new_user_appears_in_Manage_Users_Page() throws Throwable {
		Thread.sleep(1000);
		manageUsersPage.findUser(FileReaderManager.getInstance().getConfigReader().getMockUserMail());
		assert manageUsersPage.getAllMembers().size() > 0;
	}
	
	@Then("^edited user appears in Manage Users Page$")
	public void edited_user_appears_in_Manage_Users_Page() throws Throwable {
	    manageUsersPage.findUser("_edited");
	    assert manageUsersPage.getAllMembers().size() > 0;
	}

}
