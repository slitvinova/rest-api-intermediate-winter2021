package com.apicore.hooks;

import com.apicore.serenitySteps.CommonSteps;
import com.apicore.serenitySteps.ProjectSteps;
import cucumber.api.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@RemoveProjectHook")
    public static void hookBeforeRemoveAllProjects() throws Exception {
        ProjectSteps projectSteps = new ProjectSteps();
        CommonSteps.setAuthToken();
        projectSteps.listProjects();
        projectSteps.removeListOfProjects();
    }
}
