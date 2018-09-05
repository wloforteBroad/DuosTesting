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
Feature: ADMIN MANAGE USERS
As an Admin a user would like to Manage Users:
	- View List of Users
	- Add
	- Edit
	- Review Researcher Profile****
	- Search User

  @ignore
  Scenario: Manage USERS - View list of USERS
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Users
  	Then The user should see a list of Users
  	
  @ignore
  Scenario: Manage USERS - Successfuly create User
    Given The user is logged in and in the Admin Console
    And clicks in Add User button
    When The user complete and submits the User form
    Then new user appears in Manage Users Page
    
  @ignore
  Scenario: Manage USERS - Successfuly edit User
    Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Users
    When The user edits a given user
    Then edited user appears in Manage Users Page
    
    
    