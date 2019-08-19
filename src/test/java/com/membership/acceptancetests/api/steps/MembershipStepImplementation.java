package com.membership.acceptancetests.api.steps;

import com.membership.acceptancetests.api.framework.RestAssuredEndPointValidationImpl;
import com.membership.acceptancetests.api.model.Employee;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import static org.junit.Assert.*;

public class MembershipStepImplementation extends RestAssuredEndPointValidationImpl {
    DataStore scenarioStore;

    @Step("Given the membership system is hosted at address <host> on port <port>")
    public void givenMembershipHostAndPort(String hostName, String port) {
        setHostName(hostName);
        setHostPort(port);
        scenarioStore = DataStoreFactory.getScenarioDataStore();
    }

    @Step("Given an employee id <employeeId>")
    public void givenAnEmployeeId(String employeeId) {
        scenarioStore.put("employeeId", employeeId);
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
        int employeeId = Integer.parseInt((String) scenarioStore.get("employeeId"));
        Employee employee = getMemberDetails(employeeId);
        System.out.println("Retrieved Employee:");
        System.out.println(employee);

    }

    @Step("Given a new employee with card number <cardNumber>, id <employeeId>, first name <firstName>, last name <lastName>, email <email>, mobile number <mobileNo> and pin <pin>")
    public void newEmployeeWithDetails(String cardNumber, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin) {
        scenarioStore.put("cardNumber", cardNumber);
        scenarioStore.put("employeeId", employeeId);
        scenarioStore.put("firstName", firstName);
        scenarioStore.put("lastName", lastName);
        scenarioStore.put("email", email);
        scenarioStore.put("mobileNo", mobileNo);
        scenarioStore.put("pin", pin);
    }

    @Step("Then the new employee is successfully added to the system against their unique card number")
    public void addNewMemberToSystem() {
        String cardNumber = (String) scenarioStore.get("cardNumber");
        int employeeId = Integer.parseInt((String) scenarioStore.get("employeeId"));
        String firstName = (String) scenarioStore.get("firstName");
        String lastName = (String) scenarioStore.get("lastName");
        String email = (String) scenarioStore.get("email");
        String mobileNo = (String) scenarioStore.get("mobileNo");
        String pin = (String) scenarioStore.get("pin");
        Employee newEmployee = createNewEmployee(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin);
        assertEquals(cardNumber, newEmployee.getCardNumber());
        assertEquals(employeeId, newEmployee.getEmployeeId());
        assertEquals(firstName, newEmployee.getFirstName());
        assertEquals(lastName, newEmployee.getLastName());
        assertEquals(email, newEmployee.getEmail());
        assertEquals(mobileNo, newEmployee.getMobileNo());
        assertEquals(pin, newEmployee.getPin());
    }


}
