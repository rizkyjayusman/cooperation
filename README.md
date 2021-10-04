# Introduction

This repository contains about a deposit and loan management application in a village that nowhere. hehe.

# Tech Stack

I used these tech stack for create this project with:
* Java 8
* [Spring Boot](https://spring.io/projects/spring-boot)
* PostgreDB
* MongoDB
* [Kafka](https://kafka.apache.org/)
* Swagger 2

# Architecture

<div align='center'>

![Registration Flow - Synchronous Approach](docs/architecture.png)

</div>

# Api Documentation

This project already have an API Documentation that we create using Swagger 2. You can read that documentation on : http://localhost:8090/swagger-ui/


# Implemented Features

This tables shows which features that has been implemented by this repository.

:white_check_mark: : ready

:heavy_exclamation_mark: : in progress

:x: : not yet supported natively by payment gateway

| Features                        | Status                              |
| ------------------------------- | ----------------------------------- |
| Get All Members                 | :white_check_mark:                  |
| Add New Member                  | :white_check_mark:                  |
| Get All Transaction History     | :white_check_mark:                  |
| Create Transaction History      | :white_check_mark:                  |
| Get All Member's Deposit        | :white_check_mark:                  |
| Create Deposit                  | :white_check_mark:                  |
| Create Withdrawal               | :white_check_mark:                  |
| Get All Member's Loan           | :white_check_mark:                  |
| Create Loan                     | :white_check_mark:                  |
| Create Repayment                | :white_check_mark:                  |
| Unit Test                       | :heavy_exclamation_mark:            |
| Api Documentation with Swagger  | :white_check_mark:                  |


# Build and Run (Without Docker)

```
1. Clone the Project
   $ git clone git@github.com:rizkyjayusman/cooperation.git
   $ cd cooperation

2. Build and Package the Project
   $ mvn -e clean package

3. Run the Project
   $ java -jar /cooperation-project-path/app.jar
```

# Build and Run (With Docker)

```
1. Clone the Project
   $ git clone git@github.com:rizkyjayusman/cooperation.git
   $ cd cooperation

2. Build and Package the Project
   $ docker build -t alami-cooperation-app .
   $ docker-compose up -d --build
```
