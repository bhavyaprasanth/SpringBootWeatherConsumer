
# SpringBootWeatherConsumer

The SpringBootWeatherConsumer project does the following

1.Connects with weather microservice using Spring Boot Framework and REST API. 
2.Connects with MySql database and register Users

Unit tests are also implemented using junit, mockito frameworks

## Project Highlights

* APIs to get weather details by cityname and coordinates
* MySQL Database Integration using JPA
* Unit Tests
* Global Exception Handling
* API versioning
* Logging
* Spring Security
* Web API documentation using Swagger


## Getting Started
Clone [SpringBootWeatherConsumer](https://github.com/bhavyaprasanth/SpringBootWeatherConsumer) and open  with Eclipse or any IDE.

## Prerequisites

* MySql to test the user registration - The credentials specified in application.properties.
* Basic Spring security implemented in the project 
        username - user
        password - password

## Author

* Bhavya Mohan - [bhavyaprasanth](https://github.com/bhavyaprasanth)

## Steps to Run SpringBootWeatherConsumer

* Go to the directory or project folder which contains the pom.xml file.
* Run the command - mvn clean install
* mvn spring-boot:run
* Once the application is up and running , you can hit localhost:8080 in browser and give the username and password specified above,
* Once login is successful, give http://localhost:8080/swagger-ui/ (Or you can directly give swagger URl , it will prompt for credentials and then will reload the swagger UI)
* You can see the api endpoints in swagger ui and can test it
