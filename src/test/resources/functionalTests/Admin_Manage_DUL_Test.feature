#Author: wloforte@broadinstitute.org

Feature: ADMIN MANAGE DUL
- As an Admin a user would like to Manage Data Use Limitations:
	- View List of DULs
	- Add
	- Edit
	- Open Election
	- Cancel Election
	- Archive Election
	- Delete Record
	- Open New version of Election
	- View DUL Preview
	- View Election Preview
	- Search DUL

  Scenario: Manage DUL - View List of DUL's
  	Given The user is logged in and in the Admin Console
    When The user clicks on Manage Data Use Limitations
    Then the user should see a list of Data Use Limitations
    
  @addDul
  Scenario: Manage DUL - Succesfully Add DUL
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    When The user completes the form and submits
    Then the user should see the new DUL on the Manage Dul list 
  Scenario: Manage DUL - Cancel button disabled on empty form
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    When The user clicks on Add Data Use Limitations
    Then cancel button should be disabled
 
  @deleteDul
  Scenario: Manage DUL - Add DUL with same Name
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    When The user completes the form and submits
    Then The user should see the error message

  Scenario: Manage DUL - Add DUL with wrong sDUL Json
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    When The user completes the form with wrong sDul Json format and submits
    Then The user should see the error message
    
  Scenario: Manage DUL - Add DUL with wrong Data Use Json
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    When The user completes the form with wrong dataUse format and submits
    Then The user should see the error message
    
  @adminDul
  Scenario: Manage DUL - Succesfully open election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And All elections are closed
    When The user click on create button for a given Consent
    Then The Election Status should be Open
     
  @changeRoles
  Scenario: Manage DUL - Open election fails [Not enough DAC Members]
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And All elections are closed
    When The user click on create button for a given Consent
    Then The user should see the error message
    
  @adminDul
  Scenario: Manage DUL - Succesfully cancel election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And One election is opened
    When The user cancel a given election
    Then The Election Status should be Canceled
    
  @adminDul
  Scenario: Manage DUL - Succesfully archive election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And One election is opened
    When The user archive a given election
    Then The Election should be Archived
  
  @deleteDul
  Scenario: Manage DUL - Succesfully delete election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    When The user delete a given election
    Then The Election should no longer appear on the list
    
  @adminDul
  Scenario: Manage DUL - Succesfully open new version of election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And All elections are closed
    And The user click on create button for a given Consent
    When The user cancel a given election
    And The user click on create button for the same Consent again
    Then The Election Status should be Open and the new version should be increased by one
    
  Scenario: Manage DUL - View DUL preview
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    When The user clicks on Consent Id for a given Consent
    Then The user should see the preview page of that Dul
    
  Scenario: Manage DUL - View DUL Election preview
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    When The user clicks on Election Status for a given Consent
    Then The user should see the preview page of that Election depending on the status
