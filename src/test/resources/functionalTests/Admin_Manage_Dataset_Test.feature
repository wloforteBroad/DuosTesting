#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
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

  @ignore
  Scenario: Manage DATASET - Upload correct Dataset
    Given The user is logged in and in the Admin Console
    And The user clicks on Add Dataset button
    When The user selects the file and clicks Add button
    Then the uploaded dataset is shown in Dataset Catalog Page
    
  @ignore
  Scenario: Manage DATASET - Donwload Selected Datasets ****
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    When The user selects all Datasets and clicks Download Selection
    Then the file should be downloaded
    
  @ignore
  Scenario: Manage DATASET - Delete Dataset 
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    When The user clicks on the bin icon and accepts prompt
    Then dataset is no longer shown in Dataset Catalog Page
    
  @ignore
  Scenario: Manage DATASET - Disable Dataset 
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    And the dataset is enabled
    When The user clicks on the Disable Dataset icon and accepts prompt
    Then dataset appears as disabled in Dataset Catalog Page
    
  @ignore
  Scenario: Manage DATASET - Enable Dataset 
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    And the dataset is disabled
    When The user clicks on the Enable Dataset icon and accepts prompt
    Then dataset appears as enabled in Dataset Catalog Page
    
  @ignore
  Scenario: Manage DATASET - View Translated DUL 
    Given The user is logged in and in the Admin Console
    And there are datasets in the Catalog
    When The user clicks on View translated DUL
    Then correct translated DUL should appear in the Modal
    
    

  
