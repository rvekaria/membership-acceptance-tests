# Membership Specification

* Given the membership system is hosted at address "localhost" on port "8080"

## Retrieve employee information
* Given an employee id "1"
* Then the correct employee details is retrieved

## Add new employee
* Given a new employee with first name "John" and last name "Nelson"
* Then the new employee is added to the system

## Add cash to employee's balance
* Given an employee id "1"
* And the employee has a balance of "5"
* When the employee tops up by "3"
* Then the balance is "8"