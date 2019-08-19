package com.membership.acceptancetests.api.framework;

import com.membership.acceptancetests.api.model.Employee;
import com.membership.acceptancetests.api.model.resource.RegisterNewEmployeeRequest;
import io.restassured.RestAssured;
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

    public Employee getMemberDetails(int employeeId) {
        String getMemberDetailsUrl = "/employee?employeeId=" + employeeId;
        return performGetRequest(getMemberDetailsUrl).as(Employee.class);
    }

    public Employee createNewMember(String firstName, String lastName) {
        String addNewMemberUrl = "/newEmployee";
        RegisterNewEmployeeRequest employeeResource = new RegisterNewEmployeeRequest(firstName, lastName);
        return performPostRequest(employeeResource, addNewMemberUrl).as(Employee.class);

    }
}
