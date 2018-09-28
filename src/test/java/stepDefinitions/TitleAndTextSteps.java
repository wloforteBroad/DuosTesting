package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import pageObjects.AddDatasetPage;
import pageObjects.AddDulPage;
import pageObjects.AddUserPage;
import pageObjects.AdminConsolePage;
import pageObjects.DatasetCatalogPage;
import pageObjects.ManageDarPage;
import pageObjects.ManageDulPage;
import pageObjects.ManageUsersPage;

public class TitleAndTextSteps {
	TestContext testContext;
	AdminConsolePage adminConsolePage;
	ManageDulPage manageDulPage;
	ManageDarPage manageDarPage;
	AddDulPage addDulPage;
	ManageUsersPage manageUsersPage;
	AddUserPage addUserPage;
	AddDatasetPage addDatasetPage;
	DatasetCatalogPage datasetCatalogPage;
	
	public TitleAndTextSteps(TestContext context) {
		testContext = context;
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		manageDulPage = testContext.getPageObjectManager().getManageDulPage();
		manageDarPage = testContext.getPageObjectManager().getManageDarPage();
		addDulPage = testContext.getPageObjectManager().getAddDulPage();
		manageUsersPage = testContext.getPageObjectManager().getManageUsersPage();
		addUserPage = testContext.getPageObjectManager().getAddUserPage();
		addDatasetPage = testContext.getPageObjectManager().getAddDatasetPage();
		datasetCatalogPage = testContext.getPageObjectManager().getDatasetCatalogPage();
	}
	
	@Then("^All titles are correct$")
	public void all_titles_are_correct() throws Throwable {
	    assert adminConsolePage.compareTitles();
	}
	
	@Then("^All descriptions are correct$")
	public void all_descriptions_are_correct() throws Throwable {
	    assert adminConsolePage.compareDescriptions();
	}
	
	@Then("^Manage Dul Title and description are correct$")
	public void manage_dul_title_and_description_are_correct() throws Throwable {
	    assert manageDulPage.isTitleOk();
	    assert manageDulPage.isDescriptionOk();
	}
	
	@Then("^Manage Dar Title and description are correct$")
	public void manage_Dar_Title_and_description_are_correct() throws Throwable {
	    assert manageDarPage.isTitleOk();
	    assert manageDarPage.isDescriptionOk();
	}
	
	@Then("^Add Dul Title and description are correct$")
	public void add_Dul_Title_and_description_are_correct() throws Throwable {
	    assert addDulPage.isTitleOk();
	    assert addDulPage.isDescriptionOk();
	}
	
	@Then("^Manage Users Title and description are correct$")
	public void manage_Users_Title_and_description_are_correct() throws Throwable {
	    assert manageUsersPage.isTitleOk();
	    assert manageUsersPage.isDescriptionOk();
	}
	
	@Then("^Add Users Title and description are correct$")
	public void add_Users_Title_and_description_are_correct() throws Throwable {
	    addUserPage.isTitleOk();
	    addUserPage.isDescriptionOk();
	}
	
	@Then("^Add Dataset Title and description are correct$")
	public void add_Dataset_Title_and_description_are_correct() throws Throwable {
	    addDatasetPage.isTitleOk();
	    addDatasetPage.isDescriptionOk();
	}
	
	@Then("^Dataset Catalog Title and description are correct$")
	public void dataset_Catalog_Title_and_description_are_correct() throws Throwable {
	    datasetCatalogPage.isTitleOk();
	    datasetCatalogPage.isDescriptionOk();
	}

}