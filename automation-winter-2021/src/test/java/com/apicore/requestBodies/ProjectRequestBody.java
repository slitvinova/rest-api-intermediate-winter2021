package com.apicore.requestBodies;

import com.apicore.util.JsonReader;

/**
 * This is class represents project requests bodies
 */
public class ProjectRequestBody {

    private static final String dir = System.getProperty("user.dir");

    public String getCreateProjectBody(String name) throws Exception {

        String requestBody = JsonReader.readJsonFileAsString(dir + "/src/test/resources/data/createProject.json");

        return requestBody.replace("$NAME", name);
    }

    public String getUpdateProjectWithNameAndBodyAndStateBody(String newName, String newBody, String state) throws Exception {

        String requestBody = JsonReader.readJsonFileAsString(dir + "/src/test/resources/data/updateProjectWithNameAndBodyAndState.json");

        return requestBody.replace("$NAME", newName).replace("$BODY", newBody).replace("$STATE", state);
    }

    public String getUpdateProjectWithStateBody(String state) throws Exception {

        String requestBody = JsonReader.readJsonFileAsString(dir + "/src/test/resources/data/updateProjectWithState.json");

        return requestBody.replace("$STATE", state);
    }
}