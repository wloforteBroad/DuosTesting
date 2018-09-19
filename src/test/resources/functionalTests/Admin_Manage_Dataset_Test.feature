#Author: wloforte@broadinstitute.org

Feature: ADMIN MANAGE DATASETS
As an Admin, a user would like to Manage Datasets:
	- Upload Datasets file
	- Download selected Dataset/s****
	- Search Dataset
	- Delete
	- Disable/Enable
	- Connect with Data Owner****
	- View Structured DUL
	- Download Approved Requestors****

  Scenario: Manage DATASET - Upload correct Dataset
    Given The user is logged in and in the Admin Console
    And The user clicks on Add Dataset button
    When The user selects the file and clicks Add button
    Then the uploaded dataset is shown in Dataset Catalog Page
    
  @tbd
  Scenario: Manage DATASET - Donwload Selected Datasets ****
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    When The user selects all Datasets and clicks Download Selection
    Then the file should be downloaded
    
  Scenario: Manage DATASET - Delete Dataset 
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    When The user clicks on the bin icon and accepts prompt
    Then dataset is no longer shown in Dataset Catalog Page
    
  Scenario: Manage DATASET - Disable Dataset 
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    And the dataset is enabled
    When The user clicks on the Disable Dataset icon and accepts prompt
    Then dataset appears as disabled in Dataset Catalog Page
    
  Scenario: Manage DATASET - Enable Dataset 
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    And the dataset is disabled
    When The user clicks on the Enable Dataset icon and accepts prompt
    Then dataset appears as enabled in Dataset Catalog Page
    
  Scenario: Manage DATASET - View Translated DUL 
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    When The user clicks on View translated DUL
    Then correct translated DUL should appear in the Modal
    
    

  
