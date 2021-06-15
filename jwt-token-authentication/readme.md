# Authenticate using spring security with JWT and Restful API.

Demo project using spring security and JWT to provide API for authentication.

## Usage

Change file `application.properties` to meet your MySQL configuration.
```
server.port=8000
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
spring.datasource.username=<username>
spring.datasource.password=<password>

spring.jpa.hibernate.ddl-auto=update

spring.thymeleaf.cache=false
spring.jpa.show-sql=true

spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
```
Run project and test with postman (reference file `example_api.json` in this repo).
