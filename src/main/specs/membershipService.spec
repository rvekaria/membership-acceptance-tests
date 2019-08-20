# Membership Specification

* Given the membership system is hosted at address "localhost" on port "8080"

## Scan unregistered card
* Given an unregistered card with id "12345678abcdefgh"
* When the card is scanned
* Then the details are not found
* And the employee is asked to register

## Register new employee
* Given an unregistered employee with the following details:
      | field       |      fieldValue        |
      |cardId       |   12345678abcdefgh     |
      |employeeId   |   4                    |
      |firstName    |	New                  |
      |lastName     |	Person               |
      |email        | new.person@bf1HPC.com  |
      |mobileNo     |	07123456789          |
      |pin          |	1234                 |
* When the employee registers
* Then the employee's details is successfully added to the system

## Retrieve employee information given a registered card
* Given a registered employee with the following details:
      | field       |      fieldValue        |
      |cardId       |   6bb6b4c2c28b11e9     |
      |employeeId   |   5                    |
      |firstName    |	John                 |
      |lastName     |	Nelson               |
      |email        | john.nelson@bf1HPC.com |
      |mobileNo     |	07812693012          |
      |pin          |	8471                 |
* When the card is scanned
* Then the correct employee details is retrieved
* And a welcome message is received