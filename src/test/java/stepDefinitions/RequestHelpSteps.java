package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import pageObjects.RequestHelpPage;

public class RequestHelpSteps {
	TestContext testContext;
	RequestHelpPage requestHelpPage;
	WebDriver driver;
	
	public RequestHelpSteps(TestContext context) {
		testContext = context;
		requestHelpPage = testContext.getPageObjectManager().getRequestHelpPage();
	}
	
	@When("^User is on Request Help Modal$")
	public void user_is_on_Request_Help_Modal() throws Throwable {
	    assert requestHelpPage.isUserOnRequestHelpPage();
	}
	
	@When("^completes Subject and Description$")
	public void completes_Subject_and_Description() throws Throwable {
	    requestHelpPage.enter_Subject("SUBJECT");
	    requestHelpPage.enter_Description("DESCRIPTION");
	}
	
	@When("^clicks Submit button$")
	public void clicks_Submit_button() throws Throwable {
	    requestHelpPage.clickOn_Submit();
	}

}
