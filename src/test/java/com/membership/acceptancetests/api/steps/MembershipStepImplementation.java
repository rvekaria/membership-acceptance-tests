package com.membership.acceptancetests.api.steps;

import com.membership.acceptancetests.api.framework.RestAssuredEndPointValidationImpl;
import com.membership.acceptancetests.api.model.Employee;
import com.membership.acceptancetests.api.model.resource.EmployeeResource;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MembershipStepImplementation extends RestAssuredEndPointValidationImpl {
    DataStore scenarioStore;

    @Step({"Given a registered card with id <cardId>",
            "Given an unregistered card with id <cardId>"})
    public void givenACardId(String cardId) {
        scenarioStore.put("cardId", cardId);
    }

    @Step({"When the user logs in", "And taps again"})
    public void cardIsScanned() {
        String cardId = (String) scenarioStore.get("cardId");
        String pin = (String) scenarioStore.get("pin");
        Response cardScanResponse = login(cardId, pin == null ? "" : pin);
        scenarioStore.put("cardScanResponse", cardScanResponse);
    }

    @Step("Then they receive a <logoutMessage> message")
    public void thenTheyReceiveAMessage(String logoutMessage){
        Response response = (Response) scenarioStore.get("cardScanResponse");
        System.out.println(response);
        EmployeeResource employeeResource = response.as(EmployeeResource.class);
        System.out.println(employeeResource.getResponseMessage());
        assertTrue(response.then().extract()
                .response()
                .getBody()
                .asString()
                .contains(logoutMessage));
    }

    @Step("Then the details are not found")
    public void thenTheEmployeeDetailsAreNotFound() {
        Response response = (Response) scenarioStore.get("cardScanResponse");
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Step("And the employee is asked to register")
    public void theEmployeeisAskedToRegister() {
        Response response = (Response) scenarioStore.get("cardScanResponse");
        System.out.println(response);
        EmployeeResource employeeResource = response.as(EmployeeResource.class);
        System.out.println(employeeResource.getResponseMessage());
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
        Employee employee = response.as(EmployeeResource.class).getEmployee();

        assertEquals(scenarioStore.get("cardId"), employee.getCardId());
        assertEquals(scenarioStore.get("employeeId"), employee.getEmployeeId());
        assertEquals(scenarioStore.get("firstName"), employee.getFirstName());
        assertEquals(scenarioStore.get("lastName"), employee.getLastName());
        assertEquals(scenarioStore.get("email"), employee.getEmail());
        assertEquals(scenarioStore.get("mobileNo"), employee.getMobileNo());
    }

    @Step("And a welcome message is received")
    public void andAWelcomeMessageIsReceived() {
        Response response = (Response) scenarioStore.get("cardScanResponse");
        Employee employee = response.as(EmployeeResource.class).getEmployee();
        String employeeName = employee.getFirstName() + " " + employee.getLastName();
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
        scenarioStore.put("balance", fieldMap.get("balance"));

        Response registeredEmployee = registerEmployee(
                fieldMap.get("cardId"),
                fieldMap.get("employeeId"),
                fieldMap.get("firstName"),
                fieldMap.get("lastName"),
                fieldMap.get("email"),
                fieldMap.get("mobileNo"),
                fieldMap.get("pin"),
                fieldMap.get("balance")
        );

        System.out.println(registeredEmployee);
        scenarioStore.put("registeredEmployeeResponse", registeredEmployee);
    }

    @Step("When they top up by <topUpAmount>")
    public void whenTheyTopUpBy(double topUpAmount) {
        String cardId = (String) scenarioStore.get("cardId");
        Response topUpResponse = topUp(cardId, topUpAmount);
        scenarioStore.put("topUp/buyFoodResponse", topUpResponse);
    }

    @Step("When they buy food for <foodPrice>")
    public void whenTheyBuyFoodFor(double foodPrice) {
        String cardId = (String) scenarioStore.get("cardId");
        Response buyFoodResponse = buyFood(cardId, foodPrice);
        scenarioStore.put("topUp/buyFoodResponse", buyFoodResponse);

    }

    @Step("Then their balance is <finalBalance>")
    public void thenTheirBalanceIs(double finalBalance) {
        Response response = (Response) scenarioStore.get("topUp/buyFoodResponse");
        EmployeeResource employeeResource = response.as(EmployeeResource.class);
        response.then().statusCode(HttpStatus.SC_OK);
        assertEquals(finalBalance, employeeResource.getEmployee().getBalance(), 0);
    }

    @Step("Then they receive an error message asking them to top up")
    public void thenReceiveErrorToTopUp() {
        Response response = (Response) scenarioStore.get("topUp/buyFoodResponse");
        assertTrue(response.then().extract()
                .response()
                .getBody()
                .asString()
                .contains("You have insufficient funds to carry out this purchase. Please top up and try again"));
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

        Response response = (Response) scenarioStore.get("registerEmployeeResponse");
        EmployeeResource employeeResource = response.as(EmployeeResource.class);
        Employee employee = employeeResource.getEmployee();

        response.then().statusCode(HttpStatus.SC_OK);
        assertEquals(cardId, employee.getCardId());
        assertEquals(employeeId, employee.getEmployeeId());
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(email, employee.getEmail());
        assertEquals(mobileNo, employee.getMobileNo());
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
