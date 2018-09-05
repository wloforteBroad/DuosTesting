package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HeaderPage;

public class HeaderPageSteps {
	TestContext testContext;
	HeaderPage headerPage;
	
	public HeaderPageSteps(TestContext context) {
		testContext = context;
		headerPage = testContext.getPageObjectManager().getHeaderPage();
	}
	
	@Given("^The user is loged in$")
	public void the_user_is_loged_in() throws Throwable {
		Thread.sleep(2000);
	    assert headerPage.isUserLoguedIn();
	}
	
	@When("^user enters clicks on User DropDown$")
	public void user_enters_clicks_on_User_DropDown() throws Throwable {
	    headerPage.clickOn_User();
	}
	
	@When("^clicks on Logout$")
	public void clicks_on_Logout() throws Throwable {
	    headerPage.clickOn_SignOut();
	}
	
	@Then("^Sign in button is diplayed in header$")
	public void sign_in_button_is_diplayed_in_header() throws Throwable {
		Thread.sleep(1000);
	    assert headerPage.isUserLoguedOut();
	}
	

}
