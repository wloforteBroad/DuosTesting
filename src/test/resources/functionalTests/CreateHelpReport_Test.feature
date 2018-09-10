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
