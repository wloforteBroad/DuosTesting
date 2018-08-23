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
Feature: CREATE USER
  I want to use this template for my feature file
  
  Background:
    Given The user is in the Home Page
    And user navigates to Login Page
    When user enters correct AdminUserName and AdminPassword
	
	@ignore
  Scenario: Successful creation of User
    Given The user is in the Admin Console
    And clicks in Add User button
    When user enters correct name, account and role
    And clicks in add button
    Then new user appears in Manage Users Page
