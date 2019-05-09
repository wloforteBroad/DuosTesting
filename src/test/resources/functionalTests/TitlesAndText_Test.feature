#Author: wloforte@broadinstitute.org

Feature: TITLES AND TEXT

	
  Scenario: ADMIN CONSOLE TITLES
  	Given The user is logged in and in the Admin Console
    Then All titles are correct
	
  Scenario: ADMIN CONSOLE DESCRIPTIONS
  	Given The user is logged in and in the Admin Console
    Then All descriptions are correct

  Scenario: ADMIN MANAGE DUL - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Data Use Limitations
  	Then Manage Dul Title and description are correct

  Scenario: ADMIN MANAGE DAR - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Data Access Request
  	Then Manage Dar Title and description are correct	

  Scenario: ADMIN ADD DUL - Title and Description
  	Given The user is logged in and in the Admin Console
  	And The user clicks on Manage Data Use Limitations
  	When The user clicks on Add Data Use Limitations
  	Then Add Dul Title and description are correct
  
  Scenario: ADMIN MANAGE USERS - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Users
  	Then Manage Users Title and description are correct
  
  Scenario: ADMIN ADD USER - Title and Description
  	Given The user is logged in and in the Admin Console
  	When clicks in Add User button
  	Then Add Users Title and description are correct
  
  Scenario: ADMIN ADD DATASETS - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Add Dataset button
  	Then Add Dataset Title and description are correct

  Scenario: DATASET CATALOG - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Dataset Catalog
  	Then Dataset Catalog Title and description are correct

  Scenario: CHAIR CONSOLE - Title and Description
  	Given The Chairperson is logged in and in the DAC Console
  	Then Chair Console Title and description are correct
	
  Scenario: DAC MEMBER CONSOLE - Title and Description
  	Given The DAC Member is logged in and in the DAC Console
  	Then DAC Member Console Title and description are correct

  @dacmemberDul	
  Scenario: DUL REVIEW - Title and Description
  	Given The DAC Member is logged in and in the DAC Console
  	And user clicks on vote for a given DUL Election
  	Then Dul Review Page Title and Description are correct
