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
Feature: UPLOAD DATASET
  I want to use this template for my feature file

  Background:
    Given The user is in the Home Page
    And user navigates to Login Page
    When user enters correct AdminUserName and AdminPassword
	
	@ignore
  Scenario: Admin Upload correct Dataset
    Given The user is in the Admin Console
    And The user clicks on Add Dataset button
    When User is on Add Dataset Modal
    And clicks on Upload file button and selects file
    And clicks on Add button
    And the user navigates to Dataset Catalog
    Then the uploade dataset is shown
