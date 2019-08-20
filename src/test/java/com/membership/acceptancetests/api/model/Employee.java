package com.membership.acceptancetests.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class Employee {
    private String cardId;
    private String employeeId;
    private String firstName;
    private String lastName;
    private final String email;
    private final String mobileNo;
    private final String pin;
    private double balance;

    @JsonCreator
    public Employee(@JsonProperty("cardId") String cardId,
                    @JsonProperty("employeeId") String employeeId,
                    @JsonProperty("firstName") String firstName,
                    @JsonProperty("lastName") String lastName,
                    @JsonProperty("email") String email,
                    @JsonProperty("mobileNo") String mobileNo,
                    @JsonProperty("pin") String pin,
                    @JsonProperty("balance") double balance) {
        this.cardId = cardId;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.pin = pin;
        this.balance = balance;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "cardId='" + cardId + '\'' +
                ", employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                Double.compare(employee.balance, balance) == 0 &&
                Objects.equals(cardId, employee.cardId) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(mobileNo, employee.mobileNo) &&
                Objects.equals(pin, employee.pin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, employeeId, firstName, lastName, email, mobileNo, pin, balance);
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
