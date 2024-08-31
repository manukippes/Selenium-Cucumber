@landing
Feature: Check available KIMA Testing page
  
  @High
  Scenario: KIMA Testing available page
    Given The user go to KIMA Testing page
    Then "KIMA Testing" title is displayed

  @medium
  Scenario Outline: Check available Sections
    Given The user go to KIMA Testing page
    When The user go to section "<section>"
    Then section "<section>" is available

    Examples:
      | section   |
      | Blog      |
      | Cursos    |
      | Descargas |
      | Contacto  |
      | Init      |

  @low
  Scenario: Check available Contact button
    Given The user go to KIMA Testing page
    When The user clicks on the Contact button
    Then The contact page is showed
