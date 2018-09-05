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

Feature: DAC MEMBER CAPABILITIES
- As a DAC Member, a user would like to 
	- vote in a DAR Election.
	- edit a vote in a DUL Election.
	- vote in a DAR Election.
	- edit a vote in a DAR Election.
	- view all DUL pending cases for review in the Console.
	- view all DAR pending cases for review in the Console.
	- search DAR/DUL elections in console.
	
	@ignore
	@dacmemberDul
	Scenario: DAC Member Vote - Succesful DUL vote
  	Given The DAC Member is logged in and in the DAC Console 
    And user clicks on vote for a given DUL Election
    When user votes with a positive vote and submits DUL vote
    Then the DUL vote status should be Editable
    
  @ignore
	@dacmemberDul
	Scenario: DAC Member Vote - Vote Disabled until form complete
  	Given The DAC Member is logged in and in the DAC Console 
    When user clicks on vote for a given DUL Election
    Then the Vote button should be disabled
    
  @ignore
  @dacmemberDul
	Scenario: DAC Member Vote - Succesful edition of DUL Vote
  	Given The DAC Member is logged in and in the DAC Console 
    And user has already voted on a given DUL Election
    When user votes again and edits the rationale
    Then the user should see the DUL vote with the new rationale
    
  @ignore
	@dacmemberDul
	Scenario: DAC Member Vote - View Pending cases
  	Given The DAC Member is logged in and in the DAC Console 
    Then the User should see a list of Pending Cases for Review
    
  @ignore
	@dacmemberDar
	Scenario: DAC Member Vote - Succesful DAR vote
  	Given The DAC Member is logged in and in the DAC Console 
    And user clicks on vote for a given DAR Election
    When user votes with a positive vote and submits DAR vote
    Then the DAR vote status should be Editable
 

