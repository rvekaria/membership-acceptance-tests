# Membership Specification

* Given the membership system is hosted at address "localhost" on port "8080"

## Register new employee
* Given a new employee with card number "6bb6b4c2c28b11e99cb52a2ae2dbcce4", id "4", first name "John", last name "Nelson", email "john.nelson@company.com", mobile number "1234567890" and pin "1234"
* Then the new employee is successfully added to the system against their unique card number

## Retrieve employee information
* Given an employee id "1"
* Then the correct employee details is retrieved

## Add cash to employee's balance
* Given an employee id "1"
* And the employee has a balance of "5"
* When the employee tops up by "3"
* Then the balance is "8"