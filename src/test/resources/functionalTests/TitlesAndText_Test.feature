#Author: wloforte@broadinstitute.org

Feature: TITLES AND TEXT


  Scenario: Admin Console Titles
  	Given The user is logged in and in the Admin Console
    Then All titles are correct

  Scenario: Admin Console Descriptions
  	Given The user is logged in and in the Admin Console
    Then All descriptions are correct
