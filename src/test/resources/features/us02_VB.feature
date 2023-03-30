@B28G29-191
Feature: As a librarian, I want to know borrowed books number

	@ui @db @B28G29-190
	Scenario: US02AC1 Verify the total amount of borrowed books
		    Given the "librarian" on the home page_VB
		    When the librarian gets borrowed books number_VB
		    Then borrowed books number information must match with DB_VB