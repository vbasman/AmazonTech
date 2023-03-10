Feature: api get request verification

Scenario Outline:Counting posts for user
When I get a list of blog posts using the API endpoint
Then user <user> should have <numposts> posts.
    Examples:
      | user | numposts |
      |5     |10        |
      |9     |10        |
      |7     |10        |
  @wip
  Scenario: Unique ID per post
    When I get a list of blog posts using the API endpoint
    Then each blog post should have a unique ID