# ParkingLot
Parking Lot Design Implementation along with Testing

## Problem Statement
I own a parking lot that can hold up to &#39;n&#39; cars at any given point in time. Each slot is
given a number starting at 1 increasing with increasing distance from the entry point
in steps of one. I want to create an automated ticketing system that allows my
customers to use my parking lot without human intervention. <br/> <br/>
When a car enters my parking lot, I want to have a ticket issued to the driver. The
ticket issuing process includes us documenting the registration number (number
plate) and the colour of the car and allocating an available parking slot to the car
before actually handing over a ticket to the driver (we assume that our customers are
nice enough to always park in the slots allocated to them). The customer should be
allocated a parking slot which is nearest to the entry. At the exit the customer returns
the ticket which then marks the slot they were using as being available. <br/> <br/>
Due to government regulation, the system should provide me with the ability to find
out:
* Registration numbers of all cars of a particular colour.
* Slot number in which a car with a given registration number is parked.
* Slot numbers of all slots where a car of a particular colour is parked.

## Built With
* [Java](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) - Used to implement the whole design
* [Guice](https://github.com/google/guice/wiki/GettingStarted) - Used to implment Constructor Injections
* [Guava](https://github.com/google/guava) - Used to implement Bimaps, Preconditions and Orderings
* [JUnit](https://junit.org/junit5/docs/current/user-guide/) - Used to Test the final implementation

## Project Requirements

* Java JDK 8
* Gradle 6.0
* Guava 23.5-jre
* Guice 4.1.0
* Junit5

## Commands Used
Creating a Parking Lot of Size n
 ```
    create_parking_lot n 
 ``` 
Parking a car with Registration Number as regNum and Colour as colour
 ```
    park regNum colour
 ``` 
Car at Slot Number i leaving the Parking lot
 ```
    leave i
 ``` 
Getting status of all the cars parked in the Parking Lot
 ```
    status
 ``` 
Query for getting Registration Numbers of all cars in Parking lot with Colour as colour
```
    registration_numbers_for_cars_with_colour colour
``` 
Query for getting Slot Numbers of all cars in Parking lot with Colour as colour
``` 
    slot_numbers_for_cars_with_colour colour
```
Query for getting Parking Slot of a Car with Given Registration Number regNum
```
    slot_number_for_registration_number regNum
```    

## Running the Program

* File Input Mode : Change the contents of the file input.txt to whatever is required.
```
    javac Main.java
    java Main input.txt
```
* Interative Mode : Run the Main.java file via terminal.
```
    javac Main.java
    java Main
```
