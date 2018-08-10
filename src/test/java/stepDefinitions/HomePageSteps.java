package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import pageObjects.HeaderPage;
import pageObjects.HomePage;

public class HomePageSteps {
	
	TestContext testContext;
	HeaderPage headerPage;
	HomePage homePage;
	
	public HomePageSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
	}
	
	
	@Given("^The user is in the Home Page$")
	public void the_user_is_in_the_Home_Page() {
		homePage.navigateTo_HomePage();
	}

	@Given("^user navigates to Login Page$")
	public void user_navigates_to_Login_Page() {
		headerPage.clickOn_SignIn();
		homePage.clickOn_SignInGoogle();
	}

}
