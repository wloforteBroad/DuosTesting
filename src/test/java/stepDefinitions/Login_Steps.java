//package stepDefinitions;
//
//import java.util.Iterator;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import managers.PageObjectManager;
//import managers.WebDriverManager;
//import managers.FileReaderManager;
//import pageObjects.GoogleSignInPage;
//import pageObjects.HomePage;
//
//public class Login_Steps {
//	
//	WebDriver driver;
//	HomePage homePage;
//	GoogleSignInPage googleSignInPage;
//	PageObjectManager pageObjectManager;
//	WebDriverManager webDriverManager;
//	
//	
//	@Given("^The user is in the Home Page$")
//	public void the_user_is_in_the_Home_Page() {
//		webDriverManager = new WebDriverManager();
//		driver = webDriverManager.getDriver();
//		pageObjectManager = new PageObjectManager(driver);
//		homePage = pageObjectManager.getHomePage();
//		homePage.navigateTo_HomePage();
//	}
//
//	@Given("^user navigates to Login Page$")
//	public void user_navigates_to_Login_Page() {
//		homePage.clickOn_SignIn();
//		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
//		homePage.clickOn_SignInGoogle();
//	}
//
//	@When("^user enters correct UserName and Password$")
//	public void user_enters_correct_UserName_and_Password() throws InterruptedException {
//		googleSignInPage = pageObjectManager.getGoogleSignInPage();
//		// Set Parent Window
//		String parent = driver.getWindowHandle();
//
//		// Number of Windows
//		Set<String> s1 = driver.getWindowHandles();
//
//		// Iterate
//		Iterator<String> I1 = s1.iterator();
//		while (I1.hasNext()) {
//
//			String child_window = I1.next();
//
//			// If it's not Parent do things
//			if (!parent.equals(child_window)) {
//				driver.switchTo().window(child_window);
//
//				System.out.println(driver.switchTo().window(child_window).getTitle());
//
//				googleSignInPage.signIn();
//			}
//
//		}
//
//		// Switch back to parent window
//		driver.switchTo().window(parent);
//		System.out.println(driver.getTitle());
//	}
//
//	@Then("^user navigates to Admin Console$")
//	public void user_navigates_to_Admin_Console() throws InterruptedException {
//		Thread.sleep(500);
//		driver.findElement(By.id("dacUser"));
//	}
//
//	@Then("^Message displayed Login Succesfully$")
//	public void message_displayed_Login_Succesfully() {
//		System.out.println("LOGIN SUCCESFULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		// Close the driver
//		webDriverManager.closeDriver();
//	}
//
//}
