package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.AddDatasetPage;
import pageObjects.AddDulPage;
import pageObjects.AddUserPage;
import pageObjects.AdminConsolePage;
import pageObjects.DulCollectVotesPage;
import pageObjects.DulResultRecordPage;
import pageObjects.DacConsolePage;
import pageObjects.DarCollectVotesPage;
import pageObjects.DarPreviewResultsPage;
import pageObjects.DarSummaryPage;
import pageObjects.DarVotingPage;
import pageObjects.DatasetCatalogPage;
import pageObjects.DulVotingPage;
import pageObjects.GoogleSignInPage;
import pageObjects.HeaderPage;
import pageObjects.HomePage;
import pageObjects.ManageDarPage;
import pageObjects.ManageDulPage;
import pageObjects.ManageUsersPage;
import pageObjects.ModalPage;
import pageObjects.DulPreviewResultsPage;
import pageObjects.RequestHelpPage;
import pageObjects.RequestHelpReportsPage;

public class PageObjectManager {
	
	private WebDriver driver;
	private HomePage homePage;
	private GoogleSignInPage googleSingInPage;
	private AdminConsolePage adminConsole;
	private AddUserPage addUserPage;
	private ManageUsersPage manageUsersPage;
	private HeaderPage headerPage;
	private ManageDulPage manageDulPage;
	private ManageDarPage manageDarPage;
	private ModalPage modalPage;
	private DacConsolePage dacConsolePage;
	private DulVotingPage dulVotingPage;
	private DarVotingPage darVotingPage;
	private DulCollectVotesPage dulCollectVotesPage;
	private DarCollectVotesPage darCollectVotesPage;
	private DulResultRecordPage dulResultRecordPage;
	private AddDatasetPage addDatasetPage;
	private DatasetCatalogPage datasetCatalogPage;
	private RequestHelpPage requestHelpPage;
	private RequestHelpReportsPage requestHelpReportsPage;
	private AddDulPage addDulPage;
	private DulPreviewResultsPage dulPreviewResultsPage;
	private DarPreviewResultsPage darPreviewResultsPage;
	private DarSummaryPage darSummaryPage;
	
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
	
	public ManageDarPage getManageDarPage() {
		return (manageDarPage == null) ? manageDarPage = new ManageDarPage(driver) : manageDarPage;
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
	
	public DulCollectVotesPage getDulCollectVotesPage() {
		return (dulCollectVotesPage == null) ? dulCollectVotesPage = new DulCollectVotesPage(driver) : dulCollectVotesPage;
	}
	
	public DarCollectVotesPage getDarCollectVotesPage() {
		return (darCollectVotesPage == null) ? darCollectVotesPage = new DarCollectVotesPage(driver) : darCollectVotesPage;
	}
	
	public AddDatasetPage getAddDatasetPage() {
		return (addDatasetPage == null) ? addDatasetPage = new AddDatasetPage(driver) : addDatasetPage;
	}
	
	public DatasetCatalogPage getDatasetCatalogPage() {
		return (datasetCatalogPage == null) ? datasetCatalogPage = new DatasetCatalogPage(driver) : datasetCatalogPage;
	}
	
	public RequestHelpPage getRequestHelpPage() {
		return (requestHelpPage == null) ? requestHelpPage = new RequestHelpPage(driver) : requestHelpPage;
	}
	
	public RequestHelpReportsPage getRequestHelpReportsPage() {
		return (requestHelpReportsPage == null) ? requestHelpReportsPage = new RequestHelpReportsPage(driver) : requestHelpReportsPage;
	}
	
	public AddDulPage getAddDulPage() {
		return (addDulPage == null) ? addDulPage = new AddDulPage(driver) : addDulPage;
	}
	
	public DulPreviewResultsPage getDulPreviewResultsPage() {
		return (dulPreviewResultsPage == null) ? dulPreviewResultsPage = new DulPreviewResultsPage(driver) : dulPreviewResultsPage;
	}
	
	public DarPreviewResultsPage getDarPreviewResultsPage() {
		return (dulPreviewResultsPage == null) ? darPreviewResultsPage = new DarPreviewResultsPage(driver) : darPreviewResultsPage;
	}
	
	public DulResultRecordPage getDulResultRecordPage() {
		return (dulResultRecordPage == null) ? dulResultRecordPage = new DulResultRecordPage(driver) : dulResultRecordPage;
	}
	
	public DarVotingPage getDarVotingPage() {
		return (darVotingPage == null) ? darVotingPage = new DarVotingPage(driver) : darVotingPage;
	}
	
	public DarSummaryPage getDarSummaryPage() {
		return (darSummaryPage == null) ? darSummaryPage = new DarSummaryPage(driver) : darSummaryPage;
	}

}
