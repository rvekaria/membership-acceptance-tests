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

    @Step("Given a new employee with first name <firstName> and last name <lastName>")
    public void newEmployeeWithFirstAndLastName(String firstName, String lastName) {
        scenarioStore.put("firstName", firstName);
        scenarioStore.put("lastName", lastName);
    }

    @Step("Then the new employee is added to the system")
    public void addNewMemberToSystem() {
        String firstName = (String) scenarioStore.get("firstName");
        String lastName = (String) scenarioStore.get("lastName");
        Employee newEmployee = createNewMember(firstName, lastName);
        assertEquals(firstName, newEmployee.getFirstName());
        assertEquals(lastName, newEmployee.getLastName());
    }


}
