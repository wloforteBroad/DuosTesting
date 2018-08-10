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
Feature: Title of your feature
  I want to use this template for my feature file

	##@ignore
  Scenario: Successful Login as an admin
    Given The user is in the Home Page
    And user navigates to Login Page
    When user enters correct AdminUserName and AdminPassword
    Then user navigates to Admin Console
    
  @ignore  
  Scenario: Successful Login as a member1
    Given The user is in the Home Page
    And user navigates to Login Page
    When user enters correct MemberUserNameOne and MemberPassword
    Then user navigates to DAC Console
    
  @ignore 
	Scenario: Successful Login as a member2
    Given The user is in the Home Page
    And user navigates to Login Page
    When user enters correct MemberUserNameTwo and Password
    Then user navigates to DAC Console
    
  @ignore
  Scenario: Successful Login as a member3
    Given The user is in the Home Page
    And user navigates to Login Page
    When user enters correct MemberUserNameThree and Password
    Then user navigates to DAC Console 
   
