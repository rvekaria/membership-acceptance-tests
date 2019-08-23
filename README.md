## Membership Acceptance Tests

This project contains end to end test for the API endpoints of the membership system project.
You can clone the membership system project using:  
`git clone https://github.com/rvekaria/membership-system.git`

## Running the tests
The following needs to be installed in order to tun the gauge tests:  
* [Install Gauge](http://getgauge.io/get-started/index.html)
* Install Gauge java plugin `gauge install java`

The membership system needs to be running against which the tests will run. In the membership system project, run:
* `mvn clean install`
* `java -jar target/membership-system-0.0.1-SNAPSHOT.jar` 

Ensure the mongo database is running: `mongod`

Run the acceptance tests using `mvn clean install`
