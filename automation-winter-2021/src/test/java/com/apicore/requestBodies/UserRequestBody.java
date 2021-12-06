package com.apicore.requestBodies;

import com.apicore.util.JsonReader;

public class UserRequestBody {

    public static final String dir = System.getProperty("user.dir");

    public String createUser(String name, String email, String gender, String status) throws Exception {
        String requestBody = JsonReader.readJsonFileAsString("src/test/resources/data/createUser.json");

        return requestBody.replace("$NAME", name)
            .replace("$EMAIL", email)
            .replace("$GENDER", gender)
            .replace("$STATUS", status);

    }

    public String updateUserName(String newName) throws Exception {
        String requestBody = JsonReader.readJsonFileAsString("src/test/resources/data/updateUserName.json");

        return requestBody.replace("$NAME", newName);

    }
}
