package com.membership.acceptancetests.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class Member {
    private int employeeId;
    private String firstName;
    private String lastName;
    private double balance;

    @JsonCreator
    public Member(@JsonProperty("employeeId") int employeeId,
                  @JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("balance") double balance) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public int getEmployeeId() {
        return employeeId;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return employeeId == member.employeeId &&
                Double.compare(member.balance, balance) == 0 &&
                Objects.equals(firstName, member.firstName) &&
                Objects.equals(lastName, member.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employeeId, firstName, lastName, balance);
    }

    @Override
    public String toString() {
        return "Member{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
