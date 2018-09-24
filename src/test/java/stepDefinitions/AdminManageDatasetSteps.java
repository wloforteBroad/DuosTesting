package stepDefinitions;

import java.io.File;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AddDatasetPage;
import pageObjects.AdminConsolePage;
import pageObjects.DatasetCatalogPage;
import pageObjects.HeaderPage;
import pageObjects.ModalPage;

public class AdminManageDatasetSteps {
	TestContext testContext;
	WebDriver driver;
	ModalPage modalPage;
	HeaderPage headerPage;
	AdminConsolePage adminConsolePage;
	AddDatasetPage addDatasetPage;
	DatasetCatalogPage datasetCatalogPage;
	private String tmpFolderPath = "/src/test/resources/";
	private String expectedFileName = "datasets.tsv";
	private File file = new File(tmpFolderPath + expectedFileName);
	
	public AdminManageDatasetSteps(TestContext context) {
		testContext = context;
		modalPage = testContext.getPageObjectManager().getModalPage();
		headerPage = testContext.getPageObjectManager().getHeaderPage();
		adminConsolePage = testContext.getPageObjectManager().getAdminConsolePage();
		addDatasetPage = testContext.getPageObjectManager().getAddDatasetPage();
		datasetCatalogPage = testContext.getPageObjectManager().getDatasetCatalogPage();
	}
	
	@Given("^The user clicks on Add Dataset button$")
	public void the_user_clicks_on_Add_Dataset_button() throws Throwable {
	    adminConsolePage.clickOn_AddDatasets();
	}
	
	@Given("^The user clicks on Dataset Catalog$")
	public void the_user_clicks_on_Dataset_Catalog() throws Throwable {
	    headerPage.clickOn_DatasetCatalog();
	}
	
	@Given("^there are datasets in the Catalog$")
	public void there_are_datasets_in_the_Catalog() throws Throwable {
		adminConsolePage.clickOn_AddDatasets();
		addDatasetPage.upload_CorrectDataset();
	}
	
	@Given("^the dataset is enabled$")
	public void the_dataset_is_enabled() throws Throwable {
	    datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    if (datasetCatalogPage.isDatasetDisabled() ) {
	    	datasetCatalogPage.clickOn_Enable();
		    modalPage.waitForModalToLoad();
	    	modalPage.clickOn_Yes();
	    }
	}
	
	@Given("^the dataset is disabled$")
	public void the_dataset_is_disabled() throws Throwable {
		datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    if (datasetCatalogPage.isDatasetEnabled() ) {
	    	datasetCatalogPage.clickOn_Disable();
		    modalPage.waitForModalToLoad();
	    	modalPage.clickOn_Yes();
	    }
	}
	
	@When("^The user clicks on the bin icon and accepts prompt$")
	public void the_user_clicks_on_the_bin_icon_and_accepts_prompt() throws Throwable {
	    datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    datasetCatalogPage.clickOn_Delete();
	    modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();

	}
	
	@When("^The user selects the file and clicks Add button$")
	public void the_user_selects_the_file_and_clicks_Add_button() throws Throwable {
	    addDatasetPage.upload_CorrectDataset();
	}
	
	@When("^The user selects all Datasets and clicks Download Selection$")
	public void the_user_selects_all_Datasets_and_clicks_Download_Selection() throws Throwable {
		System.out.println("FILE EXISTS:" + file.exists());
		System.out.println("FILE:" + file);
		if (file.exists()) { file.delete();}
		System.out.println("FILE EXISTS AFTER DELETE:" + file.exists());
		Thread.sleep(2000);
		datasetCatalogPage.check_All();
	    datasetCatalogPage.clickOn_Download();
	}
	
	
	@When("^The user clicks on View translated DUL$")
	public void the_user_clicks_on_View_translated_DUL() throws Throwable {
		datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    datasetCatalogPage.clickOn_ViewTranslatedDul();
	    modalPage.waitForModalToLoad();
	}
	
	@When("^The user clicks on the Disable Dataset icon and accepts prompt$")
	public void the_user_clicks_on_the_Disable_Dataset_icon_and_accepts_prompt() throws Throwable {
		datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    datasetCatalogPage.clickOn_Disable();
	    modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();
	}
	
	@When("^The user clicks on the Enable Dataset icon and accepts prompt$")
	public void the_user_clicks_on_the_Enable_Dataset_icon_and_accepts_prompt() throws Throwable {
		datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    datasetCatalogPage.clickOn_Enable();
	    modalPage.waitForModalToLoad();
	    modalPage.clickOn_Yes();
	}
	
	@Then("^the uploaded dataset is shown in Dataset Catalog Page$")
	public void the_uploaded_dataset_is_shown_in_Dataset_Catalog_Page() throws Throwable {
		Thread.sleep(1000);
	    datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    assert datasetCatalogPage.getAllDatasets().size() == 1;
	}
	
	@Then("^the file should be downloaded$")
	public void the_file_should_be_downloaded() throws Throwable {
		Thread.sleep(5000);
		assert file.exists();
	}
	
	@Then("^dataset is no longer shown in Dataset Catalog Page$")
	public void dataset_is_no_longer_shown_in_Dataset_Catalog_Page() throws Throwable {
		Thread.sleep(1000);
	    datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    assert datasetCatalogPage.getAllDatasets().size() == 0;
	}

	@Then("^dataset appears as disabled in Dataset Catalog Page$")
	public void dataset_appears_as_disabled_in_Dataset_Catalog_Page() throws Throwable {
	    datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    assert datasetCatalogPage.isDatasetDisabled();
	}
	
	@Then("^dataset appears as enabled in Dataset Catalog Page$")
	public void dataset_appears_as_enabled_in_Dataset_Catalog_Page() throws Throwable {
		datasetCatalogPage.findDataset(FileReaderManager.getInstance().getConfigReader().getDatasetId());
	    assert datasetCatalogPage.isDatasetEnabled();
	}
	
	@Then("^correct translated DUL should appear in the Modal$")
	public void correct_translated_DUL_should_appear_in_the_Modal() throws Throwable {
	    //assert FileReaderManager.getInstance().getConfigReader().getStructuredDul().replaceAll("<br */?>", "\n").equals(datasetCatalogPage.getText());
		assert "TranslateMock".equals(datasetCatalogPage.getTranslatedText());
	}

}
