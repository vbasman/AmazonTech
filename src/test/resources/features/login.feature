Feature: Login Feature

Scenario: Valid login
Given I am a user of amazon.com
When I log in using valid credentials Then I should be logged in

Scenario: Invalid login
Given I am a user of amazon.com
When I log in using invalid credentials Then I should not be logged in