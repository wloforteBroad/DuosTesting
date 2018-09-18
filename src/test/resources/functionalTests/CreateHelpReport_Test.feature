#Author: wloforte@broadinstitute.org

Feature: CREATE HELP REPORT
  I want to use this template for my feature file
	
	@ignore
  Scenario: User creates help report successfuly
    Given The user is logged in and in the Admin Console
    And The user clicks on Request Help on the header
    And The user clicks on Create a Report option
    When User is on Request Help Modal 
    And completes Subject and Description
    And clicks Submit button
    Then the user navigates to Request Help Reports Page and created Report is displayed
