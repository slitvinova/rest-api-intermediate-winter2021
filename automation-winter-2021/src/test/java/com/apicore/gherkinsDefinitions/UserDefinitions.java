package com.apicore.gherkinsDefinitions;

import com.apicore.serenitySteps.UserSteps;
import cucumber.api.java.en.And;
import net.thucydides.core.annotations.Steps;

/**
 * This is class contains user definitions
 */

public class UserDefinitions {

    @Steps
    public UserSteps userSteps;

    @And("^new user is created with name \"([^\"]*)\", email \"([^\"]*)\", gender \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void createUser(String name, String email, String gender, String status) throws Exception{
        userSteps.createUser(name, email, gender, status);
    }

    @And("^the user gets deleted by id$")
    public void deleteUserById() throws Exception{
        userSteps.deleteUserById();
    }

    @And("^the user updates the name with \"([^\"]*)\"$")
    public void updateUserName(String newName) throws Exception{
        userSteps.updateUserName(newName);
    }
}
