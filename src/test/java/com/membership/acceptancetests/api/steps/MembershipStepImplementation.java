package com.membership.acceptancetests.api.steps;

import com.membership.acceptancetests.api.framework.RestAssuredEndPointValidationImpl;
import com.membership.acceptancetests.api.model.Employee;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MembershipStepImplementation extends RestAssuredEndPointValidationImpl {
    DataStore scenarioStore;

    @Step("Given the membership system is hosted at address <host> on port <port>")
    public void givenMembershipHostAndPort(String hostName, String port) {
        setHostName(hostName);
        setHostPort(port);
        scenarioStore = DataStoreFactory.getScenarioDataStore();
    }

    @Step("Given a card id <cardId>")
    public void givenAnEmployeeId(String cardId) {
        scenarioStore.put("cardId", cardId);
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

    @Step("Then the correct employee details is retrieved")
    public void employeeDetailsIsRetrieved() {
        String cardId = (String) scenarioStore.get("cardId");
        Employee employee = getMemberDetails(cardId);
        System.out.println("Retrieved Employee:");
        System.out.println(employee);

    }

    @Step("Given a new employee with the following details: <employeeDetailsTable>")
    public void employeeWithDetails(Table employeeDetailsTable){
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

    @Step("Then the new employee is successfully added to the system against their unique card number")
    public void addNewMemberToSystem() {
        String cardId = (String) scenarioStore.get("cardId");
        String employeeId = (String) scenarioStore.get("employeeId");
        String firstName = (String) scenarioStore.get("firstName");
        String lastName = (String) scenarioStore.get("lastName");
        String email = (String) scenarioStore.get("email");
        String mobileNo = (String) scenarioStore.get("mobileNo");
        String pin = (String) scenarioStore.get("pin");
        Employee newEmployee = createNewEmployee(cardId, employeeId, firstName, lastName, email, mobileNo, pin);
        assertEquals(cardId, newEmployee.getCardId());
        assertEquals(employeeId, newEmployee.getEmployeeId());
        assertEquals(firstName, newEmployee.getFirstName());
        assertEquals(lastName, newEmployee.getLastName());
        assertEquals(email, newEmployee.getEmail());
        assertEquals(mobileNo, newEmployee.getMobileNo());
        assertEquals(pin, newEmployee.getPin());
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
