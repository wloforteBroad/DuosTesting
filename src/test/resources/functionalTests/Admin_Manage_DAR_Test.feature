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
Feature: ADMIN MANAGE DAR
- As an Admin, a user would like to Manage Data Access Requests:
	- View List of DARs
	- Open Election
	- Cancel Election
	- View Application Summary
	- View Election Preview
	- Search DAR
	- DAR that needs Data Owner Approval Pending/Approved/Denied ****
	- Researcher Review ****
	
	
	@ignore
	@adminDar
  Scenario: Manage DAR - View List of DAR's
  	Given The user is logged in and in the Admin Console
    When The user clicks on Manage Data Access Request
    Then the user should see a list of Data Access Requests
    
  @ignore
  @adminDar
  Scenario: Manage DARL - Succesfully open election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    When The user click on create button for a given Dar
    Then The DAR Election Status should be Open
    
  @ignore
  @adminDar
  Scenario: Manage DAR - Succesfully cancel election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    And One DAR election is opened
    When The user cancel a given DAR election
    Then The DAR Election Status should be Canceled
    
  @ignore
  @adminDar
  Scenario: Manage DAR - View Application Summary
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    When The user clicks on Summary button
    Then The user should see the Application Summary Modal
    
  @ignore
  @adminDar
  Scenario: Manage DAR - View DAR Election preview
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    When The user clicks on Election Status for a given DAR
    Then The user should see the preview page of that DAR Election depending on the status
    
  @ignore
  @adminDarApproval
  Scenario: Manage DAR - View DAR that Needs Data Owner Approval
  	Given The user is logged in and in the Admin Console
    When The user clicks on Manage Data Access Request
    Then The user should see the DAR flagged with an icon saying that it Needs Approval
