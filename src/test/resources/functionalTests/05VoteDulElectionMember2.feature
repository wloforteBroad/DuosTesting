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
Feature: DUL ELECTION VOTING - MEMBER 2
  I want to use this template for my feature file

  Background:
    Given The user is in the Home Page
    And user navigates to Login Page
    When user enters correct MemberUserNameTwo and MemberPassword
	
	@ignore
  Scenario: Dac Member positive vote on Open Election
    Given The user is on Dac Console
    And The user search the consent
    And The user clicks on Vote button
    When User is on voting page
    And clicks yes and enters rationale
    And clicks Vote button
    Then the user navigates to Dac Console and consent is on Editable Status

