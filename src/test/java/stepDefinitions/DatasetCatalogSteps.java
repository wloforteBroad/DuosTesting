package stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.DatasetCatalogPage;

public class DatasetCatalogSteps {
	TestContext testContext;
	DatasetCatalogPage datasetCatalogPage;
	WebDriver driver;
	
	public DatasetCatalogSteps(TestContext context) {
		testContext = context;
		datasetCatalogPage = testContext.getPageObjectManager().getDatasetCatalogPage();
	}
	
	@When("^the user navigates to Dataset Catalog$")
	public void the_user_navigates_to_Dataset_Catalog() throws Throwable {
	    Thread.sleep(1000);
	    assert datasetCatalogPage.isUserOnDatasetCatalog();
	}
	
	@Then("^the uploade dataset is shown$")
	public void the_uploade_dataset_is_shown() throws Throwable {
	    datasetCatalogPage.isDatasetOnTable();
	}

}
