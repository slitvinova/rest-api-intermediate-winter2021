package com.apicore.serenitySteps;

import com.apicore.requestBodies.CommonRequestBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.decorators.request.RequestSpecificationDecorated;
import static net.serenitybdd.rest.SerenityRest.rest;
import com.apicore.support.Service;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


/**
 * Class contains implementations for the CommonSteps
 */
public class CommonSteps {

    public static final String RESPONSE = "response";
    private static RequestSpecification requestSpecification = rest();
    public static String token = null;

    /**
     * Method to create a request
     */
    @Step
    public static void sendRequest(String method, String endpoint) {

        if (token != null) {
            requestSpecification.header("Authorization", "Bearer " + token);
        }

        Serenity.setSessionVariable("REQUEST-HEADERS").to((String) ((RequestSpecificationDecorated) requestSpecification).getHeaders().toString());
        Serenity.setSessionVariable("REQUEST-METHOD").to(method);
        Serenity.setSessionVariable("REQUEST-URL").to(RestAssured.baseURI + endpoint);

        Response response = Service.sendRequest(requestSpecification, method, endpoint)
                .then()
                .extract()
                .response();
        response.prettyPrint();
        Serenity.setSessionVariable(RESPONSE).to(response);
    }

    /**
     * Method to create a request with the JSON body
     */
    @Step
    public static void sendRequestWithJsonBody(String method, String endpoint, String body) throws IOException {
        JSONObject bodyJson = new JSONObject(body);
        Map<String, Object> bodyMap = bodyJson.toMap();

        requestSpecification.body(bodyMap);

        sendRequest(method, endpoint);
    }

    /**
     * Method to assert received status code
     */
    @Step
    public static void assertStatusCode(int expectedStatusCode) {
        Response res = Serenity.sessionVariableCalled(RESPONSE);

        if (res.getStatusCode() != expectedStatusCode) {
            System.err.println(" Response Body:");
            res.getBody().prettyPrint();
        }

        Assert.assertEquals("status code doesn't match," +
                "\nRequest:\nHeaders: " + Serenity.sessionVariableCalled("REQUEST-HEADERS") + "\nMethod: " + Serenity.sessionVariableCalled("REQUEST-METHOD") +
                "\nUrl: " + Serenity.sessionVariableCalled("REQUEST-URL") +
                "\nResponse:\nHeaders: " + res.getHeaders() + "\n ~Name: " + "~" + "~\n", expectedStatusCode, res.getStatusCode());
    }

    /**
     * Method to set auth token
     */
    public static void setAuthToken() throws IOException {
        RequestSpecification requestSpecification1 = rest();
        HashMap<String, String> hashMap = new HashMap();

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));

        hashMap.put("Authorization", "token " + prop.getProperty("token"));
        hashMap.put("Content-Type", "application/json");
        hashMap.put("Accept", "application/vnd.github.v3+json");

        requestSpecification1.headers(hashMap);
        requestSpecification = requestSpecification1;
    }


    /**
     * Method to set authorization without token
     */
    public static void setAuthWithoutToken() throws IOException {
        RequestSpecification requestSpecification1 = rest();
        HashMap<String, String> hashMap = new HashMap();

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));

        hashMap.put("Content-Type", "application/json");
        hashMap.put("Accept", "application/vnd.github.v3+json");

        requestSpecification1.headers(hashMap);
        requestSpecification = requestSpecification1;
    }

    /**
     * Method to set auth with wrong token
     */
    public static void setAuthWithWrongToken() throws IOException {
        RequestSpecification requestSpecification1 = rest();
        HashMap<String, String> hashMap = new HashMap();

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));

        hashMap.put("Authorization", "token " + "12345");
        hashMap.put("Content-Type", "application/json");
        hashMap.put("Accept", "application/vnd.github.v3+json");

        requestSpecification1.headers(hashMap);
        requestSpecification = requestSpecification1;
    }

    /**
     * Method to check the expected and actual value of the path given as param
     */
    @Step
    public static void valueOfPathIs(String path, String expectedValue) {
        Response response = Serenity.sessionVariableCalled(RESPONSE);

        if (expectedValue.equals("-")) {
            //checked that value is not 0
            Assert.assertNotEquals("Expected values is " + response.body().jsonPath().getString(path), 0,
                    response.body().jsonPath().getString(path));
            String testData = "Tax value is " + response.body().jsonPath().getString(path);
            Serenity.recordReportData().withTitle("Tax value").andContents(testData);
        } else {
            Assert.assertEquals("Expected values is: " + expectedValue, expectedValue,
                    response.body().jsonPath().getString(path));
        }
    }

    /**
     * Method to assert that received status code is one of the provided in the list
     */
    @Step
    public static void verifyOneStatusCode(List<Integer> codes) {
        Response response = Serenity.sessionVariableCalled(RESPONSE);

        if (!codes.contains(response.getStatusCode())) {
            Assert.fail("Status code doesn't match with: " + codes.toString());
        }
    }

    /**
     * Check if the key(path) is present in the response body
     */
    @Step
    public static void receivedValueInThePathInTheResponse(String path, String name) {
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        Assert.assertNotNull(path + " not found in body response", response.getBody().jsonPath().getString(path));
        Serenity.setSessionVariable(name).to(response.getBody().path(path));
    }

    /**
     * Method which is used to create a request with the empty body
     */
    @Step
    public void createEmptyBodyRequest(String path) throws IOException {
        CommonSteps.sendRequestWithJsonBody("POST",
                path, new CommonRequestBody().getEmptyBody());
    }

    /**
     * Method which is used to check if path is present in the body
     */
    @Step
    public static void pathExistsInTheResponse(String path) throws IOException {
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        Assert.assertNotNull(path + " not found in body response", response.getBody().jsonPath().getString(path));
    }

    /**
     * Method which is used to unset token
     */
    @Step
    public static void unsetAuthToken() throws IOException {
        token = null;
        requestSpecification = rest();
    }

    /**
     * Check if id present in array
     */
    @Step
    public static void valuePresentInResponse(String id, String path, String searchableKey) {
        boolean isIdFound = false;
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        List<HashMap> arrayInPath = response.getBody().jsonPath().getList(path);

        String searchableValue = id;

        if (Serenity.sessionVariableCalled(id) != null) {
            searchableValue = Serenity.sessionVariableCalled(id);
        }

        for (HashMap hashMap : arrayInPath) {
            if ((hashMap.get(searchableKey)).equals(searchableValue)) {
                isIdFound = true;
                break;
            }
        }

        Assert.assertTrue(isIdFound);
    }

    /**
     * Check if value in response equal to session variable integer
     */
    @Step
    public static void integerValueInResponseEqualToSessionVariable(String path, String sessionVar) {
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        String expected = Integer.toString(Serenity.sessionVariableCalled(sessionVar));

        Assert.assertEquals("Not expected var value", response.getBody().jsonPath().getString(path), expected);
    }

    /**
     * Check if value in response equal to session variable
     */
    @Step
    public static void valueInResponseEqualToSessionVariable(String path, String sessionVar) {
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        String expected = Serenity.sessionVariableCalled(sessionVar);

        Assert.assertEquals("Not expected var value", response.getBody().jsonPath().getString(path), expected);
    }

    /**
     * Save value from the response to the session variable
     */
    @Step
    public static void saveResponseKeyToSession(String key, String sessionVar) {
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        Assert.assertNotNull(key + " not found in body response", response.getBody().jsonPath().getString(key));
        Serenity.setSessionVariable(sessionVar).to(response.getBody().jsonPath().getString(key));
    }
}
