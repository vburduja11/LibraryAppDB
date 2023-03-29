
Feature: As a data consumer, I want UI and DB book information are match.
  @wip @ui @db
  Scenario: Verify book information with DB
    Given the "librarian" on the home page_ES
    And the user navigates to "Books" page_ES
    When the user searches for "Clean Code" book_ES
    And  the user clicks edit book button_ES
    Then book information must match the Database_ES