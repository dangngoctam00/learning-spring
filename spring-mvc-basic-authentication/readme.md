# Spring Security using UserDetailService

This repo is about using spring security to implement basic register and login function. 

## Usage
To use this repo, change `database_name`, `username`, `password` (your mysql server configuration) in file `application.properties file`.
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

Run project, the url is: http://localhost:8000
