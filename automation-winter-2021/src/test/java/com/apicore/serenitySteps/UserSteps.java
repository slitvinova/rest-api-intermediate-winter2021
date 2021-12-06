package com.apicore.serenitySteps;

import com.apicore.requestBodies.UserRequestBody;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

public class UserSteps {

    /**
     * Method to create a user
     */
    @Step
    public void createUser(String name, String email, String gender, String status) throws Exception {

        CommonSteps.sendRequestWithJsonBody("POST", "users/",
                new UserRequestBody().createUser(name, email, gender, status));
    }

    /**
     * Method to delete a user by id
     */
    @Step
    public void deleteUserById() throws Exception {

        CommonSteps.sendRequest("DELETE", "users/" + Serenity.sessionVariableCalled("userId"));
    }

    /**
     * Method to update user name
     */
    @Step
    public void updateUserName(String newName) throws Exception {

        CommonSteps.sendRequestWithJsonBody("PATCH",
                "users/" + Serenity.sessionVariableCalled("userId"),
                new UserRequestBody().updateUserName(newName));
    }

}
