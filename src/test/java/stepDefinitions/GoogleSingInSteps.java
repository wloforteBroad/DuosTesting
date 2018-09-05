package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.GoogleSignInPage;

public class GoogleSingInSteps {
	
	TestContext testContext;
	GoogleSignInPage googleSignInPage;
	
	public GoogleSingInSteps(TestContext context) {
		testContext = context;
		googleSignInPage = testContext.getPageObjectManager().getGoogleSignInPage();
	}
	
	@When("^user enters correct AdminUserName and AdminPassword$")
	public void user_enters_correct_UserName_and_Password() throws InterruptedException {
		testContext.getWebDriverManager().changeWinSignIn(googleSignInPage, FileReaderManager.getInstance().getConfigReader().getAdminUserName(), FileReaderManager.getInstance().getConfigReader().getAdminPassword());
	}


	@When("^user enters correct MemberUserNameOne and MemberPassword$")
	public void user_enters_correct_MemberUserNameOne_and_MemberPassword() throws Throwable {
		Thread.sleep(5000);
		testContext.getWebDriverManager().changeWinSignIn(googleSignInPage, FileReaderManager.getInstance().getConfigReader().getMemberUserName1(), FileReaderManager.getInstance().getConfigReader().getMemberPassword());
	}

	@When("^user enters correct MemberUserNameTwo and MemberPassword$")
	public void user_enters_correct_MemberUserNameTwo_and_Password() throws Throwable {
		testContext.getWebDriverManager().changeWinSignIn(googleSignInPage, FileReaderManager.getInstance().getConfigReader().getMemberUserName2(), FileReaderManager.getInstance().getConfigReader().getMemberPassword());
	}

	@When("^user enters correct MemberUserNameThree and MemberPassword$")
	public void user_enters_correct_MemberUserNameThree_and_Password() throws Throwable {
		testContext.getWebDriverManager().changeWinSignIn(googleSignInPage, FileReaderManager.getInstance().getConfigReader().getMemberUserName3(), FileReaderManager.getInstance().getConfigReader().getMemberPassword());
	}

}
