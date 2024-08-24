@landing
Feature: Check available KIMA Testing page
  
@High 
Scenario: KIMA Testing available page
  Given A user go to KIMA Testing page
  Then "KIMA Testing" title is displayed
