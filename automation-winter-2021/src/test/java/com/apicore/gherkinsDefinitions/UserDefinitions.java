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

    @And("^user gets profile information$")
    public void getProfile() throws Exception{
        userSteps.getProfile();
    }
}
