package com.apicore.gherkinsDefinitions;

import com.apicore.serenitySteps.CommonSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import java.util.List;

/**
 * This class contains gherkin definitions that are used in multiple features and are common
 */
public class CommonDefinitions {

    @When("^the user is authorised$")
    public void authorise() throws IOException {
        CommonSteps.setAuthToken();
    }

    @When("^the user is authorised without token$")
    public void authWithoutToken() throws IOException {
        CommonSteps.setAuthWithoutToken();
    }

    @When("^the user is authorised with wrong token$")
    public void authWithWrongToken() throws IOException {
        CommonSteps.setAuthWithWrongToken();
    }

    @Then("^the user gets status code \"([^\"]*)\"$")
    public void assertStatusCode(int statusCode) {
        CommonSteps.assertStatusCode(statusCode);
    }

    @And("^the value of path \"([^\"]*)\" is \"([^\"]*)\"$")
    public void valueOfPathIs(String path, String expectedValue) {
        CommonSteps.valueOfPathIs(path, expectedValue);
    }

    @When("^the user gets one status code$")
    public void verifyOneStatusCode(List<Integer> code) throws IOException {
        CommonSteps.verifyOneStatusCode(code);
    }

    @And("^the user received one value in path \"([^\"]*)\" and sets session variable with this name \"([^\"]*)\"$")
    public void checkPathValueAndSaveToVar(String path, String name) {
        CommonSteps.receivedValueInThePathInTheResponse(path, name);
    }

    @And("^the following values are present in the body$")
    public void checkPathValueListAndSaveToVar(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);

        for (List<String> columns : rows) {
            CommonSteps.valueOfPathIs(columns.get(0), columns.get(1));
        }
    }

    @And("^the following keys exists in the body$")
    public void checkKeysExists(DataTable table) throws IOException {
        List<List<String>> rows = table.asLists(String.class);

        for (List<String> columns : rows) {
            CommonSteps.pathExistsInTheResponse(columns.get(0));
        }
    }

    @And("^the user removes auth token$")
    public void removeAuthToken() throws IOException {
        CommonSteps.unsetAuthToken();
    }

    @And("^the value for \"([^\"]*)\" present in array in path \"([^\"]*)\" with key \"([^\"]*)\"$")
    public void valuePresentInResponse(String path, String id, String searchableKey) {
        CommonSteps.valuePresentInResponse(path, id, searchableKey);
    }

    @And("^the path \"([^\"]*)\" integer value is the same as session variable with key \"([^\"]*)\"$")
    public void integerValueInResponseEqualToSessionVariable(String path, String sessionVar) {
        CommonSteps.integerValueInResponseEqualToSessionVariable(path, sessionVar);
    }

    @And("^the user saves response key \"([^\"]*)\" as session variable with name \"([^\"]*)\"$")
    public void saveResponseKeyToSession(String key, String varName) {
        CommonSteps.saveResponseKeyToSession(key, varName);
    }
}
