Feature: user

  @user @smoke
  Scenario Outline: get_user_profile
    Given the user is authorised
    And user gets profile information
    Then the user gets status code "<statusCode>"
    Examples:
      | statusCode |
      | 200        |
