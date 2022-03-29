# Retrovure Backend

## Project Description

A Java based web backend for a retail application selling retro video games. It queries the database for the characters, series, consoles, and games for all of them or specific ones based upon a number of criteria. The API uses JSON for all inputs as well as outputs. It is currently connected to the fron end application at [Retrovure Frontend](https://github.com/SequelTeamSix/Team-Nathaniel-P2-Frontend).

## Technologies Used

- Spring-Boot
- Spring-Data
- Java 8
- Lombok
- Mockito

## Features

- Lombok for constructors, getters, and setters
- Mapping of controllers to request inputs
- Repository queries using JpaRepository
- Testing using Spring and Mockito

## Getting Started

- Install Java version 8 or higher
- Download and install Maven
- Install git
- Run `git clone https://github.com/SequelTeamSix/Team-Nathaniel-P2-Backend` to clone repository to local machine
- Create resources folder under src/main
- Create application.properties file in the directory
- Add the following entries to the properties file along with the information for your database setup:
  -   server.port
  -   spring.datasource.url
  -   spring.datasource.username
  -   spring.datasource.password
  -   spring.datasource.driver
  -   spring.jpa.show-sql
  -   spring.jpa.hibernate.ddl-auto
- If using a database other than SQL Server, add the driver entry to the pom.xml file

## Usage

This project can be used with the corresponding frontend project or can be used as a backend for any frontend technology desired as long as the correct API usage is followed.
