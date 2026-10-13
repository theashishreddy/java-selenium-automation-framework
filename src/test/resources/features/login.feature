Feature: Login to the QA demo portal
  As a portal user
  I want to sign in with valid credentials
  So that I can access the portal

  Scenario: Valid credentials display a success message
    Given the demo login page is open
    When I sign in with username "qauser" and password "Pass123!"
    Then I should see the message "Login successful"
