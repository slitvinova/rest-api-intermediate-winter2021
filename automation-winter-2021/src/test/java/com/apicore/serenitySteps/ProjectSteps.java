package com.apicore.serenitySteps;

import com.apicore.requestBodies.ProjectRequestBody;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ProjectSteps {

    public static final String RESPONSE = "response";

    /**
     * Method to create a project
     */
    @Step
    public void createProject(String name) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));

        CommonSteps.sendRequestWithJsonBody("POST", "/orgs/" + prop.getProperty("org") + "/projects", new ProjectRequestBody().getCreateProjectBody(name));
    }

    /**
     * Method to get a project
     */
    @Step
    public void getCreatedProject() throws Exception {
        CommonSteps.sendRequest("GET", "/projects/" + Serenity.sessionVariableCalled("projectId"));
    }

    /**
     * Method to remove a project
     */
    @Step
    public void removeCreatedProject() throws Exception {
        CommonSteps.sendRequest("DELETE", "/projects/" + Serenity.sessionVariableCalled("projectId"));
    }

    /**
     * Method to remove a project by id
     */
    @Step
    public void removeProjectById(String id) throws Exception {
        CommonSteps.sendRequest("DELETE", "/projects/" + id);
    }

    /**
     * Method to get a project by id
     */
    @Step
    public void getProjectById(String id) throws Exception {
        CommonSteps.sendRequest("GET", "/projects/" + id);
    }

    /**
     * Method to list all project
     */
    @Step
    public void listProjects() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));

        CommonSteps.sendRequest("GET", "/orgs/" + prop.getProperty("org") + "/projects");
    }

    /**
     * Remove list of projects
     */
    @Step
    public void removeListOfProjects() throws Exception {
        Response response = Serenity.sessionVariableCalled(RESPONSE);

        List<HashMap> projects = response.getBody().jsonPath().getList("$");

        for (HashMap project: projects) {
            String id = project.get("id").toString();
            removeProjectById(id);
        }
    }


    /**
     * Method to list all projects by org
     */
    @Step
    public void getProjectListByOrg(String org) throws Exception {
        CommonSteps.sendRequest("GET", "/orgs/" + org + "/projects");
    }

    /**
     * Method to update project name, body and state
     */
    @Step
    public void updateProjectWithNameAndBodyAndState(String newName, String newBody, String state) throws Exception {
        CommonSteps.sendRequestWithJsonBody("PATCH", "/projects/" + Serenity.sessionVariableCalled("projectId"),
                new ProjectRequestBody().getUpdateProjectWithNameAndBodyAndStateBody(newName, newBody, state));
    }

    /**
     * Method to update project name, body and state
     */
    @Step
    public void theUserUpdatesCreatedProjectWithNewState(String state) throws Exception {
        CommonSteps.sendRequestWithJsonBody("PATCH", "/projects/" + Serenity.sessionVariableCalled("projectId"),
                new ProjectRequestBody().getUpdateProjectWithStateBody(state));
    }
}
