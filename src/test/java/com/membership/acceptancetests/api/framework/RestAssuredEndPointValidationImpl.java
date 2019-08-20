package com.membership.acceptancetests.api.framework;

import com.membership.acceptancetests.api.model.Employee;
import com.membership.acceptancetests.api.model.resource.RegisterNewEmployeeRequest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;

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
    public ResponseBody performGetRequest(String requestUrl) {
        return given()
                .log().all().header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept", "application/json")
                .when()
                .request("GET", requestUrl)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().response().getBody();
    }

    @Override
    public ResponseBody performPutRequest(Object resource, String requestUrlWithParameters) {
        return null;
    }

    @Override
    public ResponseBody performDeleteRequest(String requestUrlWithParameters) {
        return null;
    }

    @Override
    public ResponseBody performPostRequest(Object requestObject, String requestUrl) {
        return given()
                .log().all().header("Accept", "application/json")
                .contentType("application/json")
                .body(requestObject)
                .when()
                .request("POST", requestUrl)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().response().getBody();
    }

    public ResponseBody getMemberDetails(String cardId) {
        String getEmployeeDetailsUrl = "/employee?cardId=" + cardId;
        return performGetRequest(getEmployeeDetailsUrl);
    }

    public Employee registerNewEmployee(String cardNumber, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin) {
        String addNewEmployeeUrl = "/newEmployee";
        RegisterNewEmployeeRequest newEmployeeRequest = new RegisterNewEmployeeRequest(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin);
        return performPostRequest(newEmployeeRequest, addNewEmployeeUrl).as(Employee.class);

    }
}
