#Author: wloforte@broadinstitute.org

Feature: ADMIN MANAGE USERS
As an Admin a user would like to Manage Users:
	- View List of Users
	- Add
	- Edit
	- Review Researcher Profile****
	- Search User

  Scenario: Manage USERS - View list of USERS
  	Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Users
  	Then The user should see a list of Users
  	
  @deleteUser
  Scenario: Manage USERS - Successfuly create User
    Given The user is logged in and in the Admin Console
    And clicks in Add User button
    When The user complete and submits the User form
    Then new user appears in Manage Users Page
  
  ##Leave user in previous state TBD
  Scenario: Manage USERS - Successfuly edit User
    Given The user is logged in and in the Admin Console
  	When The user clicks on Manage Users
    When The user edits a given user
    Then edited user appears in Manage Users Page
    
    
    