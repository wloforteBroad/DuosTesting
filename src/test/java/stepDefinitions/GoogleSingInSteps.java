package stepDefinitions;

import java.util.Iterator;
import java.util.Set;

import cucumber.TestContext;
import cucumber.api.java.en.When;
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

		// Set Parent Window
		String parent = testContext.getWebDriverManager().getWindowsHandle();
		// Number of Windows
		Set<String> s1 = testContext.getWebDriverManager().getWindowsHandles();
		// Iterate
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			// If it's not Parent do things
			if (!parent.equals(child_window)) {
				testContext.getWebDriverManager().switchWindow(child_window);
				googleSignInPage.signInAdmin();
			}
		}
		// Switch back to parent window
		testContext.getWebDriverManager().switchWindow(parent);
	}

	@When("^user enters correct MemberUserNameOne and MemberPassword$")
	public void user_enters_correct_MemberUserNameOne_and_MemberPassword() throws Throwable {
		// Set Parent Window
		String parent = testContext.getWebDriverManager().getWindowsHandle();
		// Number of Windows
		Set<String> s1 = testContext.getWebDriverManager().getWindowsHandles();
		// Iterate
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			// If it's not Parent do things
			if (!parent.equals(child_window)) {
				testContext.getWebDriverManager().switchWindow(child_window);
				googleSignInPage.signInMember1();
			}
		}
		// Switch back to parent window
		testContext.getWebDriverManager().switchWindow(parent);
	}

	@When("^user enters correct MemberUserNameTwo and MemberPassword$")
	public void user_enters_correct_MemberUserNameTwo_and_Password() throws Throwable {
		// Set Parent Window
		String parent = testContext.getWebDriverManager().getWindowsHandle();
		// Number of Windows
		Set<String> s1 = testContext.getWebDriverManager().getWindowsHandles();
		// Iterate
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			// If it's not Parent do things
			if (!parent.equals(child_window)) {
				testContext.getWebDriverManager().switchWindow(child_window);
				googleSignInPage.signInMember2();
			}
		}
		// Switch back to parent window
		testContext.getWebDriverManager().switchWindow(parent);
	}

	@When("^user enters correct MemberUserNameThree and MemberPassword$")
	public void user_enters_correct_MemberUserNameThree_and_Password() throws Throwable {
		// Set Parent Window
		String parent = testContext.getWebDriverManager().getWindowsHandle();
		// Number of Windows
		Set<String> s1 = testContext.getWebDriverManager().getWindowsHandles();
		// Iterate
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			// If it's not Parent do things
			if (!parent.equals(child_window)) {
				testContext.getWebDriverManager().switchWindow(child_window);
				googleSignInPage.signInMember3();
			}
		}
		// Switch back to parent window
		testContext.getWebDriverManager().switchWindow(parent);
	}

}
