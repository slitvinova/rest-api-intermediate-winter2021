package com.apicore.serenitySteps;

import net.thucydides.core.annotations.Step;

public class UserSteps {

    /**
     * Method to get a user profile
     */

    @Step
    public void getProfile() throws Exception {
        CommonSteps.sendRequest("GET", "/user");
    }

}
