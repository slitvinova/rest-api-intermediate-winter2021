Feature: user

  @User @Smoke @run
  Scenario Outline: create_new_user
    Given the user is authorised
    And new user is created with name "<name>", email "<email>", gender "<gender>" and status "<status>"
    Then the user gets status code "201"
    And the following keys exists in the body
      | data.id |
    And the following values are present in the body
      | data.name   | <name>   |
      | data.email  | <email>  |
      | data.gender | <gender> |
      | data.status | <status> |
    When the user received one value in path "data.id" and sets session variable with this name "userId"
    And the user gets deleted by id
    Then the user gets status code "204"
    Examples:
      | name  | email                   | gender | status |
      | sveta | sveta.eml@email.com     |  male  | active |

  @User @Smoke @run
  Scenario Outline: update_user_name
    Given the user is authorised
    And new user is created with name "<name>", email "<email>", gender "<gender>" and status "<status>"
    Then the user gets status code "201"
    When the user received one value in path "data.id" and sets session variable with this name "userId"
    And the user updates the name with "<newName>"
    Then the user gets status code "200"
    And the following keys exists in the body
      | data.id |
    And the following values are present in the body
      | data.name   | <newName>   |
      | data.email  | <email>     |
      | data.gender | <gender>    |
      | data.status | <status>    |
    And the user gets deleted by id
    Then the user gets status code "204"
    Examples:
      | name   | email                  | gender | status | newName  |
      | sveta | sveta.iql@email.com     |  male  | active | newSveta |

  @User @Smoke @run
  Scenario Outline: create_a_user_with_already_existing_email
    Given the user is authorised
    And new user is created with name "<name>", email "<email>", gender "<gender>" and status "<status>"
    Then the user gets status code "201"
    Then the user received one value in path "data.id" and sets session variable with this name "userId"
    When new user is created with name "<name>", email "<email>", gender "<gender>" and status "<status>"
    Then the user gets status code "422"
    And the following values are present in the body
      | data.field    | [email]                  |
      | data.message  | [has already been taken] |
    And the user gets deleted by id
    Then the user gets status code "204"
    Examples:
      | name  | email                 | gender | status   |
      | sveta | sveta.mmm@email.com   |  male  | active   |

  @User @Smoke @run
  Scenario Outline: remove_already_removed_user
    Given the user is authorised
    And new user is created with name "<name>", email "<email>", gender "<gender>" and status "<status>"
    Then the user gets status code "201"
    Then the user received one value in path "data.id" and sets session variable with this name "userId"
    And the user gets deleted by id
    Then the user gets status code "204"
    When the user gets deleted by id
    Then the user gets status code "404"
    And the following values are present in the body
      | data.message  | Resource not found |
    Examples:
      | name  | email                 | gender | status   |
      | sveta | sveta.mnm@email.com   |  male  | active   |
