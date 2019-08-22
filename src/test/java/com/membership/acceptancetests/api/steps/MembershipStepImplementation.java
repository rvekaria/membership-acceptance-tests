package com.membership.acceptancetests.api.steps;

import com.membership.acceptancetests.api.framework.RestAssuredEndPointValidationImpl;
import com.membership.acceptancetests.api.model.Employee;
import com.membership.acceptancetests.api.model.resource.EmployeeResource;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class MembershipStepImplementation extends RestAssuredEndPointValidationImpl {
    DataStore scenarioStore;

    @Step({"Given a registered card with id <cardId>", "Given an unregistered card with id <cardId>"})
    public void givenACardId(String cardId) {
        scenarioStore.put("cardId", cardId);
        scenarioStore.put("pin", "");
    }

    @Step("When the card is scanned")
    public void cardIsScanned() {
        String cardId = (String) scenarioStore.get("cardId");
        String pin = (String) scenarioStore.get("pin");
        Response cardScanResponse = getMemberDetails(cardId, pin);
        scenarioStore.put("cardScanResponse", cardScanResponse);
    }

    @Step("Then the details are not found")
    public void thenTheEmployeeDetailsAreNotFound() {
        Response response = (Response) scenarioStore.get("cardScanResponse");
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Step("And the employee is asked to register")
    public void theEmployeeisAskedToRegister() {
        Response response = (Response) scenarioStore.get("cardScanResponse");
        assertTrue(response.then().extract()
                .response()
                .getBody()
                .asString()
                .contains("This card is not registered. Please register first to use the service"));
    }


    @Step("Given the membership system is hosted at address <host> on port <port>")
    public void givenMembershipHostAndPort(String hostName, String port) {
        setHostName(hostName);
        setHostPort(port);
        scenarioStore = DataStoreFactory.getScenarioDataStore();
    }

    @Step("Then the correct employee details is retrieved")
    public void employeeDetailsIsRetrieved() {
        Response response = (Response) scenarioStore.get("cardScanResponse");
        EmployeeResource employeeResource = response.as(EmployeeResource.class);

        assertEquals(scenarioStore.get("cardId"), employeeResource.getCardId());
        assertEquals(scenarioStore.get("employeeId"), employeeResource.getEmployeeId());
        assertEquals(scenarioStore.get("firstName"), employeeResource.getFirstName());
        assertEquals(scenarioStore.get("lastName"), employeeResource.getLastName());
        assertEquals(scenarioStore.get("email"), employeeResource.getEmail());
        assertEquals(scenarioStore.get("mobileNo"), employeeResource.getMobileNo());
    }

    @Step("And a welcome message is received")
    public void andAWelcomeMessageIsReceived() {
        Response response = (Response) scenarioStore.get("cardScanResponse");
        EmployeeResource employeeResource = response.as(EmployeeResource.class);
        String employeeName = employeeResource.getFirstName() + " " + employeeResource.getLastName();
        assertTrue(response.then().extract()
                .response()
                .getBody()
                .asString()
                .contains("Welcome, " + employeeName));
    }

    @Step({"Given a registered employee with the following details: <employeeDetailsTable>"})
    public void registerEmployeeWithDetails(Table employeeDetailsTable) {
        List<String> employeeAttributes = employeeDetailsTable.getColumnValues("field");
        List<String> employeeAttributeValues = employeeDetailsTable.getColumnValues("fieldValue");
        Map<String, String> fieldMap = getFieldMap(employeeAttributes, employeeAttributeValues);

        scenarioStore.put("cardId", fieldMap.get("cardId"));
        scenarioStore.put("employeeId", fieldMap.get("employeeId"));
        scenarioStore.put("firstName", fieldMap.get("firstName"));
        scenarioStore.put("lastName", fieldMap.get("lastName"));
        scenarioStore.put("email", fieldMap.get("email"));
        scenarioStore.put("mobileNo", fieldMap.get("mobileNo"));
        scenarioStore.put("pin", fieldMap.get("pin"));

        Response registeredEmployee = registerEmployee(
                fieldMap.get("cardId"),
                fieldMap.get("employeeId"),
                fieldMap.get("firstName"),
                fieldMap.get("lastName"),
                fieldMap.get("email"),
                fieldMap.get("mobileNo"),
                fieldMap.get("pin"));

        scenarioStore.put("registeredEmployeeResponse", registeredEmployee);
    }

    @Step("Given an unregistered employee with the following details: <employeeDetailsTable>")
    public void employeeWithDetails(Table employeeDetailsTable) {
        List<String> employeeAttributes = employeeDetailsTable.getColumnValues("field");
        List<String> employeeAttributeValues = employeeDetailsTable.getColumnValues("fieldValue");
        Map<String, String> fieldMap = getFieldMap(employeeAttributes, employeeAttributeValues);
        scenarioStore.put("cardId", fieldMap.get("cardId"));
        scenarioStore.put("employeeId", fieldMap.get("employeeId"));
        scenarioStore.put("firstName", fieldMap.get("firstName"));
        scenarioStore.put("lastName", fieldMap.get("lastName"));
        scenarioStore.put("email", fieldMap.get("email"));
        scenarioStore.put("mobileNo", fieldMap.get("mobileNo"));
        scenarioStore.put("pin", fieldMap.get("pin"));
    }

    @Step("When the employee registers")
    public void theEmployeeRegisters() {
        String cardId = (String) scenarioStore.get("cardId");
        String employeeId = (String) scenarioStore.get("employeeId");
        String firstName = (String) scenarioStore.get("firstName");
        String lastName = (String) scenarioStore.get("lastName");
        String email = (String) scenarioStore.get("email");
        String mobileNo = (String) scenarioStore.get("mobileNo");
        String pin = (String) scenarioStore.get("pin");

        Response registerEmployeeResponse = registerEmployee(cardId, employeeId, firstName, lastName, email, mobileNo, pin);

        scenarioStore.put("registerEmployeeResponse", registerEmployeeResponse);
    }

    @Step("Then the employee's details is successfully added to the system")
    public void addNewMemberToSystem() {
        String cardId = (String) scenarioStore.get("cardId");
        String employeeId = (String) scenarioStore.get("employeeId");
        String firstName = (String) scenarioStore.get("firstName");
        String lastName = (String) scenarioStore.get("lastName");
        String email = (String) scenarioStore.get("email");
        String mobileNo = (String) scenarioStore.get("mobileNo");
        String pin = (String) scenarioStore.get("pin");
        String encodedPin1234 = "$2a$10$iO50fAsnyW6ACoiqalmrW.ctevgv5tGMBpnezUcxkJVvQ7Q4aE7hi";

        Response response = (Response) scenarioStore.get("registerEmployeeResponse");
        Employee employeeResponse = response.as(Employee.class);

        response.then().statusCode(HttpStatus.SC_OK);
        assertEquals(cardId, employeeResponse.getCardId());
        assertEquals(employeeId, employeeResponse.getEmployeeId());
        assertEquals(firstName, employeeResponse.getFirstName());
        assertEquals(lastName, employeeResponse.getLastName());
        assertEquals(email, employeeResponse.getEmail());
        assertEquals(mobileNo, employeeResponse.getMobileNo());
        assertEquals(encodedPin1234, employeeResponse.getPin());
    }

    private Map<String, String> getFieldMap(List<String> fieldList, List<String> fieldValuesList) {
        Map<String, String> fieldMap = new HashMap<String, String>();

        Iterator<String> fieldListIterator = fieldList.iterator();
        Iterator<String> fieldValuesListIterator = fieldValuesList.iterator();
        while (fieldListIterator.hasNext() && fieldValuesListIterator.hasNext()) {
            fieldMap.put(fieldListIterator.next(), fieldValuesListIterator.next());
        }
        return fieldMap;
    }


}
