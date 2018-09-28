#Author: wloforte@broadinstitute.org

Feature: TITLES AND TEXT

	
  Scenario: Admin Console Titles
  	Given The user is logged in and in the Admin Console
    Then All titles are correct
	
  Scenario: Admin Console Descriptions
  	Given The user is logged in and in the Admin Console
    Then All descriptions are correct

  Scenario: Admin Manage Dul - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Data Use Limitations
  	Then Manage Dul Title and description are correct

  Scenario: Admin Manage Dar - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Data Access Request
  	Then Manage Dar Title and description are correct	

  Scenario: Admin Manage Dar - Title and Description
  	Given The user is logged in and in the Admin Console
  	And The user clicks on Manage Data Use Limitations
  	When The user clicks on Add Data Use Limitations
  	Then Add Dul Title and description are correct
  
  Scenario: Admin Manage Users - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Users
  	Then Manage Users Title and description are correct
  
  Scenario: Admin Add Users - Title and Description
  	Given The user is logged in and in the Admin Console
  	When clicks in Add User button
  	Then Add Users Title and description are correct
  
  Scenario: Admin Add Datasets - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Add Dataset button
  	Then Add Dataset Title and description are correct

  Scenario: Dataset Catalog - Title and Description
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Dataset Catalog
  	Then Dataset Catalog Title and description are correct
