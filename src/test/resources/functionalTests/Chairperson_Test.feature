#Author: wloforte@broadinstitute.org

Feature: DAC CHAIRPERSON CAPABILITIES
- As a Chairperson, a user would like to
	- Collect DUL votes an closed Election.
	- Send reminders to DAC Members.
	- See the pending cases for Review in the Console.
	- Collect DAR votes.
	- Push the Final DAR Vote and close Election.
	- Search DAR/DUL elections in console.
	
	
	@ignore
	@chairPerson
	Scenario: CHAIRPERSON - Pending cases for Review
		Given The Chairperson is logged in and in the DAC Console
    Then the user should see a list of Pending cases for Review
	
	@ignore
	@chairPerson
	Scenario: CHAIRPERSON - Collect DUL votes
		Given The Chairperson is logged in and in the DAC Console
		And user clicks on Collect Votes for a given DUL Election
    When user votes with a positive vote and submits Collect DUL vote
    Then the review case for the given Consent is not present on the Console
	
	@ignore
	@dacmemberDul
	Scenario: CHAIRPERSON - Collect DUL votes not allowed until all Members have voted
		Given The Chairperson is logged in and in the DAC Console
		And The user already voted as Member
		When user clicks on Collect Votes for a given DUL Election
    Then the Vote button is disabled until all members have voted
	
	@ignore
	@dacmemberDul
	Scenario: CHAIRPERSON - Send reminders to Members
		Given The Chairperson is logged in and in the DAC Console
		And The user already voted as Member
		And user clicks on Collect Votes for a given DUL Election
		When user click on Send a Reminder to a given user
		Then the system sends a notification to selected user
	
	@ignore
	@chairPerson
	Scenario: CHAIRPERSON - Check updated Member vote
		Given The Chairperson is logged in and in the DAC Console
		And user clicks on Collect Votes for a given DUL Election
    When one Member updates the vote
    Then the user should see a message saying Updated Vote!
	
	@tbd
	Scenario: CHAIRPERSON - Collect DAR votes
	
	@tbd
	Scenario: CHAIRPERSON - Collect DAR votes [MANUAL REVIEW]
	
	@tbd
	Scenario: CHAIRPERSON - DAR FINAL vote [APPROVE]
	
	@tbd
	Scenario: CHAIRPERSON - DAR FINAL vote [DENY]
	
	@tbd
	Scenario: CHAIRPERSON - DAR FINAL vote [MANUAL REVIEW]
	
