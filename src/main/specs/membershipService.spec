# Membership Specification

* Given the membership system is hosted at address "localhost" on port "8080"

## Retrieve member information
* Given an employee id "1"
* Then the correct member details is retrieved

## Add new member
* Given a new employee with first name "John" and last name "Nelson"
* Then the new member is added to the system

## Add cash to member's balance
* Given an employee id "1"
* And the member has a balance of "5"
* When the member tops up by "3"
* Then the balance is "8"