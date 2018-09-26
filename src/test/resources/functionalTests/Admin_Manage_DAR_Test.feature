#Author: wloforte@broadinstitute.org

Feature: ADMIN MANAGE DAR
- As an Admin, a user would like to Manage Data Access Requests:
	- View List of DARs
	- Open Election
	- Cancel Election
	- View Application Summary
	- View Election Preview
	- Search DAR
	- DAR that needs Data Owner Approval Pending/Approved/Denied ****
	- Researcher Review ****

	@adminDar
  Scenario: Manage DAR - View List of DAR's
  	Given The user is logged in and in the Admin Console
    When The user clicks on Manage Data Access Request
    Then the user should see a list of Data Access Requests
 
  @adminDar
  Scenario: Manage DARL - Succesfully open election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    When The user click on create button for a given Dar
    Then The DAR Election Status should be Open

 	@adminDar  
  @changeRoles
  Scenario: Manage DAR - Open election fails [Not enough DAC Members]
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    When The user click on create button for a given Dar
    Then The user should see the error message

  @adminDar
  Scenario: Manage DAR - Succesfully cancel election
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    And One DAR election is opened
    When The user cancel a given DAR election
    Then The DAR Election Status should be Canceled

  @adminDar
  Scenario: Manage DAR - View Application Summary
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    When The user clicks on Summary button
    Then The user should see the Application Summary Modal
    
  @tbd
  @adminDar
  Scenario: Manage DAR - Review Application Summary Content

  @adminDar
  Scenario: Manage DAR - View DAR Election preview [UN REVIEWED]
  	Given The user is logged in and in the Admin Console
    And The user clicks on Manage Data Access Request
    When The user clicks on Election Status for a given DAR
    Then The user should see the preview page of that DAR Election depending on the status
  
  @tbd
  @adminDar
  Scenario: Manage DAR - View DAR Election preview [OPEN]
  
  @tbd
  @adminDar
  Scenario: Manage DAR - View DAR Election preview [REVIEWED]
  
  @tbd
  @adminDar
  Scenario: Manage DAR - View DAR Election preview [CANCELED]
    
  @adminDarApproval
  Scenario: Manage DAR - View DAR that Needs Data Owner Approval
  	Given The user is logged in and in the Admin Console
    When The user clicks on Manage Data Access Request
    Then The user should see the DAR flagged with an icon saying that it Needs Approval
    
  @tbd
  @adminDar
  Scenario: Manage DAR - Bonafide researcher [PENDING]
  
  @tbd
  @adminDar
  Scenario: Manage DAR - Bonafide researcher [APPROVED]
  
  @tbd
  @adminDar
  Scenario: Manage DAR - Bonafide researcher [DENIED]
