#Author: wloforte@broadinstitute.org

@tag
Feature: DATA OWNER CAPABILITIES
-As a Data Owner, a user would like to 
	- Vote in a DAR Review Election.
	- View the Dataset Summary.
	- View the Application Summary.
	- View the pending cases for Review.
	- Search an open case for Review.
	- Edit Vote ?
		
	@dataOwner
	@ignore
	Scenario: DATA OWNER - DAR Review Election vote [Approve]
	Given The user is logged in and in the Data Owner Console
	And The user clicks on a given Dataset Access Review case
	When user votes and approves the case
	Then The selected case should not be present in the Console
	
	@dataOwner
	@ignore
	Scenario: DATA OWNER - View Application Summary
	Given The user is logged in and in the Data Owner Console
	And The user clicks on a given Dataset Access Review case
	When the user clicks on Application Summary
	Then The user should see the correct Application Summary
	
	@dataOwner
	@ignore
	Scenario: DATA OWNER - View Dataset Summary
	Given The user is logged in and in the Data Owner Console
	And The user clicks on a given Dataset Access Review case
	When the user clicks on Dataset Summary
	Then The user should see the correct Dataset Summary
	
	@dataOwner
	@ignore
	Scenario: DATA OWNER - Pending cases for review.
	Given The user is logged in and in the Data Owner Console
	Then the user sees a list of Pending Dataset Access Review cases


