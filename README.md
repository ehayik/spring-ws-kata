# Spring WS Kata

This project demonstrates the implementation of SOAP web services and clients using Spring WS. 
It includes modules for both the web service and the client. The project also covers mocking SOAP requests using
WireMock.

Project Modules:

- spring-ws-server: This module is responsible for implementing the SOAP web service.
- spring-ws-client: This module is responsible for implementing the SOAP client.

## Technologies Used

- **Spring WS**: Used for creating and consuming SOAP web services.
- **WireMock**: Used for mocking SOAP requests and responses.
- **Java SDK 21**
- **Lombok**

## Installation

### Prerequisites

- Java JDK 21
- Maven

### Steps

1. Clone the repository:
    ```sh
    git clone https://github.com/ehayik/spring-ws-kata.git
    ```
2. Navigate to the project directory:
    ```sh
    cd spring-ws-kata
    ```
3. Build the project:
    ```sh
    mvn clean verify
    ```

## Usage

### Running the Web Service

1. Navigate to the web service module directory:
    ```sh
    cd spring-ws-server
    ```
2. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```

### Running the Client

1. Navigate to the client module directory:
    ```sh
    cd spring-ws-client
    ```
2. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```

## Useful Resources

- [Official Spring WS documentation](https://spring.io/projects/spring-ws)
- [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service)
- [Consuming a SOAP web service](https://spring.io/guides/gs/consuming-web-service)
- [Creating a SOAP Web Service with Spring](https://www.baeldung.com/spring-boot-soap-web-service#contractfirst)
- [Invoking a SOAP Web Service in Spring](https://www.baeldung.com/spring-soap-web-service)
- [Spring Web Service Integration Tests with @WebServiceServerTest](https://www.baeldung.com/spring-webserviceservertest)
- [WireMock Documentation](http://wiremock.org/docs/)