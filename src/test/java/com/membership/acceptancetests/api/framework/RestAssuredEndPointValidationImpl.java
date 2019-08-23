package com.membership.acceptancetests.api.framework;

import com.membership.acceptancetests.api.model.resource.ChangeBalanceRequest;
import com.membership.acceptancetests.api.model.resource.RegisterNewEmployeeRequest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredEndPointValidationImpl implements EndPointValidation {
    @Override
    public void setHostName(String hostName) {
        RestAssured.baseURI = "http://" + hostName;
    }

    @Override
    public void setHostPort(String hostPort) {
        RestAssured.port = Integer.parseInt(hostPort);
        RestAssured.defaultParser = Parser.JSON;

    }

    @Override
    public Response performGetRequest(String requestUrl) {
        return given()
                .log().all().header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept", "application/json")
                .auth().preemptive().basic("test", "0000")
                .when()
                .request("GET", requestUrl)
                .thenReturn();
    }

    @Override
    public Response performPutRequest(Object requestObject, String requestUrl) {
        return given()
                .log().all().header("Accept", "application/json")
                .contentType("application/json")
                .auth().preemptive().basic("test", "0000")
                .body(requestObject)
                .when()
                .request("PUT", requestUrl)
                .thenReturn();
    }

    @Override
    public Response performDeleteRequest(String requestUrlWithParameters) {
        return null;
    }

    @Override
    public Response performPostRequest(Object requestObject, String requestUrl) {
        return given()
                .log().all().header("Accept", "application/json")
                .contentType("application/json")
                .body(requestObject)
                .when()
                .request("POST", requestUrl)
                .thenReturn();
    }

    public Response getMemberDetails(String cardId) {
        String getEmployeeDetailsUrl = "/employee?cardId=" + cardId;
        return performGetRequest(getEmployeeDetailsUrl);
    }

    public Response registerEmployee(String cardId, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin) {
        return registerEmployee(cardId, employeeId, firstName, lastName, email, mobileNo, pin, "0");
    }

    public Response registerEmployee(String cardId, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin, String balance) {
        String addNewEmployeeUrl = "/register";
        RegisterNewEmployeeRequest newEmployeeRequest = new RegisterNewEmployeeRequest(cardId, employeeId, firstName, lastName, email, mobileNo, pin, Double.parseDouble(balance));
        System.out.println(newEmployeeRequest);
        return performPostRequest(newEmployeeRequest, addNewEmployeeUrl);
    }

    public Response topUp(String cardId, double topUpAmount) {
        String topUpUrl = "/topUpBalance";
        ChangeBalanceRequest changeBalanceRequest = new ChangeBalanceRequest(cardId, topUpAmount);
        return performPutRequest(changeBalanceRequest, topUpUrl);

    }

    public Response buyFood(String cardId, double foodPrice) {
        String buyFoodUrl = "/buy";
        ChangeBalanceRequest changeBalanceRequest = new ChangeBalanceRequest(cardId, foodPrice);
        return performPutRequest(changeBalanceRequest, buyFoodUrl);
    }
}
