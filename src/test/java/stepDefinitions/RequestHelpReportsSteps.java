package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import pageObjects.RequestHelpReportsPage;

public class RequestHelpReportsSteps {
	TestContext testContext;
	RequestHelpReportsPage requestHelpReportsPage;
	WebDriver driver;
	
	public RequestHelpReportsSteps(TestContext context) {
		testContext = context;
		requestHelpReportsPage = testContext.getPageObjectManager().getRequestHelpReportsPage();
	}
	
	@Then("^the user navigates to Request Help Reports Page and created Report is displayed$")
	public void the_user_navigates_to_Request_Help_Reports_Page_and_created_Report_is_displayed() throws Throwable {
	    assert requestHelpReportsPage.isUserOnReportsPage();
	    assert requestHelpReportsPage.isNewReportDisplayed();
	}

}
