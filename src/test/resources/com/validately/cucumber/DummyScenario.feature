Feature: Login test
  As an user
  I want to login to Validately
  So that I can see Dashboard

  Scenario: I am logged in
    Given the page is open "http://www.validately.com"
    And I click Login button
    And I am on login page