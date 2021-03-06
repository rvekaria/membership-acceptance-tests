package com.membership.acceptancetests.api.model.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.membership.acceptancetests.api.model.Employee;

import java.util.Objects;

public class EmployeeResource {

    private Employee employee;
    private String responseMessage;

    @JsonCreator
    public EmployeeResource(@JsonProperty("employee") Employee employee, @JsonProperty("responseMessage") String responseMessage) {
        this.employee = employee;
        this.responseMessage = responseMessage;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeResource that = (EmployeeResource) o;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(responseMessage, that.responseMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employee, responseMessage);
    }

    @Override
    public String toString() {
        return "EmployeeResource{" +
                "employee=" + employee +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
