package com.membership.acceptancetests.api.steps;

import com.membership.acceptancetests.api.framework.RestAssuredEndPointValidationImpl;
import com.membership.acceptancetests.api.model.Employee;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import io.restassured.response.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MembershipStepImplementation extends RestAssuredEndPointValidationImpl {
    DataStore scenarioStore;

    @Step({"Given a registered card with id <cardId>", "Given an unregistered card with id <cardId>"})
    public void givenAnEmployeeId(String cardId) {
        scenarioStore.put("cardId", cardId);
    }

    @Step({"When the card is scanned"})
    public void cardIsScanned() {
        String cardId = (String) scenarioStore.get("cardId");
        System.out.println("-----------cardId-------------");
        System.out.println(cardId);
        ResponseBody employee = getMemberDetails(cardId);
        scenarioStore.put("employee", employee);
    }

    @Step("Then the employee is asked to register")
    public void thenTheEmployeeIsAskedToRegister() {
        ResponseBody responseBody = (ResponseBody) scenarioStore.get("employee");
        System.out.println("-------------responseBody-------------");
        System.out.println(responseBody);
        System.out.println(responseBody.asString());
        System.out.println(responseBody.asString().contains("register"));
        assertTrue(responseBody.asString().contains("register"));
    }

    @Step("Given an employee id <1>")
    public void givenAnEmployeeId(Object arg0) {
        throw new UnsupportedOperationException("Provide custom implementation");
    }


    @Step("Given the membership system is hosted at address <host> on port <port>")
    public void givenMembershipHostAndPort(String hostName, String port) {
        setHostName(hostName);
        setHostPort(port);
        scenarioStore = DataStoreFactory.getScenarioDataStore();
    }

    @Step("And the employee has a balance of <currentBalance>")
    public void andTheMemberHasABalanceOf(double currentBalance) {

    }

    @Step("When the employee tops up by <topUpAmount>")
    public void whenTheMemberTopsUpBy(double topUpAmount) {

    }

    @Step("Then the balance is <totalBalance>")
    public void thenTheBalanceIs(double totalBalance) {

    }

    @Step({"Then the correct employee details is retrieved"})
    public void employeeDetailsIsRetrieved() {
        ResponseBody responseBody = (ResponseBody) scenarioStore.get("employee");
        Employee employee = responseBody.as(Employee.class);

    }

    @Step({"Given an unregistered employee with the following details: <employeeDetailsTable>", "Given a registered employee with the following details: <employeeDetailsTable>"})
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
        Employee newEmployee = registerNewEmployee(cardId, employeeId, firstName, lastName, email, mobileNo, pin);
        scenarioStore.put("employee", newEmployee);
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
        Employee employee = (Employee) scenarioStore.get("employee");
        assertEquals(cardId, employee.getCardId());
        assertEquals(employeeId, employee.getEmployeeId());
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(email, employee.getEmail());
        assertEquals(mobileNo, employee.getMobileNo());
        assertEquals(pin, employee.getPin());
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
