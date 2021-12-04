package com.apicore.requestBodies;

import com.apicore.util.JsonReader;

public class ExampleRequestBody {

    public static final String dir = System.getProperty("user.dir");

    public String createExample(String paymentType, String stateCode, String zipCode, String city) throws Exception {
        String requestBody = JsonReader.readJsonFileAsString(dir + "/src/test/resources/data/paypal.json");

        requestBody = requestBody.replace("$STATE", stateCode);
        requestBody = requestBody.replace("$ZIP", zipCode);
        return requestBody.replace("$CITY", city);
    }
}
