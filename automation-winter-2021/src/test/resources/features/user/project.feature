Feature: project

#  @Project @Smoke @RemoveProjectHook
#  Scenario Outline: create_project
#    Given the user is authorised
#    When the user creates a project with name "<name>"
#    Then the user gets status code "201"
#    When the following values are present in the body
#      | name                    | <name> |
#      | state                   | open   |
#      | creator.type            | User   |
#      | organization_permission | write  |
#    Then the following keys exists in the body
#      | owner_url  |
#      | html_url   |
#      | id         |
#      | creator.id |
#    And the user saves response key "id" as session variable with name "projectId"
#    Then the user gets created project
#    When the user gets status code "200"
#    And the user removes created project
#    Then the user gets status code "204"
#    Examples:
#      | name |
#      | test |
#
#  @Project @Smoke
#  Scenario Outline: delete_not_existing_project
#    Given the user is authorised
#    When the user removes project with id "<id>"
#    And the user gets status code "404"
#    Then the value of path "message" is "Not Found"
#    Examples:
#      | id   |
#      | 6364 |
#
#  @Project @Smoke
#  Scenario Outline: get_not_existing_project
#    Given the user is authorised
#    When the user gets project with id "<id>"
#    And the user gets status code "404"
#    Then the value of path "message" is "Not Found"
#    Examples:
#      | id   |
#      | 6364 |
#
##  @Project @Smoke
##  Scenario Outline: get_project_list_with_wrong_organization
##    Given the user is authorised
##    When the user gets list of projects with organization "<org>"
##    And the user gets status code "404"
##    When the value of path "message" is "Not Found"
##    Then the following keys exists in the body
##      | documentation_url  |
##    Examples:
##      | org            |
##      | notexistingorg |
#
#  @Project @Smoke @RemoveProjectHook
#  Scenario Outline: udpate_project
#    Given the user is authorised
#    When the user creates a project with name "<name>"
#    Then the user gets status code "201"
#    And the user saves response key "id" as session variable with name "projectId"
#    Then the user updates created project with new name "<newName>", new body "<newBody>" and state "<state>"
#    When the following values are present in the body
#      | name  | <newName> |
#      | state | <state>   |
#      | body  | <newBody> |
#    When the user gets status code "200"
#    Then the user gets created project
#    When the user gets status code "200"
#    When the following values are present in the body
#      | name  | <newName>  |
#      | state | <state>    |
#      | body  | <newBody>  |
#    And the user removes created project
#    Then the user gets status code "204"
#    Examples:
#      | name | newName   | newBody | state  |
#      | test | updateTes | myBody  | closed |
#      | test | updateTes | myBody  | open   |
#
#  @Project @Smoke @RemoveProjectHook @run
#  Scenario Outline: udpate_project_with_wrong_state
#    Given the user is authorised
#    When the user creates a project with name "<name>"
#    Then the user gets status code "201"
#    And the user saves response key "id" as session variable with name "projectId"
#    Then the user updates created project with new state "<state>"
#    When the user gets status code "422"
#    When the following values are present in the body
#      | message   | Validation Failed                                               |
#      | errors[0] | Variable $state of type ProjectState was provided invalid value |
#    And the user removes created project
#    Then the user gets status code "204"
#    Examples:
#      | state   |
#      | current |

