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
Feature: ADMIN CAPABILITIES
  As an Admin a user would like to Manage:
   - Data use Limitation
   - Data Access Requests
   - Users
   - Datasets

  @ignore
  Scenario: Manage DUL - View List of DUL's
  	Given The user is logged in and in the Admin Console
    When The user clicks on Manage Data Use Limitations
    Then the user should see a list of Data Use Limitations
  
  @ignore
  Scenario: Manage DUL - Succesfully Add DUL
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    When The user completes the form and submits
    Then the user should see the new DUL on the Manage Dul list
    
  @ignore
  Scenario: Manage DUL - Succesfully Add DUL
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    Then cancel button should be disabled
    
  @ignore
  Scenario: Manage DUL - Add dul with same Name
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    When The user completes the form and submits
    Then The user should see the error message
    
  @ignore
  Scenario: Manage DUL - Add dul with wrong sDUL Json
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    When The user completes the form with wrong sDul Json format and submits
    Then The user should see the error message
    
  @ignore
  Scenario: Manage DUL - Add dul with wrong Data Use Json
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And The user clicks on Add Data Use Limitations
    When The user completes the form with wrong dataUse format and submits
    Then The user should see the error message
    
  @ignore
  Scenario: Manage DUL - Succesfully open election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And All elections are closed
    When The user click on create button for a given Consent
    Then The Election Status should be Open
    
  @ignore
  Scenario: Manage DUL - Succesfully open election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Use Limitations
    And One election is opened
    When The user cancel a given election
    Then The Election Status should be Canceled
  

