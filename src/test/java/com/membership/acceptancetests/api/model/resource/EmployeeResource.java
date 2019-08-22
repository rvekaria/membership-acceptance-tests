package com.membership.acceptancetests.api.model.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class EmployeeResource {

    private String cardId;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private double balance;
    private String responseMessage;

    @JsonCreator
    public EmployeeResource(@JsonProperty("cardId") String cardId,
                            @JsonProperty("employeeId") String employeeId,
                            @JsonProperty("firstName") String firstName,
                            @JsonProperty("lastName") String lastName,
                            @JsonProperty("email") String email,
                            @JsonProperty("mobileNo") String mobileNo,
                            @JsonProperty("balance") double balance,
                            @JsonProperty("responseMessage") String responseMessage) {
        this.cardId = cardId;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.balance = balance;
        this.responseMessage = responseMessage;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(cardId, that.cardId) &&
                Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(responseMessage, that.responseMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, employeeId, firstName, lastName, email, mobileNo, balance, responseMessage);
    }

    @Override
    public String toString() {
        return "EmployeeResource{" +
                "cardId='" + cardId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", balance=" + balance +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
