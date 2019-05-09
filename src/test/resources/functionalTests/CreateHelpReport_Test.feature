#Author: wloforte@broadinstitute.org

Feature: CREATE HELP REPORT
As a user I would like to Create Reports:
	- Create Help Report
	- View List of my reports
	- As an Admin view list of all reports****

  Scenario: User creates help report successfuly
    Given The user is logged in and in the Admin Console
    And The user clicks on Request Help on the header
    And The user clicks on Create a Report option
    When User is on Request Help Modal 
    And completes Subject and Description
    And clicks Submit button
    Then the user navigates to Request Help Reports Page and created Report is displayed
