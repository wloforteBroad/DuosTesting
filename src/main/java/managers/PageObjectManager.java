package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.AddUserPage;
import pageObjects.AdminConsolePage;
import pageObjects.DulCollectVotesPage;
import pageObjects.DacConsolePage;
import pageObjects.DulVotingPage;
import pageObjects.GoogleSignInPage;
import pageObjects.HeaderPage;
import pageObjects.HomePage;
import pageObjects.ManageDulPage;
import pageObjects.ManageUsersPage;
import pageObjects.ModalPage;

public class PageObjectManager {
	
	private WebDriver driver;
	private HomePage homePage;
	private GoogleSignInPage googleSingInPage;
	private AdminConsolePage adminConsole;
	private AddUserPage addUserPage;
	private ManageUsersPage manageUsersPage;
	private HeaderPage headerPage;
	private ManageDulPage manageDulPage;
	private ModalPage modalPage;
	private DacConsolePage dacConsolePage;
	private DulVotingPage dulVotingPage;
	private DulCollectVotesPage collectVotesPage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}
	
	public GoogleSignInPage getGoogleSignInPage() {
		return (googleSingInPage == null) ? googleSingInPage = new GoogleSignInPage(driver) : googleSingInPage;
	}
	
	public AdminConsolePage getAdminConsolePage() {
		return (adminConsole == null) ? adminConsole = new AdminConsolePage(driver) : adminConsole;
	}
	
	public AddUserPage getAddUserPage() {
		return (addUserPage == null) ? addUserPage = new AddUserPage(driver) : addUserPage;
	}
	
	public ManageUsersPage getManageUsersPage() {
		return (manageUsersPage == null) ? manageUsersPage = new ManageUsersPage(driver) : manageUsersPage;
	}
	
	public HeaderPage getHeaderPage() {
		return (headerPage == null) ? headerPage = new HeaderPage(driver) : headerPage;
	}
	
	public ManageDulPage getManageDulPage() {
		return (manageDulPage == null) ? manageDulPage = new ManageDulPage(driver) : manageDulPage;
	}
	
	public ModalPage getModalPage() {
		return (modalPage == null) ? modalPage = new ModalPage(driver) : modalPage;
	}
	
	public DacConsolePage getDacConsolePage() {
		return (dacConsolePage == null) ? dacConsolePage = new DacConsolePage(driver) : dacConsolePage;
	}
	
	public DulVotingPage getDulVotingPage() {
		return (dulVotingPage == null) ? dulVotingPage = new DulVotingPage(driver) : dulVotingPage;
	}
	
	public DulCollectVotesPage getCollectVotesPage() {
		return (collectVotesPage == null) ? collectVotesPage = new DulCollectVotesPage(driver) : collectVotesPage;
	}

}
