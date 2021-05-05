**The task's description:**

The task is about autonomous parking lot. You should design and implement a backend system with API to handle a parking lot. 
Any client car, when arrives at the parking building, gets automatically scanned and its weight and height are passed to the system as the car approaches the gate. The parking has N floors, where each floor may have a different ceiling height and a different total weight capacity. The system must assign the approaching car to the best suitable spot and calculate the resulting price per minute. The payment, car transportation, and the rest is handled by other components, and you can simply emulate them to build a business flow. 
There are only few initial business requirements in that system, but it should be easily extensible. Try to keep your code as readable as possible. We value code simplicity. Use object-oriented approach with common design patterns where applicable.

**A used stack of technologies:** Java 11, Spring, PostgreSQL, liquibase, swagger, junit, aop logger.

**Instruction for running application:**
1. Install PostgreSQL
2. Create a database: 
_**CREATE DATABASE parking;**_
3. Create database user:
_**CREATE USER admin WITH password '123456';**_
4. Grant privileges to user:
_**GRANT ALL privileges ON DATABASE parking TO admin;**_
5. Clone project from git
6. Run application local

After running the application, it will be available on port 8080. 
A swagger will be available by following links:
- http://localhost:8080/swagger-ui.html#/
- http://localhost:8080/v2/api-docs

**The project contains 2 connected tables:**
1. parking_lot(here will be stored all lots in parking) - while running application firstly all necessary data will be added automatically to this table
2. parking_order(here will be added order to park a car)

**Currently, there are 2 APIs:**
1. Park a car by sending car characteristics: number, weight, height
- Request example:
{
  "carNumber": "23d56e",
  "weight": 9.5,
  "height": 4.5
}
- Successful response example:
{
    "status": "SUCCESS",
    "message": "Request successfully processed.",
    "responseData": {
        "floorNumber": 2,
        "parkingLocation": "B4",
        "pricePerMinute": 3.21,
        "parkingStartDate": "2021-05-04T16:43:43.465762"
    }
}
- Response examples with error:
{
    "status": "ERROR_CAR_PARKED",
    "message": "Car was already parked.",
    "responseData": null
}

{
    "status": "ERROR_CAR_PARKED",
    "message": "Car was already parked.",
    "responseData": null
}
2. Unpark a car by sending car number
- Request example:
{
  "carNumber": "23d56e"
}
- Successful response example:
{
    "status": "SUCCESS",
    "message": "Request successfully processed.",
    "responseData": {
        "totalPrise": 9.63
    }
}
- Response example with error:
{
    "status": "ERROR_CAR_NOT_PARKED",
    "message": "No active parking for this car.",
    "responseData": null
}

**Business logic of the application:**
1. By getting request to park a car:
- checking is car already parked or not
- searching for suitable parking lot
- if previous checks end with success: book a parking lot by changing its availability database, save order details and send response with order details
- else send error response  
2. In the process of unparking the car:
- check is active parking exist
- if active parking is founded, the program will set the end date of order and make parking lot available for booking
- else send error response    

**How application might be improved:**
- Localization of errors might be added
- New features like: creating a bill in unparking process and APIs to paying these bills.
- Some validation might be added like checking the length, regex of car's number, also validation of possible weight and height values
- Application might me dockerized 
- More unit tests might be written