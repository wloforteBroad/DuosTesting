package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HeaderPage;
import pageObjects.RequestHelpPage;
import pageObjects.RequestHelpReportsPage;

public class RequestHelpReportsSteps {
	TestContext testContext;
	RequestHelpReportsPage requestHelpReportsPage;
	RequestHelpPage requestHelpPage;
	HeaderPage headerPage;
	WebDriver driver;
	
	public RequestHelpReportsSteps(TestContext context) {
		testContext = context;
		requestHelpReportsPage = testContext.getPageObjectManager().getRequestHelpReportsPage();
		requestHelpPage = testContext.getPageObjectManager().getRequestHelpPage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
	}
	
	@Given("^The user clicks on Request Help on the header$")
	public void the_user_clicks_on_Request_Help_on_the_header() throws Throwable {
	    headerPage.clickOn_RequestHelp();;
	}
	
	@Given("^The user clicks on Create a Report option$")
	public void the_user_clicks_on_Create_a_Report_option() throws Throwable {
	    headerPage.clickOn_CreateReport();
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
	
	@Then("^the user navigates to Request Help Reports Page and created Report is displayed$")
	public void the_user_navigates_to_Request_Help_Reports_Page_and_created_Report_is_displayed() throws Throwable {
	    assert requestHelpReportsPage.isUserOnReportsPage();
	    assert requestHelpReportsPage.isNewReportDisplayed();
	}

}
