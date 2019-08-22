package com.membership.acceptancetests.api.framework;

import com.membership.acceptancetests.api.model.resource.RegisterNewEmployeeRequest;
import io.restassured.RestAssured;
import io.restassured.internal.http.HTTPBuilder;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.*;

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
    public Response performGetRequest(String requestUrl, String cardId, String pin) {
        return given()
                .log().all().header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept", "application/json")
                .auth().basic(cardId, pin)
                .when()
                .request("GET", requestUrl)
                .thenReturn();
    }

    @Override
    public Response performPutRequest(Object resource, String requestUrlWithParameters) {
        return null;
    }

    @Override
    public Response performDeleteRequest(String requestUrlWithParameters) {
        return null;
    }

    @Override
    public Response performPostRequest(Object requestObject, String requestUrl) {
        RegisterNewEmployeeRequest newEmployeeRequest = (RegisterNewEmployeeRequest) requestObject;
        return given()
                .log().all().header("Accept", "application/json")
                .contentType("application/json")
                .body(requestObject)
                .auth().basic(newEmployeeRequest.getCardId(), newEmployeeRequest.getPin())
                .when()
                .request("POST", requestUrl)
                .thenReturn();
    }

    public Response getMemberDetails(String cardId, String pin) {
        String getEmployeeDetailsUrl = "/employee?cardId=" + cardId;
        return performGetRequest(getEmployeeDetailsUrl, cardId, pin);
    }

    public Response registerEmployee(String cardNumber, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin) {
        String addNewEmployeeUrl = "/register";
        RegisterNewEmployeeRequest newEmployeeRequest = new RegisterNewEmployeeRequest(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin);
        return performPostRequest(newEmployeeRequest, addNewEmployeeUrl);

    }
}
