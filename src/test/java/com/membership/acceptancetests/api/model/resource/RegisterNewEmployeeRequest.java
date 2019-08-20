package com.membership.acceptancetests.api.model.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RegisterNewEmployeeRequest {
    private String cardId;
    private String employeeId;
    private String firstName;
    private String lastName;
    private final String email;
    private final String mobileNo;
    private final String pin;

    @JsonCreator
    public RegisterNewEmployeeRequest(@JsonProperty("cardId") String cardId,
                                      @JsonProperty("employeeId") String employeeId,
                                      @JsonProperty("firstName") String firstName,
                                      @JsonProperty("lastName") String lastName,
                                      @JsonProperty("email") String email,
                                      @JsonProperty("mobileNo") String mobileNo,
                                      @JsonProperty("pin") String pin) {
        this.cardId = cardId;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.pin = pin;
    }

    public String getEmployeeId() {
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

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterNewEmployeeRequest that = (RegisterNewEmployeeRequest) o;
        return employeeId == that.employeeId &&
                Objects.equals(cardId, that.cardId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, employeeId, firstName, lastName, email, mobileNo, pin);
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
