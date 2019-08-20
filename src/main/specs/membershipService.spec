# Membership Specification

* Given the membership system is hosted at address "localhost" on port "8080"

## Register new employee
* Given a new employee with the following details:
      | field       |      fieldValue        |
      |cardId       |   6bb6b4c2c28b11e9     |
      |employeeId   |   4                    |
      |firstName    |	John                 |
      |lastName     |	Nelson               |
      |email        | john.nelson@bf1HPC.com |
      |mobileNo     |	07123456789          |
      |pin          |	1234                 |

* Then the new employee is successfully added to the system against their unique card number

## Retrieve employee information given card Id
* Given a card id "6bb6b4c2c28b11e9"
* Then the correct employee details is retrieved

## Add cash to employee's balance
* Given an employee id "1"
* And the employee has a balance of "5"
* When the employee tops up by "3"
* Then the balance is "8"